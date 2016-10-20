package csci446.project2.Util;

/**
 * Created by cetho on 10/20/2016.
 */
public class LocationCalc {

    public static Pair<Integer, Integer> NextLocation(int curX, int curY, Orientation orientation) {
        int nextX;
        int nextY;
        if(orientation == Orientation.North) {
            nextX = curX;
            nextY = curY + 1;
        }
        else if(orientation == Orientation.East) {
            nextX = curX + 1;
            nextY = curY;
        }
        else if(orientation == Orientation.South) {
            nextX = curX;
            nextY = curY - 1;
        }
        else {
            nextX = curX - 1;
            nextY = curY;
        }
        return new Pair<Integer, Integer>(nextX, nextY);
    }

    public static Orientation NextOrientation(Action action, Orientation current) {
        if(action == Action.TurnLeft) {
            if(current == Orientation.North) {
                return Orientation.West;
            }
            if(current == Orientation.East) {
                return Orientation.North;
            }
            if(current == Orientation.South) {
                return Orientation.East;
            }
            if(current == Orientation.West) {
                return Orientation.South;
            }
        }
        else {
            if(current == Orientation.North) {
                return Orientation.East;
            }
            if(current == Orientation.East) {
                return Orientation.South;
            }
            if(current == Orientation.South) {
                return Orientation.West;
            }
            if(current == Orientation.West) {
                return Orientation.North;
            }
        }
        //No orientation change.
        return current;
        }
}
