/*
 * eval-package - A Maven package for evaluation
 *
 * Copyright (c) 2021-2022
 *
 * @author: Viet-Man Le (vietman.le@ist.tugraz.at)
 */

package at.tugraz.ist.ase.eval.test.builder;

import at.tugraz.ist.ase.eval.test.TestSuite;
import lombok.NonNull;

import java.io.IOException;
import java.io.InputStream;

public interface ITestSuiteBuildable {
    TestSuite buildTestSuite(@NonNull InputStream is, @NonNull ITestCaseBuildable testCaseBuilder) throws IOException;
}
