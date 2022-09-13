package br.ufpr.dac.bantads.gerente.handlers;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import br.ufpr.dac.bantads.common.constants.RabbitMQConstants;
import br.ufpr.dac.bantads.common.dto.AtualizarSaldoGerenteContaDTO;
import br.ufpr.dac.bantads.common.dto.GerenteContaDTO;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ufpr.dac.bantads.gerente.model.Gerente;
import br.ufpr.dac.bantads.gerente.model.GerenteConta;
import br.ufpr.dac.bantads.gerente.repository.GerenteContaRepository;
import br.ufpr.dac.bantads.gerente.repository.GerenteRepository;
import br.ufpr.dac.bantads.gerente.services.RabbitMQService;

import org.springframework.transaction.annotation.Transactional;

@Component
public class GerenteHandler {
	@Autowired
	private GerenteRepository repo;
	@Autowired
	private GerenteContaRepository gerenteContaRepo;
	@Autowired
	private RabbitMQService rabbitmqService;
	@Autowired
	private ModelMapper mapper;

	@RabbitListener(queues = RabbitMQConstants.FILA_DEFINIR_GERENTE)
	@Transactional(rollbackFor = Exception.class)
	private void definirGerenteConta(Long numeroConta) {
		List<Gerente> todosGerentes = repo.findAll();
		
		if (todosGerentes.isEmpty()) {
			return;
		}

		Gerente gerente = todosGerentes.stream()
				.min(Comparator.comparing(Gerente::getQuantidadeContas))
				.orElse(todosGerentes.get(0));

		GerenteConta gerenteConta = new GerenteConta(UUID.randomUUID(),
				gerente.getId(), numeroConta, BigDecimal.ZERO);

		gerente.setQuantidadeContas(gerente.getQuantidadeContas() + 1);

		gerenteContaRepo.saveAndFlush(gerenteConta);
		repo.saveAndFlush(gerente);

		GerenteContaDTO gerenteContaDTO = mapper.map(
				gerenteConta, GerenteContaDTO.class);
		this.rabbitmqService.enviaMensagem(RabbitMQConstants.FILA_GERENTE_DEFINIDO, gerenteContaDTO);
	}

	@RabbitListener(queues = RabbitMQConstants.FILA_ATUALIZAR_SALDO_GERENTE_CONTA)
	private void atualizarSaldoGerenteConta(AtualizarSaldoGerenteContaDTO dto) {
		GerenteConta gerenteConta = gerenteContaRepo.findByNumeroConta(dto.getNumeroConta());
		gerenteConta.setSaldoConta(dto.getNovoSaldo());

		gerenteContaRepo.saveAndFlush(gerenteConta);
	}
}
