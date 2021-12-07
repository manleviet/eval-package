/*
 * eval-package - A Maven package for evaluation
 *
 * Copyright (c) 2021
 *
 * @author: Viet-Man Le (vietman.le@ist.tugraz.at)
 */

package at.tugraz.ist.ase.eval.test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssignmentTest {
    @Test
    public void testAssignment() {
        Assignment assignment = new Assignment("F1", "true");

        System.out.println(assignment);

        assertAll(()-> assertEquals(new Assignment("F1", "true"), assignment),
                ()-> assertEquals("F1", assignment.getVariable()),
                ()-> assertEquals("true", assignment.getValue()));
    }

    @Test
    public void shouldCloneable() {
        Assignment assignment = new Assignment("F1", "true");
        Assignment clone = assignment.clone();

        assertAll(()-> assertEquals(assignment, clone),
                ()-> assertEquals(assignment.getVariable(), clone.getVariable()),
                ()-> assertEquals(assignment.getValue(), clone.getValue()));

        clone.setVariable("F2");
        clone.setValue("false");

        assertAll(() -> assertEquals("true", assignment.getValue()),
                () -> assertEquals("F1", assignment.getVariable()),
                () -> assertEquals("false", clone.getValue()),
                () -> assertEquals("F2", clone.getVariable()));
    }
}