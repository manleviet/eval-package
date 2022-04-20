/*
 * eval-package - A Maven package for evaluation
 *
 * Copyright (c) 2022
 *
 *  @author: Viet-Man Le (vietman.le@ist.tugraz.at)
 */

package at.tugraz.ist.ase.eval.test;

import org.chocosolver.solver.constraints.Constraint;

import java.util.List;

public interface ITestCase {
    List<Assignment> getAssignments();

    List<Constraint> getChocoConstraints();
    List<Constraint> getNegChocoConstraints();

    boolean isViolated();
}
