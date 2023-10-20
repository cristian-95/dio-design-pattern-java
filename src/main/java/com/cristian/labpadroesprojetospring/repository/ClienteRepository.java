package com.cristian.labpadroesprojetospring.repository;

import com.cristian.labpadroesprojetospring.model.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {
}
