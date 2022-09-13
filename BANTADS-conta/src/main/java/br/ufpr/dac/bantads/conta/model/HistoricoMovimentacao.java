package br.ufpr.dac.bantads.conta.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tb_historico_movimentacoes")
public class HistoricoMovimentacao {
	@Id
	@Column(name="id_his")
	private UUID id;

	@Column(name="data_hora_his")
	private Date dataHora;

	@Column(name="tipo_his")
	private String tipo;

	@Column(name="numero_conta_origem_his")
	private Long numeroContaOrigem;

	@Column(name="numero_conta_destino_his")
	private Long numeroContaDestino;

	@Column(name="nome_cliente_origem_his")
	private String nomeClienteOrigem;

	@Column(name="nome_cliente_destino_his")
	private String nomeClienteDestino;

	@Column(name="valor_movimentacao_his")
	private BigDecimal valorMovimentacao;

	@Column(name="saldo_apos_movimentacao_his")
	private BigDecimal saldoAposMovimentacao;

	public HistoricoMovimentacao() {
		super();
	}

	public HistoricoMovimentacao(UUID id, Date dataHora, String tipo, Long numeroContaOrigem, Long numeroContaDestino,
								 String nomeClienteOrigem, String nomeClienteDestino, BigDecimal valorMovimentacao,
								 BigDecimal saldoAposMovimentacao) {
		this.id = id;
		this.dataHora = dataHora;
		this.tipo = tipo;
		this.numeroContaOrigem = numeroContaOrigem;
		this.numeroContaDestino = numeroContaDestino;
		this.nomeClienteOrigem = nomeClienteOrigem;
		this.nomeClienteDestino = nomeClienteDestino;
		this.valorMovimentacao = valorMovimentacao;
		this.saldoAposMovimentacao = saldoAposMovimentacao;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Date getDataHora() {
		return dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Long getNumeroContaOrigem() {
		return numeroContaOrigem;
	}

	public void setNumeroContaOrigem(Long numeroContaOrigem) {
		this.numeroContaOrigem = numeroContaOrigem;
	}

	public Long getNumeroContaDestino() {
		return numeroContaDestino;
	}

	public void setNumeroContaDestino(Long numeroContaDestino) {
		this.numeroContaDestino = numeroContaDestino;
	}

	public String getNomeClienteOrigem() {
		return nomeClienteOrigem;
	}

	public void setNomeClienteOrigem(String nomeClienteOrigem) {
		this.nomeClienteOrigem = nomeClienteOrigem;
	}

	public String getNomeClienteDestino() {
		return nomeClienteDestino;
	}

	public void setNomeClienteDestino(String nomeClienteDestino) {
		this.nomeClienteDestino = nomeClienteDestino;
	}

	public BigDecimal getValorMovimentacao() {
		return valorMovimentacao;
	}

	public void setValorMovimentacao(BigDecimal valorMovimentacao) {
		this.valorMovimentacao = valorMovimentacao;
	}

	public BigDecimal getSaldoAposMovimentacao() {
		return saldoAposMovimentacao;
	}

	public void setSaldoAposMovimentacao(BigDecimal saldoAposMovimentacao) {
		this.saldoAposMovimentacao = saldoAposMovimentacao;
	}
}
