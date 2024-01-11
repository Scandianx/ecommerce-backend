package com.projetosp.gestaodeprojetos.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.projetosp.gestaodeprojetos.dtos.ProdutoRequestDTO;
import com.projetosp.gestaodeprojetos.dtos.ProdutoRequestDtoId;
import com.projetosp.gestaodeprojetos.dtos.ProdutoResponseDTO;
import com.projetosp.gestaodeprojetos.exceptions.ProdutoNaoEncontradoException;
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
   public List<ProdutoResponseDTO> obterProdutos() {
        List<Produto> listaDeProdutos = rep.findAll();
        List<ProdutoResponseDTO> listaDeProdutosDTO = new ArrayList<>();
        for (Produto produto: listaDeProdutos){
            listaDeProdutosDTO.add(criarResponseProduto(produto));
        }
        return listaDeProdutosDTO;
    }
    private ProdutoResponseDTO criarResponseProduto(Produto produto) {
        String nome = produto.getNome();
        Integer estoque = produto.getEstoque();
        Integer valorCusto = produto.getValorCusto();
        Integer valorVenda = produto.getValorVenda();
        Date dataDeCadastro = produto.getDataDeCadastro();
        String observacao = produto.getObservação();
    
        return new ProdutoResponseDTO(nome, estoque, valorCusto, valorVenda, dataDeCadastro, observacao);
    }
    public ProdutoResponseDTO atualizarProduto(ProdutoRequestDtoId produtoDTO){
         Optional<Produto> produtoOptional = rep.findById(produtoDTO.id());
         if (produtoOptional.isPresent()) {
            Produto produto = produtoOptional.get();
            produto.setEstoque(produtoDTO.estoque());
            produto.setItempedidos(produtoDTO.lista());
            produto.setNome(produtoDTO.nome());
            produto.setObservação(produtoDTO.observação());
            produto.setValorCusto(produtoDTO.valorCusto());
            produto.setValorVenda(produtoDTO.valorVenda());
            rep.save(produto);
            return new ProdutoResponseDTO(produto.getNome(), produto.getEstoque(), produto.getValorCusto(), produto.getValorVenda() ,
             produto.getDataDeCadastro(), produto.getObservação());
         }
         else {
            return null;
         }
    }
    public void deletarProduto(Integer id) {
        Optional<Produto> produtoOptional = rep.findById(id);

        if (produtoOptional.isPresent()) {
            Produto produto = produtoOptional.get();
            rep.delete(produto);
        } else {
            throw new ProdutoNaoEncontradoException("Produto não encontrado");
        }
    }
}


