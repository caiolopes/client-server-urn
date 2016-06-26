package servidor;

import java.net.*;
import java.io.*;
import java.util.Scanner;

/**
 * Thread que atende um cliente no servidor
 */
public class ServerThread extends Thread {
     private Socket socket;
    
    public ServerThread(Socket socket) {
        this.socket = socket;
    }
    
    private int readClient(InputStream in) {
        
        Scanner s = new Scanner(in);
        int result = s.nextInt();
        System.out.println("Mensagem recebida: " + result);
        return result;
    }
        
    @Override
    public void run() {
        System.out.println("Atendimento do cliente " + socket.getInetAddress().getHostAddress() + " na porta " + socket.getPort());
        
        Protocol urnProtocol = new Protocol();
        
        try {
            switch(readClient(socket.getInputStream())) {
                case 888:
                    System.out.println("Cliente " + socket.getInetAddress().getHostAddress() + ":" + socket.getPort() + "pronto para enviar votos");
                    
                    new VotingThread(socket).start();
                case 999:
                    System.out.println("Cliente " + socket.getInetAddress().getHostAddress() + ":" + socket.getPort() + "pronto para receber lista de candidatos");
                    new CandidateListThread(socket).start();
                default:
                    System.out.println("Cliente " + socket.getInetAddress().getHostAddress() + ":" + socket.getPort() + "não enviou opcode valido");
            }
            
            System.out.println("Fechando conecão com Cliente " + socket.getInetAddress().getHostAddress() + " na porta " + socket.getPort());
            this.socket.close(); 
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
    
    
    /*
    private Socket socket = null;

    public ServerThread(Socket socket) {
        super("UrnServerThread");
        this.socket = socket;
    }
    
    private int readClient(InputStream in) {
        
        Scanner s = new Scanner(in);
        int result = s.nextInt();
        System.out.println("Mensagem recebida: " + result);
        return result;
    }
    
    public void run() {
        System.out.println("Atendimento do cliente " + socket.getInetAddress().getHostAddress() + " na porta " + socket.getPort());
        
        try (
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            int inputLine, outputLine;
            Protocol urnProtocol = new Protocol();
            
            outputLine = urnProtocol.processInput(readClient();
            out.println(outputLine);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
*/