package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import ru.gis.app.controllers.MainPageController;

import java.io.IOException;

public class Controller {
    @FXML
    private AnchorPane mainPane;

    @FXML
    private Button filmListButton;

    @FXML
    private Button mapButton;

    @FXML
    private Button actorButton;

    @FXML
    private Label label;

    @FXML
    private void initialize() {
        label.setText("");
        filmListButton.setOnAction(actionEvent -> {
            try {
                MainPageController.setSubSceneRoot(FXMLLoader.load(getClass().getResource("filmTable.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        mapButton.setOnAction(actionEvent -> {
            try {
                MainPageController.setSubSceneRoot(FXMLLoader.load(getClass().getResource("locations.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        actorButton.setOnAction(actionEvent -> {
            try {
                MainPageController.setSubSceneRoot(FXMLLoader.load(getClass().getResource("actors.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
