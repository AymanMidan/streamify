package projetjava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;

public class DatabaseHandler {
    private static final String URL = "jdbc:mysql://localhost:3306/streamify"; 
    private static final String USER = "root"; 
    private static final String PASSWORD = "1a2b"; 

    
    public static Connection getConnection() throws SQLException {
        System.out.println("Tentative de connexion à la base de données...");
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        System.out.println("Connexion réussie !");
        return conn;
    }

    
    public static void inscrireUtilisateur(Utilisateur utilisateur) {
        String query = "INSERT INTO utilisateurs (nom, email, mot_de_passe, pays, ville) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            // Hacher le mot de passe
            String motDePasseHache = BCrypt.hashpw(utilisateur.getMotDePasse(), BCrypt.gensalt());

            pstmt.setString(1, utilisateur.getNom());
            pstmt.setString(2, utilisateur.getEmail());
            pstmt.setString(3, motDePasseHache); // Utiliser le mot de passe haché
            pstmt.setString(4, utilisateur.getPays());
            pstmt.setString(5, utilisateur.getVille());
            
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   
    public static Utilisateur trouverUtilisateur(String email, String motDePasse) {
        String query = "SELECT * FROM utilisateurs WHERE email = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                String motDePasseHache = rs.getString("mot_de_passe");
                
                // Vérifier si le mot de passe fourni correspond au hachage stocké
                if (BCrypt.checkpw(motDePasse, motDePasseHache)) {
                    return new Utilisateur(
                        rs.getString("nom"),
                        rs.getString("email"),
                        new Abonnement("Premium", 9.99, 12) // Exemple d'abonnement
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}