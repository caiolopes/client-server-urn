package client;

import client.view.ConfirmBox;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Client extends Application {
    private Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));
        window.setScene(new Scene(root, Config.DEFAULT_WIDTH, Config.DEFAULT_HEIGHT));

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
