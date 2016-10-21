package csci446.project2.Agents.Inference;

import csci446.project2.Agents.KB.Cell;
import csci446.project2.Agents.KB.KnowledgeBase;
import csci446.project2.Util.LocationCalc;
import csci446.project2.Util.Pair;

/**
 * Created by cetho on 10/20/2016.
 */

public class Predicate {
    //Predicates are relative to cells.
    public final Predicates predicateType;
    public final boolean negated;
    public final Variable variable;

    /*
    Predicates is the condition, reference references a cell in on of the cardinal directions of the cell being tested.

    Example: IsSafe(X) where X is above the point being checked would be created as such:

    new Predicate(Predicates.IsSafe, Reference.North);

    */

    public Predicate(Predicates p, Variable variable, boolean negated) {
        this.predicateType = p;
        this.variable = variable;
        this.negated = negated;

    }
}
