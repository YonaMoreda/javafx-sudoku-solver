package View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * HelpView: small dialogue for displaying the "help" dialogue
 * based on "help-layout.fxml"
 */
public class HelpView extends Stage {

    public HelpView(Stage primaryStage) {
        super();
        initModality(Modality.APPLICATION_MODAL);
        initOwner(primaryStage);
        Parent root = null;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("help-layout.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert root != null;
        Scene dialogScene = new Scene(root);
        getIcons().add(new Image("sudoku-icon.png"));
        setScene(dialogScene);
    }
}
