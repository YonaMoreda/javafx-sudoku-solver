import Model.SudokuModel;
import org.junit.jupiter.api.Test;

public class SudokuTest {

//    @Test
//    public void blockCheckTest() {
//        Sudoku mySudoku = new Sudoku();
//
//        assertFalse(mySudoku.checkBlock(3, 3, '2'));
//        assertTrue(mySudoku.checkBlock(0, 0, '2'));
//        assertTrue(mySudoku.checkBlock(6, 3, '2'));
//    }

    @Test
    public void solvedTest() {
        SudokuModel mySudokuModel = new SudokuModel();
        mySudokuModel.solve();
    }
}
