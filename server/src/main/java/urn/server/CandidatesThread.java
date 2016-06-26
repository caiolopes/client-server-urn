package urn.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

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
        Scanner s = new java.util.Scanner(Server.class.getResourceAsStream("/candidates.json")).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
