package com.algaworks.algalog.mapper;

import com.algaworks.algalog.api.dto.OcorrenciaDto;
import com.algaworks.algalog.domain.model.Ocorrencia;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OcorrenciaMapper {

    @Autowired
    private ModelMapper modelMapper;

    public OcorrenciaDto ocorrenciaToDto(Ocorrencia ocorrencia){
        return modelMapper.map(ocorrencia, OcorrenciaDto.class);
    }

    public Ocorrencia ocorrenciaDtoToEntity(OcorrenciaDto ocorrenciaDto){
        return modelMapper.map(ocorrenciaDto, Ocorrencia.class);
    }

    public List<OcorrenciaDto> OcorrenciaListToListDto(List<Ocorrencia> ocorrenciaList){
        return ocorrenciaList.stream()
                .map(this::ocorrenciaToDto)
                .collect(Collectors.toList());
    }

    public List<Ocorrencia> ocorrenciaListDtoToEntityList(List<OcorrenciaDto> listDto){
        return listDto.stream()
                .map(this::ocorrenciaDtoToEntity)
                .collect(Collectors.toList());
    }

}
