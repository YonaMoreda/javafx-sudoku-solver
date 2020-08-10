package Model;

import java.util.Arrays;

/**
 * The sudoku data and its methods. The digits/grid is stored as a 2D array of chars
 */
public class SudokuModel {

    public static final int GRID_SIZE = 9;
    private char[][] grid;
    private char[][] solvedGrid;

    public SudokuModel() {
        this.grid = getSampleGrid();
        initializeSolvedGrid();
    }

    /**
     * initializes a standard sudoku grid (8x8) consisting of digits as chars
     *
     * @return: 2D empty char array
     */
    public char[][] initializeSudokuGrid() {
        char[][] returnGrid = new char[GRID_SIZE][];
        for (int i = 0; i < GRID_SIZE; i++) {
            returnGrid[i] = "         ".toCharArray();
        }
        return returnGrid;
    }

    private void initializeSolvedGrid() {
        this.solvedGrid = initializeSudokuGrid();
    }

    public SudokuModel(char[][] grid) {
        this.grid = grid;
        initializeSolvedGrid();
    }

    public char[][] getSampleGrid() {
        char[][] sampleGrid = new char[GRID_SIZE][];
        sampleGrid[0] = "   26 7 1".toCharArray();
        sampleGrid[1] = "68  7  9 ".toCharArray();
        sampleGrid[2] = "19   45  ".toCharArray();
        sampleGrid[3] = "82 1   4 ".toCharArray();
        sampleGrid[4] = "  46 29  ".toCharArray();
        sampleGrid[5] = " 5   3 28".toCharArray();
        sampleGrid[6] = "  93   74".toCharArray();
        sampleGrid[7] = " 4  5  36".toCharArray();
        sampleGrid[8] = "7 3 18   ".toCharArray();
        return sampleGrid;
    }


    /**
     * recursively solve the sudoku puzzle with backtracking
     */
    public void solve() {
        for (int r = 0; r < GRID_SIZE; r++) {
            for (int c = 0; c < GRID_SIZE; c++) {
                if (grid[r][c] == ' ') {
                    for (int value = 1; value <= 9; value++) {
                        char valueInChar = digitToChar(value);
                        if (isSuitable(valueInChar, r, c, grid)) {
                            grid[r][c] = valueInChar;
                            solve();
                            grid[r][c] = ' ';
                        }
                    }
                    // tested all 1-9 digits, dead end /or solution return
                    // Returning / Back tracking
                    return;
                }
            }
        }
        cloneGrid();
    }

    /**
     * copy from the grid under operation to auxiliary storage (solvedGrid)
     */
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

    public boolean isSuitable(char value, int r, int c) {
        return isSuitable(value, r, c, grid);
    }

    /**
     * check if a value to be inserted into a position in grid respects the rules of the sudoku game
     *
     * @param value: digit value to be inserted
     * @param r:     the row position for insertion
     * @param c:     the column position for insertion
     * @param grid:  the 2D sudoku grid
     * @return: true if the value is suitable
     */
    public boolean isSuitable(char value, int r, int c, char[][] grid) {
        if (!checkRow(r, value, grid)) {
            return false;
        }
        if (!checkColumn(c, value, grid)) {
            return false;
        }
        return checkBlock((r / 3) * 3, (c / 3) * 3, value, grid);
    }

    /**
     * pretty printer for showing the grid
     *
     * @param grid: the sudoku grid
     */
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

    /**
     * empty parameter wrapper for isValidSudokuGrid(char[][] grid)
     *
     * @return
     */
    public Tuple<Integer, Integer, Boolean> isValidSudokuGrid() {
        return isValidSudokuGrid(this.grid);
    }

    /**
     * checks for repetitions and emptiness of grid to check for validity of the provided sudoku grid
     *
     * @param grid: sudoku grid values
     * @return: Tuple consisting of the problematic cell: row, column and the validity boolean
     */
    public Tuple<Integer, Integer, Boolean> isValidSudokuGrid(char[][] grid) {
        int emptyGridValues = 0;
        for (int r = 0; r < GRID_SIZE; r++) {
            for (int c = 0; c < GRID_SIZE; c++) {
                if (grid[r][c] == ' ') {
                    emptyGridValues++;
                    continue;
                }
                char itemForChecking = grid[r][c];
                grid[r][c] = ' ';
                if (!isSuitable(itemForChecking, r, c, grid)) {
                    grid[r][c] = itemForChecking;
                    return new Tuple<>(r, c, false);
                }
                grid[r][c] = itemForChecking;
            }
        }
        // The Minimum Number of Clues Problem (17 or more clues necessary)
        if (emptyGridValues > 64) {
            return new Tuple<>(-1, -1, false);
        }
        return new Tuple<>(-1, -1, true);
    }

    private Boolean checkGridLine(char[] gridLine, char itemForInsertion) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (gridLine[i] == itemForInsertion) {
                return false;
            }
        }
        return true;
    }

    private Boolean checkRow(int row, char itemForInsertion, char[][] grid) {
        return checkGridLine(getRow(row, grid), itemForInsertion);
    }

    private char[] getRow(int row, char[][] grid) {
        return grid[row];
    }

    public Boolean checkColumn(int column, char itemForInsertion, char[][] grid) {
        char[] gridColumn = getColumn(column, grid);
        return checkGridLine(gridColumn, itemForInsertion);
    }

    private char[] getColumn(int column, char[][] grid) {
        char[] gridColumn = new char[GRID_SIZE];
        for (int row = 0; row < GRID_SIZE; row++) {
            gridColumn[row] = grid[row][column];
        }
        return gridColumn;
    }

    public Boolean checkBlock(int top, int left, char itemForInsertion, char[][] grid) {
        char[] gridBlock = getUnsolvedBlock(top, left, grid);
        return checkGridLine(gridBlock, itemForInsertion);
    }

    public char[] getUnsolvedBlock(int top, int left, char[][] grid) {
        return getBlock(top, left, grid);
    }

    public char[] getUnsolvedBlock(int top, int left) {
        return getUnsolvedBlock(top, left, grid);
    }

    public char[] getBlock(int top, int left, char[][] grid) {
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
}
