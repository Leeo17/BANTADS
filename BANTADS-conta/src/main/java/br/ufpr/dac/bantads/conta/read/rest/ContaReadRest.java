package br.ufpr.dac.bantads.conta.read.rest;

import br.ufpr.dac.bantads.common.dto.ContaReadDTO;
import br.ufpr.dac.bantads.conta.read.model.ContaRead;
import br.ufpr.dac.bantads.conta.read.repository.ContaReadRepository;
import br.ufpr.dac.bantads.conta.services.RabbitMQService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
public class ContaReadRest {
    @Autowired
    private ContaReadRepository repo;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private RabbitMQService rabbitMQService;

    @GetMapping("/contas/gerente/{id}")
    public ResponseEntity<List<ContaReadDTO>> getContasByIdGerente(@PathVariable UUID id, @RequestParam String aprovada,
                                                                  @RequestParam(required = false) String cpf, @RequestParam(required = false) String nome) {
        Boolean isAprovada = aprovada.equals("null") ? null : Boolean.valueOf(aprovada);
        boolean buscarPorCpf = cpf != null && !cpf.equals("");
        boolean buscarPorNome = nome != null && !nome.equals("");

        List<ContaRead> contas;

        if (buscarPorCpf && buscarPorNome) {
            contas = repo
                    .findByIdGerenteAndAprovadaAndCpfClienteContainingAndNomeClienteContainingIgnoreCaseOrderByNomeCliente(
                            id, isAprovada, cpf, nome);
        } else if (buscarPorCpf) {
            contas = repo.findByIdGerenteAndAprovadaAndCpfClienteContainingOrderByNomeCliente(id, isAprovada, cpf);
        } else if (buscarPorNome) {
            contas = repo.findByIdGerenteAndAprovadaAndNomeClienteContainingIgnoreCaseOrderByNomeCliente(id, isAprovada, nome);
        } else {
            contas = repo.findByIdGerenteAndAprovadaOrderByNomeCliente(id, isAprovada);
        }

        return ResponseEntity.status(200).body(contas.stream()
                .map(conta -> mapper.map(conta, ContaReadDTO.class))
                .collect(Collectors.toList()));
    }

    @GetMapping("/contas/gerente/{id}/top")
    public ResponseEntity<List<ContaReadDTO>> getTop5ContasByIdGerente(@PathVariable UUID id) {
        List<ContaRead> contas =  repo.findTop5ByIdGerenteAndAprovadaOrderBySaldoDesc(id, true);
        return ResponseEntity.status(200).body(contas.stream()
                .map(conta -> mapper.map(conta, ContaReadDTO.class))
                .collect(Collectors.toList()));
    }

    @GetMapping("/contas/gerente/{id}/cliente/{cpf}")
    public ResponseEntity<ContaReadDTO> getContaByIdGerenteCpfCliente(@PathVariable UUID id, @PathVariable String cpf) {
        ContaRead conta = repo.findByIdGerenteAndAprovadaAndCpfCliente(id, true, cpf);
        if (conta != null) {
            return ResponseEntity.status(200).body(mapper.map(conta, ContaReadDTO.class));
        }
        return ResponseEntity.notFound().build();
    }
}
