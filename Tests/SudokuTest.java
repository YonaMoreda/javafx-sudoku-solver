import Model.SudokuModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class SudokuTest {

    @Test
    public void sudokuSolverTest() {
        SudokuModel mySudokuModel = new SudokuModel();
        mySudokuModel.solve();
        char[][] solution = mySudokuModel.initializeSudokuGrid();
        solution[0] = new char[]{'4', '3', '5', '2', '6', '9', '7', '8', '1'};
        solution[1] = new char[]{'6', '8', '2', '5', '7', '1', '4', '9', '3'};
        solution[2] = new char[]{'1', '9', '7', '8', '3', '4', '5', '6', '2'};
        solution[3] = new char[]{'8', '2', '6', '1', '9', '5', '3', '4', '7'};
        solution[4] = new char[]{'3', '7', '4', '6', '8', '2', '9', '1', '5'};
        solution[5] = new char[]{'9', '5', '1', '7', '4', '3', '6', '2', '8'};
        solution[6] = new char[]{'5', '1', '9', '3', '2', '6', '8', '7', '4'};
        solution[7] = new char[]{'2', '4', '8', '9', '5', '7', '1', '3', '6'};
        solution[8] = new char[]{'7', '6', '3', '4', '1', '8', '2', '5', '9'};
        for (int i = 0; i < SudokuModel.GRID_SIZE; i++) {
            Assertions.assertArrayEquals(mySudokuModel.getSolvedGrid()[i], solution[i]);
        }
    }

    @Test
    public void isSuitableTest() {
        SudokuModel sudokuModel = new SudokuModel();

        Assertions.assertTrue(sudokuModel.isSuitable('3', 0, 0));
        Assertions.assertTrue(sudokuModel.isSuitable('4', 0, 0));
        Assertions.assertTrue(sudokuModel.isSuitable('9', 8, 8));

        Assertions.assertFalse(sudokuModel.isSuitable('6', 2, 2));
        Assertions.assertFalse(sudokuModel.isSuitable('4', 6, 0));
        Assertions.assertFalse(sudokuModel.isSuitable('8', 8, 8));
        Assertions.assertFalse(sudokuModel.isSuitable('4', 4, 4));
    }
}
