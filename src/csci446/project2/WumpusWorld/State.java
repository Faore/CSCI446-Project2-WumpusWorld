package csci446.project2.WumpusWorld;

import java.util.ArrayList;

/**
 * Created by cetho on 10/14/2016.
 */
public class State {
    protected int x;
    protected int y;
    protected Cell location;
    protected int remainingArrows;
    protected Orientation orientation;

    protected boolean hasGold;
    protected int deaths;

    protected Action actionTaken;

    protected ArrayList<Result> results = new ArrayList<Result>();

    protected State copy() {
        State state = new State();
        state.x = x;
        state.y = y;
        state.location = location;
        state.remainingArrows = remainingArrows;
        state.orientation = orientation;
        state.hasGold = hasGold;
        state.deaths = deaths;

        return state;

    }
}
