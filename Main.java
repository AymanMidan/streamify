package projetjava;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Créer la vue principale
        HomeView homeView = new HomeView(getHostServices()); // Passer HostServices

        // Configurer la scène
        Scene scene = new Scene(homeView.getView(), 1200, 800);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        // Configurer la fenêtre
        primaryStage.setTitle("Streamify");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}