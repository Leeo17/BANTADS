package br.ufpr.dac.bantads.cliente.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_enderecos_clientes")
public class EnderecoCliente {
	@Id
	@Column(name="id_endereco")
	private UUID id;

	@Column(name="logradouro_endereco")
	private String logradouro;
	
	@Column(name="numero_endereco")
	private int numero;
	
	@Column(name="complemento_endereco")
	private String complemento;
	
	@Column(name="tipo_endereco")
	private String tipo;

	@Column(name="cep_endereco")
	private String cep;

	@Column(name="cidade_endereco")
	private String cidade;

	@Column(name="estado_endereco")
	private String estado;

	public EnderecoCliente() {
		super();
	}

	public EnderecoCliente(UUID id, String logradouro, int numero, String complemento, String tipo, String cep,
			String cidade, String estado) {
		super();
		this.id = id;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.tipo = tipo;
		this.cep = cep;
		this.cidade = cidade;
		this.estado = estado;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}