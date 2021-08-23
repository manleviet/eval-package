package at.tugraz.ist.ase.eval;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public abstract class Evaluation {
    protected final String name;

    Evaluation(String name) {
        this.name = name;
    }
}
