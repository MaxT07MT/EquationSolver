package com.math.equation.solver.controller;

import com.math.equation.solver.main.Equation;
import com.math.equation.solver.repository.EquationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@Controller
public class EquationControlController {

  private final EquationRepository equationRepository;

  @Autowired
  public EquationControlController(EquationRepository equationRepository) {
    this.equationRepository = equationRepository;
  }

  @GetMapping("/equationControl")
  public String equationControl(Model model) {
    List<Equation> equations = equationRepository.findAll();
    Collections.shuffle(equations);
    List<Equation> randomEquations = equations.subList(0, 5); // Получаем 5 случайных уравнений из нашей БД
    model.addAttribute("equations", randomEquations);
    return "equation-control";
  }

  @PostMapping("/equationControl/checkAnswer")
  public String checkAnswer(@RequestParam("equationId") Long equationId,
      @RequestParam("answer") String answer,
      Model model) {
    Equation equation = equationRepository.findById(equationId).orElse(null);
    String equationResult = equation != null ? equation.getEquationResult() : null;
    boolean isCorrect = equationResult != null && equationResult.equals(answer);

    model.addAttribute("equationId", equationId);
    model.addAttribute("equationResult", equationResult);
    model.addAttribute("isCorrect", isCorrect);
    model.addAttribute("equations", equationRepository.findAll());
    return "equation-control";
  }
}

