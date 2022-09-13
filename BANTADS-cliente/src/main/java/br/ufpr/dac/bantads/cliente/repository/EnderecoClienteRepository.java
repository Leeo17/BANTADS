package br.ufpr.dac.bantads.cliente.repository;

import br.ufpr.dac.bantads.cliente.model.EnderecoCliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EnderecoClienteRepository extends JpaRepository<EnderecoCliente, String> {
    public EnderecoCliente findById(UUID id);
}