package csci446.project2;

import csci446.project2.Agents.KnowledgeExplorer;
import csci446.project2.Agents.ReactionExplorer;
import csci446.project2.WumpusWorld.State;
import csci446.project2.WumpusWorld.WumpusWorld;

import java.util.ArrayList;

/**
 * Created by cetho on 10/11/2016.
 */
public class TestRunner {

    WumpusWorld[][] worlds;

    ArrayList<ArrayList<ArrayList<State>>> reactiveResults;
    ArrayList<ArrayList<ArrayList<State>>> knowledgeResults;

    public TestRunner(int testWorldsPerSize, double obstacleProbability, double pitProbability, double wumpusProbability) throws Exception {
        worlds = new WumpusWorld[5][testWorldsPerSize];
        reactiveResults = new ArrayList<ArrayList<ArrayList<State>>>();
        knowledgeResults = new ArrayList<ArrayList<ArrayList<State>>>();

        for(int worldSize = 0; worldSize < 5; worldSize++) {
            reactiveResults.add(worldSize, new ArrayList<ArrayList<State>>());
            knowledgeResults.add(worldSize, new ArrayList<ArrayList<State>>());
            for(int test = 0; test < 10; test++) {
                System.out.println("Testing World " + ((worldSize + 1) * 5) + "-" + (test + 1));
                worlds[worldSize][test] = new WumpusWorld((worldSize + 1) * 5, obstacleProbability, pitProbability, wumpusProbability);
                System.out.println("\tTesting reactive agent.");
                //Reactive Agent
                ReactionExplorer reactionExplorer = new ReactionExplorer(worlds[worldSize][test]);
                worlds[worldSize][test].attachAgent(reactionExplorer);
                worlds[worldSize][test].simulate();
                reactiveResults.get(worldSize).add(test, worlds[worldSize][test].states);
                worlds[worldSize][test].reset();
                System.out.println("\tTesting knowledge agent.");
                //Knowledge Explorer
                KnowledgeExplorer knowledgeExplorer = new KnowledgeExplorer(worlds[worldSize][test]);
                worlds[worldSize][test].attachAgent(knowledgeExplorer);
                worlds[worldSize][test].simulate();
                knowledgeResults.get(worldSize).add(test, worlds[worldSize][test].states);
            }
        }

        System.out.println("\nTest Results:");
        for(int worldSize = 0; worldSize < 5; worldSize++) {
            System.out.println("\tWorld Size: " + ((worldSize + 1) * 5) + ":");
            for(int test = 0; test < 10; test++) {
                System.out.println("\t\tTest #" + (test + 1) + ":");

                System.out.println("\t\t\tReactive Agent:");
                System.out.println("\t\t\t\tIterations: " + (reactiveResults.get(worldSize).get(test).size() - 1));
                System.out.println("\t\t\t\tPicked Up Gold: " + reactiveResults.get(worldSize).get(test).get(reactiveResults.get(worldSize).get(test).size() - 1).hasGold);
                System.out.println("\t\t\t\tPenalty Function: " + reactiveResults.get(worldSize).get(test).get(reactiveResults.get(worldSize).get(test).size() - 1).penaltyScore);
                System.out.println("\t\t\t\tDeaths: " + reactiveResults.get(worldSize).get(test).get(reactiveResults.get(worldSize).get(test).size() - 1).deaths);
                System.out.println("\t\t\t\tWumpuses Killed: " + reactiveResults.get(worldSize).get(test).get(reactiveResults.get(worldSize).get(test).size() - 1).wumpusesKilled);

                System.out.println("\t\t\tKnowledge Agent:");
                System.out.println("\t\t\t\tIterations: " + (knowledgeResults.get(worldSize).get(test).size() - 1));
                System.out.println("\t\t\t\tPicked Up Gold: " + knowledgeResults.get(worldSize).get(test).get(knowledgeResults.get(worldSize).get(test).size() - 1).hasGold);
                System.out.println("\t\t\t\tPenalty Function: " + knowledgeResults.get(worldSize).get(test).get(knowledgeResults.get(worldSize).get(test).size() - 1).penaltyScore);
                System.out.println("\t\t\t\tDeaths: " + knowledgeResults.get(worldSize).get(test).get(knowledgeResults.get(worldSize).get(test).size() - 1).deaths);
                System.out.println("\t\t\t\tWumpuses Killed: " + knowledgeResults.get(worldSize).get(test).get(knowledgeResults.get(worldSize).get(test).size() - 1).wumpusesKilled);
            }
        }
    }
}
