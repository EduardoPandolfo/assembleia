package com.eduardokp.assembleia.controller;

import com.eduardokp.assembleia.model.dto.ResponseDTO;
import com.eduardokp.assembleia.model.dto.UsuarioDTO;
import com.eduardokp.assembleia.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller para a entidade Usuario
 *
 * @version v1
 */
@RestController
@RequestMapping("/v1/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    public ResponseEntity<?> post(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        UsuarioDTO dto = service.create(usuarioDTO);
        return new ResponseEntity<>(new ResponseDTO<>(dto.getId(), "Usuário criado com sucesso!"),
                HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return new ResponseEntity<>(new ResponseDTO<>(service.getById(id), ""), HttpStatus.OK);
    }


    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(new ResponseDTO<>(service.getAll(), ""), HttpStatus.OK);
    }
}
