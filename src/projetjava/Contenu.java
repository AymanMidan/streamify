package projetjava;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Contenu implements Serializable {
    private String titre;
    private String genre;
    private int anneeSortie;
    private String description;
    private List<Integer> notes; // Liste pour stocker les notes
    private String imagePath;
    private String youtubeUrl;

    public Contenu(String titre, String genre, int anneeSortie, String description, String imagePath, String youtubeUrl) {
        this.titre = titre;
        this.genre = genre;
        this.anneeSortie = anneeSortie;
        this.description = description;
        this.notes = new ArrayList<>(); // Initialisation de la liste de notes
        this.imagePath = imagePath;
        this.youtubeUrl = youtubeUrl;
    }
    
 // Getter pour youtubeUrl
    public String getYoutubeUrl() {
        return youtubeUrl;
    }
    
 // Getter pour imagePath
    public String getImagePath() {
        return imagePath;
    }

    public String getTitre() {
        return titre;
    }

    public String getGenre() {
        return genre;
    }

    public int getAnneeSortie() {
        return anneeSortie;
    }

    public String getDescription() {
        return description;
    }

    public String getRating() {
        if (notes.isEmpty()) {
            return "Aucune note disponible";
        }
        double moyenne = notes.stream().mapToInt(Integer::intValue).average().orElse(0.0);
        return String.format("Note moyenne : %.1f/10 (%d avis)", moyenne, notes.size());
    }

    public void ajouterNote(int note) {
        if (note < 0 || note > 10) {
            System.out.println("La note doit être comprise entre 0 et 10.");
            return;
        }
        notes.add(note);
        System.out.println("Note ajoutée : " + note);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contenu contenu = (Contenu) o;
        return Objects.equals(titre, contenu.titre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titre);
    }

    @Override
    public String toString() {
        return "Contenu [Titre: " + titre + ", Genre: " + genre + ", Année: " + anneeSortie + 
               ", Description: " + description + ", " + getRating() + "]";
    }

    // Exception interne pour les contenus existants
    public static class ContenuExistantException extends Exception {
        public ContenuExistantException(String message) {
            super(message);
        }
    }
}