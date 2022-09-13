package br.ufpr.dac.bantads.common.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

public class ClienteDTO implements Serializable {
    private UUID id;
    private String nome;
    private String email;
    private String cpf;
    private EnderecoClienteDTO endereco;
    private BigDecimal salario;

    public ClienteDTO() {
        super();
    }

    public ClienteDTO(UUID id, String nome, String email, String cpf, EnderecoClienteDTO endereco, BigDecimal salario) {
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

    public EnderecoClienteDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoClienteDTO endereco) {
        this.endereco = endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }
}
