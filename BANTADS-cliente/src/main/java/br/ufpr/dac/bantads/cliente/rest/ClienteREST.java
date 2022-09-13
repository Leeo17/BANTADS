package br.ufpr.dac.bantads.cliente.rest;

import br.ufpr.dac.bantads.cliente.model.Cliente;
import br.ufpr.dac.bantads.cliente.repository.ClienteRepository;
import br.ufpr.dac.bantads.cliente.services.RabbitMQService;
import br.ufpr.dac.bantads.common.dto.ClienteDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin
@RestController
public class ClienteREST {
    @Autowired
    private ClienteRepository repo;
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private RabbitMQService rabbitmqService;

    private final static String FILA_CLIENTE_CRIADO = "CLIENTE_CRIADO";

    @PostMapping("/clientes")
    public ResponseEntity<ClienteDTO> inserir(@RequestBody ClienteDTO clienteDTO) {
        clienteDTO.setId(UUID.randomUUID());
        clienteDTO.getEndereco().setId(UUID.randomUUID());

    	repo.saveAndFlush(mapper.map(clienteDTO, Cliente.class));

        this.rabbitmqService.enviaMensagem(FILA_CLIENTE_CRIADO, clienteDTO);

        return ResponseEntity.ok(clienteDTO);
    }

    @GetMapping("/clientes")
    public ResponseEntity<ClienteDTO> buscarClientePeloEmail(@RequestParam String email) {
        Cliente cliente = repo.findByEmail(email);
        return ResponseEntity.ok(mapper.map(cliente, ClienteDTO.class));
    }
}
