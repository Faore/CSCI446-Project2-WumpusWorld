package csci446.project2;

import csci446.project2.Agents.TestRandomExplorer;
import csci446.project2.WumpusWorld.WumpusWorld;

public class Main {

    public static boolean showStates = true;

    public static void main(String[] args) throws Exception {
	    // Configuration
        TestRandomExplorer testRandomExplorer = new TestRandomExplorer();
        // 10% chance of each bad thing.
        WumpusWorld wumpusWorld = new WumpusWorld(5, 0, 0, 0);
        // Attach the agent to the world.
        wumpusWorld.attachAgent(testRandomExplorer);
        // Simulate
        wumpusWorld.simulate();
    }
}
