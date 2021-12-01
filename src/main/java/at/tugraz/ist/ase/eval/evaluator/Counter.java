/*
 * eval-package - A Maven package for evaluation
 *
 * Copyright (c) 2021
 *
 * @author: Viet-Man Le (vietman.le@ist.tugraz.at)
 */

package at.tugraz.ist.ase.eval.evaluator;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Counter extends AbstractEvaluator {
    @Getter
    private long value = 0;

    public Counter(String name) {
        super(name);

        log.info("Counter for '{}' created", name);
    }

    public long increment(int step) {
        this.value = this.value + step;

        log.debug("Counter of '{}' incremented '{}' step(s).", name, step);

        return getValue();
    }

    @Override
    public String toString() {
        return Long.toString(getValue());
    }
}
