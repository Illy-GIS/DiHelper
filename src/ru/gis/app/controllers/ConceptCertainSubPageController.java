package ru.gis.app.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import ru.gis.app.ApplicationMain;
import ru.gis.app.entities.ConceptEntity;
import ru.gis.app.entities.GenreEntity;
import ru.gis.app.entities.MovieEntity;
import ru.gis.app.managers.ConceptManager;
import ru.gis.app.managers.GenreManager;
import ru.gis.app.util.DialogUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConceptCertainSubPageController extends AnchorPane {
    @FXML
    private Button backButton;

    @FXML
    private Button saveButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TextField conceptTitleField;

    @FXML
    private TextField budgetField;

    @FXML
    private ChoiceBox<String> genreBox1;

    @FXML
    private ChoiceBox<String> genreBox2;

    @FXML
    private ChoiceBox<String> genreBox3;

    @FXML
    private Button recommendButton;

    @FXML
    private Button detailsButton;

    @FXML
    private ChoiceBox<String> highPriorityBox;

    @FXML
    private ChoiceBox<String> mediumPriorityBox;

    @FXML
    private ChoiceBox<String> lowPriorityBox;

    private static ConceptEntity currentConcept = null;

    private final List<String> genreTitles = new ArrayList<>();

    private final List<String> priorityValues = new ArrayList<>();

    public ConceptCertainSubPageController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/ConceptCertainSubPage.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        deleteButton.setVisible(false);
        initBoxes();
        initButtons();
    }

    public ConceptCertainSubPageController(ConceptEntity conceptEntity) {
        this();
        if (conceptEntity.getID() != -1) {
            deleteButton.setVisible(true);
        }
        currentConcept = conceptEntity;
        initFields();
    }

    private void initBoxes() {
        try {
            for (GenreEntity genre : GenreManager.getAllRows()) {
                genreTitles.add(genre.getGenre());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        genreTitles.add("–");
        genreBox1.setItems(FXCollections.observableArrayList(genreTitles));
        genreBox1.setValue("–");
        genreBox1.setOnAction(actionEvent -> updateGenresBoxes(1));
        genreBox2.setItems(FXCollections.observableArrayList(genreTitles));
        genreBox2.setValue("–");
        genreBox2.setOnAction(actionEvent -> updateGenresBoxes(2));
        genreBox2.setDisable(true);
        genreBox3.setItems(FXCollections.observableArrayList(genreTitles));
        genreBox3.setValue("–");
        genreBox3.setOnAction(actionEvent -> updateGenresBoxes(3));
        genreBox3.setDisable(true);
        priorityValues.add("Пользовательский рейтинг");
        priorityValues.add("Окупаемость");
        priorityValues.add("Актуальность даты выхода");
        highPriorityBox.setItems(FXCollections.observableArrayList(priorityValues));
        highPriorityBox.setValue("Пользовательский рейтинг");
        highPriorityBox.setOnAction(actionEvent -> updatePriorityBoxes(1));
        mediumPriorityBox.setItems(FXCollections.observableArrayList(priorityValues));
        mediumPriorityBox.setValue("Окупаемость");
        mediumPriorityBox.setOnAction(actionEvent -> updatePriorityBoxes(2));
        lowPriorityBox.setItems(FXCollections.observableArrayList(priorityValues));
        lowPriorityBox.setValue("Актуальность даты выхода");
        lowPriorityBox.setOnAction(actionEvent -> updatePriorityBoxes(3));
    }

    private void updateGenresBoxes(int changedBox) {
        for (String elem : genreTitles) {
            if (!genreBox2.getItems().contains(elem)) {
                genreBox2.getItems().add(elem);
            }
            if (!genreBox3.getItems().contains(elem)) {
                genreBox3.getItems().add(elem);
            }
        }
        genreBox2.getItems().sort((o1, o2) -> Integer.compare(o1.compareTo(o2), 0));
        genreBox3.getItems().sort((o1, o2) -> Integer.compare(o1.compareTo(o2), 0));
        switch (changedBox) {
            case 1:
                if (genreBox1.getValue().equals("–")) {
                    genreBox2.setValue("–");
                    genreBox2.setDisable(true);
                    genreBox3.setValue("–");
                    genreBox3.setDisable(true);
                } else {
                    genreBox2.setDisable(false);
                    boolean needToUpdate = false;
                    if (genreBox1.getValue().equals(genreBox2.getValue())) {
                        genreBox2.setValue("–");
                        needToUpdate = true;
                    } else if (genreBox1.getValue().equals(genreBox3.getValue())) {
                        genreBox3.setValue("–");
                    }
                    genreBox2.getItems().remove(genreBox1.getValue());
                    genreBox3.getItems().remove(genreBox1.getValue());
                    if (!genreBox2.getValue().equals("–")) {
                        genreBox3.getItems().remove(genreBox2.getValue());
                    }
                    if (needToUpdate) {
                        updateGenresBoxes(2);
                    }
                }
            case 2:
                if (genreBox2.getValue().equals("–")) {
                    genreBox3.setValue("–");
                    genreBox3.setDisable(true);
                } else {
                    genreBox3.setDisable(false);
                    if (genreBox2.getValue().equals(genreBox3.getValue())) {
                        genreBox3.setValue("–");
                    }
                    genreBox3.getItems().remove(genreBox1.getValue());
                    genreBox3.getItems().remove(genreBox2.getValue());
                }
        }
    }

    private void updatePriorityBoxes(int changedBox) {
        List<String> subList = new ArrayList<>(priorityValues);
        switch (changedBox) {
            case 1:
                if (highPriorityBox.getValue().equals(mediumPriorityBox.getValue())) {
                    subList.remove(highPriorityBox.getValue());
                    subList.remove(lowPriorityBox.getValue());
                    mediumPriorityBox.setValue(subList.get(0));
                } else if (highPriorityBox.getValue().equals(lowPriorityBox.getValue())) {
                    subList.remove(highPriorityBox.getValue());
                    subList.remove(mediumPriorityBox.getValue());
                    lowPriorityBox.setValue(subList.get(0));
                }
            case 2:
                if (mediumPriorityBox.getValue().equals(highPriorityBox.getValue())) {
                    subList.remove(mediumPriorityBox.getValue());
                    subList.remove(lowPriorityBox.getValue());
                    highPriorityBox.setValue(subList.get(0));
                } else if (mediumPriorityBox.getValue().equals(lowPriorityBox.getValue())) {
                    subList.remove(mediumPriorityBox.getValue());
                    subList.remove(highPriorityBox.getValue());
                    lowPriorityBox.setValue(subList.get(0));
                }
            case 3:
                if (lowPriorityBox.getValue().equals(highPriorityBox.getValue())) {
                    subList.remove(lowPriorityBox.getValue());
                    subList.remove(mediumPriorityBox.getValue());
                    highPriorityBox.setValue(subList.get(0));
                } else if (lowPriorityBox.getValue().equals(mediumPriorityBox.getValue())) {
                    subList.remove(lowPriorityBox.getValue());
                    subList.remove(highPriorityBox.getValue());
                    mediumPriorityBox.setValue(subList.get(0));
                }
        }
    }

    private void initButtons() {
        backButton.setOnAction(actionEvent -> {
            try {
                if (currentConcept != null && currentConcept.getID() != -1) {
                    StringBuilder stringBuilder = new StringBuilder();
                    if (!genreBox1.getValue().equals("–")) {
                        stringBuilder.append(genreBox1.getValue());
                    }
                    if (!genreBox2.getValue().equals("–")) {
                        stringBuilder.append(",").append(genreBox2.getValue());
                    }
                    if (!genreBox3.getValue().equals("–")) {
                        stringBuilder.append(",").append(genreBox3.getValue());
                    }
                    ConceptEntity conceptEntity = new ConceptEntity(
                            currentConcept.getID(),
                            ApplicationMain.currentUser.getId(),
                            conceptTitleField.getText(),
                            budgetField.getText(),
                            stringBuilder.toString(),
                            currentConcept.getAgeCertificate(),
                            currentConcept.getRuntime(),
                            currentConcept.getLocations(),
                            currentConcept.getCamera(),
                            currentConcept.getCast()
                    );
                    if (conceptEntity.equals(ConceptManager.getRowByID(currentConcept.getID()))) {
                        currentConcept = null;
                        MainPageController.setSubSceneRoot(FXMLLoader.load(getClass().getResource("../views/ConceptMainSubPage.fxml")));
                    } else {
                        ButtonType chosenOption = DialogUtil.showConceptConfirm("Вы хотите сохранить изменения перед выходом?");
                        if (chosenOption.getButtonData() == ButtonBar.ButtonData.YES) {
                            saveData(false, true);
                        } else if (chosenOption.getButtonData() == ButtonBar.ButtonData.NO) {
                            currentConcept = null;
                            MainPageController.setSubSceneRoot(FXMLLoader.load(getClass().getResource("../views/ConceptMainSubPage.fxml")));
                        }
                    }
                } else {
                    ButtonType chosenOption = DialogUtil.showConceptConfirm("Вы хотите сохранить изменения перед выходом?");
                    if (chosenOption.getButtonData() == ButtonBar.ButtonData.YES) {
                        if (currentConcept == null) {
                            currentConcept = new ConceptEntity(ApplicationMain.currentUser.getId(), conceptTitleField.getText());
                        }
                        saveData(true, true);
                    } else if (chosenOption.getButtonData() == ButtonBar.ButtonData.NO) {
                        currentConcept = null;
                        MainPageController.setSubSceneRoot(FXMLLoader.load(getClass().getResource("../views/ConceptMainSubPage.fxml")));
                    }
                }
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        });
        saveButton.setOnAction(actionEvent -> {
            if (currentConcept != null && currentConcept.getID() != -1) {
                saveData(false, false);
            } else {
                if (currentConcept == null) {
                    currentConcept = new ConceptEntity(ApplicationMain.currentUser.getId(), conceptTitleField.getText());
                }
                saveData(true, false);
            }
        });
        deleteButton.setOnAction(actionEvent -> {
            if (DialogUtil.showConfirm("Вы точно хотите удалить этот концепт?")) {
                try {
                    ConceptManager.deleteRowByID(currentConcept.getID());
                    DialogUtil.showInfo("Концепт удален.");
                    currentConcept = null;
                    MainPageController.setSubSceneRoot(FXMLLoader.load(getClass().getResource("../views/ConceptMainSubPage.fxml")));
                } catch (IOException | SQLException e) {
                    e.printStackTrace();
                }

            }
        });
        recommendButton.setOnAction(actionEvent -> {
            ;
        });
        detailsButton.setOnAction(actionEvent -> {
            if (dataIsValid()) {
                List<MovieEntity> filteredList = new ArrayList<>(ApplicationMain.movieList);
                if (currentConcept == null) {
                    currentConcept = new ConceptEntity(ApplicationMain.currentUser.getId(), conceptTitleField.getText());
                }
                String budget = budgetField.getText();
                budget = budget.replace("-", ",");
                budget = budget.replace(" ", ",");
                currentConcept.setBudget(budget);
                budget = budget.replace(",", "");
                String genres = genreBox1.getValue();
                if (!genreBox2.getValue().equals("–")) {
                    genres = genres + "," + genreBox2.getValue();
                }
                if (!genreBox3.getValue().equals("–")) {
                    genres = genres + "," + genreBox3.getValue();
                }
                currentConcept.setGenres(genres);
                String finalBudget = budget;
                filteredList.removeIf(elem -> (!elem.getBudget().matches("[0-9,]*")));
                filteredList.removeIf(elem -> (Double.parseDouble(elem.getBudget().replace(",", "")) > Double.parseDouble(finalBudget)));
                filteredList.removeIf(elem -> !elem.getGenres().contains(genreBox1.getValue()) && !elem.getGenres().contains(genreBox2.getValue()) && !elem.getGenres().contains(genreBox3.getValue()));
                sortAccordingToPriorities(filteredList);
                MainPageController.setSubSceneRoot(new ConceptCertainDetailsSubPageController(filteredList));
            }
        });
    }

    private void saveData(boolean needToAddNew, boolean callFromBack) {
        if (dataIsValid()) {
            currentConcept.setTitle(conceptTitleField.getText());
            String budget = budgetField.getText();
            budget = budget.replace("-", ",");
            budget = budget.replace(" ", ",");
            currentConcept.setBudget(budget);
            budgetField.setText(budget);
            StringBuilder genresBuilder = new StringBuilder();
            genresBuilder.append(genreBox1.getValue());
            if (!genreBox2.getValue().equals("–")) {
                genresBuilder.append(",").append(genreBox2.getValue());
            }
            if (!genreBox3.getValue().equals("–")) {
                genresBuilder.append(",").append(genreBox3.getValue());
            }
            currentConcept.setGenres(genresBuilder.toString());
            try {
                if (needToAddNew) {
                    ConceptManager.addRow(currentConcept);
                } else {
                    ConceptManager.updateRow(currentConcept);
                }
                currentConcept = ConceptManager.getRowByID(currentConcept.getID());
                if (!deleteButton.isVisible()) {
                    deleteButton.setVisible(true);
                }
                DialogUtil.showInfo("Концепт сохранен.");
                if (callFromBack) {
                    currentConcept = null;
                    MainPageController.setSubSceneRoot(FXMLLoader.load(getClass().getResource("../views/ConceptMainSubPage.fxml")));
                }
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean dataIsValid() {
        StringBuilder errorBuilder = new StringBuilder();
        boolean anyTroubles = false;
        if (conceptTitleField.getText().isEmpty()) {
            errorBuilder.append("Введите название концепта.").append("\n");
            anyTroubles = true;
        }
        if (conceptTitleField.getText().length() > 300) {
            errorBuilder.append("Название концепта должно содержать не более 300 символов.").append("\n");
            anyTroubles = true;
        }
        if (!budgetField.getText().matches("[0-9\s,.-]*")) {
            errorBuilder.append("Бюджет может содержать цифры, ',', '.', '-' и символ пробела.").append("\n");
            anyTroubles = true;
        }
        try {
            String budgetToCheck = budgetField.getText();
            budgetToCheck = budgetToCheck.replace(",", "");
            budgetToCheck = budgetToCheck.replace("-", "");
            budgetToCheck = budgetToCheck.replace(" ", "");
            Double.parseDouble(budgetToCheck);
            if (budgetField.getText().contains(".") && budgetField.getText().lastIndexOf(",") > budgetField.getText().lastIndexOf(".")) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            errorBuilder.append("Что-то не так с форматом бюджета.").append("\n");
            anyTroubles = true;
        }
        if (genreBox1.getValue().equals("–")) {
            errorBuilder.append("Выберите по меньшей мере один жанр.").append("\n");
            anyTroubles = true;
        }
        if (anyTroubles) {
            errorBuilder.deleteCharAt(errorBuilder.lastIndexOf("\n"));
            DialogUtil.showError(errorBuilder.toString());
            return false;
        } else {
            return true;
        }
    }

    private void initFields() {
        conceptTitleField.setText(currentConcept.getTitle());
        budgetField.setText(currentConcept.getBudget());
        if (currentConcept.getGenres().contains(",")) {
            String[] genres = currentConcept.getGenres().split(",");
            genreBox1.setValue(genres[0]);
            genreBox2.setValue(genres[1]);
            if (genres.length == 3) {
                genreBox3.setValue(genres[2]);
            }
        } else {
            genreBox1.setValue(currentConcept.getGenres());
        }
    }

    private void sortAccordingToPriorities(List<MovieEntity> listToSort) {
        listToSort.sort((o1, o2) -> {
            if (highPriorityBox.getValue().equals("Пользовательский рейтинг")) {
                if (o1.getUserScore() > o2.getUserScore()) {
                    return -1;
                } else if (o1.getUserScore() < o2.getUserScore()) {
                    return 1;
                } else {
                    if (mediumPriorityBox.getValue().equals("Окупаемость")) {
                        Long formattedBudget1 = Long.parseLong(o1.getBudget().replace(",", ""));
                        Long formattedBudget2 = Long.parseLong(o2.getBudget().replace(",", ""));
                        Long formattedGross1 = Long.parseLong(o1.getGrossWorld().replace(",", ""));
                        Long formattedGross2 = Long.parseLong(o2.getGrossWorld().replace(",", ""));
                        if (formattedGross1 / formattedBudget1 > formattedGross2 / formattedBudget2) {
                            return -1;
                        } else if (formattedGross1 / formattedBudget1 < formattedGross2 / formattedBudget2) {
                            return 1;
                        } else {
                            return o2.getReleaseYear().compareTo(o1.getReleaseYear());
                        }
                    } else {
                        if (o1.getReleaseYear() > o2.getReleaseYear()) {
                            return -1;
                        } else if (o1.getReleaseYear() < o2.getReleaseYear()) {
                            return 1;
                        } else {
                            Long formattedBudget1 = Long.parseLong(o1.getBudget().replace(",", ""));
                            Long formattedBudget2 = Long.parseLong(o2.getBudget().replace(",", ""));
                            Long formattedGross1 = Long.parseLong(o1.getGrossWorld().replace(",", ""));
                            Long formattedGross2 = Long.parseLong(o2.getGrossWorld().replace(",", ""));
                            return Long.compare(formattedGross2 / formattedBudget2, formattedGross1 / formattedBudget1);
                        }
                    }
                }
            } else if ((highPriorityBox.getValue().equals("Окупаемость"))) {
                Long formattedBudget1 = Long.parseLong(o1.getBudget().replace(",", ""));
                Long formattedBudget2 = Long.parseLong(o2.getBudget().replace(",", ""));
                Long formattedGross1 = Long.parseLong(o1.getGrossWorld().replace(",", ""));
                Long formattedGross2 = Long.parseLong(o2.getGrossWorld().replace(",", ""));
                if (formattedGross1 / formattedBudget1 > formattedGross2 / formattedBudget2) {
                    return -1;
                } else if (formattedGross1 / formattedBudget1 < formattedGross2 / formattedBudget2) {
                    return 1;
                } else {
                    if (mediumPriorityBox.getValue().equals("Пользовательский рейтинг")) {
                        if (o1.getUserScore() > o2.getUserScore()) {
                            return -1;
                        } else if (o1.getUserScore() < o2.getUserScore()) {
                            return 1;
                        } else {
                            return o2.getReleaseYear().compareTo(o1.getReleaseYear());
                        }
                    } else {
                        if (o1.getReleaseYear() > o2.getReleaseYear()) {
                            return -1;
                        } else if (o1.getReleaseYear() < o2.getReleaseYear()) {
                            return 1;
                        } else {
                            return o2.getUserScore().compareTo(o1.getUserScore());
                        }
                    }
                }
            } else {
                if (o1.getReleaseYear() > o2.getReleaseYear()) {
                    return -1;
                } else if (o1.getReleaseYear() < o2.getReleaseYear()) {
                    return 1;
                } else {
                    if (mediumPriorityBox.getValue().equals("Пользовательский рейтинг")) {
                        if (o1.getUserScore() > o2.getUserScore()) {
                            return -1;
                        } else if (o1.getUserScore() < o2.getUserScore()) {
                            return 1;
                        } else {
                            Long formattedBudget1 = Long.parseLong(o1.getBudget().replace(",", ""));
                            Long formattedBudget2 = Long.parseLong(o2.getBudget().replace(",", ""));
                            Long formattedGross1 = Long.parseLong(o1.getGrossWorld().replace(",", ""));
                            Long formattedGross2 = Long.parseLong(o2.getGrossWorld().replace(",", ""));
                            return Long.compare(formattedGross2 / formattedBudget2, formattedGross1 / formattedBudget1);
                        }
                    } else {
                        Long formattedBudget1 = Long.parseLong(o1.getBudget().replace(",", ""));
                        Long formattedBudget2 = Long.parseLong(o2.getBudget().replace(",", ""));
                        Long formattedGross1 = Long.parseLong(o1.getGrossWorld().replace(",", ""));
                        Long formattedGross2 = Long.parseLong(o2.getGrossWorld().replace(",", ""));
                        if (formattedGross1 / formattedBudget1 > formattedGross2 / formattedBudget2) {
                            return -1;
                        } else if (formattedGross1 / formattedBudget1 < formattedGross2 / formattedBudget2) {
                            return 1;
                        } else {
                            return o2.getUserScore().compareTo(o1.getUserScore());
                        }
                    }
                }
            }
        });
    }

    public static ConceptEntity getCurrentConcept() {
        return currentConcept;
    }
}
