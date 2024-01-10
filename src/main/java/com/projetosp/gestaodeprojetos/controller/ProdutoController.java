package com.projetosp.gestaodeprojetos.controller;

import org.springframework.web.bind.annotation.RestController;

import com.projetosp.gestaodeprojetos.model.Produto;
import com.projetosp.gestaodeprojetos.repository.ProdutoRepository;

import java.util.List;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;




@RestController
@RequestMapping("produtos")
public class ProdutoController {
    @Autowired
   ProdutoRepository rep;
   @PostMapping()
   public Produto adicionarProduto(@RequestBody Produto produto) {
       
       return rep.save(produto);
   }
    @GetMapping()
    public List<Produto> obteProdutos() {
        return rep.findAll();
    }
    
}
