package br.ufpr.dac.bantads.conta.rest;

import br.ufpr.dac.bantads.common.constants.RabbitMQConstants;
import br.ufpr.dac.bantads.common.dto.ContaDTO;
import br.ufpr.dac.bantads.conta.model.HistoricoMovimentacao;
import br.ufpr.dac.bantads.conta.model.HistoricoMovimentacaoDTO;
import br.ufpr.dac.bantads.conta.repository.HistoricoMovimentacaoRepository;
import br.ufpr.dac.bantads.conta.services.RabbitMQService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import br.ufpr.dac.bantads.conta.model.Conta;
import br.ufpr.dac.bantads.conta.repository.ContaRepository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@CrossOrigin
@RestController
public class ContaREST {
	@Autowired
	private ContaRepository repo;
	@Autowired
	private HistoricoMovimentacaoRepository repoHistorico;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private RabbitMQService rabbitMQService;

	@PostMapping("/contas")
	public ResponseEntity<ContaDTO> inserir(@RequestBody ContaDTO contaDTO) {
		Conta novaConta = repo.saveAndFlush(mapper.map(contaDTO, Conta.class));
		return ResponseEntity.status(201).body(mapper.map(novaConta, ContaDTO.class));
	}

	@PostMapping("/contas/{numero}/aprovada")
	public ResponseEntity<Object> aprovarConta(@PathVariable Long numero) {
		Conta contaAprovada = repo.findByNumero(numero);
		contaAprovada.setAprovada(true);
		repo.saveAndFlush(contaAprovada);
		rabbitMQService.enviaMensagem(RabbitMQConstants.FILA_CONTA_CLIENTE_APROVADA, numero);
		return ResponseEntity.ok(null);
	}

	@PostMapping("/contas/{numero}/reprovada")
	public ResponseEntity<Object> reprovarConta(@PathVariable Long numero, @RequestParam String motivo) {
		Conta contaReprovada = repo.findByNumero(numero);
		contaReprovada.setAprovada(false);
		contaReprovada.setMotivoReprovacao(motivo);
		contaReprovada.setDataHoraAprovacaoReprovacao(new Date());
		repo.saveAndFlush(contaReprovada);
		rabbitMQService.enviaMensagem(RabbitMQConstants.FILA_CONTA_CLIENTE_REPROVADA, mapper.map(contaReprovada,ContaDTO.class));
		return ResponseEntity.ok(null);
	}

	@GetMapping("/contas/cliente/{id}")
	public ResponseEntity<ContaDTO> buscarContaCliente(@PathVariable UUID id) {
		Conta conta = repo.findByIdCliente(id);
		return ResponseEntity.status(200).body(mapper.map(conta, ContaDTO.class));
	}

	@PostMapping("/contas/{numero}/deposito")
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<Object> depositar(@PathVariable Long numero, @RequestParam BigDecimal valor) {
		Conta conta = repo.findByNumero(numero);
		conta.setSaldo(conta.getSaldo().add(valor));
		repo.saveAndFlush(conta);

		HistoricoMovimentacao historicoMovimentacao = new HistoricoMovimentacao(UUID.randomUUID(), new Date(),
				"DEPOSITO", numero, null, conta.getNomeCliente(), null, valor, conta.getSaldo());
		repoHistorico.saveAndFlush(historicoMovimentacao);

		rabbitMQService.enviaMensagem(RabbitMQConstants.FILA_MOVIMENTACAO_CONTA_REALIZADA, mapper.map(historicoMovimentacao,
				HistoricoMovimentacaoDTO.class));
		return ResponseEntity.status(200).build();
	}

	@PostMapping("/contas/{numero}/saque")
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<Object> sacar(@PathVariable Long numero, @RequestParam BigDecimal valor) {
		Conta conta = repo.findByNumero(numero);
		BigDecimal saldoComLimite = conta.getSaldo().add(conta.getLimite());

		if (valor.compareTo(saldoComLimite) > 0) {
			return ResponseEntity.status(403).build();
		}
		conta.setSaldo(conta.getSaldo().subtract(valor));
		repo.saveAndFlush(conta);

		HistoricoMovimentacao historicoMovimentacao = new HistoricoMovimentacao(UUID.randomUUID(), new Date(), "SAQUE", numero,
				null, conta.getNomeCliente(), null, valor, conta.getSaldo());
		repoHistorico.saveAndFlush(historicoMovimentacao);

		rabbitMQService.enviaMensagem(RabbitMQConstants.FILA_MOVIMENTACAO_CONTA_REALIZADA, mapper.map(historicoMovimentacao,
				HistoricoMovimentacaoDTO.class));
		return ResponseEntity.status(200).build();
	}

	@PostMapping("/contas/{numero}/transferencia")
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<Object> transferir(@PathVariable Long numero, @RequestParam Long numeroDestino, @RequestParam BigDecimal valor) {
		Conta contaOrigem = repo.findByNumero(numero);

		BigDecimal saldoComLimite = contaOrigem.getSaldo().add(contaOrigem.getLimite());

		if (valor.compareTo(saldoComLimite) > 0) {
			return ResponseEntity.status(403).build();
		}

		Conta contaDestino = repo.findByNumero(numeroDestino);

		if (contaDestino == null) {
			return ResponseEntity.status(404).build();
		}

		contaOrigem.setSaldo(contaOrigem.getSaldo().subtract(valor));
		contaDestino.setSaldo(contaDestino.getSaldo().add(valor));

		repo.saveAndFlush(contaOrigem);
		repo.saveAndFlush(contaDestino);

		HistoricoMovimentacao historicoMovimentacao = new HistoricoMovimentacao(UUID.randomUUID(), new Date(), "TRANSFERENCIA", numero,
				numeroDestino, contaOrigem.getNomeCliente(), contaDestino.getNomeCliente(), valor, contaOrigem.getSaldo());
		repoHistorico.saveAndFlush(historicoMovimentacao);

		rabbitMQService.enviaMensagem(RabbitMQConstants.FILA_MOVIMENTACAO_CONTA_REALIZADA, mapper.map(historicoMovimentacao,
				HistoricoMovimentacaoDTO.class));
		return ResponseEntity.status(200).build();
	}
}
