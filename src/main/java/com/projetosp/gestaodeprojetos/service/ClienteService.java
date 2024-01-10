package com.projetosp.gestaodeprojetos.service;
import org.springframework.stereotype.Service;

import com.projetosp.gestaodeprojetos.dtos.ClienteRequestDTO;
import com.projetosp.gestaodeprojetos.dtos.ClienteResponseDTO;
import com.projetosp.gestaodeprojetos.dtos.ItemPedidoResponseDTO;
import com.projetosp.gestaodeprojetos.dtos.ListPedidosDTO;

import com.projetosp.gestaodeprojetos.model.Cliente;
import com.projetosp.gestaodeprojetos.model.ItemPedido;
import com.projetosp.gestaodeprojetos.model.Pedido;
import com.projetosp.gestaodeprojetos.repository.ClienteRepository;
import com.projetosp.gestaodeprojetos.repository.PedidoRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ClienteService {
    @Autowired
    ClienteRepository rep;
    @Autowired
    PedidoRepository repPedido;
    public ClienteResponseDTO criarCliente(ClienteRequestDTO c) {
      Cliente cliente = new Cliente(c.nome(), c.telefone(), c.cpf(), c.email(), c.email());
         rep.save(cliente);
         return new ClienteResponseDTO(cliente.getNome(), cliente.getTelefone(), cliente.getEmail(), cliente.getCpf(), cliente.getEndereço(), null);
    }
    public List<ClienteResponseDTO> obterClientes() {
        List<Cliente> listaDeClientes = rep.findAll();
        List<ClienteResponseDTO> listaDeResponseDTO = new ArrayList<>();
        for (Cliente cliente: listaDeClientes){
            listaDeResponseDTO.add(criarResponseDTO(cliente));
        }
        return listaDeResponseDTO;
    }
    private ClienteResponseDTO criarResponseDTO (Cliente cliente){
      String nome= cliente.getNome();
      String telefone= cliente.getTelefone();
      String cpf= cliente.getCpf();
      String email= cliente.getEmail();
      List<ListPedidosDTO> listaPedidos= new ArrayList<>();
      
      for(Pedido pedido: cliente.getPedidos()){
        listaPedidos.add(new ListPedidosDTO(pedido.getId(),pedido.getData(), listarItens(pedido),pedido.getDesconto()+ "%" , pedido.getValorTotal()));
      }
      return new ClienteResponseDTO(nome, telefone, email, cpf, email, listaPedidos);
      

    }
    private List<ItemPedidoResponseDTO> listarItens(Pedido pedido){
        List<ItemPedidoResponseDTO> listaItens= new ArrayList<>();
        for(ItemPedido itemPedido: pedido.getItempedidos()){
          listaItens.add(new ItemPedidoResponseDTO(itemPedido.getProduto().getNome(), itemPedido.getProduto().getValorVenda(), itemPedido.getDescontoUnitario() + "%", itemPedido.getQuantidade()));
        }
        return listaItens;
      }
}
