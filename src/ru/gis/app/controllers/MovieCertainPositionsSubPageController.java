package ru.gis.app.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import ru.gis.app.entities.MovieEntity;

import java.io.IOException;

public class MovieCertainPositionsSubPageController extends AnchorPane {
    @FXML
    private Button backButton;

    @FXML
    private Label pageLabel;

    @FXML
    private ListView<ToggleButton> positionsList;

    @FXML
    private WebView contentWebView;

    private final MovieEntity currentMovie;

    private final int currentMoviePageIndex;

    private final int currentMovieIndex;

    private final int requiredPageType;

    private final WebEngine webEngine;

    private final ToggleGroup buttonGroup;

    public MovieCertainPositionsSubPageController(MovieEntity movieEntity, int pageIndex, int movieIndex, int requiredPageType) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/MovieCertainPositionsSubPage.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.currentMovie = movieEntity;
        this.currentMoviePageIndex = pageIndex;
        this.currentMovieIndex = movieIndex;
        this.requiredPageType = requiredPageType;
        positionsList.getStyleClass().add("movie-position-list");
        contentWebView.setMinSize(715, 746);
        webEngine = contentWebView.getEngine();
        buttonGroup = new ToggleGroup();
        buttonGroup.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
            if (newToggle == null) {
                oldToggle.setSelected(true);
            }
        });
        initButtons();
        initPositionsList();
        if (requiredPageType == 2) {
            contentWebView.setVisible(false);
            positionsList.setMinSize(1100, 746);
        } else {
            positionsList.getItems().get(0).setSelected(true);
            if (requiredPageType == 1) {
                loadLocation(positionsList.getItems().get(0).getText());
            } else if (requiredPageType == 3) {
                loadActor(positionsList.getItems().get(0).getText());
            }
        }
    }

    private void initButtons() {
        backButton.setOnAction(actionEvent -> MainPageController.setSubSceneRoot(new MovieCertainSubPageController(currentMovie, currentMoviePageIndex, currentMovieIndex)));
    }

    private void initPositionsList() {
        if (requiredPageType == 1) {
            pageLabel.setText("Съемочные локации");
            String[] locations = currentMovie.getLocations().split("; ");
            for (String location : locations) {
                ToggleButton locationButton = new ToggleButton(location);
                locationButton.getStyleClass().add("movie-position-buttons");
                locationButton.setOnAction(actionEvent -> {
                    if (locationButton.isSelected()) {
                        loadLocation(locationButton.getText());
                    }
                });
                buttonGroup.getToggles().add(locationButton);
                positionsList.getItems().add(locationButton);
            }
        } else if (requiredPageType == 2) {
            pageLabel.setText("Камеры");
            String[] cameras = currentMovie.getCamera().split("; ");
            for (String camera : cameras) {
                ToggleButton cameraPosition = new ToggleButton(camera);
                cameraPosition.getStyleClass().add("movie-position-buttons");
                cameraPosition.setMouseTransparent(true);
                positionsList.getItems().add(cameraPosition);
            }
        } else if (requiredPageType == 3) {
            pageLabel.setText("Актерский состав");
            String[] cast = currentMovie.getCast().split("; ");
            for (String person : cast) {
                ToggleButton personButton = new ToggleButton(person);
                personButton.getStyleClass().add("movie-position-buttons");
                personButton.setOnAction(actionEvent -> {
                    if (personButton.isSelected()) {
                        loadActor(personButton.getText());
                    }
                });
                buttonGroup.getToggles().add(personButton);
                positionsList.getItems().add(personButton);
            }
        }
    }

    private void loadLocation(String location) {
        location = location.replaceAll(", ", "%2C");
        location = location.replaceAll(" ", "+");
        String sourceUrl = "https://www.google.com/maps/search/?api=1&query=" + location;
        webEngine.load(sourceUrl);
    }

    private void loadActor(String actor) {
        StringBuilder pageBuilder = new StringBuilder();
        pageBuilder.append("https://ru.wikipedia.org/wiki/");
        String[] words = actor.split(" ");
        for (String word : words) {
            pageBuilder.append(word).append("_");
        }
        pageBuilder.deleteCharAt(pageBuilder.lastIndexOf("_"));
        webEngine.load(pageBuilder.toString());
    }
}
