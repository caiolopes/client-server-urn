package urn.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CandidatesThread extends Thread {
    private Socket socket = null;

    public CandidatesThread(Socket socket) {
        super("CandidatesThread");
        this.socket = socket;
    }
    
    public void run() {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(getCandidates());
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getCandidates() {
        byte[] encoded = new byte[0];
        try {
            encoded = Files.readAllBytes(Paths.get(getClass().getResource("/candidates.json").toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return new String(encoded, Charset.defaultCharset());
    }
}
