package com.projetosp.gestaodeprojetos.dtos;


import java.util.List;

import com.projetosp.gestaodeprojetos.model.ItemPedido;

public record ProdutoRequestDtoId(Integer id, String nome, Integer estoque,
 Integer valorCusto, Integer valorVenda, List<ItemPedido> lista, String observação) {
    
}
