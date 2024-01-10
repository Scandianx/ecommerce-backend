package com.projetosp.gestaodeprojetos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetosp.gestaodeprojetos.dtos.ProdutoRequestDTO;
import com.projetosp.gestaodeprojetos.dtos.ProdutoResponseDTO;
import com.projetosp.gestaodeprojetos.model.Produto;
import com.projetosp.gestaodeprojetos.repository.ProdutoRepository;

@Service
public class ProdutoService {
     @Autowired
     ProdutoRepository rep;
   public ProdutoResponseDTO adicionarProduto(ProdutoRequestDTO produto) {
       Produto produto2= new Produto(produto.nome(), produto.estoque(), produto.valorCusto(), produto.valorVenda(), produto.dataDeCadastro(), produto.observação());
       rep.save(produto2);
       return new ProdutoResponseDTO(produto2.getNome(), produto2.getEstoque(), produto2.getValorCusto(), produto2.getValorVenda(), produto2.getDataDeCadastro(), produto2.getObservação());
   }
   public List<Produto> obterProdutos() {
        return rep.findAll();
    }
}
