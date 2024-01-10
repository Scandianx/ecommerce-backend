package com.projetosp.gestaodeprojetos.controller;

import org.springframework.web.bind.annotation.RestController;

import com.projetosp.gestaodeprojetos.model.Produto;

import com.projetosp.gestaodeprojetos.service.ProdutoService;



import java.util.List;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;




@RestController
@RequestMapping("produtos")
public class ProdutoController {

    @Autowired
    ProdutoService service;
    @PostMapping()
    public ResponseEntity<Produto> adicionarProduto(@RequestBody Produto produto) {
       
       return ResponseEntity.ok(service.adicionarProduto(produto));
   }
    @GetMapping()
    public ResponseEntity<List<Produto>> obterProdutos() {
        return ResponseEntity.ok(service.obterProdutos());
    }
    
}
