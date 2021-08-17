package com.algaworks.algalog.api.controller;

import com.algaworks.algalog.api.dto.EntregaDto;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.repository.EntregaRepository;
import com.algaworks.algalog.domain.service.EntregaService;
import com.algaworks.algalog.mapper.EntregaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/entregas")
public class EntregaController {

    @Autowired
    private EntregaService entregaService;

    @Autowired
    private EntregaRepository entregaRepository;

    @Autowired
    private EntregaMapper entregaMapper;

    @GetMapping
    public List<EntregaDto> listar(){
        return entregaMapper.entregaListToListDto(entregaRepository.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntregaDto solicitarEntrega(@Valid @RequestBody Entrega entrega){
        return entregaMapper.entregaToDto(entregaService.solicitarEntrega(entrega));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntregaDto> getEntrega(@PathVariable Long id){
        return entregaRepository.findById(id).map(entrega -> {
            return ResponseEntity.ok(entregaMapper.entregaToDto(entrega));
        }).orElse(ResponseEntity.notFound().build());
    }

}
