package br.ufpr.dac.bantads.gerente.model;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_gerentes_contas")
public class GerenteConta {
	@Id
	@Column(name="id_gerente_conta")
	private UUID id;

	@Column(name="id_gerente")
	private UUID idGerente;
	
	@Column(name="numero_conta")
	private Long numeroConta;

	@Column(name="saldo_conta")
	private BigDecimal saldoConta;

	public GerenteConta() {
		super();
	}

	public GerenteConta(UUID id, UUID idGerente, Long numeroConta, BigDecimal saldoConta) {
		super();
		this.id = id;
		this.idGerente = idGerente;
		this.numeroConta = numeroConta;
		this.saldoConta = saldoConta;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getIdGerente() {
		return idGerente;
	}

	public void setIdGerente(UUID idGerente) {
		this.idGerente = idGerente;
	}

	public Long getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(Long numeroConta) {
		this.numeroConta = numeroConta;
	}

	public BigDecimal getSaldoConta() {
		return saldoConta;
	}

	public void setSaldoConta(BigDecimal saldoConta) {
		this.saldoConta = saldoConta;
	}
}
