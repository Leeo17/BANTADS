package br.ufpr.dac.bantads.gerente.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_gerentes")
public class Gerente {
	@Id
	@Column(name="id_gerente")
	private UUID id;
	
	@Column(name="nome_gerente")
	private String nome;
	
	@Column(name="email_gerente", unique = true)
	private String email;

	@Column(name="cpf_gerente", unique = true)
	private String cpf;
	
	@Column(name="quantidade_contas_gerente")
	private int quantidadeContas;

	public Gerente() {
		super();
	}

	public Gerente(UUID id, String nome, String email, String cpf, int quantidadeContas) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.quantidadeContas = quantidadeContas;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public int getQuantidadeContas() {
		return quantidadeContas;
	}

	public void setQuantidadeContas(int quantidadeContas) {
		this.quantidadeContas = quantidadeContas;
	}
}
