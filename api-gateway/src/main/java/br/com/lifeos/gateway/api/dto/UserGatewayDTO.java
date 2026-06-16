package br.com.lifeos.gateway.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.UUID;

public class UserGatewayDTO {

    @NotNull(message = "Id invalido ")
    private UUID id;

    @NotBlank(message = "Nome vazio")
    private String name;

    @NotBlank(message = "Email vazio")
    private String email;

    @NotBlank(message = "Telefone vazio")
    private String telefone;

    @NotBlank(message = "CPF vazio")
    private String cpf;

    private boolean temaEscuro = false;

    private Instant updatedAt = null;


    @NotBlank(message = "sem senha")
    private String senha;

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public boolean isTemaEscuro() {
        return temaEscuro;
    }

    public void setTemaEscuro(boolean temaEscuro) {
        this.temaEscuro = temaEscuro;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }


    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }


}
