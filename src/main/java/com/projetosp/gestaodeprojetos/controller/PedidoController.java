package com.projetosp.gestaodeprojetos.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetosp.gestaodeprojetos.model.Cliente;
import com.projetosp.gestaodeprojetos.model.ItemPedido;
import com.projetosp.gestaodeprojetos.model.Pedido;
import com.projetosp.gestaodeprojetos.model.Produto;
import com.projetosp.gestaodeprojetos.repository.ClienteRepository;
import com.projetosp.gestaodeprojetos.repository.ItemPedidoRepository;
import com.projetosp.gestaodeprojetos.repository.PedidoRepository;
import com.projetosp.gestaodeprojetos.repository.ProdutoRepository;
import com.projetosp.gestaodeprojetos.service.PedidoService;

import org.springframework.web.bind.annotation.GetMapping;




@RestController
@RequestMapping("pedido")
public class PedidoController {
    
   @Autowired
   private PedidoService pedidoService;
   
   @PostMapping()
   public ResponseEntity<Pedido> adiocinarPedidoCliente(@RequestBody Pedido pedido) {
    var ped= pedidoService.adiocinarPedidoCliente(pedido);
    return ResponseEntity.ok().body(ped);

   }
   
   @GetMapping()
   public ResponseEntity<List<Pedido>> obterTodosPedidos() {
       var ped= pedidoService.obterTodosPedidos();
       return ResponseEntity.ok().body(ped);
   }
   
}
