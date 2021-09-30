package com.soliva.os.services;

import com.soliva.os.domain.OS;
import com.soliva.os.repositories.OSRepository;
import com.soliva.os.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OsService {

    private OSRepository osRepository;

    @Autowired
    public OsService(OSRepository osRepository) {
        this.osRepository = osRepository;
    }

    public OS findById(Integer id) {
        Optional<OS> obj = osRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + OS.class.getName()));
    }

    public List<OS> findAll() {
        return osRepository.findAll();
    }
}
