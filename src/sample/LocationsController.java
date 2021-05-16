package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import ru.gis.app.controllers.MainPageController;

import java.io.IOException;

public class LocationsController {
    @FXML
    private AnchorPane mainPane;

    @FXML
    private WebView webWindow;

    @FXML
    private Button firstLoc;

    @FXML
    private Button secondLoc;

    @FXML
    private Button thirdLoc;

    @FXML
    private Button backButton;

    private WebEngine webEngine;

    @FXML
    private void initialize() {
        webWindow.setMinSize(1195, 625);
        webEngine = webWindow.getEngine();
        String firstUrl = "https://www.google.com/maps/@?api=1&map_action=map";
        webEngine.load(firstUrl);
        firstLoc.setOnAction(actionEvent -> {
            String location = "Times Square, Causeway Bay, Hong Kong";
            loadLoc(location);
        });
        secondLoc.setOnAction(actionEvent -> {
            String location = "Mount Ruapehu, Tongariro National Park, Central Plateau, New Zealand";
            loadLoc(location);
        });
        thirdLoc.setOnAction(actionEvent -> {
            String location = "Edward L. Doheny Jr. Memorial Library, University of Southern California - 3550 Trousdale Parkway, Los Angeles, California, USA";
            loadLoc(location);
        });
        backButton.setOnAction(actionEvent -> {
            try {
                MainPageController.setSubSceneRoot(FXMLLoader.load(getClass().getResource("sample.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void loadLoc(String location) {
        location = location.replaceAll(", ", "%2C");
        location = location.replaceAll(" ", "+");
        String sourceUrl = "https://www.google.com/maps/search/?api=1&query=" + location;
        webEngine.load(sourceUrl);
    }
}
