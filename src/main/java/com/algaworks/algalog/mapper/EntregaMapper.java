package com.algaworks.algalog.mapper;

import com.algaworks.algalog.api.dto.EntregaDto;
import com.algaworks.algalog.domain.model.Entrega;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EntregaMapper {

    @Autowired
    private ModelMapper modelMapper;

    public EntregaDto entregaToDto(Entrega entrega){
        return modelMapper.map(entrega, EntregaDto.class);
    }

    public List<EntregaDto> entregaListToListDto(List<Entrega> entregaList){
        return entregaList.stream()
                .map(this::entregaToDto)
                .collect(Collectors.toList());
    }

}
