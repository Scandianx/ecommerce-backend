package com.projetosp.gestaodeprojetos.model;

public record RegisterDTO(String nome, String login, String password, UsuarioRole role) {
}
