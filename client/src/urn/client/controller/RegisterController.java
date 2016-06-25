package urn.client.controller;

import javafx.scene.Scene;
import urn.client.Config;
import urn.client.util.Validator;
import urn.client.view.AlertBox;
import urn.client.view.RegisterScene;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class RegisterController {
    private String TITLE = "Registro";
    private RegisterScene registerScene;

    public RegisterController(ChangeSceneListener window) {
        registerScene = new RegisterScene(Config.DEFAULT_WIDTH, Config.DEFAULT_HEIGHT);

        registerScene.getSignInBtn().setOnAction(e -> {
            if (Validator.isValidCPF(registerScene.getCpfInput().getText())) {
                sendLoginInfo();
            } else {
                AlertBox.display("Erro", "CPF inválido");
            }
        });

        registerScene.getBackBtn().setOnAction(e -> {
            window.goToLoginScene();
        });
    }

    public Scene getScene() {
        return registerScene.getScene();
    }

    public String getTitle() {
        return TITLE;
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
