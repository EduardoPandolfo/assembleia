package com.eduardokp.assembleia.controller;

import com.eduardokp.assembleia.model.dto.ResponseDTO;
import com.eduardokp.assembleia.model.dto.VotoDTO;
import com.eduardokp.assembleia.services.VotoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller para a entidade Voto
 *
 * @version v1
 */
@RestController
@RequestMapping("/v1/voto")
public class VotoController {

    @Autowired
    private VotoService service;

    @PostMapping
    public ResponseEntity<?> post(@RequestBody @Valid VotoDTO votoDTO) {
        VotoDTO dto = service.create(votoDTO);
        return new ResponseEntity<>(new ResponseDTO<>(dto.getId(), "Voto realizado com sucesso!"),
                HttpStatus.CREATED);
    }
}
