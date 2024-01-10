package com.projetosp.gestaodeprojetos.service;
import org.springframework.stereotype.Service;


import com.projetosp.gestaodeprojetos.model.Cliente;
import com.projetosp.gestaodeprojetos.model.Pedido;
import com.projetosp.gestaodeprojetos.repository.ClienteRepository;
import com.projetosp.gestaodeprojetos.repository.PedidoRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ClienteService {
    @Autowired
    ClienteRepository rep;
    @Autowired
    PedidoRepository repPedido;
    public Cliente criarCliente(Cliente cliente) {
        if (cliente.getPedidos()!=null){
            rep.save(cliente);
          for (Pedido pedido: cliente.getPedidos()){
            pedido.setCliente(cliente);
            repPedido.save(pedido);
          }
        }
        return rep.save(cliente);
    }
    public List<Cliente> obterClientes() {
        return rep.findAll();
    }
}
