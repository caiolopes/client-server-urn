package urn.client.view;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import urn.client.util.Validator;

/**
 * Tela de cadastro de um novo usuário
 */
public class RegisterScene {
    private Scene scene;
    private TextField nameInput;
    private TextField cpfInput;
    private TextField passInput;
    private TextField passAgainInput;
    private Button signInBtn;
    private Button backBtn;

    public RegisterScene(int width, int height) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Name Label
        Label nameLabel = new Label("Nome:");
        GridPane.setConstraints(nameLabel, 0, 0);

        // Name input
        nameInput = new TextField();
        GridPane.setConstraints(nameInput, 1, 0);

        // CPF label
        Label cpfLabel = new Label("CPF:");
        GridPane.setConstraints(cpfLabel, 0, 1);

        // CPF input
        cpfInput = new TextField();
        cpfInput.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!Validator.isValidCPF(cpfInput.getText())) {
                cpfInput.setStyle("-fx-text-box-border: red;");
                cpfInput.setPromptText("CPF inválido!");
            } else {
                cpfInput.setStyle("");
            }
        });
        GridPane.setConstraints(cpfInput, 1, 1);

        // Password label
        Label passLabel = new Label("Senha:");
        GridPane.setConstraints(passLabel, 0, 2);

        // Password input
        passInput = new PasswordField();
        passInput.setPromptText("Senha");
        GridPane.setConstraints(passInput, 1, 2);

        // Password again label
        Label passAgainLabel = new Label("Senha de novo:");
        GridPane.setConstraints(passAgainLabel, 0, 3);

        // Password again input
        passAgainInput = new PasswordField();
        passAgainInput.setPromptText("Senha de novo");
        GridPane.setConstraints(passAgainInput, 1, 3);

        signInBtn = new Button("Cadastrar");
        GridPane.setHalignment(signInBtn, HPos.CENTER);
        GridPane.setConstraints(signInBtn, 1, 4);

        backBtn = new Button("Voltar");
        GridPane.setHalignment(backBtn, HPos.CENTER);
        GridPane.setConstraints(backBtn, 1, 7);

        grid.getChildren().addAll(nameLabel, nameInput, cpfLabel, cpfInput, passLabel, passAgainLabel, passAgainInput, passInput, signInBtn, backBtn);

        scene = new Scene(grid, width, height);
    }

    public Scene getScene() {
        return scene;
    }

    public TextField getNameInput() {
        return nameInput;
    }

    public TextField getCpfInput() {
        return cpfInput;
    }

    public TextField getPassInput() {
        return passInput;
    }

    public TextField getPassAgainInput() {
        return passAgainInput;
    }

    public Button getSignInBtn() {
        return signInBtn;
    }

    public Button getBackBtn() {
        return backBtn;
    }
}
