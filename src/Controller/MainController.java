package Controller;

import Model.SudokuModel;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MainController {
    @FXML
    private Button solve_button;
    @FXML
    private GridPane sudoku_grid_pane;

    SudokuModel sudokuModel;

    @FXML
    public void initialize() {
        sudokuModel = new SudokuModel();
        sudokuModel.setController(this);
        drawGrid(sudokuModel.getGrid());
        setSolveButtonEventAction();
    }

    private void setSolveButtonEventAction() {
        solve_button.setOnAction(actionEvent -> {
            sudokuModel.solve();
//            sudokuModel.printGrid(sudokuModel.getSolvedGrid());
            updateGrid(sudokuModel.getSolvedGrid());
        });
    }

    public void notifyChange() {
//        updateGrid();
        System.out.println("TO IMPLEMENT");
    }

    private Node getGridPaneNode(int r, int c) {
        for (Node node : sudoku_grid_pane.getChildren()) {
            if (sudoku_grid_pane.getChildren().indexOf(node) == 0) {
                continue;
            }
            if (GridPane.getRowIndex(node) == r && GridPane.getColumnIndex(node) == c) {
                return node;
            }
        }
        return null;
    }

    private void updateGrid(char[][] grid) {
        for (int r = 0; r < SudokuModel.GRID_SIZE; r++) {
            for (int c = 0; c < SudokuModel.GRID_SIZE; c++) {

                Text numberText = (Text) getGridPaneNode(r, c);
                assert numberText != null;
                if(numberText.getText().equals(" ")) {
                    numberText.setFill(Color.LIGHTCORAL);
                }
                numberText.setText(Character.toString(grid[r][c]));
            }
        }
    }

    private void drawGrid(char[][] grid) {
        for (int r = 0; r < SudokuModel.GRID_SIZE; r++) {
            for (int c = 0; c < SudokuModel.GRID_SIZE; c++) {
                Text numberText = new Text(Character.toString(grid[r][c]));
                numberText.setFont(Font.font("Times new roman", 20));
                GridPane.setHalignment(numberText, HPos.CENTER);
                sudoku_grid_pane.add(numberText, c, r);
            }
        }
    }
}
