package br.com.lifeos.gateway.api.dto;

import jakarta.validation.constraints.NotBlank;

public class UserLoginDTO {

    // DTO para login

    @NotBlank(message = "Nome de usuario vazio")
    private String username;

    @NotBlank(message = "Senha vazia")
    private String senha;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
