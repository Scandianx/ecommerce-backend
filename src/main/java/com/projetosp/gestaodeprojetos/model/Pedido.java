package com.projetosp.gestaodeprojetos.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "generator_ped", sequenceName = "sequence_ped", initialValue = 1, allocationSize = 1)
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_ped")
    private Integer id;
    @Column(nullable = false)
    private Date data;
    @Column(nullable = true)
    private double desconto;
    @Column(nullable = false)
    private double valorTotal;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @JsonBackReference(value = "client")
    private Cliente cliente;
    @OneToMany(mappedBy = "pedido")
    @JsonManagedReference(value = "ip")
    private List<ItemPedido> Itempedidos;

    public Pedido(Date data, double desconto, double valorTotal) {
        this.data = data;
        this.desconto = desconto;
        this.valorTotal = valorTotal;
    }

    public Integer getId() {
        return id;
    }

    public Pedido(Date data, double desconto, double valorTotal, Cliente cliente, List<ItemPedido> Itempedidos) {
        this.data = data;
        this.desconto = desconto;
        this.valorTotal = valorTotal;
        this.cliente = cliente;
        this.Itempedidos = Itempedidos;
    }

    public Pedido() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public Pedido(Date data, double desconto, double valorTotal, Cliente cliente) {
        this.data = data;
        this.desconto = desconto;
        this.valorTotal = valorTotal;
        this.cliente = cliente;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<ItemPedido> getItempedidos() {
        return Itempedidos;
    }

    public void setItempedidos(List<ItemPedido> itempedidos) {
        Itempedidos = itempedidos;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    
}
