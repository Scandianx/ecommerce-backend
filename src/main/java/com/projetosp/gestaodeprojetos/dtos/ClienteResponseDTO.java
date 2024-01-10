package com.projetosp.gestaodeprojetos.dtos;

import java.util.List;

public record ClienteResponseDTO(String nome, String telefone, String email, String cpf, String endereço, List<ListPedidosDTO> pedidos) {
    
}
