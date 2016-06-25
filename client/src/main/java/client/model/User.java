package client.model;

public class User {
    private String name;
    private String cpf;
    private String token;

    public User() {
    }

    public User(String name, String cpf, String token) {
        this.name = name;
        this.cpf = cpf;
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
