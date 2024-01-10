package com.projetosp.gestaodeprojetos.model;

import java.util.Collection;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
@Entity
@SequenceGenerator(name= "generator_usuario", sequenceName="sequence_usuario", initialValue= 1, allocationSize= 1)
public class Usuario implements UserDetails{

@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_usuario")
private Integer id;
@Column(nullable=false, unique = true)
private String login;
@Column(nullable=true)
private String password;
@Column
private UsuarioRole role;
public Usuario() {
}

public Usuario(String login, String password, UsuarioRole role) {
    this.login = login;
    this.password = password;
    this.role= role;
}

public int getId() {
    return id;
}


public void setId(int id) {
    this.id = id;
}

public void setLogin(String email) {
    this.login = email;
}
public void setPassword(String password) {
    this.password = password;
}
//IMPLEMENTACAO USERDATAILS
@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
    if(this.role == UsuarioRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
    else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
}

@Override
public String getPassword() {
 
    return password;
}
@Override
public String getUsername() {
    
    return login;
}
@Override
public boolean isAccountNonExpired() {
    
    return true;
}
@Override
public boolean isAccountNonLocked() {
   
    return true;
}
@Override
public boolean isCredentialsNonExpired() {
   
    return true;
}
@Override
public boolean isEnabled() {
   
    return true;
}



}