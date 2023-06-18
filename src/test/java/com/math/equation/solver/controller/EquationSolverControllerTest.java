package com.math.equation.solver.controller;

import com.math.equation.solver.main.EquationSolver;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EquationSolverControllerTest {

  @Test
  void solveEquation_WithValidEquation_ShouldReturnEquationFormTemplateWithResultAndSaveButton() {

    Model model = mock(Model.class);
    EquationSolverController controller = new EquationSolverController();


    String result = controller.solveEquation("2 + 2 = x", model);


    assertThat(result).isEqualTo("equation-form");
    Mockito.verify(model).addAttribute("equation", "2 + 2 = x");
    Mockito.verify(model).addAttribute("result", "x = 4");
    Mockito.verify(model).addAttribute("showSaveButton", true);
  }

  @Test
  void solveEquation_WithInvalidEquation_ShouldReturnEquationFormTemplateWithError() {

    Model model = mock(Model.class);
    EquationSolverController controller = new EquationSolverController();


    String result = controller.solveEquation("2 +", model);


    assertThat(result).isEqualTo("equation-form");
    Mockito.verify(model).addAttribute("error", "Некорректное уравнение");
  }
}
