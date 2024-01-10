package com.projetosp.gestaodeprojetos.model;

import java.util.Date;
import java.util.List;

public record PedidoResponseDTO(Cliente cliente, Date data, List<ItemPedidoResponseDTO> Itens, String Desconto, double ValorTotal) {
    
}
