package csci446.project2.Agents.Inference;

import csci446.project2.Agents.KB.Cell;

/**
 * Created by cetho on 10/20/2016.
 */
public class Variable {

    public Cell substitution = null;
    public Character letter = null;
    //All variables are cells.

    public Variable() {

    }

    public Variable(Cell substitution) {
        //Substitute a value. Typically from unification.
        this.substitution = substitution;
    }

    public void assignLetter(char letter) {
        this.letter = letter;
    }
}
