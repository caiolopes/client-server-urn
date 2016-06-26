package client.util;

import client.model.Candidate;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Network {

    public List<Candidate> getCandidatesList() {
        List<Candidate> candidates = new ArrayList<>();

        try {
            File file = new File(getClass().getResource("/candidates.json").toURI());
            if (file.exists()) {
                Gson gson = new Gson();
                BufferedReader br = new BufferedReader(new FileReader(file));
                candidates = gson.fromJson(br, new TypeToken<List<Candidate>>() {
                }.getType());
            }
        } catch (FileNotFoundException | URISyntaxException e) {
            e.printStackTrace();
        }
        /*
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
        }*/

        return candidates;
    }
}
