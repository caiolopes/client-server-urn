package urn.client.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * The Confirm box.
 */
public class ConfirmBox {
    /**
     * The Answer.
     */
    private static boolean answer = true;

    /**
     * Display the confirm box
     *
     * @param title   the title
     * @param message the message
     * @param width   the width
     * @param height  the height
     * @return the result if the user answered yes or no
     */
    public static boolean display(String title, String message, double width, double height) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);

        Label label = new Label();
        label.setText(message);

        Button confirmBtn = new Button("Sim");
        confirmBtn.setOnAction(e -> {
            answer = true;
            window.close();
        });

        Button cancelBtn = new Button("Não");
        cancelBtn.setOnAction(e -> {
            answer = false;
            window.close();
        });

        HBox buttons = new HBox(20);
        buttons.getChildren().addAll(confirmBtn, cancelBtn);
        buttons.setAlignment(Pos.CENTER);

        VBox layout = new VBox(20);
        layout.getChildren().addAll(label, buttons);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, width, height);
        window.setScene(scene);
        window.showAndWait();

        return answer;
    }
}