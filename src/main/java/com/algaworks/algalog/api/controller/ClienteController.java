package com.algaworks.algalog.api.controller;

import com.algaworks.algalog.api.dto.ClienteDto;
import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;
import com.algaworks.algalog.domain.service.CatalogoClienteService;
import com.algaworks.algalog.mapper.ClienteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CatalogoClienteService catalogoClienteService;

    @Autowired
    private ClienteMapper clienteMapper;

    @GetMapping
    public List<Cliente> listar(){
       return clienteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> getCliente(@PathVariable Long id){
        return clienteRepository.findById(id)
                .map(cliente -> {
                    return ResponseEntity.ok(clienteMapper.entityToDto(cliente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDto salvarCliente(@Valid @RequestBody ClienteDto cliente) {
        return clienteMapper.entityToDto(catalogoClienteService.salvarCliente(clienteMapper.dtoToEntity(cliente)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> atualizarCliente(@Valid @PathVariable Long id, @RequestBody ClienteDto cliente){
        if(!clienteRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        cliente.setId(id);
        cliente = clienteMapper.entityToDto(clienteRepository.save(clienteMapper.dtoToEntity(cliente)));
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id){
        if(!clienteRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        catalogoClienteService.excluirCliente(id);
        return ResponseEntity.noContent().build();
    }

}
