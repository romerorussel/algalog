package com.algaworks.algalog.api.controller;

import com.algaworks.algalog.api.dto.EntregaDto;
import com.algaworks.algalog.api.dto.OcorrenciaDto;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.repository.OcorrenciaRepository;
import com.algaworks.algalog.domain.service.BuscarEntregaService;
import com.algaworks.algalog.domain.service.OcorrenciaService;
import com.algaworks.algalog.mapper.EntregaMapper;
import com.algaworks.algalog.mapper.OcorrenciaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("entregas/{entregaId}/ocorrencias")
public class OcorrenciaController {

    @Autowired
    private OcorrenciaRepository ocorrenciaRepository;

    @Autowired
    private OcorrenciaMapper ocorrenciaMapper;

    @Autowired
    private OcorrenciaService ocorrenciaService;

    @Autowired
    private BuscarEntregaService buscarEntregaService;

    @GetMapping
    public List<OcorrenciaDto> listar(@PathVariable Long entregaId){
        Entrega entrega = buscarEntregaService.buscarEntrega(entregaId);
        return ocorrenciaMapper.OcorrenciaListToListDto(entrega.getOcorrencias());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OcorrenciaDto registrar(@PathVariable Long entregaId , @Valid @RequestBody OcorrenciaDto ocorrencia){
        return ocorrenciaMapper.ocorrenciaToDto(ocorrenciaService.registrar(entregaId, ocorrencia.getDescricao()));
    }

}
