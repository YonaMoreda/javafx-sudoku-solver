package Controller;

import Model.SudokuModel;
import View.HelpView;
import View.MainFrame;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class MainController {
    @FXML
    private Button clear_button;
    @FXML
    private Button help_button;
    @FXML
    private Button solve_button;
    @FXML
    private GridPane sudoku_grid_pane;

    private SudokuModel sudokuModel;

    @FXML
    public void initialize() {
        sudokuModel = new SudokuModel();
        drawGridValues();
        setSolveButtonEventAction();
        setClearButtonEventAction();
        setHelpButtonEventAction();
    }

    private void setHelpButtonEventAction() {
        help_button.setOnAction(actionEvent -> {
            HelpView helpView = new HelpView(MainFrame.getpStage());
            helpView.show();
        });
    }

    private void setClearButtonEventAction() {
        clear_button.setOnAction(actionEvent -> {
            sudokuModel = new SudokuModel(sudokuModel.initializeSudokuGrid());
            drawGridValues();
        });
    }

    private void setSolveButtonEventAction() {
        solve_button.setOnAction(actionEvent -> {
            sudokuModel = new SudokuModel(readViewGrid());
            drawGridValues();
            sudokuModel.solve();
            drawGridSolution();
        });
    }

    private char[][] readViewGrid() {
        char[][] returnGrid = sudokuModel.initializeSudokuGrid();
        int blockNo = 0;
        for (Node node : sudoku_grid_pane.getChildren()) {
            if (node instanceof GridPane) {
                GridPane blockGP = (GridPane) node;
                int blockItemIter = 0;
                for (Node blockNode : blockGP.getChildren()) {
                    if (blockNode instanceof TextField) {
                        TextField textField = (TextField) blockNode;
                        int r = (blockNo / 3) * 3 + (blockItemIter / 3);
                        int c = (blockNo % 3) * 3 + (blockItemIter % 3);
                        if (textField.getText().equals("")) {
                            returnGrid[r][c] = ' ';
                        } else {
                            returnGrid[r][c] = textField.getText().charAt(0);
                        }
                        blockItemIter++;
                    }
                }
            }
            blockNo++;
        }
        return returnGrid;
    }

    private void drawGridSolution() {
        sudokuModel.printGrid(sudokuModel.getSolvedGrid());
        int blockIter = 0;
        for (Node node : sudoku_grid_pane.getChildren()) {
            if (node instanceof GridPane) {
                GridPane blockGP = (GridPane) node;
                char[] blockLine = sudokuModel.getSolvedBlock((blockIter / 3) * 3, 3 * (blockIter % 3));
                int innerIter = 0;
                for (Node innerNode : blockGP.getChildren()) {
                    if (innerNode.getClass().getSimpleName().equals("TextField")) {
                        TextField numberText = (TextField) innerNode;

                        if (numberText.getText().equals(" ") || numberText.getText().equals("")) {
                            numberText.setText(Character.toString(blockLine[innerIter]));
                            numberText.setStyle("-fx-text-fill: lightcoral");
                        }
                        innerIter++;
                    }
                }
                blockIter++;
            }
        }
    }

    private void drawGridValues() {
        int blockIter = 0;
        for (Node node : sudoku_grid_pane.getChildren()) {
            if (node instanceof GridPane) {
                GridPane blockGP = (GridPane) node;

                //clearing the view grid
                Group blockGroup = (Group) blockGP.getChildren().get(0);
                blockGP.getChildren().clear();
                blockGP.getChildren().add(blockGroup);

                char[] blockLine = sudokuModel.getUnsolvedBlock((blockIter / 3) * 3, 3 * (blockIter % 3));
                for (int i = 0; i < SudokuModel.GRID_SIZE; i++) {
                    TextField numberText = new TextField(Character.toString(blockLine[i]));
                    numberText.setFont(Font.font("Times new roman", 20));
                    numberText.setStyle("-fx-text-fill: black");
                    GridPane.setHalignment(numberText, HPos.CENTER);
                    blockGP.add(numberText, i % 3, i / 3);
                }
                blockIter++;
            }
        }
    }
}
