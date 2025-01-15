package projetjava;

import java.util.ArrayList;
import java.util.List;

import projetjava.Abonnement.AbonnementExpireException;

public class Utilisateur extends Personne {
    private Abonnement abonnement;
    private List<String> preferredGenres; // Liste des genres préférés
    private String pays; // Nouvel attribut
    private String ville; // Nouvel attribut
    private String motDePasse; 

    public Utilisateur(String nom, String email, Abonnement abonnement) {
        super(nom, email);
        this.abonnement = abonnement;
        this.preferredGenres = new ArrayList<>(); // Initialisation de la liste
        this.pays = ""; // Initialisation par défaut
        this.ville = ""; // Initialisation par défaut
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
        return new ArrayList<>(preferredGenres); // Retourne une copie pour protéger la liste
    }

    public void setPreferredGenres(List<String> selectedGenres) {
        if (selectedGenres != null) {
            this.preferredGenres = new ArrayList<>(selectedGenres); // Définit une copie pour protéger la liste
        } else {
            this.preferredGenres = new ArrayList<>();
        }
    }

    // Getters et setters pour pays et ville
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