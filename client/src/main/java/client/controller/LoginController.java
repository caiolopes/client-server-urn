package client.controller;

import client.Config;
import client.util.Validator;
import client.view.AlertBox;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class LoginController {
    public static String TITLE = "Entrar";
    @FXML private TextField cpfInput;
    @FXML private TextField passwordInput;
    @FXML private Button signInBtn;
    @FXML private TextFlow registerText;

    @FXML protected void handleSignIn(ActionEvent event) {
        if (Validator.isValidCPF(cpfInput.getText())) {
            sendLoginInfo();
        } else {
            AlertBox.display("Erro", "CPF inválido");
        }
    }

    @FXML protected void goToRegisterScene(Event event) throws IOException {
        Stage window = (Stage) registerText.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/register.fxml"));
        window.setScene(new Scene(root, Config.DEFAULT_WIDTH, Config.DEFAULT_HEIGHT));
    }

    private void sendLoginInfo() {
        try {
            Socket urnSocket = new Socket(Config.HOST, Config.PORT);
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
}
