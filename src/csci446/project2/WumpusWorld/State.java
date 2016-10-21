package csci446.project2.WumpusWorld;

import csci446.project2.Util.Action;
import csci446.project2.Util.Orientation;

import java.util.ArrayList;

/**
 * Created by cetho on 10/14/2016.
 */
public class State {
    public int x;
    public int y;
    public Cell location;
    public int remainingArrows;
    public Orientation orientation;

    public boolean hasGold;
    public int deaths;
    public int wumpusesKilled;

    public Action actionTaken;

    public ArrayList<Result> results = new ArrayList<Result>();
    public ArrayList<Percept> givenPercepts = new ArrayList<Percept>();

    public int penaltyScore = 0;

    public State copy() {
        State state = new State();
        state.x = x;
        state.y = y;
        state.location = location;
        state.remainingArrows = remainingArrows;
        state.orientation = orientation;
        state.hasGold = hasGold;
        state.deaths = deaths;
        state.penaltyScore = penaltyScore;
        state.wumpusesKilled = wumpusesKilled;

        return state;

    }
}
