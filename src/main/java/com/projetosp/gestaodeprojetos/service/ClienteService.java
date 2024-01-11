package com.projetosp.gestaodeprojetos.service;

import org.springframework.stereotype.Service;

import com.projetosp.gestaodeprojetos.dtos.ClienteRequestDTO;
import com.projetosp.gestaodeprojetos.dtos.ClienteRequestDtoId;
import com.projetosp.gestaodeprojetos.dtos.ClienteResponseDTO;
import com.projetosp.gestaodeprojetos.dtos.ItemPedidoResponseDTO;
import com.projetosp.gestaodeprojetos.dtos.ListPedidosDTO;
import com.projetosp.gestaodeprojetos.exceptions.ClienteNaoEncontradoException;
import com.projetosp.gestaodeprojetos.model.Cliente;
import com.projetosp.gestaodeprojetos.model.ItemPedido;
import com.projetosp.gestaodeprojetos.model.Pedido;
import com.projetosp.gestaodeprojetos.repository.ClienteRepository;
import com.projetosp.gestaodeprojetos.repository.PedidoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    return new ClienteResponseDTO(cliente.getNome(), cliente.getTelefone(), cliente.getEmail(), cliente.getCpf(),
        cliente.getEndereço(), null);
  }

  public List<ClienteResponseDTO> obterClientes() {
    List<Cliente> listaDeClientes = rep.findAll();
    List<ClienteResponseDTO> listaDeResponseDTO = new ArrayList<>();
    for (Cliente cliente : listaDeClientes) {
      listaDeResponseDTO.add(criarResponseDTO(cliente));
    }
    return listaDeResponseDTO;
  }

  public void deletarCliente(Integer id) {
    Optional<Cliente> clienteOptional = rep.findById(id);

    if (clienteOptional.isPresent()) {
      Cliente cliente = clienteOptional.get();
      rep.delete(cliente);
    } else {
      throw new ClienteNaoEncontradoException("Cliente não encontrado");
    }
  }

  public ClienteResponseDTO atualizarCliente(ClienteRequestDtoId novoClienteDTO) {
    Optional<Cliente> clienteOptional = rep.findById(novoClienteDTO.id());

    if (clienteOptional.isPresent()) {
      Cliente clienteExistente = clienteOptional.get();
      clienteExistente.setNome(novoClienteDTO.nome());
      clienteExistente.setTelefone(novoClienteDTO.telefone());
      clienteExistente.setCpf(novoClienteDTO.cpf());
      clienteExistente.setEmail(novoClienteDTO.email());
      clienteExistente.setEndereço(novoClienteDTO.endereço());

      Cliente clienteAtualizado = rep.save(clienteExistente);
      return criarResponseDTO(clienteAtualizado);
    } else {
      throw new ClienteNaoEncontradoException("Cliente não encontrado");
    }
  }

  private ClienteResponseDTO criarResponseDTO(Cliente cliente) {
    String nome = cliente.getNome();
    String telefone = cliente.getTelefone();
    String cpf = cliente.getCpf();
    String email = cliente.getEmail();
    List<ListPedidosDTO> listaPedidos = new ArrayList<>();

    for (Pedido pedido : cliente.getPedidos()) {
      listaPedidos.add(new ListPedidosDTO(pedido.getId(), pedido.getData(), listarItens(pedido),
          pedido.getDesconto() + "%", pedido.getValorTotal()));
    }
    return new ClienteResponseDTO(nome, telefone, email, cpf, email, listaPedidos);

  }

  private List<ItemPedidoResponseDTO> listarItens(Pedido pedido) {
    List<ItemPedidoResponseDTO> listaItens = new ArrayList<>();
    for (ItemPedido itemPedido : pedido.getItempedidos()) {
      listaItens.add(new ItemPedidoResponseDTO(itemPedido.getProduto().getNome(),
          itemPedido.getProduto().getValorVenda(), itemPedido.getDescontoUnitario() + "%", itemPedido.getQuantidade()));
    }
    return listaItens;
  }

}
