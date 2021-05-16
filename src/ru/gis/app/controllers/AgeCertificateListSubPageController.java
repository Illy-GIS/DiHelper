package ru.gis.app.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;

import java.util.Objects;

public class AgeCertificateListSubPageController {
    @FXML
    private AnchorPane mainPane;

    @FXML
    private Label pageLabel;

    @FXML
    private Button gRating;

    @FXML
    private Button pgRating;

    @FXML
    private Button pg13Rating;

    @FXML
    private Button rRating;

    @FXML
    private Button nc17Rating;

    @FXML
    private void initialize() {
        gRating.setBackground(new Background(new BackgroundImage(new Image(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream("images/g-background-blur.png"))), null, null, null, null)));
        pgRating.setBackground(new Background(new BackgroundImage(new Image(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream("images/pg-background-blur.png"))), null, null, null, null)));
        pg13Rating.setBackground(new Background(new BackgroundImage(new Image(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream("images/pg13-background-blur.png"))), null, null, null, null)));
        rRating.setBackground(new Background(new BackgroundImage(new Image(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream("images/r-background-blur.png"))), null, null, null, null)));
        nc17Rating.setBackground(new Background(new BackgroundImage(new Image(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream("images/nc17-background-blur.png"))), null, null, null, null)));
        initButtons();
    }

    private void initButtons() {
        gRating.setOnAction(actionEvent -> {
            AgeCertificateCertainSubPageController certainCertificatePage = new AgeCertificateCertainSubPageController("G");
            MainPageController.setSubSceneRoot(certainCertificatePage);
        });
        pgRating.setOnAction(actionEvent -> {
            AgeCertificateCertainSubPageController certainCertificatePage = new AgeCertificateCertainSubPageController("PG");
            MainPageController.setSubSceneRoot(certainCertificatePage);
        });
        pg13Rating.setOnAction(actionEvent -> {
            AgeCertificateCertainSubPageController certainCertificatePage = new AgeCertificateCertainSubPageController("PG-13");
            MainPageController.setSubSceneRoot(certainCertificatePage);
        });
        rRating.setOnAction(actionEvent -> {
            AgeCertificateCertainSubPageController certainCertificatePage = new AgeCertificateCertainSubPageController("R");
            MainPageController.setSubSceneRoot(certainCertificatePage);
        });
        nc17Rating.setOnAction(actionEvent -> {
            AgeCertificateCertainSubPageController certainCertificatePage = new AgeCertificateCertainSubPageController("NC-17");
            MainPageController.setSubSceneRoot(certainCertificatePage);
        });
    }
}
