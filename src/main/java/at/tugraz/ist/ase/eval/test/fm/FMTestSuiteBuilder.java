/*
 * eval-package - A Maven package for evaluation
 *
 * Copyright (c) 2021
 *
 * @author: Viet-Man Le (vietman.le@ist.tugraz.at)
 */

package at.tugraz.ist.ase.eval.test.fm;

import at.tugraz.ist.ase.common.LoggerUtils;
import at.tugraz.ist.ase.eval.test.ITestCaseBuildable;
import at.tugraz.ist.ase.eval.test.ITestSuiteBuildable;
import at.tugraz.ist.ase.eval.test.TestCase;
import at.tugraz.ist.ase.eval.test.TestSuite;
import lombok.Cleanup;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

@Slf4j
public class FMTestSuiteBuilder implements ITestSuiteBuildable {

    @Override
    public TestSuite buildTestSuite(@NonNull InputStream is, @NonNull ITestCaseBuildable testCaseBuilder) throws IOException {
        log.trace("{}Building test suite from input stream >>>", LoggerUtils.tab);
        LoggerUtils.indent();

        @Cleanup BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

        List<TestCase>  testCases = new LinkedList<>();

        br.readLine(); // omit first line

        // Read all test cases
        String line;
        while ((line = br.readLine()) != null) {
            TestCase testCase = testCaseBuilder.buildTestCase(line);
            testCases.add(testCase);
        }

        TestSuite testSuite = TestSuite.builder()
                .testCases(testCases)
                .build();

        LoggerUtils.outdent();
        log.debug("{}<<< Built test suite [testsuite={}]", LoggerUtils.tab, testSuite);
        return testSuite;
    }
}
