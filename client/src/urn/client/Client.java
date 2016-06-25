package urn.client;

import javafx.application.Application;
import javafx.stage.Stage;
import urn.client.controller.ChangeSceneListener;
import urn.client.controller.LoginController;
import urn.client.controller.RegisterController;
import urn.client.view.ConfirmBox;

public class Client extends Application implements ChangeSceneListener {
    private Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        goToLoginScene();

        window.show();
        window.setOnCloseRequest(e -> {
            e.consume();
            if(ConfirmBox.display("Sair", "Tem certeza que deseja sair?")) {
                window.close();
            }
        });
    }

    @Override
    public void goToRegisterScene() {
        RegisterController registerController = new RegisterController(this);
        window.setScene(registerController.getScene());
        window.setTitle("Urna - " + registerController.getTitle());
    }

    @Override
    public void goToLoginScene() {
        LoginController loginController = new LoginController(this);
        window.setTitle("Urna - " + loginController.getTitle());
        window.setScene(loginController.getScene());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
