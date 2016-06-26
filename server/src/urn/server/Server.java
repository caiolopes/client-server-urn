package servidor;

import java.net.*;
import java.io.*; 
import java.lang.reflect.Type;
import com.google.gson.Gson;  
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe servidor que fica ouvindo num loop infinito por novos clientes querendo se conectar,
 * criando uma @see @link{urn.server.ServerThread} para cada novo cliente.
 */
public class Server {
    
    private static final int PORT = 40108;
    
    public static void main(String[] args) throws IOException {
        boolean listening = true;
                
        Gson gson = new Gson();
        BufferedReader bufferedReader = new BufferedReader(new FileReader("Candidates.json"));
        
        List<Candidate> candidates =  gson.fromJson(bufferedReader, new TypeToken<List<Candidate>>().getType());
        Candidate[] candidate = gson.fromJson(bufferedReader, Candidate[].class); 
        
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (listening) {
                System.out.println("Aguardando conexão do cliente...");
	        new ServerThread(serverSocket.accept()).start();
	    }
	} catch (IOException e) {
            System.err.println("Could not listen on port " + PORT);
            System.exit(-1);
        }
    }
}
