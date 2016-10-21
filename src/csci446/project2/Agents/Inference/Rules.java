package csci446.project2.Agents.Inference;

import java.util.ArrayList;

public class Rules {
    public static ArrayList<Sentence> generateRules() throws Exception {
        ArrayList<Sentence> rules = new ArrayList<Sentence>();
        //Create rules here. Order can matter. They are checked in the exact order declared.
        Variable spot = new Variable();

        Clause implicant = new Clause(Predicate.Safe, spot, false);
        Clause noWumpus = new Clause(Predicate.NotWumpus, spot, false);
        Clause noPit = new Clause(Predicate.NotPit, spot, false);
        ArrayList<Operator> operators = new ArrayList<Operator>();
        ArrayList<Clause> clauses = new ArrayList<Clause>();

        clauses.add(noWumpus);
        operators.add(Operator.AND);
        clauses.add(noPit);
        // Resulting Sentence:
        // ∀a Safe(a) <= NotWumpus(a) AND NotPit(a)
        Sentence sentence = new Sentence(Quantifier.ForAll, spot, implicant, clauses, operators);
        //sentence.PrintSentence();
        rules.add(sentence);
        return rules;
    }
}
