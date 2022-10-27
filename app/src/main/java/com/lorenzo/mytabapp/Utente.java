package com.lorenzo.mytabapp;

public class Utente {

    private String nome;
    private String email;
    private String password;
    private String id_utente;


    public Utente(String nome, String email, String id_utente, String password) {
        this.nome = nome;
        this.id_utente = id_utente;
        this.email = email;
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getId_utente() {
        return id_utente;
    }

    public void setId_utente(String id_utente) {
        this.id_utente = id_utente;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }



}
