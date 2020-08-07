package Model;

import Controller.MainController;

import java.util.Arrays;

public class SudokuModel {

    public static final int GRID_SIZE = 9;
    private char[][] grid;
    private char[][] solvedGrid;
    private MainController controller;

    public SudokuModel() {
        this.grid = getDefaultGrid();
        initializeSolvedGrid();
    }

    private void initializeSolvedGrid() {
        this.solvedGrid = new char[GRID_SIZE][];
        for (int i = 0; i < GRID_SIZE; i++) {
            this.solvedGrid[i] = "         ".toCharArray();
        }
    }

    public SudokuModel(char[][] grid) {
        this.grid = grid;
        initializeSolvedGrid();
    }

    public char[][] getDefaultGrid() {
        char[][] defaultGrid = new char[GRID_SIZE][];

//        defaultGrid[0] = "53  7    ".toCharArray();
//        defaultGrid[1] = "6  195   ".toCharArray();
//        defaultGrid[2] = " 98    6 ".toCharArray();
//        defaultGrid[3] = "8   6   3".toCharArray();
//        defaultGrid[4] = "4  8 3  1".toCharArray();
//        defaultGrid[5] = "7   2   6".toCharArray();
//        defaultGrid[6] = " 6    28 ".toCharArray();
//        defaultGrid[7] = "   419  5".toCharArray();
//        defaultGrid[8] = "    8  79".toCharArray();

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
        return checkGridLine(getRow(row), itemForInsertion);
    }

    private char[] getRow(int row) {
        return grid[row];
    }

    private Boolean checkColumn(int column, char itemForInsertion) {
        char[] gridColumn = getColumn(column);
        return checkGridLine(gridColumn, itemForInsertion);
    }

    private char[] getColumn(int column) {
        char[] gridColumn = new char[GRID_SIZE];
        for (int row = 0; row < GRID_SIZE; row++) {
            gridColumn[row] = grid[row][column];
        }
        return gridColumn;
    }

    public Boolean checkBlock(int top, int left, char itemForInsertion) {
        char[] gridBlock = getUnsolvedBlock(top, left);
        return checkGridLine(gridBlock, itemForInsertion);
    }

    public char[] getUnsolvedBlock(int top, int left) {
        return getBlock(top, left, grid);
    }

    private char[] getBlock(int top, int left, char[][] grid) {
        char[] gridBlock = new char[GRID_SIZE];
        for (int row = top; row < top + 3; row++) {
            for (int col = left; col < left + 3; col++) {
                gridBlock[(3 * (row - top)) + (col - left)] = grid[row][col];
            }
        }
        return gridBlock;
    }

    public char[] getSolvedBlock(int top, int left) {
        return getBlock(top, left, solvedGrid);
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
                            solve();
                            grid[r][c] = ' ';
                        }
                    }
                    //tested all 1-9 digits, dead end /or solution return
//                    System.out.println("Returning / Back tracking");
                    return;
                }
            }
        }
        cloneGrid();
    }

    private void cloneGrid() {
        for (int r = 0; r < GRID_SIZE; r++) {
            for (int c = 0; c < GRID_SIZE; c++) {
                if (grid[r] == null) {
                    return;
                }
                solvedGrid[r][c] = grid[r][c];
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
        System.out.println("---------------------------");
    }

    public char[][] getSolvedGrid() {
        return this.solvedGrid;
    }

    public void setController(MainController controller) {
        this.controller = controller;
    }

    public char[][] getGrid() {
        return grid;
    }
}
