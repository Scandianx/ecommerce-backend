package com.projetosp.gestaodeprojetos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name= "generator_prod", sequenceName="sequence_prod", initialValue= 1, allocationSize= 1)
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_prod")
    private Integer id;
    @Column(nullable=false)
    private String name;
    @Column(nullable=false)
    private Integer price;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Produto(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    public Produto() {
    }

    public Produto(Integer id, String name, Integer price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
    
}
