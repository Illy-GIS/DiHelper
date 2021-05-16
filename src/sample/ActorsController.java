package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import ru.gis.app.controllers.MainPageController;

import java.io.IOException;

public class ActorsController {
    @FXML
    private AnchorPane mainPane;

    @FXML
    private WebView webWindow;

    @FXML
    private Button firstActor;

    @FXML
    private Button secondActor;

    @FXML
    private Button thirdActor;

    @FXML
    private Button backButton;

    private WebEngine webEngine;

    @FXML
    private void initialize() {
        webWindow.setMinSize(1195, 625);
        webEngine = webWindow.getEngine();
        String firstUrl = "https://ru.wikipedia.org/wiki/Tim_Robbins";
        webEngine.load(firstUrl);
        firstActor.setOnAction(actionEvent -> {
            String actorPage = "https://ru.wikipedia.org/wiki/Tim_Robbins";
            loadActorPage(actorPage);
        });
        secondActor.setOnAction(actionEvent -> {
            String actorPage = "https://ru.wikipedia.org/wiki/Hugh_Jackman";
            loadActorPage(actorPage);
        });
        thirdActor.setOnAction(actionEvent -> {
            String actorPage = "https://ru.wikipedia.org/wiki/L%C3%A9a_Seydoux";
            loadActorPage(actorPage);
        });
        backButton.setOnAction(actionEvent -> {
            try {
                MainPageController.setSubSceneRoot(FXMLLoader.load(getClass().getResource("sample.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void loadActorPage(String actorPage) {
        webEngine.load(actorPage);
    }
}
