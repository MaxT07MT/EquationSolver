package com.math.equation.solver.service;

import com.math.equation.solver.main.Equation;

import java.util.List;

public interface EquationService {



  Equation findEquationById(long id);

  Equation saveEquation(Equation equation);


  List<Equation> findAll();

  void delete(Equation equation);
}
