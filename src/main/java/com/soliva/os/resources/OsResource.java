package com.soliva.os.resources;

import com.soliva.os.dtos.OSDTO;
import com.soliva.os.services.OsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}