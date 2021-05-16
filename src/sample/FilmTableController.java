package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import ru.gis.app.ApplicationMain;
import ru.gis.app.controllers.MainPageController;
import ru.gis.app.entities.MovieTableEntity;

import java.io.IOException;

public class FilmTableController {
    @FXML
    private AnchorPane mainPane;

    @FXML
    private TableView<MovieTableEntity> movieTable;

    @FXML
    private TableColumn<MovieTableEntity, ImageView> posterColumn;

    @FXML
    private TableColumn<MovieTableEntity, TextArea> infoColumn;

    @FXML
    private Button backButton;

    @FXML
    private void initialize() {
        /*posterColumn.setCellValueFactory(new PropertyValueFactory<>("majorPoster"));
        infoColumn.setCellValueFactory(new PropertyValueFactory<>("movieInfo"));
        ObservableList<MovieTableEntity> listForTable = FXCollections.observableArrayList(ApplicationMain.movieTableFirstPage);
        movieTable.setItems(listForTable);

        *//*movieTable.setOnMouseClicked(mouseEvent -> {
            System.out.println(movieTable.getSelectionModel().getSelectedItem().getTitle());
        });*//*

        backButton.setOnAction(actionEvent -> {
            try {
                MainPageController.setSubSceneRoot(FXMLLoader.load(getClass().getResource("sample.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });*/
    }
}
