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

/**
 * The class Server.
 */
public class Server {
    /**
     * The Candidates.
     */
    static List<Candidate> candidates;
    /**
     * The constant nullVotes.
     */
    static Integer nullVotes = 0;
    /**
     * The constant whiteVotes.
     */
    static Integer whiteVotes = 0;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws IOException        the io exception
     * @throws URISyntaxException the uri syntax exception
     */
    public static void main(String[] args) throws IOException, URISyntaxException {
        Integer portNumber = 40108;
        candidates = new ArrayList<>();

        Gson gson = new Gson();
        InputStreamReader ir = new InputStreamReader(Server.class.getResourceAsStream("/candidates.json"));
        candidates = gson.fromJson(ir, new TypeToken<List<Candidate>>() {}.getType());
        Collections.sort(candidates, (o1, o2) -> o1.getName().compareTo(o2.getName()));

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            System.out.println("Ouvindo por novos clientes na porta 40108");
            while (true) {
                Socket socket = serverSocket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                try {
                    int code = Integer.parseInt(in.readLine());

                    if (code == 999) {
                        System.out.println("999 - Criando Thread para enviar cadidatos para a urna cliente");
                        new CandidatesThread(socket).start();
                    } else if (code == 888) {
                        System.out.println("888 - Criando Thread para receber resultados de uma urna cliente");
                        new VotingThread(socket, in).start();
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