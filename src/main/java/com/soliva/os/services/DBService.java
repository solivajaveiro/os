package com.soliva.os.services;

import com.soliva.os.domain.Cliente;
import com.soliva.os.domain.OS;
import com.soliva.os.domain.Tecnico;
import com.soliva.os.domain.enuns.Prioridade;
import com.soliva.os.domain.enuns.Status;
import com.soliva.os.repositories.ClienteRepository;
import com.soliva.os.repositories.OSRepository;
import com.soliva.os.repositories.Tecnicoepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    private Tecnicoepository tecnicoepository;
    private ClienteRepository clienteRepository;
    private OSRepository osRepository;

    @Autowired
    public DBService(Tecnicoepository tecnicoepository, ClienteRepository clienteRepository, OSRepository osRepository) {
        this.tecnicoepository = tecnicoepository;
        this.clienteRepository = clienteRepository;
        this.osRepository = osRepository;
    }

    public void instanciaDB() {

        Tecnico t1 = new Tecnico(null, "Valdir Cezar", "512.009.540-25", "(21) 98705-6325");
        Tecnico t2 = new Tecnico(null, "Michel Soliva", "641.760.040-88", "(21) 94545-6325");
        Cliente c1 = new Cliente(null, "Betina Campos", "883.515.750-11", "(88) 98888-7777");

        OS os1 = new OS(null, Prioridade.ALTA, "Teste create OD", Status.ANDAMENTO, t1, c1);

        t1.getList().add(os1);
        c1.getList().add(os1);

        tecnicoepository.saveAll(Arrays.asList(t1, t2));
        clienteRepository.saveAll(Arrays.asList(c1));
        osRepository.saveAll(Arrays.asList(os1));
    }
}
