package urn.server;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import urn.server.model.Candidate;

import java.io.*;
import java.net.Socket;
import java.util.Collections;
import java.util.List;

import static urn.server.Server.candidates;
import static urn.server.Server.nullVotes;
import static urn.server.Server.whiteVotes;

/**
 * The class Voting thread.
 */
class VotingThread extends Thread {
    private Socket socket = null;
    private BufferedReader in;

    /**
     * Instantiates a new Voting thread.
     *
     * @param socket the socket
     * @param in     the in
     */
    VotingThread(Socket socket, BufferedReader in) {
        super("VotingThread");
        this.socket = socket;
        this.in = in;
    }
    
    public void run() {
        try {
            System.out.println("\033[2J");
            Gson gson = new Gson();
            int whiteVotes = Integer.parseInt(in.readLine());
            int nullVotes = Integer.parseInt(in.readLine());
            String json = in.readLine();
            socket.close();

            List<Candidate> candidatesFromClient = gson.fromJson(json, new TypeToken<List<Candidate>>() {}.getType());
            // método síncrono para garantir que apenas uma thread por vez adicione os votos dos candidatos
            addVotesFromClient(candidatesFromClient, whiteVotes, nullVotes);

            Collections.sort(candidates, (o1, o2) -> o2.getVotes().compareTo(o1.getVotes()));
            // método síncrono para garantir que apenas uma thread imprima os resultados por vez
            printResults();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private synchronized void addVotesFromClient(List<Candidate> candidatesFromClient, int whiteVotes, int nullVotes) {
        Server.whiteVotes += whiteVotes;
        Server.nullVotes += nullVotes;

        for (Candidate c : candidatesFromClient) {
            Candidate candidate = findCandidate(c.getCode());
            if (candidate != null) {
                candidate.setVotes(candidate.getVotes()+c.getVotes());
            }
        }
    }

    private synchronized void printResults() throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(new File("results.data"), false));
        out.println("Votos nulos: " + nullVotes);
        out.println("Votos em branco: " + whiteVotes);
        System.out.println("Votos em branco: " + whiteVotes);
        System.out.println("Votos em nulo: " + nullVotes);
        for (Candidate candidate : candidates) {
            String str = candidate.getPoliticalParty() + " - " + candidate.getName() + " - Total de votos: " + candidate.getVotes();
            System.out.println(str);
            out.println(str);
        }
        out.flush();
        out.close();
    }

    private Candidate findCandidate(int code) {
        for (Candidate candidate : candidates) {
            if (candidate.getCode() == code)
                return candidate;
        }
        return null;
    }
}