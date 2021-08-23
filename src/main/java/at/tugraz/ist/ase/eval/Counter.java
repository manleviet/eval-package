/*
 * eval-package - A Maven package for evaluation
 *
 * Copyright (c) 2021
 *
 * @author: Viet-Man Le (vietman.le@ist.tugraz.at)
 */

package at.tugraz.ist.ase.eval;

import lombok.Getter;

public class Counter extends Evaluation {
    @Getter
    private long value = 0;

    Counter(String name) {
        super(name);
    }

    public long increment(int step) {
        this.value = this.value + step;
        return getValue();
    }

    @Override
    public String toString() {
        return Long.toString(getValue());
    }
}
