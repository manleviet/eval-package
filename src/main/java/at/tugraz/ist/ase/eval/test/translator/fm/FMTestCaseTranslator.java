/*
 * eval-package - A Maven package for evaluation
 *
 * Copyright (c) 2022
 *
 * @author: Viet-Man Le (vietman.le@ist.tugraz.at)
 */

package at.tugraz.ist.ase.eval.test.translator.fm;

//import at.tugraz.ist.ase.debugging.testcases.AggregatedTestCase;
import at.tugraz.ist.ase.eval.test.Assignment;
import at.tugraz.ist.ase.eval.test.ITestCase;
import at.tugraz.ist.ase.eval.test.TestCase;
import at.tugraz.ist.ase.eval.test.translator.ITestCaseTranslatable;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.constraints.nary.cnf.LogOp;
import org.chocosolver.solver.variables.BoolVar;

import static at.tugraz.ist.ase.common.ChocoSolverUtils.getVariable;

public class FMTestCaseTranslator implements ITestCaseTranslatable {
    /**
     * Translates test cases to Choco constraints.
     */
    @Override
    public void translate(ITestCase testCase, Model model) {
        if (testCase instanceof TestCase tc) {
            createTestCase(tc, model);
        }
//        else if (testCase instanceof AggregatedTestCase atc) {
//            for (ITestCase tc : atc.getTestcases()) {
//                createTestCase((TestCase) tc, model);
//            }
//        }
    }

    /**
     * Translates a test case to Choco constraints.
     */
    private void createTestCase(TestCase tc, Model model) {
        int startIdx = model.getNbCstrs();

        LogOp logOp = LogOp.and(); // creates a AND LogOp
        for (Assignment assignment : tc.getAssignments()) { // get each clause
            BoolVar v = (BoolVar) getVariable(model, assignment.getVariable()); // get the corresponding variable
            if (assignment.getValue().equals("true")) { // true
                logOp.addChild(v);
            } else { // false
                logOp.addChild(v.not());
            }
        }
        model.addClauses(logOp); // add the translated constraints to the Choco model
        int lastCstrIdx = model.getNbCstrs();

        // add the translated constraints to the TestCase object
        setConstraintsToTestCase(tc, model, startIdx, lastCstrIdx - 1, false);

        // Negative test cases
        LogOp negLogOp = LogOp.nand(logOp);
        startIdx = model.getNbCstrs();
        model.addClauses(negLogOp);
        lastCstrIdx = model.getNbCstrs();
        setConstraintsToTestCase(tc, model, startIdx, lastCstrIdx - 1, true);
    }

    /**
     * Sets translated Choco constraints to the {@link TestCase} object.
     */
    private void setConstraintsToTestCase(TestCase testCase, Model model, int startIdx, int endIdx, boolean negative) {
        org.chocosolver.solver.constraints.Constraint[] constraints = model.getCstrs();
        int index = startIdx;
        while (index <= endIdx) {
            if (!negative) {
                testCase.addChocoConstraint(constraints[index]);
            } else {
                testCase.addNegChocoConstraint(constraints[index]);
            }
            index++;
        }
    }
}
