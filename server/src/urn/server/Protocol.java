package servidor;

/**
 * Classe que estabelece as regras do protocolo da urna
 */
public class Protocol {
    private static final int WAITING = 0;
    private static final int SENTKNOCKKNOCK = 1;
    private static final int SENTCLUE = 2;
    private static final int ANOTHER = 3;

    private static final int NUMJOKES = 5;

    private int state = WAITING;
    private int currentJoke = 0;

    private String[] clues = { "Turnip", "Little Old Lady", "Atch", "Who", "Who" };
    private String[] answers = { "Turnip the heat, it's cold in here!",
                                 "I didn't know you could yodel!",
                                 "Bless you!",
                                 "Is there an owl in here?",
                                 "Is there an echo in here?" };

    public String processInput(int theInput) {
        switch(theInput) {
            case 888:
                System.out.println("Cliente " + client.getInetAddress().getHostAddress() + ":" + client.getPort() + "pronto para enviar votos");
                receiveVotes();
            case 999:
                System.out.println("Cliente " + client.getInetAddress().getHostAddress() + ":" + client.getPort() + "pronto para receber lista de candidatos");
                sendCandidates();
            default:
        }      
        
        
        return ;
    }
}
