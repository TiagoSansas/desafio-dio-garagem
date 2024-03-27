package com.sansasdev.garagem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sansasdev.garagem.entites.Cliente;
import com.sansasdev.garagem.repositories.ClienteRepository;
import com.sansasdev.garagem.services.exceptions.IdNaoLocalizado;

@Service
public class ClienteService {

  @Autowired
  ClienteRepository clienteRepository;

  public Page<Cliente> buscarTodos(Pageable pageable) {
    return clienteRepository.findAll(pageable);
  }

  public Cliente buscarPorId(Long id) {
    return clienteRepository.findById(id).orElseThrow(() -> new IdNaoLocalizado("Usuario não Localizado"));
  }

}
