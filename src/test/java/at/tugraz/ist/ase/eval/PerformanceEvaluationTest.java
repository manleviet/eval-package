/*
 * eval-package - A Maven package for evaluation
 *
 * Copyright (c) 2021
 *
 * @author: Viet-Man Le (vietman.le@ist.tugraz.at)
 */

package at.tugraz.ist.ase.eval;

import org.testng.annotations.Test;

import static at.tugraz.ist.ase.eval.PerformanceEvaluation.*;

public class PerformanceEvaluationTest {

    public static final String COUNTER_FINDCONFLICT_CALLS = "The number of QX calls:";
    public static final String COUNTER_FASTDIAG_CALLS = "The number of FD calls:";
    public static final String COUNTER_CONSISTENCY_CHECKS = "The number of Consistency checks:";
    public static final String COUNTER_SIZE_CONSISTENCY_CHECKS = "The size of Consistency checks:";
    public static final String COUNTER_UNION_OPERATOR = "The number of union:";
    public static final String COUNTER_ADD_OPERATOR = "The number of add:";

    public static final String TIMER_FIRST = "Time for first:";
    public static final String TIMER_ALL = "Time for all:";

    @Test
    public void testPerformanceEvaluation() {
        PerformanceEvaluation.reset();

        start(TIMER_ALL);
        start(TIMER_FIRST);

        incrementCounter(COUNTER_FINDCONFLICT_CALLS);
        incrementCounter(COUNTER_FINDCONFLICT_CALLS);
        incrementCounter(COUNTER_FINDCONFLICT_CALLS);

        incrementCounter(COUNTER_FASTDIAG_CALLS);
        incrementCounter(COUNTER_FASTDIAG_CALLS);
        incrementCounter(COUNTER_FASTDIAG_CALLS);
        incrementCounter(COUNTER_FASTDIAG_CALLS);
        incrementCounter(COUNTER_FASTDIAG_CALLS);

        incrementCounter(COUNTER_CONSISTENCY_CHECKS, 10);
        incrementCounter(COUNTER_CONSISTENCY_CHECKS, 11);

        incrementCounter(COUNTER_SIZE_CONSISTENCY_CHECKS, 100);
        incrementCounter(COUNTER_SIZE_CONSISTENCY_CHECKS, 101);
        incrementCounter(COUNTER_SIZE_CONSISTENCY_CHECKS, 100);

        incrementCounter(COUNTER_UNION_OPERATOR);
        incrementCounter(COUNTER_UNION_OPERATOR);
        incrementCounter(COUNTER_UNION_OPERATOR);

        incrementCounter(COUNTER_ADD_OPERATOR);
        incrementCounter(COUNTER_ADD_OPERATOR);
        incrementCounter(COUNTER_ADD_OPERATOR);

        stop(TIMER_FIRST);
        stop(TIMER_ALL);

        String results = PerformanceEvaluation.getEvaluationResults();
        System.out.println(results);

        assert getCounter(COUNTER_FINDCONFLICT_CALLS).getValue() == 3;
        assert getCounter(COUNTER_FASTDIAG_CALLS).getValue() == 5;
        assert getCounter(COUNTER_CONSISTENCY_CHECKS).getValue() == 21;
        assert getCounter(COUNTER_SIZE_CONSISTENCY_CHECKS).getValue() == 301;
        assert getCounter(COUNTER_UNION_OPERATOR).getValue() == 3;
        assert getCounter(COUNTER_ADD_OPERATOR).getValue() == 3;
    }
}