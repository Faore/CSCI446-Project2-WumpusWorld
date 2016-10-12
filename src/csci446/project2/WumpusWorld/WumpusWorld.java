package csci446.project2.WumpusWorld;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by cetho on 10/11/2016.
 */
public class WumpusWorld {

    private Cell[][] world;
    private Explorer explorer;
    private int playerStartX;
    private int playerStartY;

    private ArrayList<Cell> obstacleCells;
    private ArrayList<Cell> pitCells;
    private ArrayList<Cell> wumpusCells;
    private ArrayList<Cell> emptyCells;


    public WumpusWorld(int size, double obstacleProbability, double pitProbability, double wumpusProbability) throws Exception {
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
    }

    public void simulate() {

    }
}
