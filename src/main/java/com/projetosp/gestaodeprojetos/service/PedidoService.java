package com.projetosp.gestaodeprojetos.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import org.springframework.web.server.ResponseStatusException;

import com.projetosp.gestaodeprojetos.dtos.ClientePedidoResponseDTO;
import com.projetosp.gestaodeprojetos.dtos.ItemPedidoResponseDTO;
import com.projetosp.gestaodeprojetos.dtos.PedidoRequestDTO;
import com.projetosp.gestaodeprojetos.dtos.PedidoResponseDTO;
import com.projetosp.gestaodeprojetos.model.Cliente;
import com.projetosp.gestaodeprojetos.model.ItemPedido;
import com.projetosp.gestaodeprojetos.model.Pedido;
import com.projetosp.gestaodeprojetos.model.Produto;
import com.projetosp.gestaodeprojetos.repository.ClienteRepository;
import com.projetosp.gestaodeprojetos.repository.ItemPedidoRepository;
import com.projetosp.gestaodeprojetos.repository.PedidoRepository;
import com.projetosp.gestaodeprojetos.repository.ProdutoRepository;

@Service
public class PedidoService {
    @Autowired
    PedidoRepository rep;
    @Autowired
    ItemPedidoRepository repIP;
    @Autowired
    ClienteRepository repCliente;
    @Autowired
    ProdutoRepository repProduto;
    @Autowired
    EmailSenderService senderService;

    

    public PedidoResponseDTO criarPedido(PedidoRequestDTO pedido) {
        double precoTotal = 0;
        List<ItemPedido> listaIP = new ArrayList<>();
        Pedido newPedido = new Pedido();
        newPedido.setDesconto(pedido.desconto());
        newPedido.setData(new Date());
        for (int id : pedido.produtos()) {
            Optional<Produto> OptionalProduto = repProduto.findById(id);
            Produto produto = OptionalProduto.get();
            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setProduto(produto);
            listaIP.add(itemPedido);
        }
        for (int i = 0; i < pedido.descontosUnitarios().size(); i++) {
            listaIP.get(i).setDescontoUnitario(pedido.descontosUnitarios().get(i));
            listaIP.get(i).setQuantidade(pedido.quantidade().get(i));
        }

        for (ItemPedido ip : listaIP) {
            double desconto = ip.getDescontoUnitario() / 100.0; 
            double precoProduto = ip.getProduto().getValorVenda();
            double precoComDesconto = precoProduto - (desconto * precoProduto);

            ip.setPreçoFinal(precoComDesconto * ip.getQuantidade());
            precoTotal += ip.getPreçoFinal();
        }

        newPedido.setItempedidos(listaIP);
        if (newPedido.getDesconto() != 0) {
            newPedido.setValorTotal(precoTotal - (precoTotal * newPedido.getDesconto() / 100));
        } else {
            newPedido.setValorTotal(precoTotal);
        }

        Optional<Cliente> Optionalcliente = repCliente.findById(pedido.cliente());
        if (Optionalcliente.isPresent()) {
            Cliente cliente2 = Optionalcliente.get();
            List<Pedido> listaPedido = cliente2.getPedidos();
            listaPedido.add(newPedido);
            cliente2.setPedidos(listaPedido);
            repCliente.save(cliente2);
            newPedido.setCliente(cliente2);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nao encontrado");
        }
        rep.save(newPedido);
        for (ItemPedido ip : listaIP) {
            ip.setPedido(newPedido);
            repIP.save(ip);
        }
        String emailBody= "Olá " + newPedido.getCliente().getNome()+ " seu novo pedido foi criado";
        senderService.sendEmail(newPedido.getCliente().getEmail(),"Seu pedido foi criado!", emailBody);
        return criarResponsePedido(newPedido);
    }

    private PedidoResponseDTO criarResponsePedido(Pedido pedido) {
        Cliente cliente = pedido.getCliente();
        Date data = pedido.getData();
        double valorTotal = pedido.getValorTotal();
        String desconto = pedido.getDesconto() + "%";
        List<ItemPedidoResponseDTO> lista = new ArrayList<>();
        for (ItemPedido ip : pedido.getItempedidos()) {
            lista.add(new ItemPedidoResponseDTO(ip.getProduto().getNome(),
                    ip.getProduto().getValorVenda(),
                    ip.getDescontoUnitario() + "%", ip.getQuantidade()));
        }
        return new PedidoResponseDTO(new ClientePedidoResponseDTO(cliente.getNome(), cliente.getTelefone(), cliente.getEmail(),
         cliente.getCpf(), cliente.getEndereço()), data, lista, desconto, valorTotal);

    }

    public List<PedidoResponseDTO> obterTodosPedidos() {
        List<Pedido> listaDePedidos = rep.findAll();
        List<PedidoResponseDTO> listaDePedidosDTO = new ArrayList<>();
        for (Pedido pedido: listaDePedidos){
            listaDePedidosDTO.add(criarResponsePedido(pedido));
        }
        return listaDePedidosDTO;
    }
    public void deletarPedido(Integer id) {
        Optional<Pedido> pedidoOptional = rep.findById(id);

        if (pedidoOptional.isPresent()) {
            Pedido pedido = pedidoOptional.get();
            rep.delete(pedido);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado");
        }
    }
}
