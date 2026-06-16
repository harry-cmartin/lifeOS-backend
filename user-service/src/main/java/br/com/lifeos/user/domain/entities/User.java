package br.com.lifeos.user.domain.entities;

import io.vertx.core.dns.SrvRecord;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;


@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "name",  nullable = false)
    private String name;

    @Column(name = "email",  nullable = false, unique = true)
    private String email;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "tema_escuro")
    private boolean temaEscuro =  false; // Preferência de UI

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_At")
    private Instant updatedAt = null;

    @PrePersist
    public void prePersist() {
        setCreatedAt(Instant.now());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Instant getUploadedAt() {
        return updatedAt;
    }


    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setUploadedAt(Instant uploadedAt) {
        this.updatedAt = uploadedAt;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getNome() {
        return name;
    }

    public void setNome(String nome) {
        this.name = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public boolean isTemaEscuro() {
        return temaEscuro;
    }

    public void setTemaEscuro(boolean temaEscuro) {
        this.temaEscuro = temaEscuro;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return temaEscuro == user.temaEscuro && Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(telefone, user.telefone) && Objects.equals(createdAt, user.createdAt) && Objects.equals(updatedAt, user.updatedAt);
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, telefone, temaEscuro, createdAt, updatedAt);
    }
}
