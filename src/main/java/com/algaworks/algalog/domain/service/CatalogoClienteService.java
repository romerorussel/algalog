package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.domain.exception.DomainException;
import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CatalogoClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public Cliente salvarCliente(Cliente cliente){
        boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
                .stream()
                .anyMatch(clienteExistente -> !clienteExistente.equals(cliente));
        if(emailEmUso){
            throw new DomainException("Já existe um cliente cadastro com este email");
        }
        return clienteRepository.save(cliente);
    }

    @Transactional
    public void excluirCliente(Long id){
        clienteRepository.deleteById(id);
    }

    public Cliente buscar(Long id){
        return clienteRepository.findById(id).orElseThrow(() ->  new DomainException("Cliente não encontrado"));
    }
}
