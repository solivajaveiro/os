package com.soliva.os.repositories;

import com.soliva.os.domain.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Tecnicoepository extends JpaRepository<Tecnico, Integer> {

//    @Query("SELECT obj FROM Tecnico obj where obj.cpf =:cpf")
//    Tecnico findByCPF(@Param("cpf") String cpf);

    Tecnico findByCpf(String cpf);
}