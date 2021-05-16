package ru.gis.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ru.gis.app.entities.FontEntity;
import ru.gis.app.entities.MovieEntity;
import ru.gis.app.entities.UserEntity;
import ru.gis.app.managers.MovieManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ApplicationMain extends Application {
    public static Stage mainStage;
    public static Scene mainScene;
    public static List<FontEntity> fontList = new ArrayList<>();
    public static List<MovieEntity> movieList = new ArrayList<>();
    /*public static List<MovieTableEntity> movieTableFirstPage = new ArrayList<>();
    public static List<MovieTableEntity> movieTableSecondPage = new ArrayList<>();
    public static List<MovieTableEntity> movieTableThirdPage = new ArrayList<>();
    public static List<MovieTableEntity> movieTableFourthPage = new ArrayList<>();
    public static List<MovieTableEntity> movieTableFifthPage = new ArrayList<>();*/

    public static UserEntity currentUser;

    @Override
    public void start(Stage primaryStage) throws Exception {
        initDataLists();
        initFonts();
        mainStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("views/LoginPage.fxml"));
        mainStage.setResizable(false);
        mainStage.setTitle("DiHelper – помощник начинающего режиссёра");
        mainStage.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream("images/applicationIcon.png"))));
        mainScene = new Scene(root);
        mainScene.getStylesheets().add(getClass().getResource("styles/Style.css").toExternalForm());
        mainStage.setScene(mainScene);
        mainStage.show();
    }

    private void initDataLists() {
        Thread toFillDataThread = new Thread(() -> {
            try {
                movieList.addAll(MovieManager.getAllRows());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        toFillDataThread.start();
        /*Thread toFillPagesThread = new Thread(() -> {
            try {
                for (MovieEntity movieEntity : MovieManager.getAllRowsWithLimits(1, 101)) {
                    movieEntity.setImages(540, 800);
                    movieTableFirstPage.add(new MovieTableEntity(movieEntity));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            for (MovieTableEntity elem : movieTableFirstPage) {
                elem.getMajorPoster().setPreserveRatio(true);
                elem.getMajorPoster().setFitWidth(270);
            }
            try {
                for (MovieEntity movieEntity : MovieManager.getAllRowsWithLimits(101, 201)) {
                    movieEntity.setImages(540, 800);
                    movieTableSecondPage.add(new MovieTableEntity(movieEntity));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            for (MovieTableEntity elem : movieTableSecondPage) {
                elem.getMajorPoster().setPreserveRatio(true);
                elem.getMajorPoster().setFitWidth(270);
            }
            try {
                for (MovieEntity movieEntity : MovieManager.getAllRowsWithLimits(201, 301)) {
                    movieEntity.setImages(540, 800);
                    movieTableThirdPage.add(new MovieTableEntity(movieEntity));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            for (MovieTableEntity elem : movieTableThirdPage) {
                elem.getMajorPoster().setPreserveRatio(true);
                elem.getMajorPoster().setFitWidth(270);
            }
            try {
                for (MovieEntity movieEntity : MovieManager.getAllRowsWithLimits(301, 401)) {
                    movieEntity.setImages(540, 800);
                    movieTableFourthPage.add(new MovieTableEntity(movieEntity));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            for (MovieTableEntity elem : movieTableFourthPage) {
                elem.getMajorPoster().setPreserveRatio(true);
                elem.getMajorPoster().setFitWidth(270);
            }
            try {
                for (MovieEntity movieEntity : MovieManager.getAllRowsWithLimits(401, 501)) {
                    movieEntity.setImages(540, 800);
                    movieTableFifthPage.add(new MovieTableEntity(movieEntity));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            for (MovieTableEntity elem : movieTableFifthPage) {
                elem.getMajorPoster().setPreserveRatio(true);
                elem.getMajorPoster().setFitWidth(270);
            }
        });
        toFillPagesThread.start();*/
    }

    private void initFonts() {
        fontList.add(new FontEntity("Roboto", Font.loadFont(this.getClass().getClassLoader().getResourceAsStream("fonts/roboto/Roboto-Regular.ttf"), 10)));
        fontList.add(new FontEntity("Roboto Medium", Font.loadFont(this.getClass().getClassLoader().getResourceAsStream("fonts/roboto/Roboto-Medium.ttf"), 10)));
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost/moviedb?setServerTimeZone=Europe/Moscow&characterEncoding=utf8",
                "root", "100701");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
