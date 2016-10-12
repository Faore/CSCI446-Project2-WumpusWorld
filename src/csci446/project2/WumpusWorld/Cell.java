package csci446.project2.WumpusWorld;

/**
 * Created by cetho on 10/11/2016.
 */
public class Cell {
    protected final boolean isObstacle;
    protected final boolean isPit;
    protected final boolean isWumpus;
    protected boolean wumpusKilled;
    protected boolean hasGold;
    protected boolean goldPickedUp;
    protected int x;
    protected int y;

    protected Cell(boolean isObstacle, boolean isPit, boolean isWumpus, int x, int y) throws Exception {
        if((isObstacle && isPit) || (isObstacle && isWumpus) || (isPit && isWumpus)) {
            throw new Exception("Invalid Cell");
        }
        else {
            this.isObstacle = isObstacle;
            this.isPit = isPit;
            this.isWumpus = isWumpus;
            this.x = x;
            this.y = y;
        }
    }

    protected void killWumpus() {
        wumpusKilled = true;
    }

    protected void reset() {
        wumpusKilled = false;
    }

    protected boolean hasWumpus() {
        if(isWumpus && !wumpusKilled) {
            return true;
        }
        return false;
    }

    protected boolean hasGold() {
        if (hasGold && !goldPickedUp) {
            return true;
        }
        return false;
    }

    protected boolean pickUpGold() {
        if (hasGold()) {
            this.goldPickedUp = true;
            return true;
        }
        return false;
    }
}
