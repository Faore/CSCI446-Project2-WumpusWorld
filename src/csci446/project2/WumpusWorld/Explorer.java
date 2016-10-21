package csci446.project2.WumpusWorld;

import csci446.project2.Util.Action;

import java.util.ArrayList;

public interface Explorer {
    public Action determineMove(ArrayList<Percept> percepts);
}
