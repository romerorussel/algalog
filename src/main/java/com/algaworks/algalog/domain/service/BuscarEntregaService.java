package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.domain.exception.NotFoundException;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.repository.EntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuscarEntregaService {

    @Autowired
    private EntregaRepository entregaRepository;

    public Entrega buscarEntrega(Long entregaId){
        return entregaRepository.findById(entregaId).orElseThrow(() -> new NotFoundException("Entrega não encontrada"));
    }
}
