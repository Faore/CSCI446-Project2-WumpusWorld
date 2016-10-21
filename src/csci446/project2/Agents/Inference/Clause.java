package csci446.project2.Agents.Inference;

import csci446.project2.Agents.KB.Cell;

/**
 * Created by cetho on 10/20/2016.
 */

public class Clause {
    //Predicate are relative to cells.
    public final Predicate predicateType;
    public final boolean negated;
    public final Variable variable1;
    public final Variable variable2;

    public final int variableCount;

    /*
    Predicate is the condition.

    Example: IsSafe(X) where X is some being checked would be created as such:

    new Clause(Predicate.IsSafe, someVariable, false);

    Setting negate to true makes the clause:
        ¬IsSafe(X)
    */

    public Clause(Predicate p, Variable variable, boolean negated) {
        this.predicateType = p;
        this.variable1 = variable;
        this.variable2 = null;
        this.variableCount = 1;
        this.negated = negated;
    }

    public Clause(Predicate p, Variable variable) {
        this.predicateType = p;
        this.variable1 = variable;
        this.variable2 = null;
        this.variableCount = 1;
        this.negated = false;
    }

    public Clause(Predicate p, Variable variable1, Variable variable2, boolean negated) {
        this.predicateType = p;
        this.variable1 = variable1;
        this.variable2 = variable2;
        this.variableCount = 2;
        this.negated = negated;
    }

    public Clause(Predicate p, Variable variable1, Variable variable2) {
        this.predicateType = p;
        this.variable1 = variable1;
        this.variable2 = variable2;
        this.variableCount = 2;
        this.negated = false;
    }

    public static Clause fact(Predicate p, Cell cell) {
        return new Clause(p, new Variable(cell));
    }
    public static Clause fact(Predicate p, Cell cell, Cell cell2) {
        return new Clause(p, new Variable(cell), new Variable(cell2));
    }
}
