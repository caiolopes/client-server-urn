package urn.client;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends Application {
    private String hostName = "localhost";
    private Integer portNumber = 4444;
    private Stage window;
    private Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        window.setTitle("Urna");

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
        TextField cpfInput = new TextField();
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
        TextField passInput = new TextField();
        passInput.setPromptText("Senha");
        GridPane.setConstraints(passInput, 1, 1);

        Button signInBtn = new Button("Entrar");
        GridPane.setConstraints(signInBtn, 1, 2);
        signInBtn.setOnAction(e -> {
            if (Validator.isValidCPF(cpfInput.getText())) {
                connect();
            } else {
                AlertBox.display("Erro", "CPF inválido");
            }
        });

        grid.getChildren().addAll(cpfLabel, cpfInput, passLabel, passInput, signInBtn);

        scene = new Scene(grid, 400, 300);

        window.setScene(scene);
        window.show();
        window.setOnCloseRequest(e -> {
            e.consume();
            if(ConfirmBox.display("Sair", "Tem certeza que deseja sair?")) {
                window.close();
            }
        });
    }

    private void connect() {
        try {
            Socket urnSocket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(urnSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(urnSocket.getInputStream()));

            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String fromServer;
            String fromUser;


            while ((fromServer = in.readLine()) != null) {
                System.out.println("Server: " + fromServer);
                if (fromServer.equals("Bye."))
                    break;

                fromUser = stdIn.readLine();
                if (fromUser != null) {
                    System.out.println("Client: " + fromUser);
                    out.println(fromUser);
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host");
                    System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to the host");
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
