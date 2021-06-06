package ru.gis.app.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import ru.gis.app.ApplicationMain;
import ru.gis.app.entities.MovieTableEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MovieListSubPageController extends AnchorPane {
    @FXML
    private Pane topPagePane;

    @FXML
    private Pagination movieListPages;

    private final TableView<MovieTableEntity> movieTable = createTable();

    private int movieIndexToSet;

    public MovieListSubPageController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/MovieListSubPage.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        movieListPages.setPageFactory(this::createPage);
    }

    public MovieListSubPageController(int pageIndexToSet, int movieIndexToSet) {
        this();
        movieListPages.setCurrentPageIndex(pageIndexToSet);
        this.movieIndexToSet = movieIndexToSet;
    }

    private Node createPage(int pageIndex) {
        int limit = (pageIndex + 1) * 100;
        List<MovieTableEntity> listForTable = new ArrayList<>();
        for (int i = limit - 100; i < Math.min(limit, ApplicationMain.movieList.size()); i++) {
            listForTable.add(new MovieTableEntity(ApplicationMain.movieList.get(i)));
        }
        movieTable.setItems(FXCollections.observableArrayList(listForTable));
        movieTable.scrollTo(movieIndexToSet);
        return new BorderPane(movieTable);
    }

    private TableView<MovieTableEntity> createTable() {
        TableView<MovieTableEntity> tableView = new TableView<>();
        tableView.getStyleClass().add("movie-table");
        tableView.setMinSize(1164, 660);
        tableView.setPrefSize(1164, 660);
        tableView.setMaxSize(1164, 660);
        TableColumn<MovieTableEntity, ImageView> posterColumn = new TableColumn<>("Постер");
        posterColumn.setMinWidth(350);
        posterColumn.setPrefWidth(350);
        posterColumn.setMaxWidth(350);
        posterColumn.setReorderable(false);
        posterColumn.setResizable(false);
        posterColumn.setCellValueFactory(new PropertyValueFactory<>("majorPoster"));
        TableColumn<MovieTableEntity, TextArea> infoColumn = new TableColumn<>("Информация");
        infoColumn.setMinWidth(761);
        infoColumn.setPrefWidth(761);
        infoColumn.setMaxWidth(820);
        infoColumn.setReorderable(false);
        infoColumn.setResizable(false);
        infoColumn.setCellValueFactory(new PropertyValueFactory<>("movieInfo"));
        tableView.setFixedCellSize(300.0);
        tableView.setOnMouseClicked(mouseEvent -> {
            if (tableView.getSelectionModel().getSelectedItem() != null) {
                MainPageController.setSubSceneRoot(new MovieCertainSubPageController(tableView.getSelectionModel().getSelectedItem().getMovieEntity(), movieListPages.getCurrentPageIndex(), tableView.getSelectionModel().getSelectedIndex()));
            }
        });
        tableView.getColumns().add(infoColumn);
        return tableView;
    }
}
