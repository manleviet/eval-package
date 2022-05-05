/*
 * eval-package - A Maven package for evaluation
 *
 * Copyright (c) 2022
 *
 * @author: Viet-Man Le (vietman.le@ist.tugraz.at)
 */

package at.tugraz.ist.ase.eval.test.translator;

import at.tugraz.ist.ase.eval.test.ITestCase;
import org.chocosolver.solver.Model;

public interface ITestCaseTranslatable {
    /**
     * Translates test cases to Choco constraints.
     */
    void translate(ITestCase testCase, Model model);
}
