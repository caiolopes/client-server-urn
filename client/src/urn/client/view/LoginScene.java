package urn.client.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import urn.client.util.Validator;

/**
 * Tela de login
 */
public class LoginScene {
    private Scene scene;
    private TextField cpfInput;
    private TextField passInput;
    private Button signInBtn;
    private TextFlow registerText;

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
        passInput = new PasswordField();
        passInput.setPromptText("Senha");
        GridPane.setConstraints(passInput, 1, 1);

        signInBtn = new Button("Entrar");
        GridPane.setConstraints(signInBtn, 1, 2);

        registerText = new TextFlow();

        Text registerText1 = new Text("Não é usuário ainda? ");
        Text registerText2 = new Text("Cadastre-se");
        registerText2.setStyle("-fx-font-weight: bold;");

        registerText.getChildren().addAll(registerText1, registerText2);
        GridPane.setConstraints(registerText, 1, 3);

        grid.getChildren().addAll(cpfLabel, cpfInput, passLabel, passInput, signInBtn, registerText);

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

    public TextFlow getRegisterText() {
        return registerText;
    }
}
