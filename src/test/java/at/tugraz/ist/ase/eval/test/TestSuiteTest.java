/*
 * eval-package - A Maven package for evaluation
 *
 * Copyright (c) 2021
 *
 * @author: Viet-Man Le (vietman.le@ist.tugraz.at)
 */

package at.tugraz.ist.ase.eval.test;

import at.tugraz.ist.ase.eval.test.fm.FMTestCaseBuilder;
import at.tugraz.ist.ase.eval.test.fm.FMTestSuiteBuilder;
import lombok.Cleanup;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static at.tugraz.ist.ase.common.IOUtils.getInputStream;
import static org.junit.jupiter.api.Assertions.*;

class TestSuiteTest {
    private static TestSuite testSuite;

    @BeforeAll
    static void setUp() throws IOException {
        FMTestSuiteBuilder factory = new FMTestSuiteBuilder();
        FMTestCaseBuilder testCaseFactory = new FMTestCaseBuilder();
        @Cleanup InputStream is = getInputStream(TestSuiteTest.class.getClassLoader(), "FM_10_0_c5_0.testcases");

        testSuite = factory.buildTestSuite(is, testCaseFactory);
    }

    @Test
    void testSize() {
        assertEquals(5, testSuite.size());
    }

    @Test
    public void testToString() {
        String expected = "~gui_builder & uml & sdi & ~mdi\n" +
                "~gui_builder & diagram_builder & ~uml\n" +
                "~interface & ~gui_builder & diagram_builder & ~uml & sdi\n" +
                "~interface & ~diagram_builder & uml & mdi\n" +
                "~mdi & interface";

        assertEquals(expected, testSuite.toString());
    }
}