package Controller;

import Model.SudokuModel;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

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
        drawGrid();
        setSolveButtonEventAction();
    }

    private void setSolveButtonEventAction() {
        solve_button.setOnAction(actionEvent -> {
            sudokuModel.solve();
        });
    }

    public void notifyChange() {
        updateGrid();
    }

    private void updateGrid() {
        char[][] grid = sudokuModel.getGrid();
        for (int r = 0; r < SudokuModel.GRID_SIZE; r++) {
            for (int c = 0; c < SudokuModel.GRID_SIZE; c++) {
                Label numberLabel = (Label) sudoku_grid_pane.getChildren().get(r * 9  +  c);
                assert numberLabel != null;

                numberLabel.setText(Character.toString(grid[r][c]));
            }
        }
    }

    private Node getGridPaneNode(int r, int c) {
        System.out.println(sudoku_grid_pane.getChildren());
        for(Node node : sudoku_grid_pane.getChildren()) {
            if(GridPane.getRowIndex(node) == r && GridPane.getColumnIndex(node) == c) {
                return node;
            }
        }
        return null;
    }

    private void drawGrid() {
        char[][] grid = sudokuModel.getGrid();
        for (int r = 0; r < SudokuModel.GRID_SIZE; r++) {
            for (int c = 0; c < SudokuModel.GRID_SIZE; c++) {
                Label numberLabel = new Label(Character.toString(grid[r][c]));
                numberLabel.setFont(Font.font("Times new roman", 20));
                GridPane.setHalignment(numberLabel, HPos.CENTER);
                sudoku_grid_pane.add(numberLabel, r, c);
            }
        }
    }

}
