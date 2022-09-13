package br.ufpr.dac.bantads.conta.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import javax.persistence.*;

@Entity
@Table(name="tb_contas")
public class Conta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="numero_conta")
	private Long numero;

	@Column(name="id_cliente_conta", unique = true)
	private UUID idCliente;

	@Column(name="nome_cliente_conta")
	private String nomeCliente;

	@Column(name="data_criacao_conta")
	private Date dataCriacao;

	@Column(name="limite_conta")
	private BigDecimal limite;
	
	@Column(name="saldo_conta")
	private BigDecimal saldo;

	@Column(name="aprovada_conta")
	private Boolean aprovada;

	@Column(name = "motivo_reprovacao_conta")
	private String motivoReprovacao;

	@Column(name = "data_hora_aprovacao_reprovacao_conta")
	private Date dataHoraAprovacaoReprovacao;

	@Column(name="id_gerente_conta")
	private UUID idGerente;

	public Conta() {
		super();
	}

	public Conta(Long numero, UUID idCliente, String nomeCliente, Date dataCriacao, BigDecimal limite,
				 BigDecimal saldo, Boolean aprovada, String motivoReprovacao, Date dataHoraAprovacaoReprovacao,
				 UUID idGerente) {
		this.numero = numero;
		this.idCliente = idCliente;
		this.nomeCliente = nomeCliente;
		this.dataCriacao = dataCriacao;
		this.limite = limite;
		this.saldo = saldo;
		this.aprovada = aprovada;
		this.motivoReprovacao = motivoReprovacao;
		this.dataHoraAprovacaoReprovacao = dataHoraAprovacaoReprovacao;
		this.idGerente = idGerente;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public UUID getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(UUID idCliente) {
		this.idCliente = idCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public BigDecimal getLimite() {
		return limite;
	}

	public void setLimite(BigDecimal limite) {
		this.limite = limite;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public Boolean getAprovada() {
		return aprovada;
	}

	public void setAprovada(Boolean aprovada) {
		this.aprovada = aprovada;
	}

	public String getMotivoReprovacao() {
		return motivoReprovacao;
	}

	public void setMotivoReprovacao(String motivoReprovacao) {
		this.motivoReprovacao = motivoReprovacao;
	}

	public Date getDataHoraAprovacaoReprovacao() {
		return dataHoraAprovacaoReprovacao;
	}

	public void setDataHoraAprovacaoReprovacao(Date dataHoraAprovacaoReprovacao) {
		this.dataHoraAprovacaoReprovacao = dataHoraAprovacaoReprovacao;
	}

	public UUID getIdGerente() {
		return idGerente;
	}

	public void setIdGerente(UUID idGerente) {
		this.idGerente = idGerente;
	}
}
