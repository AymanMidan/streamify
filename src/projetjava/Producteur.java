package projetjava;

import projetjava.Contenu.ContenuExistantException;

public class Producteur extends Personne {
    public Producteur(String nom, String email) {
        super(nom, email);
    }

    public void ajouterContenu(Plateforme plateforme, Contenu contenu) throws Contenu.ContenuExistantException {
        plateforme.ajouterContenu(contenu);
    }

    @Override
    public String toString() {
        return "Producteur [Nom: " + getNom() + ", Email: " + getEmail() + "]";
    }
}