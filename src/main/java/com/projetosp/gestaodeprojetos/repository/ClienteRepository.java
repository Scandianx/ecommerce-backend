package com.projetosp.gestaodeprojetos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetosp.gestaodeprojetos.model.Cliente;
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    
}
