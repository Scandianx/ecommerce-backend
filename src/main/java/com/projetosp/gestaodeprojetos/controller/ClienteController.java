package com.projetosp.gestaodeprojetos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetosp.gestaodeprojetos.model.Cliente;
import com.projetosp.gestaodeprojetos.model.Pedido;
import com.projetosp.gestaodeprojetos.repository.ClienteRepository;
import com.projetosp.gestaodeprojetos.repository.PedidoRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("cliente")
public class ClienteController {
    @Autowired
    ClienteRepository rep;
    @Autowired
    PedidoRepository repPedido;

    @PostMapping()
    public Cliente criarCliente(@RequestBody Cliente cliente) {
        if (cliente.getPedidos()!=null){
            rep.save(cliente);
          for (Pedido pedido: cliente.getPedidos()){
            pedido.setCliente(cliente);
            repPedido.save(pedido);
          }
        }
        return rep.save(cliente);
    }
    @GetMapping()
    public List<Cliente> obterClientes() {
        return rep.findAll();
    }
    
}
