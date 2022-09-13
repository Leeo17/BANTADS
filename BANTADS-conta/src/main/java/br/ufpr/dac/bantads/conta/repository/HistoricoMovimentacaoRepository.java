package br.ufpr.dac.bantads.conta.repository;

import br.ufpr.dac.bantads.conta.model.HistoricoMovimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface HistoricoMovimentacaoRepository extends JpaRepository<HistoricoMovimentacao, String> {
    public List<HistoricoMovimentacao> findByNumeroContaOrigemAndDataHoraGreaterThanEqualAndDataHoraLessThanEqualOrderByDataHoraAsc(
            @Param("numeroContaOrigem") Long numeroContaOrigem,
            @Param("dataInicio") Date dataInicio,
            @Param("dataFim") Date dataFim);
}