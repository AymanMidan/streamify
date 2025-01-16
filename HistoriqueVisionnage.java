package projetjava;

import java.io.Serializable;
import java.time.LocalDate;

public class HistoriqueVisionnage implements Serializable {
    private Utilisateur utilisateur;
    private Contenu contenu;
    private LocalDate dateVisionnage;

    public HistoriqueVisionnage(Utilisateur utilisateur, Contenu contenu, LocalDate dateVisionnage) {
        this.utilisateur = utilisateur;
        this.contenu = contenu;
        this.dateVisionnage = dateVisionnage;
        
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

    @Override
    public String toString() {
        return "HistoriqueVisionnage [Utilisateur: " + utilisateur.getNom() + ", Contenu: " + contenu.getTitre() + ", Date: " + dateVisionnage +"]";
    }
}