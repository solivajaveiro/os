package com.soliva.os.services;

import com.soliva.os.domain.Cliente;
import com.soliva.os.domain.Pessoa;
import com.soliva.os.dtos.ClienteDTO;
import com.soliva.os.repositories.ClienteRepository;
import com.soliva.os.repositories.PessoaRepository;
import com.soliva.os.services.exceptions.DataIntegratyViolationException;
import com.soliva.os.services.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;

    private PessoaRepository pessoaRepository;


    public ClienteService(ClienteRepository clienteRepository, PessoaRepository pessoaRepository) {
        this.clienteRepository = clienteRepository;
        this.pessoaRepository = pessoaRepository;
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente findById(Integer id) {
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }

    public Cliente create(ClienteDTO objDTO) {
        if(findByCPF(objDTO) != null ) {
            throw new DataIntegratyViolationException("CPF ja cadastrado na base de dados! ");
        }
        return clienteRepository.save(new Cliente(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone()));
    }

    public Cliente update(Integer id, ClienteDTO objDTO) {
        Cliente oldObj = findById(id);

        if(findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id) {
            throw new DataIntegratyViolationException("CPF ja cadastrado na base de dados! ");
        }
        oldObj.setNome(objDTO.getNome());
        oldObj.setCpf(objDTO.getCpf());
        oldObj.setTelefone(objDTO.getTelefone());

        return clienteRepository.save(oldObj);
    }

    public void delete(Integer id) {
        Cliente obj = findById(id);
        if(obj.getList().size() > 0) {
            throw new DataIntegratyViolationException("Pessoa possui Ordens de Serviços, não pode ser deletado! ");
        }
        clienteRepository.deleteById(id);
    }

    private Pessoa findByCPF(ClienteDTO objDTO) {
        Pessoa obj = pessoaRepository.findByCpf(objDTO.getCpf());
        if(obj != null) {
            return obj;
        }
        return null;
    }
}