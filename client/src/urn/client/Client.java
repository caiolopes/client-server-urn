package urn.client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import urn.client.controller.LoginController;
import urn.client.view.ConfirmBox;

public class Client extends Application {
    private Stage window;
    private Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        LoginController loginController = new LoginController();

        window.setTitle("Urna - " + loginController.getTitle());
        window.setScene(loginController.getScene());
        window.show();
        window.setOnCloseRequest(e -> {
            e.consume();
            if(ConfirmBox.display("Sair", "Tem certeza que deseja sair?")) {
                window.close();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
