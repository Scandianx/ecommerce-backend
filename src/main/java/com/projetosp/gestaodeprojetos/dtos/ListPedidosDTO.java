package com.projetosp.gestaodeprojetos.dtos;

import java.util.Date;
import java.util.List;

public record ListPedidosDTO(Integer id, Date Data, List<ItemPedidoResponseDTO> Itens, String desconto, double ValorTotal ) {
    
}
