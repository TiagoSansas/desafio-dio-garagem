package com.sansasdev.garagem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sansasdev.garagem.repositories.ClienteRepository;

@Service
public class AuthorizacaoService implements UserDetailsService {

  @Autowired
  ClienteRepository clienteRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return clienteRepository.findByEmail(username);
  }
}
