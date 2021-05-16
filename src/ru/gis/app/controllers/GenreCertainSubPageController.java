package ru.gis.app.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import ru.gis.app.entities.GenreEntity;

import java.io.IOException;

public class GenreCertainSubPageController extends AnchorPane {
    @FXML
    private Button backButton;

    @FXML
    private Label genreLabel;

    @FXML
    private TextArea genreInfoArea;

    public GenreCertainSubPageController(GenreEntity genreEntity) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/GenreCertainSubPage.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        initPageData(genreEntity);
        initButtons();
    }

    private void initPageData(GenreEntity genreEntity) {
        genreLabel.setText(genreEntity.getGenre());
        StringBuilder stringBuilder = new StringBuilder();
        String description = genreEntity.getDescription();
        description = description.replace(" англ. ", " _eng_ ");
        description = description.replace("(англ. ", "(_eng_ ");
        description = description.replace(" лат. ", " _lat_ ");
        description = description.replace("(фр. ", "(_fr_ ");
        description = description.replace(" т. д. ", " _etc_ ");
        description = description.replace(" букв. ", " _let_ ");
        description = description.replace(". ", ".\n\n");
        description = description.replace(" _eng_ ", " англ. ");
        description = description.replace("(_eng_ ", "(англ. ");
        description = description.replace(" _lat_ ", " лат. ");
        description = description.replace("(_fr_ ", "(фр. ");
        description = description.replace(" _etc_ ", " т.д. ");
        description = description.replace(" _let_ ", " букв. ");
        stringBuilder.append(description);
        genreInfoArea.setText(stringBuilder.toString());
    }

    private void initButtons() {
        backButton.setOnAction(actionEvent -> {
            try {
                MainPageController.setSubSceneRoot(FXMLLoader.load(getClass().getResource("../views/GenreListSubPage.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
