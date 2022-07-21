package sudoku.computationlogic;

import sudoku.problemdomain.SudokuGame;

public class SudokuUtilities {

    public static void copySudokuArrayValues(int[][] oldArray, int[][] newArray) {
        for (int xIndex = 0; xIndex < SudokuGame.GRID_BOUDARY; xIndex++) {
            for (int yIndex = 0; yIndex < SudokuGame.GRID_BOUDARY; yIndex++) {
                newArray[xIndex][yIndex] = oldArray[xIndex][yIndex];
            }
        }
    }

    public static int[][] copyToNewArray(int[][] oldArray) {
        int[][] newArray = new int[SudokuGame.GRID_BOUDARY][SudokuGame.GRID_BOUDARY];

        for (int xIndex = 0; xIndex < SudokuGame.GRID_BOUDARY; xIndex++) {
            for (int yIndex = 0; yIndex < SudokuGame.GRID_BOUDARY; yIndex++) {
                newArray[xIndex][yIndex] = oldArray[xIndex][yIndex];
            }
        }
        return newArray;
    }

}