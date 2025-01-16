package projetjava;

import java.time.LocalDate;
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
    private List<HistoriqueVisionnage> historiqueVisionnages = new ArrayList<>();

    public HomeView(HostServices hostServices) {
        this.hostServices = hostServices;
        root = new BorderPane();
        root.setStyle("-fx-background-color: #141414;");

        Image backgroundImage = new Image("file:images/background.jpg");
        BackgroundImage background = new BackgroundImage(
            backgroundImage,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
        );
        root.setBackground(new Background(background));

        plateforme = new Plateforme();
        chargerExemplesContenus();

        afficherPageConnexion();
    }

    private void afficherPageConnexion() {
        root.getChildren().clear(); 
        VBox loginBox = new VBox(20);
        loginBox.setAlignment(Pos.CENTER);
        loginBox.setPadding(new Insets(50));

        ImageView logo = new ImageView(new Image("file:images/logo.png"));
        logo.setFitWidth(200);
        logo.setFitHeight(100);

        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        emailField.setMaxWidth(300);
        emailField.setStyle("-fx-background-color: #333; -fx-text-fill: white; -fx-padding: 10px;");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Mot de passe");
        passwordField.setMaxWidth(300);
        passwordField.setStyle("-fx-background-color: #333; -fx-text-fill: white; -fx-padding: 10px;");

        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);

        Button loginButton = new Button("Se connecter");
        loginButton.getStyleClass().add("login-button");
        loginButton.setOnAction(e -> {
            String email = emailField.getText();
            String password = passwordField.getText();

            if (email.isEmpty() || password.isEmpty()) {
                errorLabel.setText("Veuillez remplir tous les champs.");
            } else if (!email.contains("@")) {
                errorLabel.setText("Veuillez entrer une adresse email valide.");
            } else {

                utilisateur = DatabaseHandler.trouverUtilisateur(email, password);
                if (utilisateur != null) {
                    errorLabel.setText(""); 
                    afficherPageAccueil();
                } else {
                    errorLabel.setText("Email ou mot de passe incorrect.");
                }
            }
        });

        Button signupButton = new Button("Pas de compte ? S'inscrire");
        signupButton.getStyleClass().add("login-button");
        signupButton.setOnAction(e -> afficherPageInscription());

        loginBox.getChildren().addAll(logo, emailField, passwordField, loginButton, signupButton, errorLabel);

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

    	ComboBox<String> abonnementComboBox = new ComboBox<>();
    	abonnementComboBox.getItems().addAll("Gratuit", "Standard", "Premium");
    	abonnementComboBox.setValue("Gratuit");
    	abonnementComboBox.setMaxWidth(300);
    	abonnementComboBox.setStyle("-fx-background-color: #333; -fx-text-fill: white; -fx-padding: 10px;");
    	
        root.getChildren().clear(); 
        VBox inscriptionBox = new VBox(20);
        inscriptionBox.setAlignment(Pos.CENTER);
        inscriptionBox.setPadding(new Insets(50));

        ImageView logo = new ImageView(new Image("file:images/logo.png"));
        logo.setFitWidth(200);
        logo.setFitHeight(100);

        TextField nomField = new TextField();
        nomField.setPromptText("Nom complet");
        nomField.setMaxWidth(300);
        nomField.setStyle("-fx-background-color: #333; -fx-text-fill: white; -fx-padding: 10px;");

        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        emailField.setMaxWidth(300);
        emailField.setStyle("-fx-background-color: #333; -fx-text-fill: white; -fx-padding: 10px;");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Mot de passe");
        passwordField.setMaxWidth(300);
        passwordField.setStyle("-fx-background-color: #333; -fx-text-fill: white; -fx-padding: 10px;");

        TextField paysField = new TextField();
        paysField.setPromptText("Pays");
        paysField.setMaxWidth(300);
        paysField.setStyle("-fx-background-color: #333; -fx-text-fill: white; -fx-padding: 10px;");

        TextField villeField = new TextField();
        villeField.setPromptText("Ville");
        villeField.setMaxWidth(300);
        villeField.setStyle("-fx-background-color: #333; -fx-text-fill: white; -fx-padding: 10px;");

        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);

        Button inscriptionButton = new Button("S'inscrire");
        inscriptionButton.setOnAction(e -> {
            String nom = nomField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();
            String pays = paysField.getText();
            String ville = villeField.getText();
            String typeAbonnement = abonnementComboBox.getValue(); 

            if (nom.isEmpty() || email.isEmpty() || password.isEmpty() || pays.isEmpty() || ville.isEmpty()) {
                errorLabel.setText("Veuillez remplir tous les champs.");
            } else if (!email.contains("@")) {
                errorLabel.setText("Veuillez entrer une adresse email valide.");
            } else {
                errorLabel.setText("");

                Abonnement abonnement;
                switch (typeAbonnement) {
                    case "Gratuit":
                        abonnement = new Abonnement("Gratuit", 0.0, 0);
                        break;
                    case "Standard":
                        abonnement = new Abonnement("Standard", 4.99, 1);
                        break;
                    case "Premium":
                        abonnement = new Abonnement("Premium", 9.99, 12);
                        break;
                    default:
                        abonnement = new Abonnement("Gratuit", 0.0, 0);
                        break;
                }

                // Créer l'utilisateur avec l'abonnement choisi
                utilisateur = new Utilisateur(nom, email, abonnement);
                utilisateur.setPays(pays);
                utilisateur.setVille(ville);
                utilisateur.setMotDePasse(password); 
                DatabaseHandler.inscrireUtilisateur(utilisateur); 
                String fichierAbonnement = "abonnement_" + utilisateur.getEmail() + ".dat";
                FileHandler.sauvegarderAbonnement(utilisateur, fichierAbonnement);
                afficherPageAccueil();
            }
        });

        Button loginButton = new Button("Déjà un compte ? Se connecter");
        loginButton.getStyleClass().add("login-button");
        loginButton.setOnAction(e -> afficherPageConnexion());

        inscriptionBox.getChildren().addAll(logo, nomField, emailField, passwordField, paysField, villeField, abonnementComboBox, inscriptionButton, loginButton, errorLabel);

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
        root.getChildren().clear(); 

        HBox header = createHeader();
        root.setTop(header);

        ScrollPane mainContent = createMainContent();
        root.setCenter(mainContent);
    }

    private HBox createHeader() {
        HBox header = new HBox(20);
        header.setPadding(new Insets(15));
        header.setStyle("-fx-background-color: #000;");
        header.setAlignment(Pos.CENTER_LEFT);

        ImageView logo = new ImageView(new Image("file:images/logo.png"));
        logo.setFitWidth(150);
        logo.setFitHeight(50);

        Button accueilButton = new Button("Accueil");
        accueilButton.setStyle("-fx-background-color: #E50914; -fx-text-fill: white; -fx-font-size: 14px;");
        accueilButton.setOnAction(e -> afficherPageAccueil());

        TextField searchField = new TextField();
        searchField.setPromptText("Rechercher un film, une série...");
        searchField.setPrefWidth(400);
        searchField.setStyle("-fx-background-color: #333; -fx-text-fill: white; -fx-padding: 10px;");

        Button searchButton = new Button("Rechercher");
        searchButton.setStyle("-fx-background-color: #E50914; -fx-text-fill: white; -fx-font-size: 14px;");
        searchButton.setOnAction(e -> handleSearch(searchField.getText()));

        Button historiqueButton = new Button("Historique");
        historiqueButton.setStyle("-fx-background-color: #E50914; -fx-text-fill: white; -fx-font-size: 14px;");
        historiqueButton.setOnAction(e -> afficherHistoriqueVisionnage());

        MenuBar userMenu = new MenuBar();
        Menu menu = new Menu("Profil");
        MenuItem profileItem = new MenuItem("Mon profil");
        profileItem.setOnAction(e -> afficherPageProfil());
        MenuItem logoutItem = new MenuItem("Déconnexion");
        logoutItem.setOnAction(e -> handleDeconnexion());
        menu.getItems().addAll(profileItem, logoutItem);
        userMenu.getMenus().add(menu);

        header.getChildren().addAll(logo, accueilButton, searchField, searchButton, historiqueButton, userMenu);
        return header;
    }
    
    private ScrollPane createMainContent() {
        VBox mainContent = new VBox(20);
        mainContent.setPadding(new Insets(20));
     
        Button historiqueButton = new Button("Historique");
        historiqueButton.setOnAction(e -> afficherHistoriqueVisionnage());

        Label recommendedLabel = new Label("Recommandé pour vous");
        recommendedLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        recommendedLabel.setTextFill(Color.WHITE);

        List<Contenu> contenus = new ArrayList<>(plateforme.getContenus());
        int nombreDeLignes = (int) Math.ceil((double) contenus.size() / 7); 

        for (int i = 0; i < nombreDeLignes; i++) {
            HBox ligne = new HBox(20); 
            ligne.setAlignment(Pos.CENTER_LEFT);

            for (int j = i * 7; j < Math.min((i + 1) * 7, contenus.size()); j++) {
                ligne.getChildren().add(createContentCard(contenus.get(j)));
            }

            mainContent.getChildren().add(ligne);
        }

        mainContent.getChildren().add(0, recommendedLabel); 

        ScrollPane scrollPane = new ScrollPane(mainContent);
        scrollPane.setFitToWidth(true); 
        scrollPane.setStyle("-fx-background: #141414; -fx-border-color: #141414;");
        return scrollPane;
    }

    private VBox createContentCard(Contenu contenu) {
        VBox card = new VBox(10); 
        card.setPadding(new Insets(10)); 
        card.setStyle("-fx-background-color: #333; -fx-border-radius: 5px; -fx-background-radius: 5px;");

        ImageView image = new ImageView(new Image("file:images/" + contenu.getImagePath()));
        image.setFitWidth(200); 
        image.setFitHeight(300);

        Label titleLabel = new Label(contenu.getTitre());
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        titleLabel.setTextFill(Color.WHITE);

        Button watchButton = new Button("Regarder");
        watchButton.setStyle("-fx-background-color: #E50914; -fx-text-fill: white;");
        watchButton.setOnAction(e -> handleWatchContent(contenu));

        card.getChildren().addAll(image, titleLabel, watchButton);
        return card;
    }

    private void afficherPageProfil() {
        VBox profilBox = new VBox(20);
        profilBox.setAlignment(Pos.CENTER);
        profilBox.setPadding(new Insets(50));

        Label titleLabel = new Label("Mon Profil");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titleLabel.setTextFill(Color.WHITE);

        TextField nomField = new TextField(utilisateur.getNom());
        nomField.setPromptText("Nom complet");
        nomField.setMaxWidth(300);
        nomField.setStyle("-fx-background-color: #333; -fx-text-fill: white; -fx-padding: 10px;");

        TextField emailField = new TextField(utilisateur.getEmail());
        emailField.setPromptText("Email");
        emailField.setMaxWidth(300);
        emailField.setStyle("-fx-background-color: #333; -fx-text-fill: white; -fx-padding: 10px;");

        TextField paysField = new TextField(utilisateur.getPays());
        paysField.setPromptText("Pays");
        paysField.setMaxWidth(300);
        paysField.setStyle("-fx-background-color: #333; -fx-text-fill: white; -fx-padding: 10px;");

        TextField villeField = new TextField(utilisateur.getVille());
        villeField.setPromptText("Ville");
        villeField.setMaxWidth(300);
        villeField.setStyle("-fx-background-color: #333; -fx-text-fill: white; -fx-padding: 10px;");

        Button saveButton = new Button("Sauvegarder");
        saveButton.getStyleClass().add("login-button");
        saveButton.setOnAction(e -> {
            utilisateur.setNom(nomField.getText());
            utilisateur.setEmail(emailField.getText());
            utilisateur.setPays(paysField.getText());
            utilisateur.setVille(villeField.getText());
            System.out.println("Profil mis à jour !");
        });

        Button backButton = new Button("Retour");
        backButton.getStyleClass().add("login-button");
        backButton.setOnAction(e -> afficherPageAccueil());

        profilBox.getChildren().addAll(titleLabel, nomField, emailField, paysField, villeField, saveButton, backButton);
        root.setCenter(profilBox);
    }

    private void handleDeconnexion() {
        utilisateur = null; 
        root.getChildren().clear(); 
        afficherPageConnexion(); 
    }

    private void handleSearch(String searchTerm) {
        System.out.println("Recherche : " + searchTerm);

        Set<Contenu> resultats = plateforme.rechercherContenu(searchTerm);

        afficherResultatsRecherche(resultats);
    }
    
    private void afficherResultatsRecherche(Set<Contenu> resultats) {
        VBox mainContent = new VBox(20); 
        mainContent.setPadding(new Insets(20)); 

        Label resultatsLabel = new Label("Résultats de la recherche");
        resultatsLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24)); 
        resultatsLabel.setTextFill(Color.WHITE); 

        HBox resultatsSection = new HBox(20);
        resultatsSection.setAlignment(Pos.CENTER_LEFT); 

        for (Contenu contenu : resultats) {
            resultatsSection.getChildren().add(createContentCard(contenu));
        }

        mainContent.getChildren().addAll(resultatsLabel, resultatsSection);

        ScrollPane scrollPane = new ScrollPane(mainContent);
        scrollPane.setFitToWidth(true); 
        scrollPane.setStyle("-fx-background: #141414; -fx-border-color: #141414;"); 
        root.setCenter(scrollPane); 
    }

    private void handleWatchContent(Contenu contenu) {
        try {
            plateforme.regarderContenu(utilisateur, contenu); 

            HistoriqueVisionnage historique = new HistoriqueVisionnage(
                utilisateur,
                contenu,
                LocalDate.now() 
            );

            historiqueVisionnages.add(historique);
            
            hostServices.showDocument(contenu.getYoutubeUrl());
        } catch (Abonnement.AbonnementExpireException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void afficherHistoriqueVisionnage() {
        VBox historiqueBox = new VBox(10);
        historiqueBox.setAlignment(Pos.CENTER);
        historiqueBox.setPadding(new Insets(20));

        Label historiqueLabel = new Label("Historique de visionnage");
        historiqueLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        historiqueLabel.setTextFill(Color.WHITE);

        historiqueBox.getChildren().add(historiqueLabel);

        for (HistoriqueVisionnage historique : historiqueVisionnages) {
            Label historiqueEntry = new Label(historique.toString());
            historiqueEntry.setTextFill(Color.WHITE);
            historiqueBox.getChildren().add(historiqueEntry);
        }

        ScrollPane scrollPane = new ScrollPane(historiqueBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: #141414; -fx-border-color: #141414;");

        root.setCenter(scrollPane);
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