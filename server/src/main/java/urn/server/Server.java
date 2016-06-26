package urn.server;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import urn.server.model.Candidate;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Server {
    public static List<Candidate> candidates;
    public static void main(String[] args) throws IOException, URISyntaxException {
        Integer portNumber = 4444;
        candidates = new ArrayList<>();
        Integer nullVotes = 0;
        Integer whiteVotes = 0;

        Gson gson = new Gson();
        FileReader fr = new FileReader(new File(Server.class.getResource("/candidates.json").toURI()));
        candidates = gson.fromJson(fr, new TypeToken<List<Candidate>>() {}.getType());
        Collections.sort(candidates, (o1, o2) -> o1.getName().compareTo(o2.getName()));

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            while (true) {
                Socket socket = serverSocket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                try {
                    int code = Integer.parseInt(in.readLine());

                    if (code == 999) {
                        System.out.println("999");
                        new CandidatesThread(socket).start();
                    } else if (code == 888) {
                        System.out.println("888");
                        whiteVotes += Integer.parseInt(in.readLine());
                        nullVotes += Integer.parseInt(in.readLine());
                        System.out.println("Votos em branco: " + whiteVotes);
                        System.out.println("Votos em nulo: " + nullVotes);
                        new VotingThread(socket).start();
                    } else {
                        socket.close();
                    }
                } catch(NumberFormatException e) {
                    e.printStackTrace();
                }

	        }
	    } catch (IOException e) {
            System.err.println("Could not listen on port " + portNumber);
            System.exit(-1);
        }
    }
}