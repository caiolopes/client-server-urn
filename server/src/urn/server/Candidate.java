package servidor;

/**
 * Classe que representa um candidato possível de votar através da aplicação cliente
 */
public class Candidate {
    /**
     * Código de votação
     */
    private Integer code;
    /**
     * Nome do candidato
     */
    private String name;
    /**
     * Nome do partido político a que o candidato pertence
     */
    private String politicalParty;
    /**
     * Número de votos que esse candidato possui
     */
    private Integer votes;

    /**
     * Cria um novo candidato a partir de um código, nome e partido.
     * @param code código de votação
     * @param name nome do candidato
     * @param politicalParty nome do partido político
     */
    public Candidate(Integer code, String name, String politicalParty) {
        this.code = code;
        this.name = name;
        this.politicalParty = politicalParty;
        this.votes = 0;
    }

    /**
     * @return código de votação
     */
    public Integer getCode() {
        return code;
    }

    /**
     * @param code código do candidato
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * @return nome do candidato
     */
    public String getName() {
        return name;
    }

    /**
     * @param name nome do candidato
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return nome do partido político
     */
    public String getPoliticalParty() {
        return politicalParty;
    }

    /**
     * @param politicalParty nome do partido político do candidato
     */
    public void setPoliticalParty(String politicalParty) {
        this.politicalParty = politicalParty;
    }

    /**
     * @return Integer
     */
    public Integer getVotes() {
        return votes;
    }

    /**
     * @param votes votos do candidato
     */
    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    /**
     * Reseta o contador de votos
     */
    public void resetVotes() {
        this.votes = 0;
    }

    /**
     * Vota nesse candidato, incrementando seu contador de votos
     */
    public void vote() {
        this.votes++;
    }
}
