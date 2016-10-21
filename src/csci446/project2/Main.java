package csci446.project2;

import csci446.project2.Agents.Inference.*;
import csci446.project2.Agents.KnowledgeExplorer;
import csci446.project2.Agents.TestRandomExplorer;
import csci446.project2.WumpusWorld.WumpusWorld;

import java.util.ArrayList;

public class Main {

    public static boolean showStates = true;

    public static void main(String[] args) throws Exception {
	    // Configuration

        //Testing Clauses
        Variable spot = new Variable();
        Clause implicant = new Clause(Predicate.Safe, spot, false);

        Clause noWumpus = new Clause(Predicate.NotWumpus, spot, false);
        Clause noPit = new Clause(Predicate.NotPit, spot, false);

        ArrayList<Operator> operators = new ArrayList<Operator>();
        ArrayList<Clause> clauses = new ArrayList<Clause>();

        clauses.add(noWumpus);
        clauses.add(noPit);

        operators.add(Operator.AND);

        Sentence sentence = new Sentence(Quantifier.ForAll, spot, implicant, clauses, operators);
        sentence.PrintSentence();
        // 0% chance of each bad thing.
        WumpusWorld wumpusWorld = new WumpusWorld(5, 0, 0, 0);
        // Attach the agent to the world.
        KnowledgeExplorer knowledgeExplorer = new KnowledgeExplorer(wumpusWorld);
        wumpusWorld.attachAgent(knowledgeExplorer);
        // Simulate
        wumpusWorld.simulate();
    }
}
