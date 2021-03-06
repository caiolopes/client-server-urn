package urn.client;

import urn.client.controller.MainController;
import urn.client.view.ConfirmBox;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The type Client.
 */
public class Client extends Application {
    private Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        Parent root = FXMLLoader.load(getClass().getResource("/main.fxml"));
        window.setScene(new Scene(root, Config.DEFAULT_WIDTH, Config.DEFAULT_HEIGHT));
        window.setTitle(MainController.TITLE);
        window.show();
        window.setOnCloseRequest(e -> {
            e.consume();
            if(ConfirmBox.display("Sair", "Tem certeza que deseja sair?", 300, 200)) {
                window.close();
            }
        });
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}