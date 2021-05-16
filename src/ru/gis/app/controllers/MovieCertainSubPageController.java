package ru.gis.app.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import ru.gis.app.entities.MovieEntity;

import java.io.IOException;

public class MovieCertainSubPageController extends AnchorPane {
    @FXML
    private Button backButton;

    @FXML
    private Label movieLabel;

    @FXML
    private TextArea movieInfoArea;

    @FXML
    private ImageView poster;

    @FXML
    private Button locationsListButton;

    @FXML
    private Button camerasListButton;

    @FXML
    private Button castListButton;

    private final MovieEntity movieEntity;

    private final int currentPageIndex;

    private final int thisMovieIndex;

    public MovieCertainSubPageController(MovieEntity movieEntity, int pageIndex, int movieIndex) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/MovieCertainSubPage.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.movieEntity = movieEntity;
        this.currentPageIndex = pageIndex;
        this.thisMovieIndex = movieIndex;
        initPageInfo();
        initButtons();
    }

    private void initPageInfo() {
        poster.setImage(new Image(movieEntity.getMajorPoster()));
        movieLabel.setText(movieEntity.getTitle());
        StringBuilder infoBuilder = new StringBuilder();
        String[] genres = movieEntity.getGenres().split(",");
        infoBuilder.append("Год выхода: ").append(movieEntity.getReleaseYear());
        infoBuilder.append("\nСредняя оценка пользователей: ").append(movieEntity.getUserScore());
        infoBuilder.append("\nЖанры: ");
        for (String genre : genres) {
            infoBuilder.append(genre).append(", ");
        }
        infoBuilder.delete(infoBuilder.length() - 2, infoBuilder.length() - 1);
        if (movieEntity.getAgeCertificate() != null) {
            infoBuilder.append("\nВозрастной рейтинг: ").append(movieEntity.getAgeCertificate());
        }
        if (movieEntity.getRuntime() != null) {
            infoBuilder.append("\nДлительность: ").append(movieEntity.getRuntime()).append(" мин.");
        }
        infoBuilder.append("\nБюджет: ").append(movieEntity.getBudget());
        if (movieEntity.getBudget().matches("[0-9,]*")) {
            infoBuilder.append(" $");
        }
        infoBuilder.append("\nСборы по миру: ").append(movieEntity.getGrossWorld()).append(" $");
        movieInfoArea.setText(infoBuilder.toString());
    }

    private void initButtons() {
        locationsListButton.setAlignment(Pos.CENTER_LEFT);
        camerasListButton.setAlignment(Pos.CENTER_LEFT);
        castListButton.setAlignment(Pos.CENTER_LEFT);
        backButton.setOnAction(actionEvent -> MainPageController.setSubSceneRoot(new MovieListSubPageController(currentPageIndex, thisMovieIndex)));
        if (movieEntity.getLocations() == null) {
            locationsListButton.setDisable(true);
        } else {
            locationsListButton.setOnAction(actionEvent -> MainPageController.setSubSceneRoot(new MovieCertainPositionsSubPageController(movieEntity, currentPageIndex, thisMovieIndex, 1)));
        }
        if (movieEntity.getCamera() == null) {
            camerasListButton.setDisable(true);
        } else {
            camerasListButton.setOnAction(actionEvent -> MainPageController.setSubSceneRoot(new MovieCertainPositionsSubPageController(movieEntity, currentPageIndex, thisMovieIndex, 2)));
        }
        castListButton.setOnAction(actionEvent -> MainPageController.setSubSceneRoot(new MovieCertainPositionsSubPageController(movieEntity, currentPageIndex, thisMovieIndex, 3)));
    }
}
