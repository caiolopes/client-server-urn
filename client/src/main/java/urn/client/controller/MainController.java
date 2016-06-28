package urn.client.controller;

import urn.client.model.Candidate;
import urn.client.util.Network;
import urn.client.view.ConfirmBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The Main controller.
 */
public class MainController {
    /**
     * The constant TITLE.
     */
    public static final String TITLE = "Urna - Tela Principal";
    /**
     * The Send button.
     */
    public Button sendBtn;
    /**
     * The Load button.
     */
    public Button loadBtn;
    /**
     * The Null button.
     */
    public Button nullBtn;
    /**
     * The White button.
     */
    public Button whiteBtn;
    /**
     * The list view of candidates
     */
    @FXML private ListView<String> listView;
    /**
     * The Confirm button
     */
    @FXML private Button confirmBtn;
    /**
     * The Network.
     */
    Network network = new Network();
    private List<Candidate> candidates = new ArrayList<>();
    private Integer nullVotes = 0;
    private Integer whiteVotes = 0;
    private int votes = 0;

    /**
     * Initialize.
     */
    public void initialize() {
        listView.getSelectionModel().selectedItemProperty().addListener(e -> {
            confirmBtn.setDisable(false);
            if (listView.getSelectionModel().getSelectedItems().size() > 0) {
                String code = listView.getSelectionModel().getSelectedItems().get(0).split(" - ")[2];
                confirmBtn.setText("Confirma " + code);
            }
        });
    }

    /**
     * Confirm vote.
     */
    @FXML protected void confirmVote() {
        String[] arr = listView.getSelectionModel().getSelectedItems().get(0).split(" - ");
        if(ConfirmBox.display("Votar", "Confirma votar em " + arr[1] + " do " + arr[0] + "?", 400, 300)) {
            Candidate candidate = findCandidate(Integer.parseInt(arr[2]));
            if (candidate != null) {
                candidate.vote();
                loadBtn.setDisable(true);
                sendBtn.setDisable(false);
            }
        }
    }

    private Candidate findCandidate(int code) {
        for (Candidate candidate : candidates) {
            if (candidate.getCode() == code)
                return candidate;
        }
        return null;
    }

    /**
     * Load candidates.
     *
     * @param event the event
     */
    public void loadCandidates(ActionEvent event) {
        if (votes == 0) {
            listView.getItems().clear();
            candidates.clear();
            System.out.println("999");
            candidates = network.getCandidatesList();
            Collections.sort(candidates, (o1, o2) -> o1.getName().compareTo(o2.getName()));
            for (Candidate candidate : candidates) {
                candidate.setVotes(0);
                listView.getItems().add(candidate.getPoliticalParty() + " - " + candidate.getName() + " - " + candidate.getCode());
            }
            whiteBtn.setDisable(false);
            nullBtn.setDisable(false);
        }
    }

    /**
     * Send and finish.
     *
     * @param event the event
     */
    public void sendAndFinish(ActionEvent event) {
        loadBtn.setDisable(false);
        listView.getItems().clear();
        System.out.println("888");
        network.sendVotes(candidates, nullVotes, whiteVotes);
        votes = 0;
        whiteVotes = 0;
        nullVotes = 0;
        confirmBtn.setText("Confirma");
        confirmBtn.setDisable(true);
        whiteBtn.setDisable(true);
        nullBtn.setDisable(true);
        sendBtn.setDisable(true);
        candidates.clear();
    }

    /**
     * Null vote.
     *
     * @param event the event
     */
    public void nullVote(ActionEvent event) {
        if (ConfirmBox.display("Votar", "Confirma votar em nulo?", 300, 200)) {
            nullVotes++;
            votes++;
            loadBtn.setDisable(true);
            sendBtn.setDisable(false);
        }
    }

    /**
     * White vote.
     *
     * @param event the event
     */
    public void whiteVote(ActionEvent event) {
        if (ConfirmBox.display("Votar", "Confirma votar em branco?", 300, 200)) {
            whiteVotes++;
            votes++;
            loadBtn.setDisable(true);
            sendBtn.setDisable(false);
        }
    }
}