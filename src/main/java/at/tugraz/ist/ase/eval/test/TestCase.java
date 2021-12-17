/*
 * eval-package - A Maven package for evaluation
 *
 * Copyright (c) 2021
 *
 * @author: Viet-Man Le (vietman.le@ist.tugraz.at)
 */

package at.tugraz.ist.ase.eval.test;

import lombok.*;
import org.chocosolver.solver.constraints.Constraint;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/***
 * Represents a test case.
 */
@Builder
@Getter @Setter
@EqualsAndHashCode
public class TestCase implements Cloneable {
    private @NonNull String testcase; // a test case
    private @NonNull List<Assignment> assignments; // the list of assignments
    private List<Constraint> constraints; // the list of Choco constraints which are translated from this test case
    private List<Constraint> negConstraints; // a list of NEGATIVE Choco constraints

    private boolean isViolated; // represents the violation of this test case with the knowledge base

    /**
     * Sets a Choco constraint translated from this test case.
     * @param constraint a Choco constraint
     */
    public void setConstraint(@NonNull Constraint constraint) {
        if (constraints == null) {
            constraints = new LinkedList<>();
        }
        constraints.add(constraint);
    }

    /**
     * Sets a negative Choco constraint
     * @param neg_constraint a Choco constraint
     */
    public void setNegConstraint(@NonNull Constraint neg_constraint) {
        if (negConstraints == null) {
            negConstraints = new LinkedList<>();
        }
        negConstraints.add(neg_constraint);
    }

    // TODO: Constraint from ChocoKB

    @Override
    public String toString() {
        return testcase;
    }

    @Override
    public TestCase clone() {
        try {
            TestCase clone = (TestCase) super.clone();
            // copy assignments, constraints and negConstraints
            List<Assignment> assignments = new ArrayList<>();
            for (Assignment assignment : this.assignments) {
                Assignment cloneAssignment = assignment.clone();
                assignments.add(cloneAssignment);
            }
            clone.setAssignments(assignments);

            List<Constraint> constraints = null;
            if (this.constraints != null) {
                constraints = new ArrayList<>(this.constraints);
            }
            List<Constraint> negConstraints = null;
            if (this.negConstraints != null) {
                negConstraints = new ArrayList<>(this.negConstraints);
            }

            clone.setConstraints(constraints);
            clone.setNegConstraints(negConstraints);
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
