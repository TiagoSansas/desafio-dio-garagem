package com.sansasdev.garagem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sansasdev.garagem.entites.AuthteticationDTO;
import com.sansasdev.garagem.entites.Cliente;
import com.sansasdev.garagem.entites.LoginResponseDTO;
import com.sansasdev.garagem.entites.RegisterDTO;
import com.sansasdev.garagem.infra.security.TokenService;
import com.sansasdev.garagem.repositories.ClienteRepository;

@RestController
@RequestMapping(value = "/auth")
public class AutheticationContoller {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private ClienteRepository clienteRepository;

  @Autowired
  TokenService tokenService;

  @PostMapping("/login")
  public ResponseEntity<LoginResponseDTO> login(@RequestBody @Validated AuthteticationDTO data) {

    var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
    var auth = this.authenticationManager.authenticate(usernamePassword);

    var token = tokenService.generateToken((Cliente) auth.getPrincipal());

    return ResponseEntity.ok(new LoginResponseDTO(token));

  }

  // Registra Usuario
  @PostMapping("/register")
  public ResponseEntity<RegisterDTO> register(@RequestBody @Validated RegisterDTO data) {
    if (this.clienteRepository.findByEmail(data.email()) != null) {
      return ResponseEntity.badRequest().build();
    }
    String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

    Cliente newUser = new Cliente(null, data.nome(), data.email(), encryptedPassword, data.role());

    this.clienteRepository.save(newUser);
    return ResponseEntity.ok().build();
  }
}
