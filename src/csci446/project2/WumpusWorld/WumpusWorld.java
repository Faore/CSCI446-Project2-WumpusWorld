package csci446.project2.WumpusWorld;

import csci446.project2.Util.Action;
import csci446.project2.Util.LocationCalc;
import csci446.project2.Util.Orientation;
import csci446.project2.Util.Pair;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by cetho on 10/11/2016.
 */
public class WumpusWorld {

    public final int worldSize;

    private Cell[][] world;

    private Explorer explorer;
    //Independently store state information
    private ArrayList<State> states;
    //Start Location
    public int playerStartX;
    public int playerStartY;
    //Cells for reference.
    private ArrayList<Cell> obstacleCells;
    private ArrayList<Cell> pitCells;
    private ArrayList<Cell> wumpusCells;
    private ArrayList<Cell> emptyCells;
    //Queued Percepts
    private ArrayList<Percept> queuedPercepts = new ArrayList<Percept>();

    public WumpusWorld(int size, double obstacleProbability, double pitProbability, double wumpusProbability) throws Exception {
        this.worldSize = size;
        this.world = new Cell[size][size];
        if(obstacleProbability + pitProbability + wumpusProbability >= 1) {
            throw new Exception("Invalid probabilities.");
        }

        Random random =  new Random();

        emptyCells = new ArrayList<Cell>();
        obstacleCells = new ArrayList<Cell>();
        pitCells = new ArrayList<Cell>();
        wumpusCells = new ArrayList<Cell>();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                double roll = random.nextDouble();
                if(roll < obstacleProbability) {
                    this.world[i][j] = new Cell(true, false, false, i, j);
                    obstacleCells.add(this.world[i][j]);
                }
                else if(roll >= obstacleProbability && roll < (obstacleProbability + pitProbability)) {
                    this.world[i][j] = new Cell(false, true, false, i, j);
                    pitCells.add(this.world[i][j]);
                }

                else if(roll >= (obstacleProbability + pitProbability) && roll < obstacleProbability + pitProbability + wumpusProbability) {
                    this.world[i][j] = new Cell(false, false, true, i, j);
                    wumpusCells.add(this.world[i][j]);
                }
                else {
                    this.world[i][j] = new Cell(false, false, false, i, j);
                    emptyCells.add(this.world[i][j]);
                }
            }
        }

        Cell startCell = emptyCells.get(random.nextInt(emptyCells.size()));
        playerStartX = startCell.x;
        playerStartY = startCell.y;

        this.states = new ArrayList<State>();

        //Create the start state.
        State state = new State();
        state.x = playerStartX;
        state.y = playerStartY;
        state.deaths = 0;
        state.hasGold = false;
        state.location = world[playerStartX][playerStartY];
        state.remainingArrows = wumpusCells.size();
        state.orientation = Orientation.North;

        states.add(state);
    }

    public void attachAgent(Explorer explorer) {
        this.explorer = explorer;
    }

    public void reset() {
        for(int i = 0; i < world.length; i++) {
            for(int j = 0; j < world.length; j++) {
                this.world[i][j].reset();
            }
        }
        this.states = new ArrayList<State>();

        //Create the start state.
        State state = new State();
        state.x = playerStartX;
        state.y = playerStartY;
        state.deaths = 0;
        state.hasGold = false;
        state.location = world[playerStartX][playerStartY];
        state.remainingArrows = wumpusCells.size();

        states.add(state);
    }

    public void simulate() {
        while (!states.get(states.size() - 1).hasGold) {
            ArrayList<Percept> percepts = new ArrayList<Percept>();

            percepts.addAll(queuedPercepts);
            queuedPercepts.clear();

            Cell cell = states.get(states.size() - 1).location;

            percepts.addAll(getPercepts(cell));

            Action action = this.explorer.determineMove(percepts);

            performAction(percepts, action);
        }
    }

    public boolean inBounds(Pair<Integer, Integer> pair) {
        return inBounds(pair.left, pair.right);
    }

    public boolean inBounds(int x, int y) {
        if(x < 0 || x > world.length - 1 || y < 0 || y > world.length - 1) {
            return false;
        }
        return true;
    }

    private void performAction(ArrayList<Percept> givenPercepts, Action action) {
        State state = states.get(states.size() - 1).copy();
        state.givenPercepts = givenPercepts;
        state.actionTaken = action;

        if (action == Action.TurnLeft || action == Action.TurnRight) {
            state.orientation = LocationCalc.NextOrientation(action, state.orientation);
            //Each move costs 1 point.
            state.penaltyScore--;
            state.results.add(Result.OrientationChanged);
        }

        if (action == Action.MoveForward) {
            int nextX;
            int nextY;
            //Each move costs 1 point.
            state.penaltyScore--;

            Pair<Integer, Integer> next = LocationCalc.NextLocation(state.x, state.y, state.orientation);

            nextX = next.left;
            nextY = next.right;

            if(inBounds(nextX, nextY)) {
                Cell cell = world[nextX][nextY];
                if(cell.isPit) {
                    state.deaths++;
                    state.penaltyScore -= 1000;
                    state.results.add(Result.FallInPit);
                }
                else if (cell.hasWumpus()) {
                    state.deaths++;
                    state.penaltyScore -= 1000;
                    state.results.add(Result.DieByWumpus);
                    state.x = nextX;
                    state.y = nextY;
                    state.location = world[nextX][nextY];
                    cell.killWumpus();
                }
                else if(cell.isObstacle) {
                    state.results.add(Result.BumpIntoWall);
                    queuedPercepts.add(Percept.Bump);
                }
                else {
                    state.x = nextX;
                    state.y = nextY;
                    state.location = world[nextX][nextY];
                    state.results.add(Result.SuccessfulMove);
                }
            } else {
                state.results.add(Result.BumpIntoWall);
                queuedPercepts.add(Percept.Bump);
            }
        }

        if(action == Action.PickUpGold) {
            state.penaltyScore--;
            if(state.location.hasGold()) {
                state.results.add(Result.PickUpGold);
                state.hasGold = true;
                state.penaltyScore += 1000;
            } else {
                state.results.add(Result.FailToPickUpGold);
            }
        }
        if(action == Action.FireArrow) {
            state.remainingArrows--;
            state.penaltyScore -= 10;
            if(state.orientation == Orientation.North) {
                state.results.add(Result.MissArrow);
                for(int i = state.y; i < world.length; i++) {
                    if (world[state.x][i].isObstacle) {
                        break;
                    }
                    if(world[state.x][i].hasWumpus()) {
                        world[state.x][i].killWumpus();
                        state.remainingArrows++;
                        state.penaltyScore += 10;
                        state.results.remove(Result.MissArrow);
                        state.results.add(Result.KillWumpus);
                        break;
                    }
                }
            } else if(state.orientation == Orientation.East) {
                state.results.add(Result.MissArrow);
                for(int i = state.y; i < world.length; i++) {
                    if (world[i][state.y].isObstacle) {
                        break;
                    }
                    if(world[i][state.y].hasWumpus()) {
                        world[i][state.y].killWumpus();
                        state.remainingArrows++;
                        state.penaltyScore += 10;
                        state.results.remove(Result.MissArrow);
                        state.results.add(Result.KillWumpus);
                        break;
                    }
                }
            } else if(state.orientation == Orientation.South) {
                state.results.add(Result.MissArrow);
                for(int i = state.y; i >= 0; i--) {
                    if (world[state.x][i].isObstacle) {
                        break;
                    }
                    if(world[state.x][i].hasWumpus()) {
                        world[state.x][i].killWumpus();
                        state.remainingArrows++;
                        state.penaltyScore += 10;
                        state.results.remove(Result.MissArrow);
                        state.results.add(Result.KillWumpus);
                        break;
                    }
                }
            } else {
                //West
                state.results.add(Result.MissArrow);
                for(int i = state.y; i >= 0; i--) {
                    if (world[i][state.y].isObstacle) {
                        break;
                    }
                    if(world[i][state.y].hasWumpus()) {
                        world[i][state.y].killWumpus();
                        state.remainingArrows++;
                        state.penaltyScore += 10;
                        state.results.remove(Result.MissArrow);
                        state.results.add(Result.KillWumpus);
                        break;
                    }
                }
            }
        }
        this.states.add(state);

        if(csci446.project2.Main.showStates) {
            System.out.println("WumpusWorld State Change:");

            System.out.print("\tGiven Percepts: ");
            for( Percept percept : state.givenPercepts) {
                System.out.print(percept + ", ");
            }
            System.out.println();

            System.out.println("\tAction Taken: " + state.actionTaken);

            System.out.print("\tResults: ");
            for( Result result : state.results) {
                System.out.print(result + ", ");
            }
            System.out.println();

            System.out.println("\tLocation: (" + state.x + ", " + state.y + ")");
            System.out.println("\tOrientation: " + state.orientation);

            System.out.println("\tPenalty Function Score: " + state.penaltyScore);
        }
    }

    private ArrayList<Percept> getPercepts(Cell cell) {
        ArrayList<Percept> list = new ArrayList<Percept>();
        int x = cell.x;
        int y = cell.y;
        //Check top cell
        if(inBounds(x, y+1)) {
            if(world[x][y+1].isPit) {
                list.add(Percept.Breeze);
            }
            if(world[x][y+1].hasWumpus()) {
                list.add(Percept.Smell);
            }
            if(world[x][y+1].hasGold()) {
                list.add(Percept.Twinkle);
            }
        }
        //Check right cell
        if(inBounds(x+1, y)) {
            if(world[x+1][y].isPit) {
                list.add(Percept.Breeze);
            }
            if(world[x+1][y].hasWumpus()) {
                list.add(Percept.Smell);
            }
            if(world[x+1][y].hasGold()) {
                list.add(Percept.Twinkle);
            }
        }
        //Check bottom cell
        if(inBounds(x, y-1)) {
            if(world[x][y-1].isPit) {
                list.add(Percept.Breeze);
            }
            if(world[x][y-1].hasWumpus()) {
                list.add(Percept.Smell);
            }
            if(world[x][y-1].hasGold()) {
                list.add(Percept.Twinkle);
            }
        }
        //Check left cell
        if(inBounds(x-1, y)) {
            if(world[x-1][y].isPit) {
                list.add(Percept.Breeze);
            }
            if(world[x-1][y].hasWumpus()) {
                list.add(Percept.Smell);
            }
            if(world[x-1][y].hasGold()) {
                list.add(Percept.Twinkle);
            }
        }
        return list;
    }
}
