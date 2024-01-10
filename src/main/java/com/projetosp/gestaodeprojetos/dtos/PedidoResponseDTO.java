package com.projetosp.gestaodeprojetos.dtos;

import java.util.Date;
import java.util.List;



public record PedidoResponseDTO(ClientePedidoResponseDTO cliente, Date data, List<ItemPedidoResponseDTO> Itens, String Desconto, double ValorTotal) {
    
}
