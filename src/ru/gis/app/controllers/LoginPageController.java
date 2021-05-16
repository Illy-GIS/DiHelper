package ru.gis.app.controllers;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import ru.gis.app.ApplicationMain;
import ru.gis.app.entities.UserEntity;
import ru.gis.app.managers.UserManager;
import ru.gis.app.util.CipherUtil;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Objects;

public class LoginPageController {
    @FXML
    private AnchorPane mainPane;

    @FXML
    private Pane leftBluePane;

    @FXML
    private ImageView logoImageView;

    @FXML
    private ImageView loginPicture;

    @FXML
    private Pane rightWhitePane;

    @FXML
    private Label enterLabel;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Label noAccountLabel;

    @FXML
    private Button registerSuggestionButton;

    @FXML
    private Pane registerForChangePane;

    @FXML
    private Label registrationLabel;

    @FXML
    private TextField regEmailField;

    @FXML
    private TextField regNameField;

    @FXML
    private Button goNextButton;

    @FXML
    private Label haveAccountLabel;

    @FXML
    private Button loginSuggestionButton;

    @FXML
    private PasswordField regPasswordField;

    @FXML
    private PasswordField regPasswordConfirmField;

    @FXML
    private Button regBackButton;

    @FXML
    private Button registerButton;

    @FXML
    private Pane loginFieldsPane;

    @FXML
    private Pane firstRegPane;

    @FXML
    private Pane secondRegPane;

    @FXML
    private Label errorTextLog;

    @FXML
    private Label errorTextReg;

    @FXML
    private void initialize() {
        emailField.setText("gameinsir@gmail.com");
        passwordField.setText("100701ZHil");

        registerForChangePane.setVisible(false);
        secondRegPane.setVisible(false);
        logoImageView.setImage(new Image(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream("images/bigLogo.png"))));
        loginPicture.setImage(new Image(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream("images/11273.png"))));
        mainPane.setOnMouseClicked(mouseEvent -> mainPane.requestFocus());
        initSuggestionButtons();
        initFunctionalButtons();
        errorTextLog.setWrapText(true);
        errorTextReg.setWrapText(true);
    }

    private void initFunctionalButtons() {
        loginButton.setOnAction(actionEvent -> {
            try {
                UserEntity potentialUser = UserManager.getRowByEmail(emailField.getText());
                if (potentialUser != null) {
                    int iterations = Integer.parseInt(String.valueOf(potentialUser.getPassword().charAt(potentialUser.getPassword().length() - 1)));
                    byte[] enteredPassword = passwordField.getText().getBytes();
                    String hashedEnteredPassword = CipherUtil.hashDataSetIterations(enteredPassword, iterations);
                    if (potentialUser.getPassword().equals(hashedEnteredPassword)) {
                        ApplicationMain.currentUser = potentialUser;
                        ApplicationMain.mainScene.setRoot(FXMLLoader.load(getClass().getResource("../views/MainPage.fxml")));
                    } else {
                        errorTextLog.setText("Введенный пароль неверен");
                    }
                } else {
                    errorTextLog.setText("Пользователя с введенной почтой не существует");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        goNextButton.setOnAction(actionEvent -> {
            if (regEmailField.getText().length() < 1 || regNameField.getText().length() < 1) {
                errorTextReg.setText("Поля не должны быть пустыми");
            } else {
                if (isValidDataFirstPage()) {
                    errorTextReg.setText("");
                    firstRegPane.setVisible(true);
                    secondRegPane.setVisible(true);
                    TranslateTransition toRegFirstTransition = new TranslateTransition();
                    toRegFirstTransition.setNode(firstRegPane);
                    toRegFirstTransition.setToX(-590);
                    toRegFirstTransition.setDuration(new Duration(800));
                    TranslateTransition toRegSecondTransition = new TranslateTransition();
                    toRegSecondTransition.setNode(secondRegPane);
                    toRegSecondTransition.setToX(-590);
                    toRegSecondTransition.setDuration(new Duration(800));
                    toRegFirstTransition.setOnFinished(actionEvent1 -> firstRegPane.setVisible(false));
                    toRegFirstTransition.play();
                    toRegSecondTransition.play();
                }
            }
        });
        registerButton.setOnAction(actionEvent -> {
            if (regPasswordField.getText().length() < 8) {
                errorTextReg.setText("Пароль должен состоять не менее чем из 8 символов");
            } else if (!regPasswordField.getText().equals(regPasswordConfirmField.getText())) {
                errorTextReg.setText("Пароли не совпадают");
            } else if (!regPasswordField.getText().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=\\S+$).{8,}$")) {
                errorTextReg.setText("Пароль может содержать буквы английского алфавита и цифры от 0 до 9, не может содержать пробелов и спец. символов. При этом в пароле должна присутствовать как минимум одна цифра и заглавная буква");
            } else {
                try {
                    UserEntity userToRegister = new UserEntity(regEmailField.getText(), regNameField.getText(), CipherUtil.hashData(regPasswordField.getText().getBytes()));
                    UserManager.addRow(userToRegister);
                    ApplicationMain.currentUser = userToRegister;
                    ApplicationMain.mainScene.setRoot(FXMLLoader.load(getClass().getResource("../../../../sample/sample.fxml")));
                } catch (NoSuchAlgorithmException | SQLException | IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private boolean isValidDataFirstPage() {
        StringBuilder errorLog = new StringBuilder();
        boolean troublesFound = false;
        if (!regEmailField.getText().matches("^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$")) {
            troublesFound = true;
            errorLog.append("Пожалуйста введите корректный email");
        }
        if (!regNameField.getText().matches("[a-zA-Zа-яА-я\s\\-`']*")) {
            troublesFound = true;
            if (!errorLog.isEmpty()) {
                errorLog.append("\n");
            }
            errorLog.append("В имени можно использовать буквы русского и английского алфавита, пробел, дефис и апострофы");
        }
        if (troublesFound) {
            errorTextReg.setText(errorLog.toString());
            return false;
        } else {
            return true;
        }
    }

    private void initSuggestionButtons() {
        registerSuggestionButton.setOnAction(actionEvent -> {
            rightWhitePane.setVisible(true);
            registerForChangePane.setVisible(true);
            TranslateTransition toRegFirstTransition = new TranslateTransition();
            toRegFirstTransition.setNode(rightWhitePane);
            toRegFirstTransition.setToY(-950);
            toRegFirstTransition.setDuration(new Duration(800));
            TranslateTransition toRegSecondTransition = new TranslateTransition();
            toRegSecondTransition.setNode(registerForChangePane);
            toRegSecondTransition.setToY(-950);
            toRegSecondTransition.setDuration(new Duration(800));
            toRegFirstTransition.setOnFinished(actionEvent1 -> rightWhitePane.setVisible(false));
            toRegFirstTransition.play();
            toRegSecondTransition.play();
        });
        loginSuggestionButton.setOnAction(actionEvent -> {
            rightWhitePane.setVisible(true);
            registerForChangePane.setVisible(true);
            TranslateTransition toLogFirstTransition = new TranslateTransition();
            toLogFirstTransition.setNode(rightWhitePane);
            toLogFirstTransition.setToY(0);
            toLogFirstTransition.setDuration(new Duration(800));
            TranslateTransition toLogSecondTransition = new TranslateTransition();
            toLogSecondTransition.setNode(registerForChangePane);
            toLogSecondTransition.setToY(0);
            toLogSecondTransition.setDuration(new Duration(800));
            toLogSecondTransition.setOnFinished(actionEvent1 -> registerForChangePane.setVisible(false));
            toLogFirstTransition.play();
            toLogSecondTransition.play();
        });
        regBackButton.setOnAction(actionEvent -> {
            errorTextReg.setText("");
            firstRegPane.setVisible(true);
            secondRegPane.setVisible(true);
            TranslateTransition toRegFirstTransition = new TranslateTransition();
            toRegFirstTransition.setNode(firstRegPane);
            toRegFirstTransition.setToX(0);
            toRegFirstTransition.setDuration(new Duration(800));
            TranslateTransition toRegSecondTransition = new TranslateTransition();
            toRegSecondTransition.setNode(secondRegPane);
            toRegSecondTransition.setToX(0);
            toRegSecondTransition.setDuration(new Duration(800));
            toRegFirstTransition.setOnFinished(actionEvent1 -> secondRegPane.setVisible(false));
            toRegFirstTransition.play();
            toRegSecondTransition.play();
        });
    }
}
