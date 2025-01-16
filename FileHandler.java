package projetjava;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class FileHandler {

 
    public static void sauvegarderAbonnement(Utilisateur utilisateur, String fichier) {
        try (FileOutputStream fileOut = new FileOutputStream(fichier);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            
           
            out.writeObject(utilisateur);
            System.out.println("Abonnement sauvegardé dans " + fichier);
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde de l'abonnement : " + e.getMessage());
        }
    }
    

    public static Utilisateur chargerAbonnement(String fichier) {
        try (FileInputStream fileIn = new FileInputStream(fichier);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            
          
            Utilisateur utilisateur = (Utilisateur) in.readObject();
            System.out.println("Abonnement chargé depuis " + fichier);
            return utilisateur;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erreur lors du chargement de l'abonnement : " + e.getMessage());
            return null;
        }
    }
}
