package ru.gis.app.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import ru.gis.app.entities.AgeCertificateEntity;
import ru.gis.app.managers.AgeCertificateManager;

import java.io.IOException;
import java.sql.SQLException;

public class AgeCertificateCertainSubPageController extends AnchorPane {
    @FXML
    private Button backButton;

    @FXML
    private Label ageCertificateLabel;

    @FXML
    private TextArea ageCertificateInfoArea;

    public AgeCertificateCertainSubPageController(String ageCertificate) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/AgeCertificateCertainSubPage.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        initPageData(ageCertificate);
        initButtons();
    }

    private void initPageData(String ageCertificate) {
        try {
            AgeCertificateEntity ageCertificateEntity = AgeCertificateManager.getRowByOriginal(ageCertificate);
            if (ageCertificateEntity != null) {
                ageCertificateLabel.setText(ageCertificateEntity.getOriginal() + " | " + ageCertificateEntity.getAnalogue());
                StringBuilder stringBuilder = new StringBuilder();
                String description = ageCertificateEntity.getDescription();
                description = description.replace(". ", ".");
                for (char letter : description.toCharArray()) {
                    stringBuilder.append(letter);
                    if (letter == '.') {
                        stringBuilder.append("\n\n");
                    }
                }
                ageCertificateInfoArea.setText(stringBuilder.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initButtons() {
        backButton.setOnAction(actionEvent -> {
            try {
                MainPageController.setSubSceneRoot(FXMLLoader.load(getClass().getResource("../views/AgeCertificateListSubPage.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
