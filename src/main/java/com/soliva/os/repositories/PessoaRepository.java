package com.soliva.os.repositories;

import com.soliva.os.domain.Pessoa;
import com.soliva.os.domain.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

    Pessoa findByCpf(String cpf);
}