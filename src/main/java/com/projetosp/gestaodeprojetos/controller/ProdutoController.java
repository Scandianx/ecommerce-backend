package com.projetosp.gestaodeprojetos.controller;

import org.springframework.web.bind.annotation.RestController;

import com.projetosp.gestaodeprojetos.dtos.ProdutoRequestDTO;
import com.projetosp.gestaodeprojetos.dtos.ProdutoRequestDtoId;
import com.projetosp.gestaodeprojetos.dtos.ProdutoResponseDTO;
import com.projetosp.gestaodeprojetos.exceptions.ProdutoNaoEncontradoException;


import com.projetosp.gestaodeprojetos.service.ProdutoService;



import java.util.List;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("produtos")
public class ProdutoController {

    @Autowired
    ProdutoService service;
    @PostMapping()
    public ResponseEntity<ProdutoResponseDTO> adicionarProduto(@RequestBody ProdutoRequestDTO produto) {
       
       return ResponseEntity.ok(service.adicionarProduto(produto));
   }
    @GetMapping()
    public ResponseEntity<List<ProdutoResponseDTO>> obterProdutos() {
        return ResponseEntity.ok(service.obterProdutos());
    }
    @PutMapping()
    public ResponseEntity<ProdutoResponseDTO> atualizarProduto(@RequestBody ProdutoRequestDtoId produtoDTO) {
        ProdutoResponseDTO produtoResponseDTO= service.atualizarProduto(produtoDTO);

        if (produtoResponseDTO!=null){
        return ResponseEntity.ok(produtoResponseDTO);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarProduto(@PathVariable Integer id) {
        try {
            service.deletarProduto(id);
            return ResponseEntity.ok("Produto deletado com sucesso");
        } catch (ProdutoNaoEncontradoException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro interno do servidor");
        }
    }
    
    
}
