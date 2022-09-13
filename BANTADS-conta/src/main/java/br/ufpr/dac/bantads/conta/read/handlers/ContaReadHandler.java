package br.ufpr.dac.bantads.conta.read.handlers;

import br.ufpr.dac.bantads.common.constants.RabbitMQConstants;
import br.ufpr.dac.bantads.common.dto.AtualizarSaldoGerenteContaDTO;
import br.ufpr.dac.bantads.common.dto.ContaDTO;
import br.ufpr.dac.bantads.common.dto.ContaReadDTO;
import br.ufpr.dac.bantads.common.dto.GerenteContaDTO;
import br.ufpr.dac.bantads.conta.model.HistoricoMovimentacaoDTO;
import br.ufpr.dac.bantads.conta.services.EnviarEmailService;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ufpr.dac.bantads.conta.read.model.ContaRead;
import br.ufpr.dac.bantads.conta.read.repository.ContaReadRepository;
import br.ufpr.dac.bantads.conta.services.RabbitMQService;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Component
public class ContaReadHandler {
	@Autowired
	private ContaReadRepository repo;
	@Autowired
	private ModelMapper mapper;
	@Autowired
    private RabbitMQService rabbitMQService;
	@Autowired
	private EnviarEmailService enviarEmailService;

	@RabbitListener(queues = RabbitMQConstants.FILA_CONTA_CRIADA)
	private void criarContaReadHandler(ContaReadDTO conta) {
		repo.saveAndFlush(mapper.map(conta, ContaRead.class));
		this.rabbitMQService.enviaMensagem(RabbitMQConstants.FILA_CONTA_READ_CRIADA,
				conta.getNumero());
	}

	@RabbitListener(queues = RabbitMQConstants.FILA_ATRIBUIR_GERENTE_CONTA_READ)
	private void atribuirGerenteContaReadHandler(GerenteContaDTO gerenteContaDTO) {
		ContaRead contaRead = repo.findByNumero(gerenteContaDTO.getNumeroConta());
		contaRead.setIdGerente(gerenteContaDTO.getIdGerente());
		repo.saveAndFlush(contaRead);
	}

	@RabbitListener(queues = RabbitMQConstants.FILA_CONTA_CLIENTE_APROVADA)
	public void aprovarContaRead(Long numeroContaAprovada) {
		ContaRead contaReadAprovada = repo.findByNumero(numeroContaAprovada);
		contaReadAprovada.setAprovada(true);
		repo.save(contaReadAprovada);
		rabbitMQService.enviaMensagem(RabbitMQConstants.FILA_CONTA_READ_CLIENTE_APROVADA, mapper.map(
				contaReadAprovada, ContaReadDTO.class));
	}

	@RabbitListener(queues = RabbitMQConstants.FILA_CONTA_CLIENTE_REPROVADA)
	public void reprovarContaRead(ContaDTO contaDTO) {
		ContaRead contaReadReprovada = repo.findByNumero(contaDTO.getNumero());
		contaReadReprovada.setAprovada(false);
		contaReadReprovada.setMotivoReprovacao(contaDTO.getMotivoReprovacao());
		contaReadReprovada.setDataHoraAprovacaoReprovacao(contaDTO.getDataHoraAprovacaoReprovacao());
		repo.save(contaReadReprovada);

		enviarEmailService.sendEmail(contaReadReprovada.getEmailCliente(), "Infelizmente sua conta" +
				" BANTADS foi reprovada.", contaDTO.getMotivoReprovacao());
	}

	@RabbitListener(queues = RabbitMQConstants.FILA_MOVIMENTACAO_CONTA_REALIZADA)
	@Transactional(rollbackFor = Exception.class)
	private void atualizarSaldosContaRead(HistoricoMovimentacaoDTO historicoMovimentacao) {
		switch (historicoMovimentacao.getTipo()) {
			case "DEPOSITO": {
				ContaRead conta = repo.findByNumero(historicoMovimentacao.getNumeroContaOrigem());
				BigDecimal novoSaldo = conta.getSaldo().add(historicoMovimentacao.getValorMovimentacao());
				conta.setSaldo(novoSaldo);
				repo.saveAndFlush(conta);

				rabbitMQService.enviaMensagem(RabbitMQConstants.FILA_SALDO_CONTA_ATUALIZADO, new
						AtualizarSaldoGerenteContaDTO(novoSaldo, conta.getNumero()));
				break;
			}
			case "SAQUE": {
				ContaRead conta = repo.findByNumero(historicoMovimentacao.getNumeroContaOrigem());
				BigDecimal novoSaldo = conta.getSaldo().subtract(historicoMovimentacao.getValorMovimentacao());
				conta.setSaldo(novoSaldo);
				repo.saveAndFlush(conta);

				rabbitMQService.enviaMensagem(RabbitMQConstants.FILA_SALDO_CONTA_ATUALIZADO, new
						AtualizarSaldoGerenteContaDTO(novoSaldo, conta.getNumero()));
				break;
			}
			case "TRANSFERENCIA": {
				ContaRead contaOrigem = repo.findByNumero(historicoMovimentacao.getNumeroContaOrigem());
				BigDecimal novoSaldoOrigem = contaOrigem.getSaldo().subtract(historicoMovimentacao.getValorMovimentacao());
				contaOrigem.setSaldo(novoSaldoOrigem);
				repo.saveAndFlush(contaOrigem);

				ContaRead contaDestino = repo.findByNumero(historicoMovimentacao.getNumeroContaDestino());
				BigDecimal novoSaldoDestino = contaDestino.getSaldo().add(historicoMovimentacao.getValorMovimentacao());
				contaDestino.setSaldo(novoSaldoDestino);
				repo.saveAndFlush(contaDestino);

				rabbitMQService.enviaMensagem(RabbitMQConstants.FILA_SALDO_CONTA_ATUALIZADO, new
						AtualizarSaldoGerenteContaDTO(novoSaldoOrigem, contaOrigem.getNumero()));

				rabbitMQService.enviaMensagem(RabbitMQConstants.FILA_SALDO_CONTA_ATUALIZADO, new
						AtualizarSaldoGerenteContaDTO(novoSaldoDestino, contaDestino.getNumero()));

				break;
			}
		}
	}
}
