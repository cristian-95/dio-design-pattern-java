package com.cristian.labpadroesprojetospring.repository;

import com.cristian.labpadroesprojetospring.model.Endereco;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends CrudRepository<Endereco, String> {
}
