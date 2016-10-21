package csci446.project2.Agents.Inference;

import java.util.ArrayList;

/**
 * Created by cetho on 10/20/2016.
 */
public class Rule {
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

    public final Clause[] conditions;

    public final Quantifier quantifier;

    public final Variable quantifiedVariable;

    public Rule(Quantifier quantifier, Variable quantifiedVariable, Clause implication, ArrayList<Clause> conditions ) {
        this.quantifier = quantifier;
        this.quantifiedVariable = quantifiedVariable;
        this.implication = implication;
        this.conditions = (Clause[]) conditions.toArray();
    }
}
