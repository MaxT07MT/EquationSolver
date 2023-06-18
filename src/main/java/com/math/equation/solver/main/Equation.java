package com.math.equation.solver.main;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
//Здесь будем использовать аанотации Lombok для сокращения стандартного кода
@Entity
@Table(name = "equation_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Equation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;

  @Column(name = "equationBody")
  private String equationBody;
  @Column(name = "equationResult")
  private String equationResult;
}
