package com.cristian.labpadroesprojetospring.service.impl;

import com.cristian.labpadroesprojetospring.model.Cliente;
import com.cristian.labpadroesprojetospring.model.Endereco;
import com.cristian.labpadroesprojetospring.repository.ClienteRepository;
import com.cristian.labpadroesprojetospring.repository.EnderecoRepository;
import com.cristian.labpadroesprojetospring.service.ClienteService;
import com.cristian.labpadroesprojetospring.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ViaCepService viaCepService;

    @Override
    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id).isPresent() ? clienteRepository.findById(id).get() : null;
    }

    @Override
    public void inserir(Cliente cliente) {
        salvarClienteComCep(cliente);
    }

    @Override
    public Boolean atualizar(Long id, Cliente cliente) {
        Optional<Cliente> clienteBd = clienteRepository.findById(id);
        if (clienteBd.isPresent()) {
            salvarClienteComCep(cliente);
            return true;
        }
        return false;
    }


    @Override
    public Boolean deletar(Long id) {
        if (clienteRepository.findById(id).isPresent()) {
            clienteRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private void salvarClienteComCep(Cliente cliente) {
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
    }
}
