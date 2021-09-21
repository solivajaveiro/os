package com.soliva.os.services;

import com.soliva.os.domain.Pessoa;
import com.soliva.os.domain.Tecnico;
import com.soliva.os.dtos.TecnicoDTO;
import com.soliva.os.repositories.PessoaRepository;
import com.soliva.os.repositories.Tecnicoepository;
import com.soliva.os.services.exceptions.DataIntegratyViolationException;
import com.soliva.os.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    private Tecnicoepository tecnicorepository;

    private PessoaRepository pessoaRepository;

    public TecnicoService(Tecnicoepository tecnicorepository, PessoaRepository pessoaRepository) {
        this.tecnicorepository = tecnicorepository;
        this.pessoaRepository = pessoaRepository;
    }

    @Autowired


    public List<Tecnico> findAll() {
        return tecnicorepository.findAll();
    }

    public Tecnico findById(Integer id) {
        Optional<Tecnico> obj = tecnicorepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Tecnico.class.getName()));
    }

    public Tecnico create(TecnicoDTO objDTO) {
        if(findByCPF(objDTO) != null ) {
            throw new DataIntegratyViolationException("CPF ja cadastrado na base de dados! ");
        }
        return tecnicorepository.save(new Tecnico(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone()));
    }

    public Tecnico update(Integer id, TecnicoDTO objDTO) {
        Tecnico oldObj = findById(id);

        if(findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id) {
            throw new DataIntegratyViolationException("CPF ja cadastrado na base de dados! ");
        }
        oldObj.setNome(objDTO.getNome());
        oldObj.setCpf(objDTO.getCpf());
        oldObj.setTelefone(objDTO.getTelefone());

        return tecnicorepository.save(oldObj);
    }

    public void delete(Integer id) {
        Tecnico obj = findById(id);
        if(obj.getList().size() > 0) {
            throw new DataIntegratyViolationException("Tecnico possui Ordens de Serviços, não pode ser deletado! ");
        }
        tecnicorepository.deleteById(id);
    }

    private Pessoa findByCPF(TecnicoDTO objDTO) {
        Pessoa obj = pessoaRepository.findByCpf(objDTO.getCpf());
        if(obj != null) {
            return obj;
        }
        return null;
    }
}