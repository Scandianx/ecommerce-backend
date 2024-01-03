package com.projetosp.gestaodeprojetos.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.projetosp.gestaodeprojetos.model.Usuario;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
 UserDetails findByLogin(String login);
    
    
}
