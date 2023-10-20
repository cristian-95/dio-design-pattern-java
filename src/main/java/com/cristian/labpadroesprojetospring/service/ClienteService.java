package com.cristian.labpadroesprojetospring.service;

import com.cristian.labpadroesprojetospring.model.Cliente;
import org.springframework.stereotype.Service;

@Service
public interface ClienteService {

    Iterable<Cliente> buscarTodos();

    Cliente buscarPorId(Long id);

    void inserir(Cliente cliente);

    Boolean atualizar(Long id, Cliente cliente);

    Boolean deletar(Long id);
}
