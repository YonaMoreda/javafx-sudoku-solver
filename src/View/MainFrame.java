package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * Main view frame for displaying the sudoku application
 */
public class MainFrame extends Application {
    private static Stage pStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("main-layout.fxml")));
        primaryStage.setTitle("Sudoku Solver");
        Scene scene = new Scene(root);
        scene.getStylesheets().add("Stylesheet.css");
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("sudoku-icon.png"));
        pStage = primaryStage;
        primaryStage.show();
    }

    public static Stage getpStage() {
        return pStage;
    }
}
















