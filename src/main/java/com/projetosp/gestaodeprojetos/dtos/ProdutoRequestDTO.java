package com.projetosp.gestaodeprojetos.dtos;

import java.util.Date;

public record ProdutoRequestDTO(String nome, Integer estoque, Integer valorCusto, Integer valorVenda, Date dataDeCadastro, String observação) {
    
}
