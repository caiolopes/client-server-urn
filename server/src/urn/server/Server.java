package urn.server;

import java.net.*;
import java.io.*;

/**
 * Classe servidor que fica ouvindo num loop infinito por novos clientes querendo se conectar,
 * criando uma @see @link{urn.server.ServerThread} para cada novo cliente.
 */
public class Server {
    public static void main(String[] args) throws IOException {
        Integer portNumber = 4444;
        boolean listening = true;

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) { 
            while (listening) {
	            new ServerThread(serverSocket.accept()).start();
	        }
	    } catch (IOException e) {
            System.err.println("Could not listen on port " + portNumber);
            System.exit(-1);
        }
    }
}
