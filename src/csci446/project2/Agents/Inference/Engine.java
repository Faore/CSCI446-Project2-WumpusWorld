package csci446.project2.Agents.Inference;

import csci446.project2.Agents.KB.Cell;
import csci446.project2.Agents.KB.KnowledgeBase;
import csci446.project2.Util.Action;

import java.util.ArrayList;

public class Engine {

    private KnowledgeBase kb;
    private ArrayList<Sentence> rules;
    private ArrayList<Clause> facts;

    public Engine(KnowledgeBase kb) throws Exception {
        this.kb = kb;
        this.rules = Rules.generateRules();
        this.facts = kb.facts;
    }

    public ArrayList<Clause> generateFacts() throws Exception {
        //Run resolution for each rule with each cell. Slow but functional.
        ArrayList<Clause> facts = new ArrayList<Clause>();
        for (Cell cell : this.kb.KBList) {
            //Clean rules for manipulation.
            this.rules = Rules.generateRules();
            //Attempt to resolve each rule, and take any facts it added and add them to the list.
            for (Sentence rule: this.rules) {
                facts.addAll(resolveRule(rule, cell));
            }
        }
        return facts;
    }

    public ArrayList<Clause> resolveRule(Sentence rule, Cell implicant) {
        ArrayList<Clause> facts = new ArrayList<>();
        //modus ponus

        return facts;
    }

    private boolean isSafe(int x, int y){
        // Ask the KB if a certain space is safe.  Returns false if it is unknown or if it is not valid.
        for(Cell cell : kb.exploredCells){
            if(cell.x == x && cell.y == y) {
                if (cell.isPit || cell.isWumpus || cell.isObstacle)
                    return false;
            }
        }
        return true;
    }

    public Action findBestMove() {
        int curX = 0, curY = 0;

        if(isGold(curX, curY))
            return Action.PickUpGold;
        else if(isSafe(curX + 1, curY))
            return Action.TurnRight;
        else if(isSafe(curX - 1, curY))
            return Action.TurnLeft;
        else if(isSafe(curX, curY + 1))
            return Action.MoveForward;
        else if(isSafe(curX, curY - 1))
            return Action.TurnRight;
        else if(isWumpus(curX, curY))
            return Action.FireArrow;

        return Action.MoveForward;
    }

    private boolean isWumpus(int curX, int curY) {
        for(Cell cell : kb.exploredCells){
            if(cell.isWumpus)
                return true;
        }
        return false;
    }

    private boolean isGold(int curX, int curY) {
        for(Cell cell : kb.exploredCells){
        }
        return false;
    }
}
