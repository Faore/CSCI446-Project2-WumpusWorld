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
public class KnowledgeExplorer implements Explorer {

    private KnowledgeBase kb;
    private Engine engine;

    public KnowledgeExplorer(WumpusWorld world) throws Exception {
        kb = new KnowledgeBase(world);
        engine = new Engine(kb);
    }

    @Override
    public Action determineMove(ArrayList<Percept> percepts) {
        //Tell the KB everything we know.
        kb.addPercepts(percepts);
        //Tell the inference engine to see if it can learn anything new.

        /*
        Tell the inference engine to generate a plan and gimme an action.
        I'm thinking I'll store the plan in the KB and have it iterate through the plan for each move,
        logging the percepts and then creating new facts. Then generate a new plan when the existing one is finished.
        This should help prevent plans from being constantly created and having the agent loop in something.
        */
        return null;
    }
}
