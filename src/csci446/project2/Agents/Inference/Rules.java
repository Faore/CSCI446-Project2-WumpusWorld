package csci446.project2.Agents.Inference;

import java.util.ArrayList;

/**
 * Created by cetho on 10/20/2016.
 */
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
        
        
        //same thing but with percepts
        clauses.clear();
        operators.clear();
        
        implicant = new Clause(Predicate.Safe, spot, false);
        noWumpus = new Clause(Predicate.NotStench, spot, false);
        noPit = new Clause(Predicate.NotBreeze, spot, false);
        operators = new ArrayList<Operator>();
        clauses = new ArrayList<Clause>();

        clauses.add(noWumpus);
        operators.add(Operator.AND);
        clauses.add(noPit);
        // Resulting Sentence:
        // ∀a Safe(a) <= NotWumpus(a) AND NotPit(a)
        sentence = new Sentence(Quantifier.ForAll, spot, implicant, clauses, operators);
        //sentence.PrintSentence();
        rules.add(sentence);

        
        //Pits
        //basic
        //defines safe as safe, unsafe as unsafe, etc.
//        clauses.clear();
//        operators.clear();
//        implicant = new Clause(Predicate.Safe, spot, false);
//        Clause safe = new Clause(Predicate.NotPit, spot, false);
//        clauses.add(safe);
//        sentence = new Sentence(Quantifier.ForAll, spot, implicant, clauses, operators);
//        rules.add(sentence);
//        
//        clauses.clear();
//        operators.clear();
//        implicant = new Clause(Predicate.Unsafe, spot, false);
//        safe = new Clause(Predicate.Pit, spot, false);
//        clauses.add(safe);
//        sentence = new Sentence(Quantifier.ForAll, spot, implicant, clauses, operators);
//        rules.add(sentence);
//        
//        clauses.clear();
//        operators.clear();
//        implicant = new Clause(Predicate.Safe, spot, false);
//        safe = new Clause(Predicate.Safe, spot, false);
//        clauses.add(safe);
//        sentence = new Sentence(Quantifier.ForAll, spot, implicant, clauses, operators);
//        rules.add(sentence);
// 
//        clauses.clear();
//        operators.clear();
//        implicant = new Clause(Predicate.Unsafe, spot, false);
//        Clause notsafe = new Clause(Predicate.Unsafe, spot, false);
//        clauses.add(notsafe);
//        sentence = new Sentence(Quantifier.ForAll, spot, implicant, clauses, operators);
//        rules.add(sentence);        
//        
//        clauses.clear();
//        operators.clear();
//        implicant = new Clause(Predicate.Breeze, spot, false);
//        Clause breeze = new Clause(Predicate.Breeze, spot, false);
//        clauses.add(breeze);
//        sentence = new Sentence(Quantifier.ForAll, spot, implicant, clauses, operators);
//        rules.add(sentence); 
//        
//        clauses.clear();
//        operators.clear();
//        implicant = new Clause(Predicate.NotBreeze, spot, false);
//        Clause notbreeze = new Clause(Predicate.NotBreeze, spot, false);
//        clauses.add(notbreeze);
//        sentence = new Sentence(Quantifier.ForAll, spot, implicant, clauses, operators);
//        rules.add(sentence); 
        
        clauses.clear();
        operators.clear();
        implicant = new Clause(Predicate.Breeze, spot, false);
        Clause north = new Clause(Predicate.Above.Pit, spot, false);
        Clause south = new Clause(Predicate.Below.Pit, spot, false);
        Clause east = new Clause(Predicate.RightOf.Pit, spot, false);
        Clause west = new Clause(Predicate.LeftOf.Pit, spot, false);
        clauses.add(north);
        clauses.add(south);
        clauses.add(east);
        clauses.add(west);
        operators.add(Operator.OR);
        operators.add(Operator.OR);
        operators.add(Operator.OR);
        sentence = new Sentence(Quantifier.ForAll, spot, implicant, clauses, operators);
        rules.add(sentence);
        
        clauses.clear();
        operators.clear();
        implicant = new Clause(Predicate.NotBreeze, spot, false);
        Clause notnorth = new Clause(Predicate.Above.NotPit, spot, false);
        Clause notsouth = new Clause(Predicate.Below.NotPit, spot, false);
        Clause noteast = new Clause(Predicate.RightOf.NotPit, spot, false);
        Clause notwest = new Clause(Predicate.LeftOf.NotPit, spot, false);
        clauses.add(notnorth);
        clauses.add(notsouth);
        clauses.add(noteast);
        clauses.add(notwest);
        operators.add(Operator.AND);
        operators.add(Operator.AND);
        operators.add(Operator.AND);
        sentence = new Sentence(Quantifier.ForAll, spot, implicant, clauses, operators);
        rules.add(sentence);
        
        //pit north
        clauses.clear();
        operators.clear();
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
        clauses.clear();
        operators.clear();
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
        clauses.clear();
        operators.clear();
        implicant = new Clause(Predicate.LeftOf.Pit, spot, false);// east cell
        east = new Clause(Predicate.Breeze, spot, false); //not really east, but cell in question
        south = new Clause(Predicate.Below.NotPit, spot, false);
        north = new Clause(Predicate.Above.NotPit, spot, false);
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
        
        
        //pit west
        clauses.clear();
        operators.clear();
        implicant = new Clause(Predicate.RightOf.Pit, spot, false);// west cell
        west = new Clause(Predicate.Breeze, spot, false); //not really west, but cell in question
        south = new Clause(Predicate.Below.NotPit, spot, false);
        east = new Clause(Predicate.RightOf.NotPit, spot, false);
        north = new Clause(Predicate.Above.NotPit, spot, false);
        clauses.add(notnorth);
        clauses.add(notsouth);
        clauses.add(noteast);
        clauses.add(notwest);
        operators.add(Operator.AND);
        operators.add(Operator.AND);
        operators.add(Operator.AND);
        sentence = new Sentence(Quantifier.ForAll, spot, implicant, clauses, operators);
        rules.add(sentence);
        
        //Wumpus
        //basic
        //defines safe as safe, unsafe as unsafe, etc.
//        clauses.clear();
//        operators.clear();
//        implicant = new Clause(Predicate.Safe, spot, false);
//        safe = new Clause(Predicate.NotWumpus, spot, false);
//        clauses.add(safe);
//        sentence = new Sentence(Quantifier.ForAll, spot, implicant, clauses, operators);
//        rules.add(sentence);
//        
//        clauses.clear();
//        operators.clear();
//        implicant = new Clause(Predicate.Unsafe, spot, false);
//        safe = new Clause(Predicate.Wumpus, spot, false);
//        clauses.add(safe);
//        sentence = new Sentence(Quantifier.ForAll, spot, implicant, clauses, operators);
//        rules.add(sentence);
//        
//        clauses.clear();
//        operators.clear();
//        implicant = new Clause(Predicate.Stench, spot, false);
//        Clause Stench = new Clause(Predicate.Stench, spot, false);
//        clauses.add(Stench);
//        sentence = new Sentence(Quantifier.ForAll, spot, implicant, clauses, operators);
//        rules.add(sentence); 
//        
//        clauses.clear();
//        operators.clear();
//        implicant = new Clause(Predicate.NotStench, spot, false);
//        Clause notStench = new Clause(Predicate.NotStench, spot, false);
//        clauses.add(notStench);
//        sentence = new Sentence(Quantifier.ForAll, spot, implicant, clauses, operators);
//        rules.add(sentence); 
        
        clauses.clear();
        operators.clear();
        implicant = new Clause(Predicate.Stench, spot, false);
        north = new Clause(Predicate.Above.Wumpus, spot, false);
        south = new Clause(Predicate.Below.Wumpus, spot, false);
        east = new Clause(Predicate.RightOf.Wumpus, spot, false);
        west = new Clause(Predicate.LeftOf.Wumpus, spot, false);
        clauses.add(north);
        clauses.add(south);
        clauses.add(east);
        clauses.add(west);
        operators.add(Operator.OR);
        operators.add(Operator.OR);
        operators.add(Operator.OR);
        sentence = new Sentence(Quantifier.ForAll, spot, implicant, clauses, operators);
        rules.add(sentence);
        
        clauses.clear();
        operators.clear();
        implicant = new Clause(Predicate.NotStench, spot, false);
        notnorth = new Clause(Predicate.Above.NotWumpus, spot, false);
        notsouth = new Clause(Predicate.Below.NotWumpus, spot, false);
        noteast = new Clause(Predicate.RightOf.NotWumpus, spot, false);
        notwest = new Clause(Predicate.LeftOf.NotWumpus, spot, false);
        clauses.add(notnorth);
        clauses.add(notsouth);
        clauses.add(noteast);
        clauses.add(notwest);
        operators.add(Operator.AND);
        operators.add(Operator.AND);
        operators.add(Operator.AND);
        sentence = new Sentence(Quantifier.ForAll, spot, implicant, clauses, operators);
        rules.add(sentence);
        
        //Wumpus north
        clauses.clear();
        operators.clear();
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
        clauses.clear();
        operators.clear();
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
        clauses.clear();
        operators.clear();
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
        clauses.clear();
        operators.clear();
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
        
        
        
        return rules;
    }
}
