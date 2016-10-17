package csci446.project2.Agents;

import csci446.project2.WumpusWorld.Action;
import csci446.project2.WumpusWorld.Explorer;
import csci446.project2.WumpusWorld.Percept;

import java.util.ArrayList;

/**
 * Created by cetho on 10/16/2016.
 */
public class TestRandomExplorer implements Explorer {

    public Action determineMove(ArrayList<Percept> percepts) {
        //Just return a random action, or pick up the gold if it's there.
        if(percepts.contains(Percept.Twinkle)) {
            return Action.PickUpGold;
        }
        return Action.values()[(int) (Math.random()* 4)];
    }
}
