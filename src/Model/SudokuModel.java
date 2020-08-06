package Model;

import Controller.MainController;

import java.util.Arrays;

public class SudokuModel {

    public static final int GRID_SIZE = 9;
    private char[][] grid;
    private MainController controller;

    public SudokuModel() {
        this.grid = getDefaultGrid();
    }

    public SudokuModel(char[][] grid) {
        this.grid = grid;
    }

    public char[][] getDefaultGrid() {
        char[][] defaultGrid = new char[GRID_SIZE][];

        defaultGrid[0] = "   26 7 1".toCharArray();
        defaultGrid[1] = "68  7  9 ".toCharArray();
        defaultGrid[2] = "19   45  ".toCharArray();
        defaultGrid[3] = "82 1   4 ".toCharArray();
        defaultGrid[4] = "  46 29  ".toCharArray();
        defaultGrid[5] = " 5   3 28".toCharArray();
        defaultGrid[6] = "  93   74".toCharArray();
        defaultGrid[7] = " 4  5  36".toCharArray();
        defaultGrid[8] = "7 3 18   ".toCharArray();
        return defaultGrid;
    }

    private Boolean checkGridLine(char[] gridLine, char itemForInsertion) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (gridLine[i] == itemForInsertion) {
                return false;
            }
        }
        return true;
    }

    private Boolean checkRow(int row, char itemForInsertion) {
        return checkGridLine(grid[row], itemForInsertion);
    }

    private Boolean checkColumn(int column, char itemForInsertion) {
        char[] gridColumn = new char[GRID_SIZE];
        for (int row = 0; row < GRID_SIZE; row++) {
            gridColumn[row] = grid[row][column];
        }
        return checkGridLine(gridColumn, itemForInsertion);
    }

    public Boolean checkBlock(int top, int left, char itemForInsertion) {
        char[] gridBlock = new char[GRID_SIZE];

        for (int row = top; row < top + 3; row++) {
            for (int col = left; col < left + 3; col++) {
                gridBlock[(3 * (row - top)) + (col - left)] = grid[row][col];
            }
        }
        return checkGridLine(gridBlock, itemForInsertion);
    }

    private char digitToChar(int digit) {
        return (char) (digit + 48);
    }

    public void solve() {
        for (int r = 0; r < GRID_SIZE; r++) {
            for (int c = 0; c < GRID_SIZE; c++) {
                if (grid[r][c] == ' ') {
                    for (int value = 1; value <= 9; value++) {
                        char valueInChar = digitToChar(value);
                        if (isSuitable(valueInChar, r, c)) {
                            grid[r][c] = valueInChar;
                            controller.notifyChange();
                            solve();
                            grid[r][c] = ' ';
                        }
                    }
                    return;
                }
            }
        }
    }

    private boolean isSuitable(char value, int r, int c) {
        if (!checkRow(r, value)) {
            return false;
        }
        if (!checkColumn(c, value)) {
            return false;
        }
        return checkBlock((r / 3) * 3, (c / 3) * 3, value);
    }

    public void printGrid(char[][] grid) {
        System.out.println("GRID ----------------------");
        for (int r = 0; r < GRID_SIZE; r++) {
            System.out.println(Arrays.toString(grid[r]));
        }
        System.out.println("---------------------------\n");
    }

    public char[][] getGrid() {
        return grid;
    }

    public void setController(MainController controller) {
        this.controller = controller;
    }
}
