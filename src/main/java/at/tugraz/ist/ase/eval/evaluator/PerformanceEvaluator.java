/*
 * eval-package - A Maven package for evaluation
 *
 * Copyright (c) 2021-2022
 *
 * @author: Viet-Man Le (vietman.le@ist.tugraz.at)
 */

package at.tugraz.ist.ase.eval.evaluator;

import at.tugraz.ist.ase.common.LoggerUtils;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class PerformanceEvaluator {

    public static boolean showEvaluation = false;

    private static ConcurrentHashMap<String, Counter> counters = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, Timer> timers = new ConcurrentHashMap<>();

    /**
     * Returns a counter with the given name. If counter does not exist, it will be created by the method and added
     * to the set of counters
     *
     * @param name of the counter
     * @return a counter
     */
    public static Counter getCounter(String name) {
        return counters.computeIfAbsent(name, (key) -> new Counter(name));
    }

    /**
     * Returns a timer with the given name. If timer does not exist, it will be created by the method and added to
     * the set of timers
     *
     * @param name of the timer
     * @return a timer
     */
    public static Timer getTimer(String name) {
        return timers.computeIfAbsent(name, (key) -> new Timer(name));
    }

    /**
     * Increments a counter and returns its new value
     *
     * @param name of the counter
     * @return new value of the counter
     */
    public static long incrementCounter(String name) {
        return incrementCounter(name, 1);
    }

    /**
     * Increments counter to a given number of steps
     *
     * @param name of the counter
     * @param step to increment the counter
     * @return new value of the counter
     */
    public static long incrementCounter(String name, int step) {
        return getCounter(name).increment(step);
    }

    /**
     * Starts a timer with the given name
     *
     * @param name of the counter
     */
    public static void start(String name) {
        getTimer(name).start();
    }

    /**
     * Stops a timer with the given name
     *
     * @param name of the timer
     * @return elapsed time since the timer was started
     */
    public static long stop(String name) {
        return getTimer(name).stop();
    }

    /**
     * @param name of the timer
     * @return the total time that the timer was running
     */
    public static long total(String name) {
        return getTimer(name).total();
    }

    /**
     * @return an unmodifiable map of counters
     */
    public static Map<String, Counter> getCounters() {
        return Collections.unmodifiableMap(counters);
    }

    /**
     * @return an unmodifiable map of timers
     */
    public static Map<String, Timer> getTimers() {
        return Collections.unmodifiableMap(timers);
    }

    /**
     * Reinitialize all existing counters.
     */
    public static void reset() {
        counters = new ConcurrentHashMap<>();
        timers = new ConcurrentHashMap<>();

        log.debug("{}Reset PerformanceEvaluator", LoggerUtils.tab);
    }

    /**
     * Get evaluation results in the format of a string.
     * @return a string of evaluation results.
     */
    public static String getEvaluationResults() {
        StringBuilder st = new StringBuilder();

        for (String key: counters.keySet()) {
            st.append(key).append(" ").append(getCounter(key)).append("\n");
        }

        st.append("\n");

        for (String key: timers.keySet()) {
            st.append(key).append(" ").append(getTimer(key)).append("\n");

            List<Long> times = getTimer(key).getTimings();

            times.forEach(time -> st.append((double)time / 1000000000.0).append(" "));

            st.append("\n");
        }

        return st.toString();
    }

    /**
     * Get average results after the number of iterations in the format of a string.
     * @param numIteration the number of iterations
     * @return a string of evaluation results.
     */
    public static String getEvaluationResults(int numIteration) {
        StringBuilder st = new StringBuilder();

        for (String key: counters.keySet()) {
            st.append(key).append(" ").append(getCounter(key).getValue() / numIteration).append("\n");
        }

        st.append("\n");

        for (String key: timers.keySet()) {
            st.append(key).append(" ").append((double)getTimer(key).total() / 1000000000.0 / numIteration).append("\n");
        }

        return st.toString();
    }
}
