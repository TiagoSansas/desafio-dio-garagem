package com.sansasdev.garagem.entites;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_cliente")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Cliente {
  private Long id;

  private String nome;

  @Column(unique = true)
  private String email;

  private String password;

}
