package com.math.equation.solver.controller;

import com.math.equation.solver.main.Equation;
import com.math.equation.solver.repository.EquationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EquationControlControllerTest {

  @Test
  void equationControl_ShouldReturnEquationControlTemplate() {

    EquationRepository equationRepository = mock(EquationRepository.class);
    Model model = mock(Model.class);
    List<Equation> equations = new ArrayList<>();
    equations.add(new Equation(1L, "2 + 2", "4"));
    when(equationRepository.findAll()).thenReturn(equations);
    EquationControlController controller = new EquationControlController(equationRepository);


    String result = controller.equationControl(model);


    assertThat(result).isEqualTo("equation-control");
    Mockito.verify(model).addAttribute("equations", equations.subList(0, 5));
  }

  @Test
  void checkAnswer_WithCorrectAnswer_ShouldReturnEquationControlTemplateWithCorrectFlagTrue() {

    EquationRepository equationRepository = mock(EquationRepository.class);
    Model model = mock(Model.class);
    Equation equation = new Equation(1L, "2 + 2", "4");
    when(equationRepository.findById(1L)).thenReturn(java.util.Optional.of(equation));
    EquationControlController controller = new EquationControlController(equationRepository);


    String result = controller.checkAnswer(1L, "4", model);


    assertThat(result).isEqualTo("equation-control");
    Mockito.verify(model).addAttribute("isCorrect", true);
    Mockito.verify(model).addAttribute("equations", equationRepository.findAll());
  }
}

