package com.projetosp.gestaodeprojetos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetosp.gestaodeprojetos.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
