package com.projetosp.gestaodeprojetos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetosp.gestaodeprojetos.dtos.ClienteRequestDTO;
import com.projetosp.gestaodeprojetos.dtos.ClienteResponseDTO;

import com.projetosp.gestaodeprojetos.service.ClienteService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("cliente")
public class ClienteController {
    @Autowired
    ClienteService service;

    @PostMapping()
    public ResponseEntity<ClienteResponseDTO> criarCliente(@RequestBody ClienteRequestDTO cliente) {
        return ResponseEntity.ok(service.criarCliente(cliente));
    }
    @GetMapping()
    public ResponseEntity<List<ClienteResponseDTO>> obterClientes() {
        return ResponseEntity.ok(service.obterClientes());
    }
    
}
