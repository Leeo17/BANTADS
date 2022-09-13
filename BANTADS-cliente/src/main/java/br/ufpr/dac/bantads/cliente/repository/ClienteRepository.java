package br.ufpr.dac.bantads.cliente.repository;

import br.ufpr.dac.bantads.cliente.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, String>  {
	public Cliente findById(UUID id);
	public Cliente findByEmail(String email);
}
