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

    public ArrayList<Integer> removeAdjacent(Predicate predicate, Cell cell, Cell[][] KBMap) {
        Cell adjNorth = null;
        Cell adjSouth = null;
        Cell adjEast = null;
        Cell adjWest = null;
        try {
            adjNorth = KBMap[cell.x][cell.y + 1];
        } catch (IndexOutOfBoundsException e) {} catch (NullPointerException e) {}
        try {
            adjSouth = KBMap[cell.x][cell.y - 1];
        } catch (IndexOutOfBoundsException e) {} catch (NullPointerException e) {}
        try {
            adjEast = KBMap[cell.x + 1][cell.y];
        } catch (IndexOutOfBoundsException e) {} catch (NullPointerException e) {}
        try {
            adjWest = KBMap[cell.x - 1][cell.y + 1];
        } catch (IndexOutOfBoundsException e) {} catch (NullPointerException e) {}

        ArrayList<Integer> facts = new ArrayList<Integer>();

        for (int i = 0; i < this.size(); i++) {
            if(this.get(i).variableCount == 1 && this.get(i).predicateType == predicate && (
                    this.get(i).variable1.substitution.equals(adjNorth) || this.get(i).variable1.substitution.equals(adjSouth)
                            || this.get(i).variable1.substitution.equals(adjEast) || this.get(i).variable1.substitution.equals(adjWest)
                    )) {
                facts.add(i);
                this.remove(i);
            }
        }

        return facts;
    }
}
