package br.ufpr.dac.bantads.conta.read.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufpr.dac.bantads.conta.read.model.ContaRead;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ContaReadRepository extends JpaRepository<ContaRead, String> {
    public ContaRead findByNumero(Long numero);

    public List<ContaRead> findByIdGerenteAndAprovadaOrderByNomeCliente(
            @Param("idGerente") UUID idGerente,
            @Param("aprovada") Boolean aprovada);

    public List<ContaRead> findByIdGerenteAndAprovadaAndCpfClienteContainingAndNomeClienteContainingIgnoreCaseOrderByNomeCliente(
            @Param("idGerente") UUID idGerente,
            @Param("aprovada") Boolean aprovada,
            @Param("cpfCliente") String cpfCliente,
            @Param("nomeCliente") String nomeCliente);

    public List<ContaRead> findByIdGerenteAndAprovadaAndCpfClienteContainingOrderByNomeCliente(
            @Param("idGerente") UUID idGerente,
            @Param("aprovada") Boolean aprovada,
            @Param("cpfCliente") String cpfCliente);

    public List<ContaRead> findByIdGerenteAndAprovadaAndNomeClienteContainingIgnoreCaseOrderByNomeCliente(
            @Param("idGerente") UUID idGerente,
            @Param("aprovada") Boolean aprovada,
            @Param("nomeCliente") String nomeCliente);

    public List<ContaRead> findTop5ByIdGerenteAndAprovadaOrderBySaldoDesc(@Param("idGerente") UUID idGerente,
                                                                          @Param("aprovada") Boolean aprovada);

    public ContaRead findByIdGerenteAndAprovadaAndCpfCliente(@Param("idGerente") UUID idGerente,
                                                             @Param("aprovada") Boolean aprovada,
                                                             @Param("cpfCliente") String cpf);
}
