package projetjava;

import java.io.Serializable;
import java.time.LocalDate;

public class HistoriqueVisionnage implements Serializable {
    private Utilisateur utilisateur;
    private Contenu contenu;
    private LocalDate dateVisionnage;
    private int dureeVisionnage;

    public HistoriqueVisionnage(Utilisateur utilisateur, Contenu contenu, LocalDate dateVisionnage, int dureeVisionnage) {
        this.utilisateur = utilisateur;
        this.contenu = contenu;
        this.dateVisionnage = dateVisionnage;
        this.dureeVisionnage = dureeVisionnage;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public Contenu getContenu() {
        return contenu;
    }

    public LocalDate getDateVisionnage() {
        return dateVisionnage;
    }

    public int getDureeVisionnage() {
        return dureeVisionnage;
    }

    @Override
    public String toString() {
        return "HistoriqueVisionnage [Utilisateur: " + utilisateur.getNom() + ", Contenu: " + contenu.getTitre() + ", Date: " + dateVisionnage + ", Durée: " + dureeVisionnage + " minutes]";
    }
}