package ru.gis.app.controllers;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import ru.gis.app.entities.GenreEntity;
import ru.gis.app.managers.GenreManager;

import java.sql.SQLException;
import java.util.List;

public class GenreListSubPage {
    @FXML
    private AnchorPane mainPane;

    @FXML
    private Label pageLabel;

    @FXML
    private GridPane genreTablePane;

    private List<GenreEntity> genreList;

    @FXML
    private void initialize() {
        try {
            genreList = GenreManager.getAllRows();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int rowLimit = 0;
        int columnLimit = 0;
        for (GenreEntity genre : genreList) {
            Button genreButton = new Button();
            genreButton.setText(genre.getGenre());
            genreButton.getStyleClass().add("age-certificate-buttons");
            genreButton.getStyleClass().add("genre-buttons-extra");
            genreButton.setMinSize(280, 100);
            genreButton.setOnAction(actionEvent -> {
                MainPageController.setSubSceneRoot(new GenreCertainSubPageController(genre));
            });
            genreTablePane.add(genreButton, columnLimit, rowLimit);
            columnLimit++;
            if (columnLimit > 2) {
                columnLimit = 0;
                rowLimit++;
            }
        }
    }
}
