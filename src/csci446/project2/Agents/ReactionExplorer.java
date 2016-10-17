package csci446.project2.Agents;

import csci446.project2.WumpusWorld.Action;
import csci446.project2.WumpusWorld.Explorer;
import csci446.project2.WumpusWorld.Percept;

import java.util.ArrayList;

/**
 * Created by cetho on 10/11/2016.
 */
public class ReactionExplorer implements Explorer {
    public Action determineMove(ArrayList<Percept> percepts) {
        //We found the gold.
        if(percepts.contains(Percept.Twinkle)) {
            return Action.PickUpGold;
        }
        if(percepts.contains(Percept.Bump)) {

        }
        return Action.MoveForward;
    }
}
