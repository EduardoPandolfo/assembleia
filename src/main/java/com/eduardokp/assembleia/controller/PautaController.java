package com.eduardokp.assembleia.controller;

import com.eduardokp.assembleia.model.dto.PautaDTO;
import com.eduardokp.assembleia.model.dto.ResponseDTO;
import com.eduardokp.assembleia.services.PautaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller para a entidade Pauta
 *
 * @version v1
 */
@RestController
@RequestMapping("/v1/pauta")
public class PautaController {

    @Autowired
    private PautaService service;

    @PostMapping
    public ResponseEntity<?> post(@RequestBody @Valid PautaDTO pautaDTO) {
        PautaDTO dto = service.create(pautaDTO);
        return new ResponseEntity<>(new ResponseDTO<>(dto.getId(), "Pauta criada com sucesso!"),
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

    /**
     * Retorna dados de votação da pauta, independe se a mesma estiver finalizada ou não
     *
     * @param id codigo da pauta
     * @return DTO com a quantidade de votos positivos e negativos para a pauta
     */
    @GetMapping("/resultado/{id}")
    public ResponseEntity<?> getByResultadoPautaById(@PathVariable Long id) {
        return new ResponseEntity<>(new ResponseDTO<>(service.pesquisarResultadoPauta(id), ""), HttpStatus.OK);
    }
}

