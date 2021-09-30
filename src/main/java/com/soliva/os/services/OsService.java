package com.soliva.os.services;

import com.soliva.os.domain.Cliente;
import com.soliva.os.domain.OS;
import com.soliva.os.domain.Tecnico;
import com.soliva.os.domain.enuns.Prioridade;
import com.soliva.os.domain.enuns.Status;
import com.soliva.os.dtos.OSDTO;
import com.soliva.os.repositories.OSRepository;
import com.soliva.os.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OsService {

    private OSRepository osRepository;

    private TecnicoService tecnicoService;

    private ClienteService clienteService;

    @Autowired
    public OsService(OSRepository osRepository, TecnicoService tecnicoService, ClienteService clienteService) {
        this.osRepository = osRepository;
        this.tecnicoService = tecnicoService;
        this.clienteService = clienteService;
    }

    public OS findById(Integer id) {
        Optional<OS> obj = osRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + OS.class.getName()));
    }

    public List<OS> findAll() {
        return osRepository.findAll();
    }

    public OS create(OSDTO obj) {
        return fromDTO(obj);
    }

    public OS update(OSDTO obj) {
        findById(obj.getId());
        return fromDTO(obj);
    }

    private OS fromDTO(OSDTO obj) {
        OS newObj = new OS();
        newObj.setId(obj.getId());
        newObj.setObservacoes(obj.getObservacoes());
        newObj.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
        newObj.setStatus(Status.toEnum(obj.getStatus()));

        Tecnico tec = tecnicoService.findById(obj.getTecnico());

        Cliente cli = clienteService.findById(obj.getCliente());

        newObj.setTecnico(tec);
        newObj.setCliente(cli);

        if(newObj.getStatus().getCod().equals(2)) {
            newObj.setDataFechamento(LocalDateTime.now());
        }

        return osRepository.save(newObj);
    }
}