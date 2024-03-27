package com.sansasdev.garagem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sansasdev.garagem.entites.Cliente;
import com.sansasdev.garagem.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

  @Autowired
  ClienteService clienteService;

  @GetMapping("/{id}")
  public ResponseEntity<Cliente> buscarPorId(Long id) {
    Cliente cliente = clienteService.buscarPorId(id);
    return ResponseEntity.ok(cliente);
  }

  @GetMapping
  public ResponseEntity<Page<Cliente>> buscarTodos(Pageable pageable) {
    Page<Cliente> clientes = clienteService.buscarTodos(pageable);
    return ResponseEntity.ok(clientes);
  }

  @PostMapping
  public ResponseEntity<?> registrar(@RequestBody Cliente cliente) {
    try {
      this.clienteService.registar(cliente);
      return ResponseEntity.status(201).body(cliente);
    } catch (Exception e) {
      return ResponseEntity.status(500).body("Erro ao processar requisição" + e.getMessage());
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
    clienteService.atualizar(id, cliente);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletar(Long id) {
    clienteService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
