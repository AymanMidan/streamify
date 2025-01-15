package projetjava;

import java.io.Serializable;
import java.time.LocalDate;

public class Abonnement implements Serializable {
    private String typeAbonnement;
    private double prixMensuel;
    private int dureeEngagement;
    private LocalDate dateDebut;

    public Abonnement(String typeAbonnement, double prixMensuel, int dureeEngagement) {
        this.typeAbonnement = typeAbonnement;
        this.prixMensuel = prixMensuel;
        this.dureeEngagement = dureeEngagement;
        this.dateDebut = LocalDate.now();
    }

    public String getTypeAbonnement() {
        return typeAbonnement;
    }

    public double getPrixMensuel() {
        return prixMensuel;
    }

    public int getDureeEngagement() {
        return dureeEngagement;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public boolean estActif() {
        return LocalDate.now().isBefore(dateDebut.plusMonths(dureeEngagement));
    }

    @Override
    public String toString() {
        return "Abonnement [Type: " + typeAbonnement + ", Prix: " + prixMensuel + "€/mois, Durée: " + dureeEngagement + " mois]";
    }

    // Exception interne pour les abonnements expirés
    public static class AbonnementExpireException extends Exception {
        public AbonnementExpireException(String message) {
            super(message);
        }
    }
}
