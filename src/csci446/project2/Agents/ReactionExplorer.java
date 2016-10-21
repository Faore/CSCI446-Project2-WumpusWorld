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
    private Engine engine;
    private int count;
    private int fire = 0;
    private int randomNum;
    
    public ReactionExplorer(WumpusWorld world) {
        kb = new KnowledgeBase(world);
        engine = new Engine(kb);
    }
    
    @Override
    public Action determineMove(ArrayList<Percept> percepts) {
        System.out.println(percepts);
        if(count == 4){
            randomNum = 1 + (int)(Math.random() * 100);
        }
        //We found the gold.
        if(percepts.contains(Percept.Twinkle)) {
            //System.out.println("----------------------------------------------------");
            return Action.PickUpGold;
        }
        //Fires an arrow if wumpus is detected
        if(percepts.contains(Percept.Smell) && fire < 1){
            fire = 1;
            return Action.FireArrow;
        }
        if(percepts.contains(Percept.Scream)){
            System.out.println("Holy cow that actually worked!");
        }
        //if bump, turn 90 degrees clock wise
        if(percepts.contains(Percept.Bump)) {
            if(count < 4){
            count = count + 1;}
            
            return Action.TurnRight;
        }
        if(randomNum < 25 && count == 4){
            return Action.TurnLeft;
        }
        return Action.MoveForward;
    }

}
