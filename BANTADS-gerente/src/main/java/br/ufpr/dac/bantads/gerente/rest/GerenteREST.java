package br.ufpr.dac.bantads.gerente.rest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.ufpr.dac.bantads.common.constants.RabbitMQConstants;
import br.ufpr.dac.bantads.common.dto.AlterarUsuarioGerenteDTO;
import br.ufpr.dac.bantads.common.dto.GerenteDTO;
import br.ufpr.dac.bantads.gerente.model.GerenteConta;
import br.ufpr.dac.bantads.gerente.repository.GerenteContaRepository;
import br.ufpr.dac.bantads.gerente.services.RabbitMQService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.ufpr.dac.bantads.gerente.model.Gerente;
import br.ufpr.dac.bantads.gerente.repository.GerenteRepository;

@CrossOrigin
@RestController
public class GerenteREST {
	@Autowired
	private GerenteRepository repo;
	@Autowired
	private GerenteContaRepository gerenteContaRepo;
	@Autowired
	private ModelMapper mapper;

	@Autowired
	private RabbitMQService rabbitMQService;

	@GetMapping("/gerentes")
	public ResponseEntity<List<GerenteDTO>> listarTodos() {
		List<Gerente> gerentes = repo.findAll();
		List<GerenteConta> gerentesContas = gerenteContaRepo.findAll();
		List<GerenteDTO> output = new ArrayList<>();

		for (Gerente gerente : gerentes) {
			BigDecimal somaSaldosPositivos = gerentesContas.stream().filter(gerenteConta -> gerenteConta.getIdGerente().equals(gerente.getId()))
					.map(GerenteConta::getSaldoConta)
					.filter(saldoConta -> saldoConta.compareTo(BigDecimal.ZERO) > 0)
					.reduce(BigDecimal.ZERO, BigDecimal::add);

			BigDecimal somaSaldosNegativos = gerentesContas.stream().filter(gerenteConta -> gerenteConta.getIdGerente().equals(gerente.getId()))
					.map(GerenteConta::getSaldoConta)
					.filter(saldoConta -> saldoConta.compareTo(BigDecimal.ZERO) < 0)
					.reduce(BigDecimal.ZERO, BigDecimal::add);

			output.add(new GerenteDTO(gerente.getId(), gerente.getNome(),
					gerente.getEmail(), gerente.getCpf(), gerente.getQuantidadeContas(), somaSaldosPositivos, somaSaldosNegativos));
		}

		return ResponseEntity.ok(output);
	}

	@GetMapping("/gerentes/{id}")
	public ResponseEntity<GerenteDTO> buscarPorId(@PathVariable("id") UUID id) {
		Gerente gerenteEncontrado = repo.findAll().stream().filter(gerente -> gerente.getId().equals(id))
				.findAny()
				.orElse(null);

		if (gerenteEncontrado != null) {
			return ResponseEntity.ok(mapper.map(gerenteEncontrado, GerenteDTO.class));
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping("/gerentes")
	public ResponseEntity<GerenteDTO> inserir(@RequestBody GerenteDTO gerenteDTO) {
		gerenteDTO.setQuantidadeContas(0);
		repo.saveAndFlush(mapper.map(gerenteDTO, Gerente.class));

		rabbitMQService.enviaMensagem(RabbitMQConstants.FILA_GERENTE_CRIADO, gerenteDTO);
		return ResponseEntity.ok(gerenteDTO);
	}

	@PutMapping("/gerentes/{id}")
	public ResponseEntity<GerenteDTO> alterar(@PathVariable("id") UUID id, @RequestBody GerenteDTO gerenteDTO) {
		Gerente gerenteAtualizar = repo.findAll().stream().filter(gerente -> gerente.getId().equals(id))
				.findAny()
				.orElse(null);

		if (gerenteAtualizar != null) {
			AlterarUsuarioGerenteDTO alterarUsuarioGerenteDTO = new AlterarUsuarioGerenteDTO(
					gerenteAtualizar.getEmail(), gerenteDTO.getEmail(), gerenteDTO.getNome());

			gerenteAtualizar.setNome(gerenteDTO.getNome());
			gerenteAtualizar.setCpf(gerenteDTO.getCpf());
			gerenteAtualizar.setEmail(gerenteDTO.getEmail());
			repo.saveAndFlush(gerenteAtualizar);
			rabbitMQService.enviaMensagem(RabbitMQConstants.FILA_GERENTE_EDITADO, alterarUsuarioGerenteDTO);
			return ResponseEntity.ok(mapper.map(gerenteAtualizar, GerenteDTO.class));
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/gerentes/{id}")
	public ResponseEntity<GerenteDTO> remover(@PathVariable("id") UUID id) {
		List<Gerente> gerentes = repo.findAll();
		Gerente gerenteRemover = gerentes.stream().filter(gerente -> gerente.getId().equals(id))
				.findAny()
				.orElse(null);

		if (gerenteRemover != null) {
			if (gerenteRemover.getQuantidadeContas() > 0) {
				return ResponseEntity.status(403).build();
			}

			repo.delete(gerenteRemover);
			rabbitMQService.enviaMensagem(RabbitMQConstants.FILA_GERENTE_REMOVIDO, gerenteRemover.getEmail());
			return ResponseEntity.ok(mapper.map(gerenteRemover, GerenteDTO.class));
		}

		return ResponseEntity.notFound().build();
	}

	@GetMapping("/gerentes/email/{email}")
	public ResponseEntity<GerenteDTO> getIdGerentePorEmail(@PathVariable String email) {
		Gerente gerente = repo.findByEmail(email);
		return ResponseEntity.ok(mapper.map(gerente, GerenteDTO.class));
	}
}
