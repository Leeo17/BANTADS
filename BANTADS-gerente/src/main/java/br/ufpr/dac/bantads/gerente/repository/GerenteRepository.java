package br.ufpr.dac.bantads.gerente.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import br.ufpr.dac.bantads.gerente.model.Gerente;

public interface GerenteRepository extends JpaRepository<Gerente, String> {
	public Gerente findById(UUID id);
	public Gerente findByEmail(String email);
}
