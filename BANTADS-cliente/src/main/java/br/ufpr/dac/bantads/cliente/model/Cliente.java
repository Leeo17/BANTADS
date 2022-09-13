package br.ufpr.dac.bantads.cliente.model;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name="tb_clientes")
public class Cliente {
	@Id
	@Column(name="id_cli")
	private UUID id;

	@Column(name="nome_cli")
	private String nome;

	@Column(name="email_cli")
	private String email;

	@Column(name="cpf_cli", unique = true)
	private String cpf;

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco_cli", referencedColumnName = "id_endereco")
	private EnderecoCliente endereco;

	@Column(name="salario_cli")
	private BigDecimal salario;

	public Cliente() {
		super();
	}

	public Cliente(UUID id, String nome, String email, String cpf, EnderecoCliente endereco, BigDecimal salario) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.endereco = endereco;
		this.salario = salario;
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

	public EnderecoCliente getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoCliente endereco) {
		this.endereco = endereco;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}
}

