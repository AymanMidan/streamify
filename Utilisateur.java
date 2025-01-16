package projetjava;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import projetjava.Abonnement.AbonnementExpireException;

public class Utilisateur extends Personne implements Serializable {
    private Abonnement abonnement;
    private List<String> preferredGenres; 
    private String pays; 
    private String ville; 
    private String motDePasse; 

    public Utilisateur(String nom, String email, Abonnement abonnement) {
        super(nom, email);
        this.abonnement = abonnement;
        this.preferredGenres = new ArrayList<>(); 
        this.pays = ""; 
        this.ville = ""; 
        this.motDePasse = "";
    }
    
 // Getter et Setter pour motDePasse
    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public Abonnement getAbonnement() {
        return abonnement;
    }

    public void setAbonnement(Abonnement abonnement) {
        this.abonnement = abonnement;
    }

    public void verifierAbonnement() throws Abonnement.AbonnementExpireException {
        if (!abonnement.estActif()) {
            throw new Abonnement.AbonnementExpireException("Votre abonnement a expiré.");
        }
    }

    public void afficherDetailsAbonnement() {
        System.out.println("Abonnement : " + abonnement.getTypeAbonnement());
        System.out.println("Prix mensuel : " + abonnement.getPrixMensuel() + "€");
        System.out.println("Durée : " + abonnement.getDureeEngagement() + " mois");
        System.out.println("Statut : " + (abonnement.estActif() ? "Actif" : "Expiré"));
    }

    public List<String> getPreferredGenres() {
        return new ArrayList<>(preferredGenres);
    }

    public void setPreferredGenres(List<String> selectedGenres) {
        if (selectedGenres != null) {
            this.preferredGenres = new ArrayList<>(selectedGenres);
        } else {
            this.preferredGenres = new ArrayList<>();
        }
    }

 
    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    @Override
    public String toString() {
        return "Utilisateur [Nom: " + getNom() + ", Email: " + getEmail() + ", Abonnement: " + abonnement +
               ", Genres préférés: " + preferredGenres + ", Pays: " + pays + ", Ville: " + ville + "]";
    }
}