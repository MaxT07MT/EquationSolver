package com.math.equation.solver.controller;

import com.math.equation.solver.main.EquationSolver;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
//Контроллер для работы с классом EquationSolver отвечающим за решение уравнений
@Controller
public class EquationSolverController {

  @GetMapping("/equationSolver")
  public String showEquationForm(Model model) {
    model.addAttribute("equation", "");
    return "equation-form";
  }

  @PostMapping("/equationSolver/solveEquation")
  public String solveEquation(@RequestParam("equation") String equation, Model model) {
    try {
      String result = EquationSolver.solveEquation(equation);
      model.addAttribute("equation", equation);
      model.addAttribute("result", result);
      model.addAttribute("showSaveButton", true); // Добавляем атрибут для отображения кнопки сохранения
    } catch (IllegalArgumentException | ArithmeticException e) {
      model.addAttribute("error", e.getMessage());
    }
    return "equation-form";
  }

  @GetMapping("/equationSolver/newEquation")
  public String resetEquation(Model model) {
    model.addAttribute("equation", "");
    return "equation-form";
  }
}



