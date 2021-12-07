/*
 * eval-package - A Maven package for evaluation
 *
 * Copyright (c) 2021
 *
 * @author: Viet-Man Le (vietman.le@ist.tugraz.at)
 */

package at.tugraz.ist.ase.eval.test.fm;

import at.tugraz.ist.ase.common.LoggerUtils;
import at.tugraz.ist.ase.eval.test.Assignment;
import at.tugraz.ist.ase.eval.test.ITestCaseBuildable;
import at.tugraz.ist.ase.eval.test.TestCase;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;

@Slf4j
public class FMTestCaseBuilder implements ITestCaseBuildable {
    public TestCase buildTestCase(@NonNull String testcase) {
        log.debug("{}Building a test case for '{}' >>>", LoggerUtils.tab, testcase);
        LoggerUtils.indent();

        List<Assignment> assignments = splitTestCase(testcase);

        TestCase testCase = TestCase.builder()
                .testcase(testcase)
                .assignments(assignments)
                .build();

        LoggerUtils.outdent();
        log.debug("{}<<< Test case '{}' built", LoggerUtils.tab, testcase);

        return testCase;
    }

    private List<Assignment> splitTestCase(String testcase) {
        List<Assignment> assignments = new LinkedList<>();
        String[] clauses = testcase.split(" & ");

        for (String clause: clauses) {
            String variable;
            String value;
            if (clause.startsWith("~")) {
                value = "false";
                variable = clause.substring(1);
            } else {
                value = "true";
                variable = clause;
            }
            Assignment assignment = Assignment.builder()
                    .variable(variable)
                    .value(value)
                    .build();

            assignments.add(assignment);

            log.trace("{}Assignment '{}' parsed - {}", LoggerUtils.tab, clause, assignment);
        }
        return assignments;
    }
}