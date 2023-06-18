package com.math.equation.solver.service;

import com.math.equation.solver.main.Equation;
import com.math.equation.solver.repository.EquationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquationServiceImpl implements EquationService {

  private final EquationRepository equationRepository;

  @Autowired
  public EquationServiceImpl(EquationRepository equationRepository) {
    this.equationRepository = equationRepository;
  }

  @Override
  public Equation findEquationById(long id) {
    return equationRepository.findById(id).orElse(null);
  }

  @Override
  public Equation saveEquation(Equation equation) {
    return equationRepository.save(equation);
  }

  @Override
  public List<Equation> findAll() {
    return equationRepository.findAll();
  }

  @Override
  public void delete(Equation equation) {
    equationRepository.delete(equation);
  }
}
