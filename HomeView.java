package projetjava;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javafx.application.HostServices;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class HomeView {
    private BorderPane root;
    private Plateforme plateforme;
    private Utilisateur utilisateur;
    private HostServices hostServices;

    public HomeView(HostServices hostServices) {
        this.hostServices = hostServices;
        root = new BorderPane();
        root.setStyle("-fx-background-color: #141414;");

        // Appliquer l'image de fond dès le démarrage
        Image backgroundImage = new Image("file:images/background.jpg");
        BackgroundImage background = new BackgroundImage(
            backgroundImage,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
        );
        root.setBackground(new Background(background));

        // Initialiser la plateforme
        plateforme = new Plateforme();
        chargerExemplesContenus();

        // Afficher la page de connexion par défaut
        afficherPageConnexion();
    }

    private void afficherPageConnexion() {
        root.getChildren().clear(); // Vider la vue
        VBox loginBox = new VBox(20);
        loginBox.setAlignment(Pos.CENTER);
        loginBox.setPadding(new Insets(50));

        // Logo
        ImageView logo = new ImageView(new Image("file:images/logo.png"));
        logo.setFitWidth(200);
        logo.setFitHeight(100);

        // Champ de texte pour l'email
        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        emailField.setMaxWidth(300);
        emailField.setStyle("-fx-background-color: #333; -fx-text-fill: white; -fx-padding: 10px;");

        // Champ de texte pour le mot de passe
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Mot de passe");
        passwordField.setMaxWidth(300);
        passwordField.setStyle("-fx-background-color: #333; -fx-text-fill: white; -fx-padding: 10px;");

        // Label pour les messages d'erreur
        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);

        // Bouton de connexion
        Button loginButton = new Button("Se connecter");
        loginButton.getStyleClass().add("login-button");
        loginButton.setOnAction(e -> {
            String email = emailField.getText();
            String password = passwordField.getText();

            // Validation des entrées
            if (email.isEmpty() || password.isEmpty()) {
                errorLabel.setText("Veuillez remplir tous les champs.");
            } else if (!email.contains("@")) {
                errorLabel.setText("Veuillez entrer une adresse email valide.");
            } else {
                // Vérifier si l'utilisateur existe dans la base de données
                utilisateur = DatabaseHandler.trouverUtilisateur(email, password);
                if (utilisateur != null) {
                    errorLabel.setText(""); // Effacer le message d'erreur
                    afficherPageAccueil();
                } else {
                    errorLabel.setText("Email ou mot de passe incorrect.");
                }
            }
        });

        // Bouton pour aller à la page d'inscription
        Button signupButton = new Button("Pas de compte ? S'inscrire");
        signupButton.getStyleClass().add("login-button");
        signupButton.setOnAction(e -> afficherPageInscription());

        loginBox.getChildren().addAll(logo, emailField, passwordField, loginButton, signupButton, errorLabel);
        
        // Ajouter l'image de fond
        Image backgroundImage = new Image("file:images/background.jpg");
        BackgroundImage background = new BackgroundImage(
            backgroundImage,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
        );
        root.setBackground(new Background(background));
        
        root.setCenter(loginBox);
    }

    private void afficherPageInscription() {
        root.getChildren().clear(); // Vider la vue
        VBox inscriptionBox = new VBox(20);
        inscriptionBox.setAlignment(Pos.CENTER);
        inscriptionBox.setPadding(new Insets(50));

        // Logo
        ImageView logo = new ImageView(new Image("file:images/logo.png"));
        logo.setFitWidth(200);
        logo.setFitHeight(100);

        // Champ de texte pour le nom
        TextField nomField = new TextField();
        nomField.setPromptText("Nom complet");
        nomField.setMaxWidth(300);
        nomField.setStyle("-fx-background-color: #333; -fx-text-fill: white; -fx-padding: 10px;");

        // Champ de texte pour l'email
        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        emailField.setMaxWidth(300);
        emailField.setStyle("-fx-background-color: #333; -fx-text-fill: white; -fx-padding: 10px;");

        // Champ de texte pour le mot de passe
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Mot de passe");
        passwordField.setMaxWidth(300);
        passwordField.setStyle("-fx-background-color: #333; -fx-text-fill: white; -fx-padding: 10px;");

        // Champ de texte pour le pays
        TextField paysField = new TextField();
        paysField.setPromptText("Pays");
        paysField.setMaxWidth(300);
        paysField.setStyle("-fx-background-color: #333; -fx-text-fill: white; -fx-padding: 10px;");

        // Champ de texte pour la ville
        TextField villeField = new TextField();
        villeField.setPromptText("Ville");
        villeField.setMaxWidth(300);
        villeField.setStyle("-fx-background-color: #333; -fx-text-fill: white; -fx-padding: 10px;");

        // Label pour les messages d'erreur
        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);

        // Bouton d'inscription
        Button inscriptionButton = new Button("S'inscrire");
        inscriptionButton.getStyleClass().add("login-button");
        inscriptionButton.setOnAction(e -> {
            String nom = nomField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();
            String pays = paysField.getText();
            String ville = villeField.getText();

            // Validation des entrées
            if (nom.isEmpty() || email.isEmpty() || password.isEmpty() || pays.isEmpty() || ville.isEmpty()) {
                errorLabel.setText("Veuillez remplir tous les champs.");
            } else if (!email.contains("@")) {
                errorLabel.setText("Veuillez entrer une adresse email valide.");
            } else {
                errorLabel.setText(""); // Effacer le message d'erreur
                utilisateur = new Utilisateur(nom, email, new Abonnement("Premium", 9.99, 12));
                utilisateur.setPays(pays);
                utilisateur.setVille(ville);
                utilisateur.setMotDePasse(password); // Définir le mot de passe
                DatabaseHandler.inscrireUtilisateur(utilisateur); // Enregistrer dans la base de données
                afficherPageAccueil();
            }
        });

        // Bouton pour aller à la page de connexion
        Button loginButton = new Button("Déjà un compte ? Se connecter");
        loginButton.getStyleClass().add("login-button");
        loginButton.setOnAction(e -> afficherPageConnexion());

        inscriptionBox.getChildren().addAll(logo, nomField, emailField, passwordField, paysField, villeField, inscriptionButton, loginButton, errorLabel);
        
        // Ajouter l'image de fond
        Image backgroundImage = new Image("file:images/background.jpg");
        BackgroundImage background = new BackgroundImage(
            backgroundImage,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
        );
        root.setBackground(new Background(background));
        
        root.setCenter(inscriptionBox);
    }

    private void afficherPageAccueil() {
        root.getChildren().clear(); // Vider la page de connexion

        // En-tête
        HBox header = createHeader();
        root.setTop(header);

        // Contenu principal
        ScrollPane mainContent = createMainContent();
        root.setCenter(mainContent);
    }

    private HBox createHeader() {
        HBox header = new HBox(20);
        header.setPadding(new Insets(15));
        header.setStyle("-fx-background-color: #000;");
        header.setAlignment(Pos.CENTER_LEFT);

        // Logo
        ImageView logo = new ImageView(new Image("file:images/logo.png"));
        logo.setFitWidth(150);
        logo.setFitHeight(50);

        // Barre de recherche
        TextField searchField = new TextField();
        searchField.setPromptText("Rechercher un film, une série...");
        searchField.setPrefWidth(400);
        searchField.setStyle("-fx-background-color: #333; -fx-text-fill: white; -fx-padding: 10px;");

        // Bouton de recherche
        Button searchButton = new Button("Rechercher");
        searchButton.setStyle("-fx-background-color: #E50914; -fx-text-fill: white; -fx-font-size: 14px;");
        searchButton.setOnAction(e -> handleSearch(searchField.getText()));

        // Menu utilisateur
        MenuBar userMenu = new MenuBar();
        Menu menu = new Menu("Profil");
        MenuItem profileItem = new MenuItem("Mon profil");
        profileItem.setOnAction(e -> afficherPageProfil());
        MenuItem logoutItem = new MenuItem("Déconnexion");
        logoutItem.setOnAction(e -> handleDeconnexion());
        menu.getItems().addAll(profileItem, logoutItem);
        userMenu.getMenus().add(menu);

        header.getChildren().addAll(logo, searchField, searchButton, userMenu);
        return header;
    }

    private ScrollPane createMainContent() {
        VBox mainContent = new VBox(20); // Conteneur principal
        mainContent.setPadding(new Insets(20));

        // Section "Recommandé pour vous"
        Label recommendedLabel = new Label("Recommandé pour vous");
        recommendedLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        recommendedLabel.setTextFill(Color.WHITE);

        // Diviser les contenus en groupes de 7
        List<Contenu> contenus = new ArrayList<>(plateforme.getContenus());
        int nombreDeLignes = (int) Math.ceil((double) contenus.size() / 7); // Calculer le nombre de lignes nécessaires

        for (int i = 0; i < nombreDeLignes; i++) {
            HBox ligne = new HBox(20); // Créer une nouvelle ligne
            ligne.setAlignment(Pos.CENTER_LEFT);

            // Ajouter jusqu'à 7 contenus dans la ligne
            for (int j = i * 7; j < Math.min((i + 1) * 7, contenus.size()); j++) {
                ligne.getChildren().add(createContentCard(contenus.get(j)));
            }

            // Ajouter la ligne au conteneur principal
            mainContent.getChildren().add(ligne);
        }

        // Ajouter le titre et les résultats au conteneur principal
        mainContent.getChildren().add(0, recommendedLabel); // Ajouter le titre en haut

        // Mettre à jour le contenu principal de la vue
        ScrollPane scrollPane = new ScrollPane(mainContent);
        scrollPane.setFitToWidth(true); // Ajuster la largeur
        scrollPane.setStyle("-fx-background: #141414; -fx-border-color: #141414;"); // Style du ScrollPane
        return scrollPane;
    }

    private VBox createContentCard(Contenu contenu) {
        VBox card = new VBox(10); // Conteneur pour la carte
        card.setPadding(new Insets(10)); // Espacement interne
        card.setStyle("-fx-background-color: #333; -fx-border-radius: 5px; -fx-background-radius: 5px;");

        // Charger l'image associée au contenu
        ImageView image = new ImageView(new Image("file:images/" + contenu.getImagePath()));
        image.setFitWidth(200); // Largeur de l'image
        image.setFitHeight(300); // Hauteur de l'image

        // Titre du contenu
        Label titleLabel = new Label(contenu.getTitre());
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        titleLabel.setTextFill(Color.WHITE);

        // Bouton "Regarder"
        Button watchButton = new Button("Regarder");
        watchButton.setStyle("-fx-background-color: #E50914; -fx-text-fill: white;");
        watchButton.setOnAction(e -> handleWatchContent(contenu));

        // Ajouter l'image, le titre et le bouton à la carte
        card.getChildren().addAll(image, titleLabel, watchButton);
        return card;
    }

    private void afficherPageProfil() {
        VBox profilBox = new VBox(20);
        profilBox.setAlignment(Pos.CENTER);
        profilBox.setPadding(new Insets(50));

        // Titre
        Label titleLabel = new Label("Mon Profil");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titleLabel.setTextFill(Color.WHITE);

        // Champ pour le nom
        TextField nomField = new TextField(utilisateur.getNom());
        nomField.setPromptText("Nom complet");
        nomField.setMaxWidth(300);
        nomField.setStyle("-fx-background-color: #333; -fx-text-fill: white; -fx-padding: 10px;");

        // Champ pour l'email
        TextField emailField = new TextField(utilisateur.getEmail());
        emailField.setPromptText("Email");
        emailField.setMaxWidth(300);
        emailField.setStyle("-fx-background-color: #333; -fx-text-fill: white; -fx-padding: 10px;");

        // Champ pour le pays
        TextField paysField = new TextField(utilisateur.getPays());
        paysField.setPromptText("Pays");
        paysField.setMaxWidth(300);
        paysField.setStyle("-fx-background-color: #333; -fx-text-fill: white; -fx-padding: 10px;");

        // Champ pour la ville
        TextField villeField = new TextField(utilisateur.getVille());
        villeField.setPromptText("Ville");
        villeField.setMaxWidth(300);
        villeField.setStyle("-fx-background-color: #333; -fx-text-fill: white; -fx-padding: 10px;");

        // Bouton pour sauvegarder les modifications
        Button saveButton = new Button("Sauvegarder");
        saveButton.getStyleClass().add("login-button");
        saveButton.setOnAction(e -> {
            utilisateur.setNom(nomField.getText());
            utilisateur.setEmail(emailField.getText());
            utilisateur.setPays(paysField.getText());
            utilisateur.setVille(villeField.getText());
            System.out.println("Profil mis à jour !");
        });

        // Bouton pour retourner à la page d'accueil
        Button backButton = new Button("Retour");
        backButton.getStyleClass().add("login-button");
        backButton.setOnAction(e -> afficherPageAccueil());

        // Ajouter les éléments au conteneur
        profilBox.getChildren().addAll(titleLabel, nomField, emailField, paysField, villeField, saveButton, backButton);
        root.setCenter(profilBox);
    }

    private void handleDeconnexion() {
        utilisateur = null; // Réinitialiser l'utilisateur
        root.getChildren().clear(); // Vider la vue
        afficherPageConnexion(); // Rediriger vers la page de connexion
    }

    private void handleSearch(String searchTerm) {
        System.out.println("Recherche : " + searchTerm);

        // Appeler la méthode de recherche de la plateforme
        Set<Contenu> resultats = plateforme.rechercherContenu(searchTerm);

        // Afficher les résultats dans l'interface utilisateur
        afficherResultatsRecherche(resultats);
    }
    
    private void afficherResultatsRecherche(Set<Contenu> resultats) {
        VBox mainContent = new VBox(20); // Conteneur principal pour les résultats
        mainContent.setPadding(new Insets(20)); // Espacement interne

        // Titre de la section de résultats
        Label resultatsLabel = new Label("Résultats de la recherche");
        resultatsLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24)); // Style du titre
        resultatsLabel.setTextFill(Color.WHITE); // Couleur du texte

        // Conteneur pour les cartes de contenu
        HBox resultatsSection = new HBox(20);
        resultatsSection.setAlignment(Pos.CENTER_LEFT); // Alignement des cartes

        // Ajouter chaque contenu trouvé sous forme de carte
        for (Contenu contenu : resultats) {
            resultatsSection.getChildren().add(createContentCard(contenu));
        }

        // Ajouter le titre et les résultats au conteneur principal
        mainContent.getChildren().addAll(resultatsLabel, resultatsSection);

        // Mettre à jour le contenu principal de la vue
        ScrollPane scrollPane = new ScrollPane(mainContent);
        scrollPane.setFitToWidth(true); // Ajuster la largeur
        scrollPane.setStyle("-fx-background: #141414; -fx-border-color: #141414;"); // Style du ScrollPane
        root.setCenter(scrollPane); // Afficher les résultats dans le centre de la vue
    }

    private void handleWatchContent(Contenu contenu) {
        try {
            plateforme.regarderContenu(utilisateur, contenu); // Enregistrer dans l'historique
            hostServices.showDocument(contenu.getYoutubeUrl()); // Ouvrir l'URL YouTube
        } catch (Abonnement.AbonnementExpireException e) {
            System.out.println(e.getMessage());
        }
    }

    private void chargerExemplesContenus() {
        try {
            // Films
            plateforme.ajouterContenu(new Contenu("Inception", "Science-Fiction", 2010, "Un voleur qui s'infiltre dans les rêves.", "inception.jpg", "https://www.youtube.com/watch?v=YoHD9XEInc0"));
            plateforme.ajouterContenu(new Contenu("The Dark Knight", "Action", 2008, "Batman contre le Joker.", "the-dark-knight.jpg", "https://www.youtube.com/watch?v=EXeTwQWrcwY"));
            plateforme.ajouterContenu(new Contenu("Interstellar", "Science-Fiction", 2014, "Un voyage à travers l'espace pour sauver l'humanité.", "interstellar.jpg", "https://www.youtube.com/watch?v=zSWdZVtXT7E"));

            // Séries
            plateforme.ajouterContenu(new Contenu("Breaking Bad", "Crime", 2008, "Un professeur de chimie se lance dans la fabrication de méthamphétamine.", "breakingbad.jpg", "https://www.youtube.com/watch?v=HhesaQXLuRY"));
            plateforme.ajouterContenu(new Contenu("Game of Thrones", "Fantasy", 2011, "Des familles nobles se battent pour le contrôle du Trône de Fer.", "got.jpg", "https://www.youtube.com/watch?v=gcTkNV5Vg1E"));

            // Documentaires
            plateforme.ajouterContenu(new Contenu("Planet Earth", "Documentaire", 2006, "Une exploration des écosystèmes de la Terre.", "planet_earth.jpg", "https://www.youtube.com/watch?v=7Pq-S557XQU"));
            plateforme.ajouterContenu(new Contenu("The Social Dilemma", "Documentaire", 2020, "Les dangers des réseaux sociaux et de la technologie.", "the_social_dilemma.jpg", "https://www.youtube.com/watch?v=uaaC57tcci0"));
        
         // Nouveaux contenus
            plateforme.ajouterContenu(new Contenu("Stranger Things", "Science-Fiction", 2016, "Des événements étranges se produisent dans une petite ville.", "stranger_things.jpg", "https://www.youtube.com/watch?v=b9EkMc79ZSU"));
            plateforme.ajouterContenu(new Contenu("The Witcher", "Fantasy", 2019, "Un chasseur de monstres parcourt un monde rempli de dangers.", "the_witcher.jpg", "https://www.youtube.com/watch?v=ndl1W4ltcmg"));
            plateforme.ajouterContenu(new Contenu("Black Mirror", "Science-Fiction", 2011, "Une série d'histoires sur les dangers de la technologie.", "black_mirror.jpg", "https://www.youtube.com/watch?v=jDiYGjp5iFg"));
            plateforme.ajouterContenu(new Contenu("The Mandalorian", "Science-Fiction", 2019, "Un chasseur de primes solitaire explore la galaxie.", "the_mandalorian.jpg", "https://www.youtube.com/watch?v=aOC8E8z_ifw"));
            plateforme.ajouterContenu(new Contenu("The Crown", "Drame historique", 2016, "Une série dramatique sur le règne de la reine Elizabeth II.", "the_crown.jpg", "https://www.youtube.com/watch?v=JWtnJjn6ng0"));
            plateforme.ajouterContenu(new Contenu("Avengers: Endgame", "Super-héros", 2019, "Les Avengers se réunissent pour inverser les dégâts causés par Thanos.", "avengers_endgame.jpg", "https://www.youtube.com/watch?v=TcMBFSGVi1c"));
            plateforme.ajouterContenu(new Contenu("Narcos", "Crime", 2015, "Une série dramatique sur le cartel de Medellín et Pablo Escobar.", "narcos.jpg", "https://www.youtube.com/watch?v=xl8zdCY-abw"));
        } catch (Contenu.ContenuExistantException e) {
            System.out.println(e.getMessage());
        }
    }

    public BorderPane getView() {
        return root;
    }
}