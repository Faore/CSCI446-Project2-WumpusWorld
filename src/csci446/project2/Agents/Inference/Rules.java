package csci446.project2.Agents.Inference;

import java.util.ArrayList;

public class Rules {
    public static ArrayList<Sentence> generateRules() throws Exception {
        ArrayList<Sentence> rules = new ArrayList<Sentence>();

        //Negation Rules
        //Safe/Unsafe
        Variable x = new Variable();
        Clause implicant = new Clause(Predicate.Safe, x);
        Clause negation = new Clause(Predicate.Unsafe, x, true);
        ArrayList<Clause> clauses = new ArrayList<Clause>();
        ArrayList<Operator> operators = new ArrayList<Operator>();
        clauses.add(negation);

        rules.add(new Sentence(Quantifier.ForAll, x, implicant, clauses, operators));
        //Wumpus/NotWumpus
        x = new Variable();
        implicant = new Clause(Predicate.Wumpus, x);
        negation = new Clause(Predicate.NotWumpus, x, true);
        clauses = new ArrayList<Clause>();
        operators = new ArrayList<Operator>();
        clauses.add(negation);

        rules.add(new Sentence(Quantifier.ForAll, x, implicant, clauses, operators));

        //Pit/NotPit
        x = new Variable();
        implicant = new Clause(Predicate.Pit, x);
        negation = new Clause(Predicate.NotPit, x, true);
        clauses = new ArrayList<Clause>();
        operators = new ArrayList<Operator>();
        clauses.add(negation);

        rules.add(new Sentence(Quantifier.ForAll, x, implicant, clauses, operators));

        //Obstacle/NotObstacle
        x = new Variable();
        implicant = new Clause(Predicate.Obstacle, x);
        negation = new Clause(Predicate.NotObstacle, x, true);
        clauses = new ArrayList<Clause>();
        operators = new ArrayList<Operator>();
        clauses.add(negation);

        rules.add(new Sentence(Quantifier.ForAll, x, implicant, clauses, operators));

        //Stench/NotStench
        x = new Variable();
        implicant = new Clause(Predicate.Stench, x);
        negation = new Clause(Predicate.NotStench, x, true);
        clauses = new ArrayList<Clause>();
        operators = new ArrayList<Operator>();
        clauses.add(negation);

        rules.add(new Sentence(Quantifier.ForAll, x, implicant, clauses, operators));

        //Breeze/NotBreeze
        x = new Variable();
        implicant = new Clause(Predicate.Breeze, x);
        negation = new Clause(Predicate.NotBreeze, x, true);
        clauses = new ArrayList<Clause>();
        operators = new ArrayList<Operator>();
        clauses.add(negation);

        rules.add(new Sentence(Quantifier.ForAll, x, implicant, clauses, operators));

        //Create rules here. Order can matter. They are checked in the exact order declared.
        Variable spot = new Variable();

        implicant = new Clause(Predicate.Safe, spot, false);
        Clause c1 = new Clause(Predicate.NotWumpus, spot, false);
        Clause c2 = new Clause(Predicate.NotPit, spot, false);
        operators = new ArrayList<Operator>();
        clauses = new ArrayList<Clause>();

        clauses.add(c1);
        operators.add(Operator.AND);
        clauses.add(c2);
        // Resulting Sentence:
        // ∀a Safe(a) <= NotWumpus(a) AND NotPit(a)
        Sentence sentence = new Sentence(Quantifier.ForAll, spot, implicant, clauses, operators);
        //sentence.PrintSentence();
        rules.add(sentence);
        
        
        //same thing but with percepts
        clauses = new ArrayList<Clause>();
        operators = new ArrayList<Operator>();
        
        implicant = new Clause(Predicate.Safe, spot, false);
        c1 = new Clause(Predicate.NotStench, spot, false);
        c2 = new Clause(Predicate.NotBreeze, spot, false);
        operators = new ArrayList<Operator>();
        clauses = new ArrayList<Clause>();

        clauses.add(c1);
        operators.add(Operator.AND);
        clauses.add(c2);
        // Resulting Sentence:
        // ∀a Safe(a) <= NotWumpus(a) AND NotPit(a)
        sentence = new Sentence(Quantifier.ForAll, spot, implicant, clauses, operators);
        //sentence.PrintSentence();
        rules.add(sentence);

        //Can't be represented with OR operators.
        /*
        clauses = new ArrayList<Clause>();
        operators = new ArrayList<Operator>();
        implicant = new Clause(Predicate.Breeze, spot, false);
        Clause north = new Clause(Predicate.Above.Pit, new Variable(), spot, false);
        Clause south = new Clause(Predicate.Below.Pit, new Variable(), spot, false);
        Clause east = new Clause(Predicate.RightOf.Pit, new Variable(), spot, false);
        Clause west = new Clause(Predicate.LeftOf.Pit, new Variable(), spot, false);
        clauses.add(north);
        clauses.add(south);
        clauses.add(east);
        clauses.add(west);
        operators.add(Operator.OR);
        operators.add(Operator.OR);
        operators.add(Operator.OR);
        sentence = new Sentence(Quantifier.ForAll, spot, implicant, clauses, operators);
        rules.add(sentence);
        */

        clauses = new ArrayList<Clause>();
        operators = new ArrayList<Operator>();
        implicant = new Clause(Predicate.NotBreeze, spot, false);

        Variable vn = new Variable();
        Variable vs = new Variable();
        Variable ve = new Variable();
        Variable vw = new Variable();

        Clause north = new Clause(Predicate.Above, vn, spot, false);
        Clause south = new Clause(Predicate.Below, vs, spot, false);
        Clause east = new Clause(Predicate.RightOf, ve, spot, false);
        Clause west = new Clause(Predicate.LeftOf, vw, spot, false);

        Clause npit = new Clause(Predicate.NotPit, vn);
        Clause spit = new Clause(Predicate.NotPit, vs);
        Clause epit = new Clause(Predicate.NotPit, ve);
        Clause wpit = new Clause(Predicate.NotPit, vw);

        clauses.add(north);
        clauses.add(south);
        clauses.add(east);
        clauses.add(west);

        clauses.add(npit);
        clauses.add(spit);
        clauses.add(epit);
        clauses.add(wpit);

        operators.add(Operator.AND);
        operators.add(Operator.AND);
        operators.add(Operator.AND);
        operators.add(Operator.AND);
        operators.add(Operator.AND);
        operators.add(Operator.AND);
        operators.add(Operator.AND);

        sentence = new Sentence(Quantifier.ForAll, spot, implicant, clauses, operators);
        rules.add(sentence);

        /*
        Unnecessary
        //pit north
        clauses = new ArrayList<Clause>();
        operators = new ArrayList<Operator>();
        implicant = new Clause(Predicate.Above.Pit, spot, false);// north cell
        north = new Clause(Predicate.Breeze, spot, false); //not really north, but cell in question
        south = new Clause(Predicate.Below.NotPit, spot, false);
        east = new Clause(Predicate.RightOf.NotPit, spot, false);
        west = new Clause(Predicate.LeftOf.NotPit, spot, false);
        clauses.add(notnorth);
        clauses.add(notsouth);
        clauses.add(noteast);
        clauses.add(notwest);
        operators.add(Operator.AND);
        operators.add(Operator.AND);
        operators.add(Operator.AND);
        sentence = new Sentence(Quantifier.ForAll, spot, implicant, clauses, operators);
        rules.add(sentence);
        
        //pit south
        clauses = new ArrayList<Clause>();
        operators = new ArrayList<Operator>();
        implicant = new Clause(Predicate.Below.Pit, spot, false);// north cell
        south = new Clause(Predicate.Breeze, spot, false); //not really south, but cell in question
        north = new Clause(Predicate.Above.NotPit, spot, false);
        east = new Clause(Predicate.RightOf.NotPit, spot, false);
        west = new Clause(Predicate.LeftOf.NotPit, spot, false);
        clauses.add(notnorth);
        clauses.add(notsouth);
        clauses.add(noteast);
        clauses.add(notwest);
        operators.add(Operator.AND);
        operators.add(Operator.AND);
        operators.add(Operator.AND);
        sentence = new Sentence(Quantifier.ForAll, spot, implicant, clauses, operators);
        rules.add(sentence);

        
        //pit east
        clauses = new ArrayList<Clause>();
        operators = new ArrayList<Operator>();
        implicant = new Clause(Predicate.LeftOf.Pit, new Variable(), spot, false);// east cell
        east = new Clause(Predicate.Breeze, new Variable(), spot, false); //not really east, but cell in question
        south = new Clause(Predicate.Below.NotPit, new Variable(), spot, false);
        north = new Clause(Predicate.Above.NotPit, new Variable(), spot, false);
        west = new Clause(Predicate.LeftOf.NotPit, new Variable(), spot, false);
        clauses.add(notnorth);
        clauses.add(notsouth);
        clauses.add(noteast);
        clauses.add(notwest);
        operators.add(Operator.AND);
        operators.add(Operator.AND);
        operators.add(Operator.AND);
        sentence = new Sentence(Quantifier.ForAll, spot, implicant, clauses, operators);
        rules.add(sentence);
        
        
        //pit west
        clauses = new ArrayList<Clause>();
        operators = new ArrayList<Operator>();
        implicant = new Clause(Predicate.RightOf.Pit, new Variable(), spot, false);// west cell
        west = new Clause(Predicate.Breeze, new Variable(), spot, false); //not really west, but cell in question
        south = new Clause(Predicate.Below.NotPit, new Variable(), spot, false);
        east = new Clause(Predicate.RightOf.NotPit, new Variable(), spot, false);
        north = new Clause(Predicate.Above.NotPit, new Variable(), spot, false);
        clauses.add(notnorth);
        clauses.add(notsouth);
        clauses.add(noteast);
        clauses.add(notwest);
        operators.add(Operator.AND);
        operators.add(Operator.AND);
        operators.add(Operator.AND);
        sentence = new Sentence(Quantifier.ForAll, spot, implicant, clauses, operators);
        rules.add(sentence);
        */

        /* Can't represent OR clauses.
        clauses = new ArrayList<Clause>();
        operators = new ArrayList<Operator>();
        implicant = new Clause(Predicate.Stench, new Variable(), spot, false);
        north = new Clause(Predicate.Above.Wumpus, new Variable(), spot, false);
        south = new Clause(Predicate.Below.Wumpus, new Variable(), spot, false);
        east = new Clause(Predicate.RightOf.Wumpus, new Variable(), spot, false);
        west = new Clause(Predicate.LeftOf.Wumpus, new Variable(), spot, false);
        clauses.add(north);
        clauses.add(south);
        clauses.add(east);
        clauses.add(west);
        operators.add(Operator.OR);
        operators.add(Operator.OR);
        operators.add(Operator.OR);
        sentence = new Sentence(Quantifier.ForAll, spot, implicant, clauses, operators);
        rules.add(sentence);
        */

        clauses = new ArrayList<Clause>();
        operators = new ArrayList<Operator>();
        implicant = new Clause(Predicate.NotStench, spot, false);

        vn = new Variable();
        vs = new Variable();
        ve = new Variable();
        vw = new Variable();

        north = new Clause(Predicate.Above, vn, spot, false);
        south = new Clause(Predicate.Below, vs, spot, false);
        east = new Clause(Predicate.RightOf, ve, spot, false);
        west = new Clause(Predicate.LeftOf, vw, spot, false);

        npit = new Clause(Predicate.NotWumpus, vn);
        spit = new Clause(Predicate.NotWumpus, vs);
        epit = new Clause(Predicate.NotWumpus, ve);
        wpit = new Clause(Predicate.NotWumpus, vw);


        clauses.add(north);
        clauses.add(south);
        clauses.add(east);
        clauses.add(west);

        clauses.add(npit);
        clauses.add(spit);
        clauses.add(epit);
        clauses.add(wpit);

        operators.add(Operator.AND);
        operators.add(Operator.AND);
        operators.add(Operator.AND);
        operators.add(Operator.AND);
        operators.add(Operator.AND);
        operators.add(Operator.AND);
        operators.add(Operator.AND);

        sentence = new Sentence(Quantifier.ForAll, spot, implicant, clauses, operators);
        rules.add(sentence);

        /* Unnecessary
        //Wumpus north
        clauses = new ArrayList<Clause>();
        operators = new ArrayList<Operator>();
        implicant = new Clause(Predicate.Above.Wumpus, spot, false);// north cell
        north = new Clause(Predicate.Stench, spot, false); //not really north, but cell in question
        south = new Clause(Predicate.Below.NotWumpus, spot, false);
        east = new Clause(Predicate.RightOf.NotWumpus, spot, false);
        west = new Clause(Predicate.LeftOf.NotWumpus, spot, false);
        clauses.add(notnorth);
        clauses.add(notsouth);
        clauses.add(noteast);
        clauses.add(notwest);
        operators.add(Operator.AND);
        operators.add(Operator.AND);
        operators.add(Operator.AND);
        sentence = new Sentence(Quantifier.ForAll, spot, implicant, clauses, operators);
        rules.add(sentence);
        
        //Wumpus south
        clauses = new ArrayList<Clause>();
        operators = new ArrayList<Operator>();
        implicant = new Clause(Predicate.Below.Wumpus, spot, false);// north cell
        south = new Clause(Predicate.Stench, spot, false); //not really south, but cell in question
        north = new Clause(Predicate.Above.NotWumpus, spot, false);
        east = new Clause(Predicate.RightOf.NotWumpus, spot, false);
        west = new Clause(Predicate.LeftOf.NotWumpus, spot, false);
        clauses.add(notnorth);
        clauses.add(notsouth);
        clauses.add(noteast);
        clauses.add(notwest);
        operators.add(Operator.AND);
        operators.add(Operator.AND);
        operators.add(Operator.AND);
        sentence = new Sentence(Quantifier.ForAll, spot, implicant, clauses, operators);
        rules.add(sentence);

        
        //Wumpus east
        clauses = new ArrayList<Clause>();
        operators = new ArrayList<Operator>();
        implicant = new Clause(Predicate.LeftOf.Wumpus, spot, false);// east cell
        east = new Clause(Predicate.Stench, spot, false); //not really east, but cell in question
        south = new Clause(Predicate.Below.NotWumpus, spot, false);
        north = new Clause(Predicate.Above.NotWumpus, spot, false);
        west = new Clause(Predicate.LeftOf.NotWumpus, spot, false);
        clauses.add(notnorth);
        clauses.add(notsouth);
        clauses.add(noteast);
        clauses.add(notwest);
        operators.add(Operator.AND);
        operators.add(Operator.AND);
        operators.add(Operator.AND);
        sentence = new Sentence(Quantifier.ForAll, spot, implicant, clauses, operators);
        rules.add(sentence);
        
        
        //Wumpus west
        clauses = new ArrayList<Clause>();
        operators = new ArrayList<Operator>();
        implicant = new Clause(Predicate.RightOf.Wumpus, spot, false);// west cell
        west = new Clause(Predicate.Stench, spot, false); //not really west, but cell in question
        south = new Clause(Predicate.Below.NotWumpus, spot, false);
        east = new Clause(Predicate.RightOf.NotWumpus, spot, false);
        north = new Clause(Predicate.Above.NotWumpus, spot, false);
        clauses.add(notnorth);
        clauses.add(notsouth);
        clauses.add(noteast);
        clauses.add(notwest);
        operators.add(Operator.AND);
        operators.add(Operator.AND);
        operators.add(Operator.AND);
        sentence = new Sentence(Quantifier.ForAll, spot, implicant, clauses, operators);
        rules.add(sentence);
        */
        
        
        return rules;
    }
}
