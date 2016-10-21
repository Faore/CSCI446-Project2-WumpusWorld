package csci446.project2.Agents.Inference;

import csci446.project2.Agents.KB.Cell;
import csci446.project2.Agents.KB.KnowledgeBase;
import csci446.project2.Util.LocationCalc;
import csci446.project2.Util.Orientation;
import csci446.project2.Util.Pair;

/**
 * Created by cetho on 10/20/2016.
 */

public class Predicate {
    //Predicates are relative to cells.
    public final Predicates predicateType;
    public final Reference reference;

    /*
    Predicates is the condition, reference references a cell in on of the cardinal directions of the cell being tested.

    Example: IsSafe(X) where X is above the point being checked would be created as such:

    new Predicate(Predicates.IsSafe, Reference.North);

    */

    public Predicate(Predicates p, Reference reference) {
        this.predicateType = p;
        this.reference = reference;
    }

    public Cell relativeCell(Cell cell, KnowledgeBase kb) {
        Pair<Integer, Integer> relativeLocation = LocationCalc.ReferenceLocation(cell.x, cell.y, reference);
        return kb.KBMap[relativeLocation.left][relativeLocation.right];
    }
}
