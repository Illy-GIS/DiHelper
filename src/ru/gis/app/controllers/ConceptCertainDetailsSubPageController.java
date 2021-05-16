package ru.gis.app.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import ru.gis.app.entities.DetailTableEntity;
import ru.gis.app.entities.MovieEntity;
import ru.gis.app.util.DialogUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConceptCertainDetailsSubPageController extends AnchorPane {
    @FXML
    private Button backButton;

    @FXML
    private TextField ageCertificateField;

    @FXML
    private TextField runtimeField;

    @FXML
    private TextField locationsField;

    @FXML
    private TextField camerasField;

    @FXML
    private TextField castField;

    @FXML
    private ToggleButton ageCertificateButton;

    @FXML
    private ToggleButton runtimeButton;

    @FXML
    private ToggleButton locationsButton;

    @FXML
    private ToggleButton camerasButton;

    @FXML
    private ToggleButton castButton;

    @FXML
    private ToggleGroup DetailsGroup;

    @FXML
    private TableView<DetailTableEntity> detailsTable;

    @FXML
    private TableColumn<DetailTableEntity, Label> titleColumn;

    @FXML
    private TableColumn<DetailTableEntity, Label> yearColumn;

    @FXML
    private TableColumn<DetailTableEntity, Label> scoreColumn;

    @FXML
    private TableColumn<DetailTableEntity, Label> budgetColumn;

    @FXML
    private TableColumn<DetailTableEntity, Label> grossColumn;

    @FXML
    private TableColumn<DetailTableEntity, Label> valueColumn;

    private final List<MovieEntity> sourceList = new ArrayList<>();

    public ConceptCertainDetailsSubPageController(List<MovieEntity> filteredList) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/ConceptCertainDetailsSubPage.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        initValues();
        sourceList.addAll(filteredList);
        DetailsGroup.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
            if (newToggle == null) {
                oldToggle.setSelected(true);
            }
        });
        initButtons();
        initTable();
    }

    private void initValues() {
        if (ConceptCertainSubPageController.getCurrentConcept().getAgeCertificate() != null) {
            ageCertificateField.setText(ConceptCertainSubPageController.getCurrentConcept().getAgeCertificate());
        }
        if (ConceptCertainSubPageController.getCurrentConcept().getRuntime() != null) {
            runtimeField.setText(String.valueOf(ConceptCertainSubPageController.getCurrentConcept().getRuntime()));
        }
        if (ConceptCertainSubPageController.getCurrentConcept().getLocations() != null) {
            locationsField.setText(ConceptCertainSubPageController.getCurrentConcept().getLocations());
        }
        if (ConceptCertainSubPageController.getCurrentConcept().getCamera() != null) {
            camerasField.setText(ConceptCertainSubPageController.getCurrentConcept().getCamera());
        }
        if (ConceptCertainSubPageController.getCurrentConcept().getCast() != null) {
            castField.setText(ConceptCertainSubPageController.getCurrentConcept().getCast());
        }
    }

    private void initButtons() {
        backButton.setOnAction(actionEvent -> {
            if (dataIsValid()) {
                if (!ageCertificateField.getText().isEmpty()) {
                    ConceptCertainSubPageController.getCurrentConcept().setAgeCertificate(ageCertificateField.getText());
                } else {
                    ConceptCertainSubPageController.getCurrentConcept().setAgeCertificate(null);
                }
                if (!runtimeField.getText().isEmpty()) {
                    ConceptCertainSubPageController.getCurrentConcept().setRuntime(Integer.parseInt(runtimeField.getText()));
                } else {
                    ConceptCertainSubPageController.getCurrentConcept().setRuntime(0);
                }
                if (!locationsField.getText().isEmpty()) {
                    ConceptCertainSubPageController.getCurrentConcept().setLocations(locationsField.getText());
                } else {
                    ConceptCertainSubPageController.getCurrentConcept().setLocations(null);
                }
                if (!camerasField.getText().isEmpty()) {
                    ConceptCertainSubPageController.getCurrentConcept().setCamera(camerasField.getText());
                } else {
                    ConceptCertainSubPageController.getCurrentConcept().setCamera(null);
                }
                if (!castField.getText().isEmpty()) {
                    ConceptCertainSubPageController.getCurrentConcept().setCast(castField.getText());
                } else {
                    ConceptCertainSubPageController.getCurrentConcept().setCast(null);
                }
                MainPageController.setSubSceneRoot(new ConceptCertainSubPageController(ConceptCertainSubPageController.getCurrentConcept()));
            }
        });
        ageCertificateButton.setOnAction(actionEvent -> {
            if (ageCertificateButton.isSelected()) {
                ageCertificateField.setVisible(true);
                runtimeField.setVisible(false);
                locationsField.setVisible(false);
                camerasField.setVisible(false);
                castField.setVisible(false);
                updateTable();
            }
        });
        runtimeButton.setOnAction(actionEvent -> {
            if (runtimeButton.isSelected()) {
                runtimeField.setVisible(true);
                ageCertificateField.setVisible(false);
                locationsField.setVisible(false);
                camerasField.setVisible(false);
                castField.setVisible(false);
                updateTable();
            }
        });
        locationsButton.setOnAction(actionEvent -> {
            if (locationsButton.isSelected()) {
                locationsField.setVisible(true);
                ageCertificateField.setVisible(false);
                runtimeField.setVisible(false);
                camerasField.setVisible(false);
                castField.setVisible(false);
                updateTable();
            }
        });
        camerasButton.setOnAction(actionEvent -> {
            if (camerasButton.isSelected()) {
                camerasField.setVisible(true);
                ageCertificateField.setVisible(false);
                runtimeField.setVisible(false);
                locationsField.setVisible(false);
                castField.setVisible(false);
                updateTable();
            }
        });
        castButton.setOnAction(actionEvent -> {
            if (castButton.isSelected()) {
                castField.setVisible(true);
                ageCertificateField.setVisible(false);
                runtimeField.setVisible(false);
                locationsField.setVisible(false);
                camerasField.setVisible(false);
                updateTable();
            }
        });
    }

    private void initTable() {
        titleColumn.setReorderable(false);
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        yearColumn.setReorderable(false);
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        scoreColumn.setReorderable(false);
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        budgetColumn.setReorderable(false);
        budgetColumn.setCellValueFactory(new PropertyValueFactory<>("budget"));
        grossColumn.setReorderable(false);
        grossColumn.setCellValueFactory(new PropertyValueFactory<>("gross"));
        valueColumn.setReorderable(false);
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        List<DetailTableEntity> detailsList = new ArrayList<>();
        for (MovieEntity movieEntity : sourceList) {
            if (movieEntity.getAgeCertificate() != null) {
                detailsList.add(new DetailTableEntity(movieEntity.getTitle(), movieEntity.getReleaseYear().toString(), movieEntity.getUserScore().toString(), movieEntity.getBudget(), movieEntity.getGrossWorld(), movieEntity.getAgeCertificate()));
            }
        }
        detailsTable.setItems(FXCollections.observableArrayList(detailsList));
        detailsTable.setOnMouseClicked(mouseEvent -> {
            if (detailsTable.getSelectionModel().getSelectedItem() != null) {
                if (ageCertificateButton.isSelected()) {
                    ageCertificateField.setText(detailsTable.getSelectionModel().getSelectedItem().getValue().getText());
                } else if (runtimeButton.isSelected()) {
                    runtimeField.setText(detailsTable.getSelectionModel().getSelectedItem().getValue().getText());
                } else if (locationsButton.isSelected()) {
                    if (locationsField.getText().isEmpty()) {
                        locationsField.setText(detailsTable.getSelectionModel().getSelectedItem().getValue().getText());
                    } else {
                        locationsField.setText(locationsField.getText().concat("; " + detailsTable.getSelectionModel().getSelectedItem().getValue().getText()));
                    }
                } else if (camerasButton.isSelected()) {
                    if (camerasField.getText().isEmpty()) {
                        camerasField.setText(detailsTable.getSelectionModel().getSelectedItem().getValue().getText());
                    } else {
                        camerasField.setText(camerasField.getText().concat("; " + detailsTable.getSelectionModel().getSelectedItem().getValue().getText()));
                    }
                } else {
                    if (castField.getText().isEmpty()) {
                        castField.setText(detailsTable.getSelectionModel().getSelectedItem().getValue().getText());
                    } else {
                        castField.setText(castField.getText().concat("; " + detailsTable.getSelectionModel().getSelectedItem().getValue().getText()));
                    }
                }
            }
        });
        if (detailsTable.getItems().isEmpty()) {
            detailsTable.setVisible(false);
        }
    }

    private void updateTable() {
        if (ageCertificateButton.isSelected()) {
            List<DetailTableEntity> detailsList = new ArrayList<>();
            for (MovieEntity movieEntity : sourceList) {
                if (movieEntity.getAgeCertificate() != null) {
                    detailsList.add(new DetailTableEntity(movieEntity.getTitle(), movieEntity.getReleaseYear().toString(), movieEntity.getUserScore().toString(), movieEntity.getBudget(), movieEntity.getGrossWorld(), movieEntity.getAgeCertificate()));
                }
            }
            detailsTable.getItems().clear();
            detailsTable.getItems().addAll(detailsList);
        }
        if (runtimeButton.isSelected()) {
            List<DetailTableEntity> detailsList = new ArrayList<>();
            for (MovieEntity movieEntity : sourceList) {
                detailsList.add(new DetailTableEntity(movieEntity.getTitle(), movieEntity.getReleaseYear().toString(), movieEntity.getUserScore().toString(), movieEntity.getBudget(), movieEntity.getGrossWorld(), movieEntity.getRuntime().toString()));
            }
            detailsTable.getItems().clear();
            detailsTable.getItems().addAll(detailsList);
        }
        if (locationsButton.isSelected()) {
            List<DetailTableEntity> detailsList = new ArrayList<>();
            for (MovieEntity movieEntity : sourceList) {
                if (movieEntity.getLocations() != null) {
                    String[] locations = movieEntity.getLocations().split("; ");
                    for (String location : locations) {
                        detailsList.add(new DetailTableEntity(movieEntity.getTitle(), movieEntity.getReleaseYear().toString(), movieEntity.getUserScore().toString(), movieEntity.getBudget(), movieEntity.getGrossWorld(), location));
                    }
                }
            }
            detailsTable.getItems().clear();
            detailsTable.getItems().addAll(detailsList);
        }
        if (camerasButton.isSelected()) {
            List<DetailTableEntity> detailsList = new ArrayList<>();
            for (MovieEntity movieEntity : sourceList) {
                if (movieEntity.getCamera() != null) {
                    String[] cameras = movieEntity.getCamera().split("; ");
                    for (String camera : cameras) {
                        detailsList.add(new DetailTableEntity(movieEntity.getTitle(), movieEntity.getReleaseYear().toString(), movieEntity.getUserScore().toString(), movieEntity.getBudget(), movieEntity.getGrossWorld(), camera));
                    }
                }
            }
            detailsTable.getItems().clear();
            detailsTable.getItems().addAll(detailsList);
        }
        if (castButton.isSelected()) {
            List<DetailTableEntity> detailsList = new ArrayList<>();
            for (MovieEntity movieEntity : sourceList) {
                String[] cast = movieEntity.getCast().split("; ");
                for (String actor : cast) {
                    detailsList.add(new DetailTableEntity(movieEntity.getTitle(), movieEntity.getReleaseYear().toString(), movieEntity.getUserScore().toString(), movieEntity.getBudget(), movieEntity.getGrossWorld(), actor));
                }
            }
            detailsTable.getItems().clear();
            detailsTable.getItems().addAll(detailsList);
        }
    }

    private boolean dataIsValid() {
        StringBuilder errorBuilder = new StringBuilder();
        boolean anyTroubles = false;
        if (!ageCertificateField.getText().isEmpty()) {
            String value = ageCertificateField.getText();
            if (!value.equals("G") && !value.equals("PG") && !value.equals("PG-13") && !value.equals("R") && !value.equals("NC-17")) {
                errorBuilder.append("Введенный возрастной рейтинг некорректен.").append("\n");
                anyTroubles = true;
            }
        }
        if (!runtimeField.getText().isEmpty()) {
            try {
                int value = Integer.parseInt(runtimeField.getText());
                if (value < 0 || value > 300) {
                    errorBuilder.append("Длительность – целое положительное число, не превышающее 300.").append("\n");
                    anyTroubles = true;
                }
            } catch (NumberFormatException e) {
                errorBuilder.append("Что-то не так с форматом длительности.").append("\n");
                anyTroubles = true;
            }
        }
        if (!locationsField.getText().isEmpty()) {
            if (locationsField.getText().length() > 30000) {
                errorBuilder.append("Поле локаций слишком длинное.").append("\n");
                anyTroubles = true;
            }
        }
        if (!camerasField.getText().isEmpty()) {
            if (camerasField.getText().length() > 30000) {
                errorBuilder.append("Поле камер слишком длинное.").append("\n");
                anyTroubles = true;
            }
        }
        if (!castField.getText().isEmpty()) {
            if (castField.getText().length() > 30000) {
                errorBuilder.append("Поле актеров слишком длинное.").append("\n");
                anyTroubles = true;
            }
        }
        if (anyTroubles) {
            errorBuilder.deleteCharAt(errorBuilder.lastIndexOf("\n"));
            DialogUtil.showError(errorBuilder.toString());
            return false;
        } else {
            return true;
        }
    }
}
