package urn.client.util;

import urn.client.Config;
import urn.client.model.Candidate;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * The Network methods all in one class.
 */
public class Network {
    private static final String HOST = Config.HOST_PRODUCTION;

    /**
     * Gets candidates list.
     *
     * @return the candidates list
     */
    public List<Candidate> getCandidatesList() {
        List<Candidate> candidates = new ArrayList<>();

        try {
            Socket urnSocket = new Socket(HOST, Config.PORT);
            PrintWriter out = new PrintWriter(urnSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(urnSocket.getInputStream()));

            out.println(999);
            out.flush();

            Gson gson = new Gson();
            candidates = gson.fromJson(in.readLine(), new TypeToken<List<Candidate>>() {}.getType());
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to the host");
            System.exit(1);
        }

        return candidates;
    }

    /**
     * Send votes list.
     *
     * @param candidates the candidates
     * @param nullVotes  the null votes
     * @param whiteVotes the white votes
     * @return the list
     */
    public List<Candidate> sendVotes(List<Candidate> candidates, Integer nullVotes, Integer whiteVotes) {
        try {
            Socket urnSocket = new Socket(HOST, Config.PORT);
            PrintWriter out = new PrintWriter(urnSocket.getOutputStream(), true);
            //BufferedReader in = new BufferedReader(new InputStreamReader(urnSocket.getInputStream()));
            out.println(888);
            out.flush();
            out.println(whiteVotes);
            out.println(nullVotes);
            Gson gson = new Gson();
            out.println(gson.toJson(candidates));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to the host");
            System.exit(1);
        }

        return candidates;
    }
}
