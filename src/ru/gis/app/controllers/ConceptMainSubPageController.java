package ru.gis.app.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import ru.gis.app.ApplicationMain;
import ru.gis.app.entities.ConceptEntity;
import ru.gis.app.managers.ConceptManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConceptMainSubPageController {
    @FXML
    private AnchorPane mainPane;

    @FXML
    private Label pageLabel;

    @FXML
    private Button createConceptButton;

    @FXML
    private Label emptyLabel;

    @FXML
    private ListView<Button> conceptList;

    @FXML
    private void initialize() {
        initList();
        initButtons();
    }

    private void initList() {
        try {
            List<ConceptEntity> concepts = ConceptManager.getAllRowsByUserOwner(ApplicationMain.currentUser.getId());
            List<Button> buttons = new ArrayList<>();
            if (!concepts.isEmpty()) {
                for (ConceptEntity concept : concepts) {
                    Button conceptButton = new Button();
                    conceptButton.setText(concept.getTitle());
                    conceptButton.getStyleClass().add("concept-buttons");
                    conceptButton.setOnAction(actionEvent -> {
                        MainPageController.setSubSceneRoot(new ConceptCertainSubPageController(concept));
                    });
                    buttons.add(conceptButton);
                }
                conceptList.setItems(FXCollections.observableArrayList(buttons));
            } else {
                emptyLabel.setText("Вы пока не создали ни одного концепта.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initButtons() {
        createConceptButton.setOnAction(actionEvent -> MainPageController.setSubSceneRoot(new ConceptCertainSubPageController()));
    }
}
