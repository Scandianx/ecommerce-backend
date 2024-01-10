package com.projetosp.gestaodeprojetos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetosp.gestaodeprojetos.model.ItemPedido;
@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {
    
}
