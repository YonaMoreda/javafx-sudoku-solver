package Controller;

import Model.SudokuModel;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
        drawGridValues();
        setSolveButtonEventAction();
    }

    private void setSolveButtonEventAction() {
        solve_button.setOnAction(actionEvent -> {

//            sudokuModel = new SudokuModel(readViewGrid());
            sudokuModel.solve();
            drawGridSolution();
        });
    }

    private char[][] readViewGrid() {
        char[][] returnGrid = new char[SudokuModel.GRID_SIZE][];
        for (int i = 0; i < SudokuModel.GRID_SIZE; i++) {
            returnGrid[i] = "         ".toCharArray();
        }

        int blockNo = 0;
        for (Node node : sudoku_grid_pane.getChildren()) {
            if (node.getClass().getSimpleName().equals("GridPane")) {
                GridPane blockGP = (GridPane) node;
//                StringBuilder stringBuilder = new StringBuilder();
                int blockItemIter = 0;
                for(Node blockNode : blockGP.getChildren()) {
                    if (blockNode.getClass().getSimpleName().equals("TextField")) {
                        TextField textField = (TextField) blockNode;
//                        stringBuilder.append(textField.getText().charAt(0));
                        int r = (3 * blockNo) + (blockItemIter / 3);
                        int c = (3 * blockNo) + (blockItemIter % 3);
                        System.out.println("r:" + r + c);
                        returnGrid[r][c] = textField.getText().charAt(0);
                    }
                    blockItemIter++;
                }
//                System.out.println("A block: \"" + stringBuilder.toString() + "\"");
            }
            blockNo++;
        }
        return returnGrid;
    }

    private void drawGridSolution() {
        System.out.println("DRAWING SOLUTION");
        sudokuModel.printGrid(sudokuModel.getSolvedGrid());
        int blockIter = 0;
        for (Node node : sudoku_grid_pane.getChildren()) {
            if (node.getClass().getSimpleName().equals("GridPane")) {
                GridPane blockGP = (GridPane) node;
                char[] blockLine = sudokuModel.getSolvedBlock((blockIter / 3) * 3, 3 * (blockIter % 3));
//                System.out.println("Block: " + Arrays.toString(blockLine));
                int innerIter = 0;
                for (Node innerNode : blockGP.getChildren()) {
                    if (innerNode.getClass().getSimpleName().equals("TextField")) {
                        TextField numberText = (TextField) innerNode;
                        if(numberText.getText().equals(" ")) {
                            numberText.setStyle("-fx-text-fill: lightcoral");
                            numberText.setText(Character.toString(blockLine[innerIter]));
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
            if (node.getClass().getSimpleName().equals("GridPane")) {
                GridPane blockGP = (GridPane) node;
                char[] blockLine = sudokuModel.getUnsolvedBlock((blockIter / 3) * 3, 3 * (blockIter % 3));
                for (int i = 0; i < SudokuModel.GRID_SIZE; i++) {
//                    System.out.print(blockLine[i]+ ", ");
                    TextField numberText = new TextField(Character.toString(blockLine[i]));
                    numberText.setFont(Font.font("Times new roman", 20));
                    GridPane.setHalignment(numberText, HPos.CENTER);
                    blockGP.add(numberText, i % 3, i / 3);
                }
//                System.out.println();
                blockIter++;
            }
        }
    }
}
