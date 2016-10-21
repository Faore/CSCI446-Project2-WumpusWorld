package csci446.project2.Agents.KB;

public class Cell {

    //Percepts
    public boolean pStench;
    public boolean pBreeze;
    //Facts
    public boolean determined; //Guaranteed answer
    public boolean safe; //Marked safe to walk on
    public boolean explored; //Visited this cell.

    public boolean isWumpus;
    public boolean isObstacle;
    public boolean isPit;

    public final int id;
    public final int x;
    public final int y;

    public Cell(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;

        pStench = false;
        pBreeze = false;

        determined = false;
        explored = false;
        safe = false;

        isObstacle = false;
        isPit = false;
        isWumpus = false;

    }
}
