package csci446.project2;

import csci446.project2.Agents.Inference.*;
import csci446.project2.Agents.KnowledgeExplorer;
import csci446.project2.Agents.ReactionExplorer;
import csci446.project2.Agents.TestRandomExplorer;
import csci446.project2.WumpusWorld.WumpusWorld;

import java.util.ArrayList;

public class Main {

    public static boolean showStates = false;
    public static int iterationLimit = 50000;

    public static void main(String[] args) throws Exception {
	    TestRunner runner = new TestRunner(10, .05, .05, .05);
        System.out.println("We were unable to successfully create a knowledge agent using first-order logic.\n" +
                "We had trouble with resolution and unification in the inference engine.\n" +
                "While, the knowledge agent still runs, it always returns null for a move.\n" +
                "The knowledge base is still created and does store starting facts about the world, as well as a ruleset." +
                "They are just not evaluated and updated by the inference engine and no best actions are generated based" +
                "on it.");
    }
}
