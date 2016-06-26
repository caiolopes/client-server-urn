package client.controller;

import client.model.Candidate;
import client.util.Network;
import client.view.ConfirmBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.util.List;

public class MainController {
    public static final String TITLE = "Urna - Tela Principal";
    public Button sendBtn;
    public Button loadBtn;
    public Button nullBtn;
    public Button whiteBtn;
    @FXML private ListView<String> listView;
    @FXML private Button confirmBtn;
    Network network = new Network();
    private List<Candidate> candidates;

    public void initialize() {
        listView.getSelectionModel().selectedItemProperty().addListener(e -> {
            confirmBtn.setDisable(false);
            String code = listView.getSelectionModel().getSelectedItems().get(0).split(" - ")[2];
            confirmBtn.setText("Confirma " + code);
        });
    }

    @FXML protected void confirmVote() {
        String[] arr = listView.getSelectionModel().getSelectedItems().get(0).split(" - ");
        ConfirmBox.display("Votar", "Confirma votar em " + arr[1] + " do " + arr[0] + "?", 400, 300);
    }

    public void loadCandidates(ActionEvent event) {
        listView.getItems().removeAll();
        candidates = network.getCandidatesList();
        for (Candidate candidate : candidates) {
            listView.getItems().add(candidate.getPoliticalParty() + " - " + candidate.getName() + " - " + candidate.getCode());
        }
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