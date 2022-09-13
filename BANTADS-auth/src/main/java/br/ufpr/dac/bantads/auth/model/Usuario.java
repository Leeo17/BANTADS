package br.ufpr.dac.bantads.auth.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="tb_usuarios")
public class Usuario {
	@Id
	@Column(name="id_usu")
	private UUID id;

	@Column(name="nome_usu")
	private String nome;
	
	@Column(name="login_usu", unique=true)
	private String login;
	
	@Column(name="senha_usu")
	private String senha;
	
	@Column(name="perfil_usu")
	private String perfil;

	public Usuario() {
		super();
	}

	public Usuario(UUID id, String nome, String login, String senha, String perfil) {
		super();
		this.id = id;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.perfil = perfil;
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
}