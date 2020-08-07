package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class MainFrame extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("layout.fxml")));
        primaryStage.setTitle("Sudoku Solver");
        Scene scene = new Scene(root, 320, 325);
        scene.getStylesheets().add("Stylesheet.css");
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("sudoku-icon.png"));
        primaryStage.show();
    }
}
















