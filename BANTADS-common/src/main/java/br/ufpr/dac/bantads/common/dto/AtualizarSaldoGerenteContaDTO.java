package br.ufpr.dac.bantads.common.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class AtualizarSaldoGerenteContaDTO implements Serializable {
    private BigDecimal novoSaldo;
    private Long numeroConta;

    public AtualizarSaldoGerenteContaDTO() {
        super();
    }

    public AtualizarSaldoGerenteContaDTO(BigDecimal novoSaldo, Long numeroConta) {
        this.novoSaldo = novoSaldo;
        this.numeroConta = numeroConta;
    }

    public BigDecimal getNovoSaldo() {
        return novoSaldo;
    }

    public void setNovoSaldo(BigDecimal novoSaldo) {
        this.novoSaldo = novoSaldo;
    }

    public Long getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(Long numeroConta) {
        this.numeroConta = numeroConta;
    }
}
