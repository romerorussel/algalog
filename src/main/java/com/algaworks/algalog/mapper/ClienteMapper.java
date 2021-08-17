package com.algaworks.algalog.mapper;

import com.algaworks.algalog.api.dto.ClienteDto;
import com.algaworks.algalog.domain.model.Cliente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClienteMapper {

    @Autowired
    private ModelMapper modelMapper;

    public ClienteDto entityToDto(Cliente cliente){
        return modelMapper.map(cliente, ClienteDto.class);
    }

    public Cliente dtoToEntity(ClienteDto clienteDto){
        return modelMapper.map(clienteDto, Cliente.class);
    }

    public List<ClienteDto> entityListToDtoList(List<Cliente> clienteList){
        return clienteList.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public List<Cliente> dtoListToEntityList(List<ClienteDto> clienteList){
        return clienteList.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }
}
