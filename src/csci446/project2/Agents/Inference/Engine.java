package csci446.project2.Agents.Inference;

import csci446.project2.Agents.KB.Cell;
import csci446.project2.Agents.KB.KnowledgeBase;
import csci446.project2.Util.Action;

import java.util.ArrayList;

/**
 * Created by cetho on 10/20/2016.
 */
public class Engine {

    private KnowledgeBase kb;
    private ArrayList<Sentence> rules;
    private ArrayList<Clause> facts;

    public Engine(KnowledgeBase kb) {
        this.kb = kb;
        this.rules = Rules.generateRules();
        this.facts = kb.facts;
    }

    public ArrayList<Clause> generateFacts() {
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
        ArrayList<Clause> facts = new ArrayList<Clause>();

        return facts;
    }

    public Action findBestMove() {
        return null;
    }
}