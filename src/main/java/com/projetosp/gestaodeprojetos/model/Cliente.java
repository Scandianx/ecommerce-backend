package com.projetosp.gestaodeprojetos.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name= "generator_cliente", sequenceName="sequence_cliente", initialValue= 1, allocationSize= 1)
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_cliente")
    private Integer id;
    @Column(nullable=false)
    private String nome;
    @Column(nullable=false)
    private String telefone;
    @Column(nullable=false)
    private String cpf;
    @Column(nullable=false)
    private String email;
    @Column(nullable=false)
    private String endereço;
    public Cliente() {
    }
    @OneToMany(mappedBy="cliente")
    @JsonManagedReference(value="client")
    private List<Pedido> pedidos;
    public Cliente(String nome, String telefone, String cpf, String email, String endereço) {
        this.nome = nome;
        this.telefone = telefone;
        this.cpf = cpf;
        this.email = email;
        this.endereço = endereço;
    }
    
    public Cliente(String nome, String telefone, String cpf, String email, String endereço, List<Pedido> pedidos) {
        this.nome = nome;
        this.telefone = telefone;
        this.cpf = cpf;
        this.email = email;
        this.endereço = endereço;
        this.pedidos = pedidos;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEndereço() {
        return endereço;
    }
    public void setEndereço(String endereço) {
        this.endereço = endereço;
    }
    public List<Pedido> getPedidos() {
        return pedidos;
    }
    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
    
}
