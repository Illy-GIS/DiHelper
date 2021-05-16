package sample;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilmListController {
    @FXML
    private Pane filmListPane;

    @FXML
    public ListView<ImageView> posterList;

    @FXML
    public ListView<Label> filmDataList;

    @FXML
    void initialize() {
        try {
            List<FilmEntity> allMoviesList = FilmManager.getAll();
            List<javafx.scene.image.ImageView> posterArray = new ArrayList<>();
            List<String> movieDataArray = new ArrayList<>();
            List<Label> labelList = new ArrayList<>();
            for (FilmEntity film : allMoviesList) {
                ImageView imageView = film.getPosterView();
                imageView.setFitWidth(300);
                imageView.setFitHeight(480);
                posterArray.add(imageView);
                movieDataArray.add(film.getTitle() + ", " + film.getReleaseYear().toString() + ", " + film.getUserScore().toString());
                labelList.add(new Label(film.getTitle() + ", " + film.getReleaseYear().toString() + ", " + film.getUserScore().toString()));
            }
            posterList.setItems(FXCollections.observableList(posterArray));
            filmDataList.setItems(FXCollections.observableList(labelList));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
