package projetjava;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Plateforme {
    private Set<Contenu> contenus = new HashSet<>();
    private ArrayList<HistoriqueVisionnage> historiques = new ArrayList<>();

   
    public void ajouterContenu(Contenu contenu) throws Contenu.ContenuExistantException {
        if (contenus.contains(contenu)) {
            throw new Contenu.ContenuExistantException("Le contenu existe déjà : " + contenu.getTitre());
        }
        contenus.add(contenu);
        System.out.println("Contenu ajouté : " + contenu.getTitre());
    }

   
    public Set<Contenu> rechercherContenu(String motCle) {
        Set<Contenu> resultats = new HashSet<>();
        if (motCle == null || motCle.trim().isEmpty()) {
            return resultats; 
        }

        String motCleLower = motCle.toLowerCase();
        for (Contenu contenu : contenus) {
            String titre = contenu.getTitre();
            if (titre != null && titre.toLowerCase().contains(motCleLower)) {
                resultats.add(contenu);
            }
        }
        return resultats;
    }


    public void recommanderContenu(Utilisateur utilisateur) {
        System.out.println("Recommandations pour " + utilisateur.getNom() + ":");
        ArrayList<String> genresVus = new ArrayList<>();
        for (HistoriqueVisionnage h : historiques) {
            if (h.getUtilisateur().equals(utilisateur) && !genresVus.contains(h.getContenu().getGenre())) {
                genresVus.add(h.getContenu().getGenre());
            }
        }
        for (Contenu contenu : contenus) {
            if (genresVus.contains(contenu.getGenre())) {
                System.out.println(" - " + contenu.getTitre() + " (" + contenu.getGenre() + ")");
            }
        }
    }

    
    public void sauvegarderDonnees(String fichierContenus, String fichierHistoriques) {
        try (ObjectOutputStream oosContenus = new ObjectOutputStream(new FileOutputStream(fichierContenus));
             ObjectOutputStream oosHistoriques = new ObjectOutputStream(new FileOutputStream(fichierHistoriques))) {
            oosContenus.writeObject(new ArrayList<>(contenus)); // Convertir Set en ArrayList pour la sérialisation
            oosHistoriques.writeObject(historiques);
            System.out.println("Données sauvegardées avec succès !");
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde des données : " + e.getMessage());
        }
    }

  
    public void chargerDonnees(String fichierContenus, String fichierHistoriques) {
        try {
       
            if (!new File(fichierContenus).exists() || !new File(fichierHistoriques).exists()) {
                System.out.println("Les fichiers de données n'existent pas. Création des fichiers...");
                sauvegarderDonnees(fichierContenus, fichierHistoriques); // Crée les fichiers vides
                return;
            }

    
            try (ObjectInputStream oisContenus = new ObjectInputStream(new FileInputStream(fichierContenus));
                 ObjectInputStream oisHistoriques = new ObjectInputStream(new FileInputStream(fichierHistoriques))) {
                contenus = new HashSet<>((ArrayList<Contenu>) oisContenus.readObject()); // Convertir ArrayList en Set
                historiques = (ArrayList<HistoriqueVisionnage>) oisHistoriques.readObject();
                System.out.println("Données chargées avec succès !");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erreur lors du chargement des données : " + e.getMessage());
        }
    }

  
    public void regarderContenu(Utilisateur utilisateur, Contenu contenu) throws Abonnement.AbonnementExpireException {
        utilisateur.verifierAbonnement(); 
        HistoriqueVisionnage historique = new HistoriqueVisionnage(utilisateur, contenu, LocalDate.now()); // 120 minutes
        historiques.add(historique);
        System.out.println("Vous regardez : " + contenu.getTitre());
    }

 
    public Set<Contenu> getContenus() {
        return contenus;
    }

    public ArrayList<HistoriqueVisionnage> getHistoriques() {
        return historiques;
    }
}