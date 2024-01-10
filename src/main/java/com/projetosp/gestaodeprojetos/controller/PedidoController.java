package com.projetosp.gestaodeprojetos.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetosp.gestaodeprojetos.dtos.PedidoRequestDTO;
import com.projetosp.gestaodeprojetos.dtos.PedidoResponseDTO;

import com.projetosp.gestaodeprojetos.service.PedidoService;

import org.springframework.web.bind.annotation.GetMapping;




@RestController
@RequestMapping("pedido")
public class PedidoController {
    
   @Autowired
   private PedidoService pedidoService;
   

   @PostMapping("/criar")
   public ResponseEntity<PedidoResponseDTO> adiocinarPedidoClienteDTO(@RequestBody PedidoRequestDTO pedido) {
    var ped= pedidoService.criarPedido(pedido);
    return ResponseEntity.ok().body(ped);

   }

   
   @GetMapping()
   public ResponseEntity<List<PedidoResponseDTO>> obterTodosPedidos() {
       var ped= pedidoService.obterTodosPedidos();
       return ResponseEntity.ok().body(ped);
   }
   
}
