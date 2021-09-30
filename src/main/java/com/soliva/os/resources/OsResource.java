package com.soliva.os.resources;

import com.soliva.os.dtos.OSDTO;
import com.soliva.os.services.OsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/os")
public class OsResource {

    private OsService osService;

    @Autowired
    public OsResource(OsService osService) {
        this.osService = osService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OSDTO> findById(@PathVariable Integer id) {
        OSDTO obj = new OSDTO(osService.findById(id));
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<List<OSDTO>> findAll() {
        List<OSDTO> list = osService.findAll().stream().map(OSDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<OSDTO> create(@Valid @RequestBody OSDTO obj) {
        obj = new OSDTO(osService.create(obj));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping
    public ResponseEntity<OSDTO> update(@Valid @RequestBody OSDTO obj) {
        obj = new OSDTO(osService.update(obj));
        return ResponseEntity.ok().body(obj);
    }
}