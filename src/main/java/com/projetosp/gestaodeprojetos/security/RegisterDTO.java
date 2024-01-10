package com.projetosp.gestaodeprojetos.security;

import com.projetosp.gestaodeprojetos.model.UsuarioRole;

public record RegisterDTO(String nome, String login, String password, UsuarioRole role) {
}
