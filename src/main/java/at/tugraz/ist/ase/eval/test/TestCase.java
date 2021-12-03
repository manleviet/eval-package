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

    /**
     * Sets a Choco constraint translated from this test case.
     * @param constraint a Choco constraint
     */
    public void setConstraint(@NonNull Constraint constraint) {
        if (constraints == null) {
            constraints = List.of(constraint);
        } else {
            constraints.add(constraint);
        }
    }

    /**
     * Sets a negative Choco constraint
     * @param neg_constraint a Choco constraint
     */
    public void setNegConstraint(@NonNull Constraint neg_constraint) {
        if (negConstraints == null) {
            negConstraints = List.of(neg_constraint);
        } else {
            negConstraints.add(neg_constraint);
        }
    }

    @Override
    public String toString() {
        return testcase;
    }

    @Override
    public TestCase clone() {
        TestCase testCase;
        try {
            testCase = (TestCase) super.clone();
        } catch (CloneNotSupportedException e) {
            testCase = TestCase.builder().build();
        }

        // copy assignments, constraints and negConstraints
        List<Assignment> assignments = new ArrayList<>();
        for (Assignment assignment : this.assignments) {
            Assignment cloneAssignment = assignment.clone();
            assignments.add(cloneAssignment);
        }
        List<Constraint> constraints = null;
        if (this.constraints != null) {
            constraints = new ArrayList<>(this.constraints);
        }
        List<Constraint> negConstraints = null;
        if (this.negConstraints != null) {
            negConstraints = new ArrayList<>(this.negConstraints);
        }

        testCase.setAssignments(assignments);
        testCase.setConstraints(constraints);
        testCase.setNegConstraints(negConstraints);

        return testCase;

//        return TestCase.builder()
//                .testcase(testcase)
//                .assignments(assignments)
//                .constraints(constraints)
//                .negConstraints(negConstraints)
//                .build();
    }
}
