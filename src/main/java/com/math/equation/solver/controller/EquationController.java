package com.math.equation.solver.controller;

import com.math.equation.solver.service.EquationService;
import com.math.equation.solver.main.Equation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
//стандартный контроллер для CRUD , метод update не используем для предотвращения внесения изменения в уравнения и возможности появления в бд поломаных уравнений
@Controller
@RequestMapping("/equation")
public class EquationController {
  private final EquationService equationService;

  @Autowired
  public EquationController(EquationService equationService) {
    this.equationService = equationService;
  }

  @GetMapping()
  public String allEquation(Model model) {
    model.addAttribute("equation", equationService.findAll());
    return "equation-all";
  }

  @PostMapping("/saveEquation")
  public String saveEquation(
      @RequestParam("equation") String equation,
      @RequestParam("result") String result,
      Model model
  ) {
    Equation savedEquation = new Equation();
    savedEquation.setEquationBody(equation);
    savedEquation.setEquationResult(result);
    equationService.saveEquation(savedEquation);
    model.addAttribute("message", "Уравнение успешно сохранено");
    return "equation-form";
  }

  @GetMapping("/{id}")
  public String getEquationById(@PathVariable("id") long id, Model model) {
    Equation equation = equationService.findEquationById(id);
    if (equation == null) {
      return "equation-not-found";
    }
    model.addAttribute("equation", equation);
    return "equation-byId";
  }

  @PostMapping("/deleteEquation")
  public String deleteEquation(@RequestParam("equationId") long equationId, Model model) {
    Equation equation = equationService.findEquationById(equationId);
    if (equation == null) {
      return "equation-not-found";
    }
    equationService.delete(equation);
    model.addAttribute("message", "Уравнение успешно удалено");
    return "equation-all";
  }
}
