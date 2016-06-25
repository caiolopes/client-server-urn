package client.controller;

import client.view.ConfirmBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class MainController {
    public static final String TITLE = "Urna - Tela Principal";
    public Button sendBtn;
    public Button loadBtn;
    public Button nullBtn;
    public Button whiteBtn;
    @FXML private ListView<String> listView;
    @FXML private Button confirmBtn;

    public void initialize() {
        listView.getSelectionModel().selectedItemProperty().addListener(e -> {
            confirmBtn.setDisable(false);
            String code = listView.getSelectionModel().getSelectedItems().get(0).split(" - ")[2];
            confirmBtn.setText("Confirma " + code);
        });
    }

    @FXML protected void confirmVote() {
        String[] arr = listView.getSelectionModel().getSelectedItems().get(0).split(" - ");
        ConfirmBox.display("Votar", "Confirma votar em " + arr[1] + " do " + arr[0] + "?");
    }

    public void loadCandidates(ActionEvent event) {
        listView.getItems().removeAll();
        listView.getItems().addAll("PP - Waldir Maranhão - 232", "PRB - Beto Mansur - 221", "PSDB - Mara Gabrilli - 532"
        ,"PP - Waldir Maranhão - 232", "PRB - Beto Mansur - 221", "PSDB - Mara Gabrilli - 532"
        ,"PP - Waldir Maranhão - 232", "PRB - Beto Mansur - 221", "PSDB - Mara Gabrilli - 532");
        whiteBtn.setDisable(false);
        nullBtn.setDisable(false);
    }

    public void sendAndFinish(ActionEvent event) {
    }

    public void nullVote(ActionEvent event) {
    }

    public void whiteVote(ActionEvent event) {
    }
}
