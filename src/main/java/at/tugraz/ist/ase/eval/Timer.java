/*
 * eval-package - A Maven package for evaluation
 *
 * Copyright (c) 2021
 *
 * @author: Viet-Man Le (vietman.le@ist.tugraz.at)
 */

package at.tugraz.ist.ase.eval;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;

import static com.google.common.base.Preconditions.checkState;
import static java.util.Collections.unmodifiableList;

/**
 * Simple class for measuring time in experiments. The timings of time measurements are stored in an array and can be
 * retrieved using getTimings method.
 */
@Slf4j
public class Timer extends AbstractEvaluator {

    private final List<Long> timings = new LinkedList<>();
    private long time = 0;
    private boolean running = false;

    Timer(String name) {
        super(name);

        log.info("Timer for '{}' created", name);
    }

    /**
     * Start the timer.
     */
    public void start() {
        checkState(!this.running, "The timer \"%s\" is already running!", this.name);
        this.running = true;
        this.time = System.nanoTime();

        log.debug("Timer for '{}' started.", name);
    }

    /**
     * Stop the timer.
     *
     * @return return the time elapsed since the start in nanoseconds.
     */
    public long stop() {
        this.time = getElapsedTime();
        this.running = false;
        this.timings.add(this.time);

        log.debug("Timer for '{}' stopped.", name);

        return this.time;
    }

    /**
     * @return the time elapsed since the timer is started.
     */
    public long getElapsedTime() {
        checkState(this.running, "The timer \"%s\" is not running!", this.name);

        return System.nanoTime() - this.time;
    }

    /**
     * @return timings of the time measurement
     */
    public List<Long> getTimings() {
        return unmodifiableList(this.timings);
    }

    /**
     * @return the total time that the timer was running
     */
    public long total() {
        long total = 0;
        for (long t : this.timings)
            total += t;
        return total;
    }

    @Override
    public String toString() {
        return Double.toString((double)total()/1000000000.0); // convert to seconds
    }
}
