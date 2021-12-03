/*
 * eval-package - A Maven package for evaluation
 *
 * Copyright (c) 2021
 *
 * @author: Viet-Man Le (vietman.le@ist.tugraz.at)
 */

package at.tugraz.ist.ase.eval.test;

import lombok.NonNull;

import java.io.IOException;
import java.io.InputStream;

public interface ITestSuiteBuildable {
    TestSuite buildTestSuite(@NonNull InputStream is, @NonNull ITestCaseBuildable testCaseBuilder) throws IOException;
}
