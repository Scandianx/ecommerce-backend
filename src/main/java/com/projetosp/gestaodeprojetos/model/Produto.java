package com.projetosp.gestaodeprojetos.model;

import java.util.Date;
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
@SequenceGenerator(name= "generator_prod", sequenceName="sequence_prod", initialValue= 1, allocationSize= 1)
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_prod")
    private Integer id;
    @Column(nullable=false)
    private String nome;
    @Column(nullable=false)
    private Integer estoque;
    @Column(nullable=false)
    private Integer valorCusto;
    @Column(nullable=false)
    private Integer valorVenda;
    @Column(nullable=false)
    private Date dataDeCadastro;
    public List<ItemPedido> getItempedidos() {
        return Itempedidos;
    }

    public void setItempedidos(List<ItemPedido> itempedidos) {
        Itempedidos = itempedidos;
    }

    @Column(nullable=true)
    private String observação;
    @OneToMany(mappedBy="produto")
    @JsonManagedReference(value="product")
    private List<ItemPedido> Itempedidos;

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    

    public void setId(Integer id) {
        this.id = id;
    }

    

    public Produto(String nome, Integer estoque, Integer valorCusto, Integer valorVenda, Date dataDeCadastro) {
        this.nome = nome;
        this.estoque = estoque;
        this.valorCusto = valorCusto;
        this.valorVenda = valorVenda;
        this.dataDeCadastro = dataDeCadastro;
    }

    public Produto(String nome, Integer estoque, Integer valorCusto, Integer valorVenda, Date dataDeCadastro,
            String observação) {
        this.nome = nome;
        this.estoque = estoque;
        this.valorCusto = valorCusto;
        this.valorVenda = valorVenda;
        this.dataDeCadastro = dataDeCadastro;
        this.observação = observação;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    public void setValorCusto(Integer valorCusto) {
        this.valorCusto = valorCusto;
    }

    public void setValorVenda(Integer valorVenda) {
        this.valorVenda = valorVenda;
    }

    public void setDataDeCadastro(Date dataDeCadastro) {
        this.dataDeCadastro = dataDeCadastro;
    }

    public void setObservação(String observação) {
        this.observação = observação;
    }

    public Produto() {
    }

    

    public Integer getEstoque() {
        return estoque;
    }

    public Integer getValorCusto() {
        return valorCusto;
    }

    public Integer getValorVenda() {
        return valorVenda;
    }

    public Date getDataDeCadastro() {
        return dataDeCadastro;
    }

    public String getObservação() {
        return observação;
    }

    

    
    
}
