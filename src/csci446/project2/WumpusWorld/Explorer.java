package csci446.project2.WumpusWorld;

import csci446.project2.Util.Action;

import java.util.ArrayList;

/**
 * Created by cetho on 10/11/2016.
 */
public interface Explorer {
    public Action determineMove(ArrayList<Percept> percepts);
}
