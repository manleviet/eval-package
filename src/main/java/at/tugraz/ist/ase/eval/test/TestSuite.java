/*
 * eval-package - A Maven package for evaluation
 *
 * Copyright (c) 2021
 *
 * @author: Viet-Man Le (vietman.le@ist.tugraz.at)
 */

package at.tugraz.ist.ase.eval.test;

import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

/***
 * Represents a test suite, i.e., a list of test cases.
 */
@Builder
@Getter @Setter
@EqualsAndHashCode
public class TestSuite {
    private @NonNull List<TestCase> testCases; // list of test cases

    /**
     * Gets the number of test cases.
     * @return the number of test cases.
     */
    public int size() {
        return this.testCases.size();
    }

    @Override
    public String toString() {
        return testCases.stream()
                .map(TestCase::toString)
                .collect(Collectors.joining("\n"));
    }
}
