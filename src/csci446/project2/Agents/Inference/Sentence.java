package csci446.project2.Agents.Inference;

import java.util.ArrayList;

/**
 * Created by cetho on 10/20/2016.
 */
public class Sentence {
    /*
    Rules are horn clauses
    https://en.wikipedia.org/wiki/Horn_clause

    Negating will negate the implication only.
    Format: ¬P(X) <= Q(Y) ^ T(Z) ...

    All variables are cells. Available predicates are in the Predicate Enum. If we need more, add them there.

    Fun facts:
    Alt+0172 = negation (¬)
    */

    public final Clause implication;

    public final ArrayList<Clause> conditions;
    public final ArrayList<Operator> operators;

    public final Quantifier quantifier;

    public final Variable quantifiedVariable;

    public Sentence(Quantifier quantifier, Variable quantifiedVariable, Clause implication, ArrayList<Clause> conditions, ArrayList<Operator> operators ) throws Exception {
        this.quantifier = quantifier;
        this.quantifiedVariable = quantifiedVariable;
        this.implication = implication;
        this.operators = operators;
        this.conditions = conditions;
        if(operators.size() != conditions.size() - 1) {
            throw new Exception("Mismatch on operators and clauses.");
        }

        char currentLetter = 'a';
        if(quantifiedVariable.letter == null) {
            quantifiedVariable.assignLetter(currentLetter);
            currentLetter++;
        }
        for( Clause clause : conditions) {
            if(clause.variable1.letter == null) {
                quantifiedVariable.assignLetter(currentLetter);
                currentLetter++;
            }
            if(clause.variableCount == 2) {
                if (clause.variable2.letter == null) {
                    quantifiedVariable.assignLetter(currentLetter);
                    currentLetter++;
                }
            }
        }
    }

    public void PrintSentence() {
        if(quantifier == Quantifier.Exist) {
            System.out.print("Ǝ");
        } else {
            System.out.print("∀");
        }
        if(implication.variableCount == 1) {
            System.out.print(quantifiedVariable.letter + " " + implication.predicateType + "(" + implication.variable1.letter + ")");
        } else {
            System.out.print(quantifiedVariable.letter + " " + implication.predicateType + "(" + implication.variable1.letter + ", " + implication.variable2.letter + ")");
        }
        System.out.print(" <= ");
        for (int i = 0; i < conditions.size(); i++) {
            Clause condition = conditions.get(i);
            if(condition.variableCount == 1) {
                System.out.print(condition.predicateType + "(" + condition.variable1.letter + ")");
            } else {
                System.out.print(condition.predicateType + "(" + condition.variable1.letter + ", " + condition.variable2.letter + ")");
            }

            if(i < operators.size()) {
                System.out.print(" " + operators.get(i) + " ");
            }
        }
        System.out.println();
    }
}
