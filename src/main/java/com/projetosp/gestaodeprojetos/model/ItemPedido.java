package com.projetosp.gestaodeprojetos.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name= "generator_ip", sequenceName="sequence_ip", initialValue= 1, allocationSize= 1)
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_ip")
    private Integer id;
    @Column(nullable=false)
    private Integer quantidade;
    @Column(nullable=false)
    private Double preçoFinal;
    @Column(nullable=true)
    private Double descontoUnitario;
    @ManyToOne
    @JoinColumn(name="pedido_id")
    @JsonBackReference(value="ip")
    private Pedido pedido;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
    public Double getPreçoFinal() {
        return preçoFinal;
    }
    public void setPreçoFinal(Double preçoFinal) {
        this.preçoFinal = preçoFinal;
    }
    public Double getDescontoUnitario() {
        return descontoUnitario;
    }
    public void setDescontoUnitario(Double descontoUnitario) {
        this.descontoUnitario = descontoUnitario;
    }
    public Pedido getPedido() {
        return pedido;
    }
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    public Produto getProduto() {
        return produto;
    }
    public ItemPedido(Integer quantidade, Double preçoFinal, Double descontoUnitario) {
        this.quantidade = quantidade;
        this.preçoFinal = preçoFinal;
        this.descontoUnitario = descontoUnitario;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    public ItemPedido() {
    }
    @ManyToOne
    @JoinColumn(name="produto_id")
    @JsonBackReference(value="product")
    private Produto produto;
    public ItemPedido(Integer quantidade, Double preçoFinal, Double descontoUnitario, Pedido pedido,
            Produto produto) {
        this.quantidade = quantidade;
        this.preçoFinal = preçoFinal;
        this.descontoUnitario = descontoUnitario;
        this.pedido = pedido;
        this.produto = produto;
    }
    public ItemPedido(Integer quantidade, Double preçoFinal, Double descontoUnitario, Produto produto) {
        this.quantidade = quantidade;
        this.preçoFinal = preçoFinal;
        this.descontoUnitario = descontoUnitario;
        this.produto = produto;
    }

}
