package br.ufpr.dac.bantads.gerente.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufpr.dac.bantads.gerente.model.GerenteConta;

public interface GerenteContaRepository extends JpaRepository<GerenteConta, String>{
	public GerenteConta findById(UUID id);
	public GerenteConta findByNumeroConta(Long numeroConta);
}
