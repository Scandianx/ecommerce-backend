package com.projetosp.gestaodeprojetos.model;

import java.util.List;

public record PedidoRequestDTO(double desconto, Integer cliente, List<Integer> produtos, List<Double> descontosUnitarios, List<Integer> quantidade) {
    
}
