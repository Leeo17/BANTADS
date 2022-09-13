package br.ufpr.dac.bantads.orchestrator.handlers;

import br.ufpr.dac.bantads.common.constants.RabbitMQConstants;
import br.ufpr.dac.bantads.common.dto.ClienteDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ufpr.dac.bantads.orchestrator.services.RabbitMQService;

@Component
public class ClienteHandler {
	@Autowired
    private RabbitMQService rabbitmqService;
	
	@RabbitListener(queues = RabbitMQConstants.FILA_CLIENTE_CRIADO)
	private void clienteCriadoHandler(ClienteDTO cliente) {
		this.rabbitmqService.enviaMensagem(RabbitMQConstants.FILA_CRIAR_CONTA, cliente);
	}
}
