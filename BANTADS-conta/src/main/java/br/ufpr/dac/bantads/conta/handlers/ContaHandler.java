package br.ufpr.dac.bantads.conta.handlers;

import java.math.BigDecimal;
import java.util.Date;

import br.ufpr.dac.bantads.common.constants.RabbitMQConstants;
import br.ufpr.dac.bantads.common.dto.ClienteDTO;
import br.ufpr.dac.bantads.common.dto.ContaDTO;
import br.ufpr.dac.bantads.common.dto.ContaReadDTO;
import br.ufpr.dac.bantads.conta.repository.HistoricoMovimentacaoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ufpr.dac.bantads.conta.services.RabbitMQService;
import br.ufpr.dac.bantads.conta.model.Conta;
import br.ufpr.dac.bantads.conta.repository.ContaRepository;
import br.ufpr.dac.bantads.common.dto.GerenteContaDTO;

@Component
public class ContaHandler {
	@Autowired
	private ContaRepository repo;
	@Autowired
	private HistoricoMovimentacaoRepository historicoRepo;
	@Autowired
	private ModelMapper mapper;
    @Autowired
    private RabbitMQService rabbitmqService;
	
	@RabbitListener(queues = RabbitMQConstants.FILA_CRIAR_CONTA)
	private void criarContaHandler(ClienteDTO cliente) {
		ContaDTO conta = new ContaDTO();

		conta.setAprovada(null);
		conta.setIdCliente(cliente.getId());
		conta.setNomeCliente(cliente.getNome());
		conta.setSaldo(BigDecimal.ZERO);
		conta.setDataCriacao(new Date());

		if (cliente.getSalario().compareTo(BigDecimal.valueOf(2000.0)) >= 0) {
			conta.setLimite(cliente.getSalario().divide(BigDecimal.valueOf(2)));
		} else {
			conta.setLimite(BigDecimal.ZERO);
		}

		Conta contaCriada = repo.saveAndFlush(mapper.map(conta, Conta.class));

		ContaReadDTO contaReadDTO = new ContaReadDTO(
				contaCriada.getNumero(), contaCriada.getDataCriacao(), contaCriada.getLimite(),
				contaCriada.getSaldo(), null, "", null,
				contaCriada.getIdGerente(), contaCriada.getIdCliente(),
				cliente.getNome(), cliente.getEmail(), cliente.getCpf(), cliente.getSalario(),
				cliente.getEndereco().getCidade(), cliente.getEndereco().getEstado(), cliente.getEndereco().getTipo(),
				cliente.getEndereco().getLogradouro(), cliente.getEndereco().getComplemento(), cliente.getEndereco().getCep());

		this.rabbitmqService.enviaMensagem(RabbitMQConstants.FILA_CONTA_CRIADA, contaReadDTO);
	}

	@RabbitListener(queues = RabbitMQConstants.FILA_ATRIBUIR_GERENTE_CONTA)
	private void atribuirGerenteContaHandler(GerenteContaDTO gerenteContaDTO) {
		Conta conta = repo.findByNumero(gerenteContaDTO.getNumeroConta());
		conta.setIdGerente(gerenteContaDTO.getIdGerente());
		repo.saveAndFlush(conta);
	}
}
