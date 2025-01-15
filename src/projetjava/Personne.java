package projetjava;

import java.io.Serializable;

public class Personne implements Serializable {
    private static int compteurId = 0;
    private int id;
    private String nom;
    private String email;

    public Personne(String nom, String email) {
        this.id = ++compteurId;
        this.nom = nom;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getEmail() {
        return email;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean estEmailValide() {
        return email != null && email.contains("@");
    }

    @Override
    public String toString() {
        return "Personne [ID: " + id + ", Nom: " + nom + ", Email: " + email + "]";
    }
}