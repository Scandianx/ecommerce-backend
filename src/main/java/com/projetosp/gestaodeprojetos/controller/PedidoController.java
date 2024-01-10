package com.projetosp.gestaodeprojetos.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import org.springframework.web.bind.annotation.GetMapping;




@RestController
@RequestMapping("pedido")
public class PedidoController {
    @Autowired
    PedidoRepository rep;
    @Autowired
    ItemPedidoRepository repIP;
    @Autowired
    ClienteRepository repCliente;
    @Autowired
    ProdutoRepository repProduto;

   //@PostMapping()
  // public Pedido adiocinarPedido(@RequestBody Pedido pedido) {
       
       //return rep.save(pedido);
   //}
   @PostMapping()
   public Pedido adiocinarPedidoCliente(@RequestBody Pedido pedido) {
    Pedido p1= pedido;
       
        
        Cliente c1= new Cliente();
        c1= p1.getCliente();
        List<Pedido> listaDePedidos = new ArrayList<>();
        listaDePedidos.add(p1);
        c1.setPedidos(listaDePedidos);
        c1= repCliente.save(c1);
        p1.setCliente(c1);
       
        List<ItemPedido> listaDeIp = new ArrayList<>();
        for(ItemPedido ip: pedido.getItempedidos()){
            Produto z1= ip.getProduto();
             listaDeIp.add(ip);
             z1.setItempedidos(listaDeIp);
            z1=repProduto.save(z1);
            p1=rep.save(p1);
            ip.setPedido(p1);
            ip.setProduto(z1);
            repIP.save(ip);
            
            
        }
       
       
       return p1;
   }
   
   @GetMapping()
   public List<Pedido> obterTodosPedidos() {
       return rep.findAll();
   }
   
}
