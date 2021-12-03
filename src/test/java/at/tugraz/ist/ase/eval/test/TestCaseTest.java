/*
 * eval-package - A Maven package for evaluation
 *
 * Copyright (c) 2021
 *
 * @author: Viet-Man Le (vietman.le@ist.tugraz.at)
 */

package at.tugraz.ist.ase.eval.test;

import at.tugraz.ist.ase.eval.test.fm.FMTestCaseBuilder;
import lombok.Cleanup;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

import static at.tugraz.ist.ase.common.IOUtils.getInputStream;
import static org.junit.jupiter.api.Assertions.*;

class TestCaseTest {
    static List<TestCase> testsuite;

    @BeforeAll
    static void setup() throws IOException {
        testsuite = new LinkedList<>();
        FMTestCaseBuilder builder = new FMTestCaseBuilder();

        @Cleanup InputStream is = getInputStream(TestCaseTest.class.getClassLoader(), "FM_10_0_c5_0.testcases");

        @Cleanup BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

        String line = br.readLine(); // read the first line and ignore it

        // Read all test cases
        while ((line = br.readLine()) != null) {
            TestCase testCase = builder.buildTestCase(line);
            testsuite.add(testCase);
        }
    }

    @Test
    public void testTestCase () {
        assertAll(() -> assertEquals(5, testsuite.size()),
                () -> assertEquals("~gui_builder & uml & sdi & ~mdi", testsuite.get(0).getTestcase()),
                () -> assertEquals("~gui_builder & diagram_builder & ~uml", testsuite.get(1).getTestcase()),
                () -> assertEquals("~interface & ~gui_builder & diagram_builder & ~uml & sdi", testsuite.get(2).getTestcase()),
                () -> assertEquals("~interface & ~diagram_builder & uml & mdi", testsuite.get(3).getTestcase()),
                () -> assertEquals("~mdi & interface", testsuite.get(4).getTestcase()),
                () -> assertEquals("gui_builder", testsuite.get(0).getAssignments().get(0).getVariable()),
                () -> assertEquals("false", testsuite.get(0).getAssignments().get(0).getValue()),
                () -> assertEquals("uml", testsuite.get(0).getAssignments().get(1).getVariable()),
                () -> assertEquals("true", testsuite.get(0).getAssignments().get(1).getValue()),
                () -> assertEquals("sdi", testsuite.get(0).getAssignments().get(2).getVariable()),
                () -> assertEquals("true", testsuite.get(0).getAssignments().get(2).getValue()),
                () -> assertEquals("mdi", testsuite.get(0).getAssignments().get(3).getVariable()),
                () -> assertEquals("false", testsuite.get(0).getAssignments().get(3).getValue()),
                () -> assertEquals("~gui_builder & diagram_builder & ~uml", testsuite.get(1).toString()));

        assertNull(testsuite.get(0).getConstraints());
        assertNull(testsuite.get(0).getNegConstraints());
    }

    @Test
    public void shouldCloneable() {
        TestCase tc = testsuite.get(0);
        assertDoesNotThrow(tc::clone);

        TestCase tc2 = tc.clone();

        assertAll(() -> assertEquals(tc.getTestcase(), tc2.getTestcase(), "Testcase"),
                () -> assertEquals(tc.getAssignments().get(0), tc2.getAssignments().get(0), "Assignments"));
    }
}