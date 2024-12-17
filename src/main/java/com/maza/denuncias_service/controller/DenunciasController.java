package com.maza.denuncias_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.maza.denuncias_service.entity.Denuncia;
import com.maza.denuncias_service.service.DenunciaService;




@RestController
@RequestMapping("/v2/denuncias")
public class DenunciasController {
	@Autowired
    private DenunciaService service;

    // Obtener todos los Denuncias con paginación
    @GetMapping
    public ResponseEntity<List<Denuncia>> findAll(
            @RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        List<Denuncia> registros = service.findAll(page);
        return new ResponseEntity<>(registros, HttpStatus.OK);
    }

    // Obtener un rol por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Denuncia> getById(@PathVariable("id") int id) {
        Denuncia registro = service.findById(id);
        return new ResponseEntity<>(registro, HttpStatus.OK);
    }

    // Crear un nuevo rol
    @PostMapping
    public ResponseEntity<Denuncia> create(@RequestBody Denuncia registro) {
        Denuncia Denuncia = service.Save(registro);
        return new ResponseEntity<>(Denuncia, HttpStatus.CREATED);
    }

    // Actualizar un rol existente
    @PutMapping("/{id}")
    public ResponseEntity<Denuncia> update(@PathVariable("id") int id, @RequestBody Denuncia registro) {
        // Se actualiza el rol
        registro.setId(id);
        Denuncia Denuncia = service.Save(registro);
        return new ResponseEntity<>(Denuncia, HttpStatus.OK);
    }

    // Eliminar un rol por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        service.Delete(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
