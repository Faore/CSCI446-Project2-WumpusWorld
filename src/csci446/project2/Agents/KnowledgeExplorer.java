package csci446.project2.Agents;

import csci446.project2.WumpusWorld.Action;
import csci446.project2.WumpusWorld.Explorer;
import csci446.project2.WumpusWorld.Percept;

import java.util.ArrayList;

/**
 * Created by cetho on 10/11/2016.
 */
public class KnowledgeExplorer implements Explorer {

    private KnowledgeBase kb;
    private int t;

    public KnowledgeExplorer() {
        kb = new KnowledgeBase();
        t = 0;
    }

    public Action determineMove(ArrayList<Percept> percepts) {
        kb.tell(makePerceptSentence(percepts));
        Action action = kb.ask(makeActionQuery());
        kb.tell(makeActionSentence(action));

        t++;

        return action;
    }

    public Sentence makePerceptSentence(ArrayList<Percept> percepts) {
        return new Sentence();
    }

    public Sentence makeActionSentence(Action action) {
        return new Sentence();
    }
    public Query makeActionQuery() {
        return new Query();
    }
}
