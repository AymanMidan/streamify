package projetjava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseHandler {
    private static final String URL = "jdbc:mysql://localhost:3306/streamify"; // Pour MySQL
    private static final String USER = "root"; // Votre utilisateur MySQL
    private static final String PASSWORD = "1a2b"; // Votre mot de passe MySQL

    // Méthode pour établir une connexion à la base de données
    public static Connection getConnection() throws SQLException {
        System.out.println("Tentative de connexion à la base de données...");
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        System.out.println("Connexion réussie !");
        return conn;
    }

    // Méthode pour inscrire un utilisateur
    public static void inscrireUtilisateur(Utilisateur utilisateur) {
        String query = "INSERT INTO utilisateurs (nom, email, mot_de_passe, pays, ville) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, utilisateur.getNom());
            pstmt.setString(2, utilisateur.getEmail());
            pstmt.setString(3, utilisateur.getMotDePasse());
            pstmt.setString(4, utilisateur.getPays());
            pstmt.setString(5, utilisateur.getVille());
            int rowsAffected = pstmt.executeUpdate();
            System.out.println(rowsAffected + " ligne(s) insérée(s).");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour vérifier les informations de connexion
    public static Utilisateur trouverUtilisateur(String email, String motDePasse) {
        String query = "SELECT * FROM utilisateurs WHERE email = ? AND mot_de_passe = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, email);
            pstmt.setString(2, motDePasse);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Utilisateur(
                    rs.getString("nom"),
                    rs.getString("email"),
                    new Abonnement("Premium", 9.99, 12) // Exemple d'abonnement
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}