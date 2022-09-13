package br.ufpr.dac.bantads.orchestrator.handlers;

import br.ufpr.dac.bantads.common.constants.RabbitMQConstants;
import br.ufpr.dac.bantads.common.dto.AlterarUsuarioGerenteDTO;
import br.ufpr.dac.bantads.common.dto.AtualizarSaldoGerenteContaDTO;
import br.ufpr.dac.bantads.common.dto.GerenteContaDTO;
import br.ufpr.dac.bantads.common.dto.GerenteDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ufpr.dac.bantads.orchestrator.services.RabbitMQService;

@Component
public class GerenteHandler {
	@Autowired
    private RabbitMQService rabbitmqService;

	@RabbitListener(queues = RabbitMQConstants.FILA_GERENTE_DEFINIDO)
	private void gerenteDefinidoHandler(GerenteContaDTO gerenteContaDTO) {
		this.rabbitmqService.enviaMensagem(RabbitMQConstants.FILA_ATRIBUIR_GERENTE_CONTA, gerenteContaDTO);
		this.rabbitmqService.enviaMensagem(RabbitMQConstants.FILA_ATRIBUIR_GERENTE_CONTA_READ, gerenteContaDTO);
	}

	@RabbitListener(queues = RabbitMQConstants.FILA_GERENTE_CRIADO)
	private void gerenteCriadoHandler(GerenteDTO gerenteDTO) {
		this.rabbitmqService.enviaMensagem(RabbitMQConstants.FILA_CRIAR_USUARIO_GERENTE, gerenteDTO);
	}

	@RabbitListener(queues = RabbitMQConstants.FILA_GERENTE_EDITADO)
	private void gerenteEditadoHandler(AlterarUsuarioGerenteDTO alterarUsuarioGerenteDTO) {
		this.rabbitmqService.enviaMensagem(RabbitMQConstants.FILA_ATUALIZAR_USUARIO_GERENTE, alterarUsuarioGerenteDTO);
	}

	@RabbitListener(queues = RabbitMQConstants.FILA_GERENTE_REMOVIDO)
	private void gerenteRemovidoHandler(String emailGerente) {
		this.rabbitmqService.enviaMensagem(RabbitMQConstants.FILA_REMOVER_USUARIO_GERENTE, emailGerente);
	}

	@RabbitListener(queues = RabbitMQConstants.FILA_SALDO_CONTA_ATUALIZADO)
	private void saldoContaAtualizado(AtualizarSaldoGerenteContaDTO dto) {
		this.rabbitmqService.enviaMensagem(RabbitMQConstants.FILA_ATUALIZAR_SALDO_GERENTE_CONTA, dto);
	}
}
