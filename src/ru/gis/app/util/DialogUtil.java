package ru.gis.app.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import ru.gis.app.ApplicationMain;

import java.util.Optional;

public class DialogUtil {
    private static final Alert alert = new Alert(Alert.AlertType.NONE);
    private static boolean ownerInitFlag = false;

    public static void showError(String message) {
        checkInit();
        alert.setAlertType(Alert.AlertType.ERROR);
        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().clear();
        alert.getButtonTypes().add(okButton);
        alert.setTitle("Ошибка!");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static boolean showConfirm(String message) {
        checkInit();
        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        ButtonType okButton = new ButtonType("Да", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Нет", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(okButton, cancelButton);
        alert.setTitle("Подтверждение");
        alert.setHeaderText(null);
        alert.setContentText(message);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();
        return optionalButtonType.isPresent() && optionalButtonType.get() == okButton;
    }

    public static ButtonType showConceptConfirm(String message) {
        checkInit();
        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        ButtonType yesButton = new ButtonType("Да", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("Нет", ButtonBar.ButtonData.NO);
        ButtonType cancelButton = new ButtonType("Отмена", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(yesButton, noButton, cancelButton);
        alert.setTitle("Подтверждение");
        alert.setHeaderText(null);
        alert.setContentText(message);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();
        return optionalButtonType.orElse(null);
    }

    public static void showInfo(String message) {
        checkInit();
        alert.setAlertType(Alert.AlertType.INFORMATION);
        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().clear();
        alert.getButtonTypes().add(okButton);
        alert.setTitle("Сообщение");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private static void checkInit() {
        if (!ownerInitFlag) {
            alert.initOwner(ApplicationMain.mainStage);
            ownerInitFlag = true;
        }
    }
}
