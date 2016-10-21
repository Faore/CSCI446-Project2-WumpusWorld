package csci446.project2.Agents;

import csci446.project2.Agents.Inference.Engine;
import csci446.project2.Agents.KB.KnowledgeBase;
import csci446.project2.Util.Action;
import csci446.project2.WumpusWorld.Explorer;
import csci446.project2.WumpusWorld.Percept;
import csci446.project2.WumpusWorld.WumpusWorld;

import java.util.ArrayList;

/**
 * Created by cetho on 10/11/2016.
 */
public class ReactionExplorer implements Explorer {

    private KnowledgeBase kb;
    //private Engine engine;
    private int fire = 0;
    private int randomNum;
    
    public ReactionExplorer(WumpusWorld world) {
        kb = new KnowledgeBase(world);
        //engine = new Engine(kb);

    }
    
    @Override
    public Action determineMove(ArrayList<Percept> percepts) {
        //Used to help the agent turn
        randomNum = 1 + (int)(Math.random() * 100);
        //We found the gold.
        if(percepts.contains(Percept.Twinkle)) {
            return Action.PickUpGold;
        }
        //Has a 5 percent chance to shot an arrow if a wumpus is detected. That's really just for fun.
        if(percepts.contains(Percept.Smell) && randomNum < 5){
            return Action.FireArrow;
        }
        //The print statement here sums it up nicely
        if(percepts.contains(Percept.Scream)){
//            System.out.println();
//            System.out.println("Holy cow that actually worked!");
//            System.out.println("      >>-'( X __ X )'-|>      ");
//            System.out.println();
        }
        //if bump, turn 90 degrees clock wise
        if(percepts.contains(Percept.Bump)) {
            return Action.TurnRight;
        }
        //With no other knowledge for the agent to go on, it will have to turn to eventually avoid obstacles. 
        if(randomNum < 5){
            return Action.TurnLeft;
        }
        if(randomNum < 20){
            return Action.TurnRight;
        }
        return Action.MoveForward;
    }

}
