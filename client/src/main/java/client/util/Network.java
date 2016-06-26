package client.util;

import client.Config;
import client.model.Candidate;
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

public class Network {

    public List<Candidate> getCandidatesList() {
        List<Candidate> candidates = new ArrayList<>();

        try {
            Socket urnSocket = new Socket(Config.HOST, Config.PORT);
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

    public List<Candidate> sendVotes(List<Candidate> candidates, Integer nullVotes, Integer whiteVotes) {
        try {
            Socket urnSocket = new Socket(Config.HOST, Config.PORT);
            PrintWriter out = new PrintWriter(urnSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(urnSocket.getInputStream()));

            out.println(888);
            out.flush();
            out.println(nullVotes);
            out.println(whiteVotes);
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
