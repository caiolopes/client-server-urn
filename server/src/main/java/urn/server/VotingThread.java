package urn.server;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import urn.server.model.Candidate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.List;

import static urn.server.Server.candidates;

public class VotingThread extends Thread {
    private Socket socket = null;

    public VotingThread(Socket socket) {
        super("VotingThread");
        this.socket = socket;
    }
    
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Gson gson = new Gson();
            List<Candidate> candidatesFromClient = gson.fromJson(in.readLine(), new TypeToken<List<Candidate>>() {}.getType());
            for (Candidate c : candidatesFromClient) {
                Candidate candidate = findCandidate(c.getCode());
                if (candidate != null) {
                    candidate.vote();
                    System.out.println("Candidate: " + candidate.getName() + ", votes: " + candidate.getVotes());
                }
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Candidate findCandidate(int code) {
        for (Candidate candidate : candidates) {
            if (candidate.getCode() == code)
                return candidate;
        }
        return null;
    }
}