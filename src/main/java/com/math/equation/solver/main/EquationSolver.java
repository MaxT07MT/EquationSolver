package com.math.equation.solver.main;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.solvers.BisectionSolver;
import org.apache.commons.math3.analysis.solvers.UnivariateSolver;
import org.apache.commons.math3.util.Precision;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
//Класс для решения уравнений , используем библиотеку common.math3, что бы не писать лишнюю логику.
public class EquationSolver {
  public static String solveEquation(String equation) throws IllegalArgumentException, ArithmeticException {
    equation = equation.replace(" ", "");

    String[] parts = equation.split("=");

    if (parts.length != 2) {
      throw new IllegalArgumentException("Некорректное уравнение");
    }

    String leftExpression = parts[0];
    String rightExpression = parts[1];

    if (!leftExpression.contains("x") && !rightExpression.contains("x")) {
      throw new IllegalArgumentException("Некорректное уравнение");
    }

    UnivariateFunction function = x -> {
      double leftResult = evaluateExpression(leftExpression, x);
      double rightResult = evaluateExpression(rightExpression, x);
      return leftResult - rightResult;
    };

    UnivariateSolver solver = new BisectionSolver();
    double result = solver.solve(100, function, -100000, 100000);

    String resultString = formatResult(result);

    return resultString;
  }

  private static double evaluateExpression(String expression, double x) {
    expression = expression.replace("x", Double.toString(x));
    Expression exp = new ExpressionBuilder(expression).build();
    return exp.evaluate();
  }

  private static String formatResult(double result) {
    double roundedResult = Precision.round(result, 2);
    if (isInteger(roundedResult)) {
      return String.format("x = %.0f", roundedResult);
    } else {
      return String.format("x = %.2f", roundedResult);
    }
  }

  private static boolean isInteger(double number) {
    return number == Math.floor(number);
  }
}


