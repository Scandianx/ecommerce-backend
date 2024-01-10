package com.projetosp.gestaodeprojetos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.projetosp.gestaodeprojetos.model.Produto;
import com.projetosp.gestaodeprojetos.repository.ProdutoRepository;

@Service
public class ProdutoService {
     @Autowired
     ProdutoRepository rep;
   public Produto adicionarProduto(Produto produto) {
       
       return rep.save(produto);
   }
   public List<Produto> obterProdutos() {
        return rep.findAll();
    }
}
