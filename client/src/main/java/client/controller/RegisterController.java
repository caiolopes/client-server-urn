package client.controller;

import client.Config;
import client.util.Validator;
import client.view.AlertBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterController {
    public static String TITLE = "Registro";
    @FXML private TextField nameInput;
    @FXML private TextField cpfInput;
    @FXML private TextField passwordInput;
    @FXML private TextField passwordAgainInput;
    @FXML private Button signInBtn;
    @FXML private Button backBtn;

    @FXML protected void handleRegister(ActionEvent actionEvent) {
        if (Validator.isValidCPF(cpfInput.getText())) {
            // send to the server
        } else {
            AlertBox.display("Erro", "CPF inválido");
        }
    }

    @FXML protected void handleBack(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) backBtn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));
        window.setScene(new Scene(root, Config.DEFAULT_WIDTH, Config.DEFAULT_HEIGHT));
    }
}
