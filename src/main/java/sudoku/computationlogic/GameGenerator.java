package sudoku.computationlogic;

import sudoku.problemdomain.Coordinates;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static sudoku.problemdomain.SudokuGame.GRID_BOUDARY;

public class GameGenerator {

    public static int [][] getNewGameGrid() {
        return unsolveGame(getSolvedGame());
    }

    private static int[][] unsolveGame(int[][] solvedGame) {

        Random random = new Random(System.currentTimeMillis());

        boolean solvable = false;
        int[][] solvableArray = new int[GRID_BOUDARY][GRID_BOUDARY];

        while (solvable == false) {
            SudokuUtilities.copySudokuArrayValues(solvedGame,solvableArray);
            int index = 0;

            while (index < 40) {
                int xCodrdinate = random.nextInt(GRID_BOUDARY);
                int yCodrdinate = random.nextInt(GRID_BOUDARY);

                if (solvableArray[xCodrdinate][yCodrdinate] != 0) {
                    solvableArray[xCodrdinate][yCodrdinate] = 0;
                    index++;
                }
            }

            int[][] toBeSolved = new int[GRID_BOUDARY][GRID_BOUDARY];
            SudokuUtilities.copySudokuArrayValues(solvableArray,toBeSolved);

            solvable = SudokuSolver.puzzleIsSolvable(toBeSolved);

        }

        return solvableArray;
    }

    private static int[][] getSolvedGame() {
        Random random = new Random(System.currentTimeMillis());
        int[][] newGrid = new int[GRID_BOUDARY][GRID_BOUDARY];

        for (int value = 1;value <= GRID_BOUDARY; value++) {
            int allocations = 0;
            int interrupt = 0;

            List<Coordinates> allocTracker = new ArrayList<>();

            int attempts = 0;

            while (allocations < GRID_BOUDARY) {
                if (interrupt >200) {
                    allocTracker.forEach(coord -> {
                        newGrid[coord.getX()][coord.getY()] = 0;
                    });

                    interrupt = 0;
                    allocations = 0;
                    allocTracker.clear();
                    attempts++;

                    if (attempts >500) {
                        clearArray(newGrid);
                        attempts = 0;
                        value = 1;
                    }
                }

                int xCordinate = random.nextInt(GRID_BOUDARY);
                int yCordinate = random.nextInt(GRID_BOUDARY);

                if(newGrid[xCordinate][yCordinate] == 0) {
                    newGrid[xCordinate][yCordinate] = value;

                    if(GameLogic.sudokuIsInvalid(newGrid)) {
                        newGrid[xCordinate][yCordinate] = 0;
                        interrupt++;
                    } else {
                        allocTracker.add(new Coordinates(xCordinate,yCordinate));
                        allocations++;
                    }


                }
            }

        }

        return newGrid;
    }

    private static void clearArray(int[][] newGrid) {

        for (int xIndex = 0; xIndex < GRID_BOUDARY; xIndex++) {
            for (int yIndex = 0; yIndex < GRID_BOUDARY; yIndex++) {
                newGrid[xIndex][yIndex] = 0;
            }
        }

    }

}
