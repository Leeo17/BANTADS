package br.ufpr.dac.bantads.auth.model;

import java.util.UUID;

public class UsuarioDTO {
	private UUID id;
	private String nome;
	private String login;
	private String senha;
	private String perfil;

	public UsuarioDTO() {
		super();
	}

	public UsuarioDTO(UUID id, String nome, String login, String senha, String perfil) {
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