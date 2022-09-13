package br.ufpr.dac.bantads.conta.repository;

import org.springframework.data.jpa.repository.*;

import br.ufpr.dac.bantads.conta.model.Conta;

import java.util.UUID;

public interface ContaRepository extends JpaRepository<Conta, String>  {
	public Conta findByNumero(Long numero);
	public Conta findByIdCliente(UUID idCliente);
}
