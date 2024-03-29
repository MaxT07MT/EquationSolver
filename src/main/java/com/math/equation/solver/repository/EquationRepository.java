package com.math.equation.solver.repository;

import com.math.equation.solver.main.Equation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface EquationRepository extends JpaRepository<Equation, Long> {

  List<Equation> findAll();
}
