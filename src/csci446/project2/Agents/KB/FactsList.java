package csci446.project2.Agents.KB;

import csci446.project2.Agents.Inference.Clause;
import csci446.project2.Agents.Inference.Predicate;

import java.util.ArrayList;

/**
 * Created by cetho on 10/21/2016.
 */
public class FactsList extends ArrayList<Clause> {
    public ArrayList<Integer> find(Predicate predicate, Cell cell) {
        ArrayList<Integer> facts = new ArrayList<Integer>();

        for (int i = 0; i < this.size(); i++) {
            if(this.get(i).variableCount == 1 && this.get(i).predicateType == predicate && this.get(i).variable1.substitution.equals(cell) ) {
                facts.add(i);
            }
        }

        return facts;
    }

    public ArrayList<Integer> remove(Predicate predicate, Cell cell) {
        ArrayList<Integer> facts = new ArrayList<Integer>();

        for (int i = 0; i < this.size(); i++) {
            if(this.get(i).variableCount == 1 && this.get(i).predicateType == predicate && this.get(i).variable1.substitution.equals(cell) ) {
                facts.add(i);
                this.remove(i);
            }
        }

        return facts;
    }
}
