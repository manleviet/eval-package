/*
 * eval-package - A Maven package for evaluation
 *
 * Copyright (c) 2021-2022
 *
 * @author: Viet-Man Le (vietman.le@ist.tugraz.at)
 */

package at.tugraz.ist.ase.eval.test.builder;

import at.tugraz.ist.ase.eval.test.TestCase;
import lombok.NonNull;

public interface ITestCaseBuildable {
    TestCase buildTestCase(@NonNull String testcase);
}
