package urn.client.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import urn.client.util.Validator;

/**
 * Tela de login
 */
public class LoginScene {
    private Scene scene;
    private TextField cpfInput;
    private TextField passInput;
    private Button signInBtn;

    public LoginScene(int width, int height) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // CPF Label
        Label cpfLabel = new Label("CPF:");
        GridPane.setConstraints(cpfLabel, 0, 0);

        // CPF Input
        cpfInput = new TextField();
        cpfInput.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!Validator.isValidCPF(cpfInput.getText())) {
                cpfInput.setStyle("-fx-text-box-border: red;");
                cpfInput.setPromptText("CPF inválido!");
            } else {
                cpfInput.setStyle("");
            }
        });
        GridPane.setConstraints(cpfInput, 1, 0);

        // Password label
        Label passLabel = new Label("Senha:");
        GridPane.setConstraints(passLabel, 0, 1);

        // Password Input
        passInput = new TextField();
        passInput.setPromptText("Senha");
        GridPane.setConstraints(passInput, 1, 1);

        signInBtn = new Button("Entrar");
        GridPane.setConstraints(signInBtn, 1, 2);

        grid.getChildren().addAll(cpfLabel, cpfInput, passLabel, passInput, signInBtn);

        scene = new Scene(grid, width, height);
    }

    public Scene getScene() {
        return scene;
    }

    public TextField getCpfInput() {
        return cpfInput;
    }

    public TextField getPassInput() {
        return passInput;
    }

    public Button getSignInBtn() {
        return signInBtn;
    }
}
