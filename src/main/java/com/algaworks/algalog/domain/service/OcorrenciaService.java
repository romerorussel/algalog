package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.api.dto.OcorrenciaDto;
import com.algaworks.algalog.domain.exception.NotFoundException;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.model.Ocorrencia;
import com.algaworks.algalog.domain.repository.EntregaRepository;
import com.algaworks.algalog.domain.repository.OcorrenciaRepository;
import com.algaworks.algalog.mapper.OcorrenciaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OcorrenciaService {

    @Autowired
    private OcorrenciaRepository ocorrenciaRepository;

    @Autowired
    private OcorrenciaMapper ocorrenciaMapper;

    @Autowired
    private EntregaRepository entregaRepository;

    @Autowired
    private BuscarEntregaService buscarEntregaService;

    public void salvar(OcorrenciaDto ocorrenciaDto){
        ocorrenciaRepository.save(ocorrenciaMapper.ocorrenciaDtoToEntity(ocorrenciaDto));
    }

    @Transactional
    public Ocorrencia registrar(Long entregaId, String descricao){
        Entrega entrega = buscarEntregaService.buscarEntrega(entregaId);
        return entrega.adicionarOcorrencia(descricao);
    }
}
