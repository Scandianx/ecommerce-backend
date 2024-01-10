package com.projetosp.gestaodeprojetos.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import org.springframework.web.server.ResponseStatusException;

import com.projetosp.gestaodeprojetos.model.Cliente;
import com.projetosp.gestaodeprojetos.model.ItemPedido;
import com.projetosp.gestaodeprojetos.model.ItemPedidoResponseDTO;
import com.projetosp.gestaodeprojetos.model.Pedido;
import com.projetosp.gestaodeprojetos.model.PedidoRequestDTO;
import com.projetosp.gestaodeprojetos.model.PedidoResponseDTO;
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

    public Pedido adicionarPedidoCliente(Pedido pedido) {
        Pedido p1 = pedido;

        Cliente c1 = new Cliente();
        c1 = p1.getCliente();
        List<Pedido> listaDePedidos = new ArrayList<>();
        listaDePedidos.add(p1);
        c1.setPedidos(listaDePedidos);
        c1 = repCliente.save(c1);
        p1.setCliente(c1);

        List<ItemPedido> listaDeIp = new ArrayList<>();
        for (ItemPedido ip : pedido.getItempedidos()) {
            Produto z1 = ip.getProduto();
            listaDeIp.add(ip);
            z1.setItempedidos(listaDeIp);
            z1 = repProduto.save(z1);
            p1 = rep.save(p1);
            ip.setPedido(p1);
            ip.setProduto(z1);
            repIP.save(ip);

        }

        return p1;
    }

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
        return new PedidoResponseDTO(cliente, data, lista, desconto, valorTotal);

    }

    public List<Pedido> obterTodosPedidos() {
        return rep.findAll();
    }
}
