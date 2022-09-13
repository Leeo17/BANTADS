package br.ufpr.dac.bantads.orchestrator.handlers;

import br.ufpr.dac.bantads.common.constants.RabbitMQConstants;
import br.ufpr.dac.bantads.common.dto.ContaReadDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ufpr.dac.bantads.orchestrator.services.RabbitMQService;

@Component
public class ContaHandler {
	@Autowired
    private RabbitMQService rabbitmqService;
	
	@RabbitListener(queues = RabbitMQConstants.FILA_CONTA_READ_CRIADA)
	private void contaReadCriadaHandler(Long numeroConta) {
		this.rabbitmqService.enviaMensagem(RabbitMQConstants.FILA_DEFINIR_GERENTE, numeroConta);
	}

	@RabbitListener(queues = RabbitMQConstants.FILA_CONTA_READ_CLIENTE_APROVADA)
	private void contaReadClienteAprovadaHandler(ContaReadDTO contaReadDTO) {
		this.rabbitmqService.enviaMensagem(RabbitMQConstants.FILA_CRIAR_USUARIO_CLIENTE, contaReadDTO);
	}
}
