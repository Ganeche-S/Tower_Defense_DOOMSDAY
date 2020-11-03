package application.controleur;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class ControlerMainMenu implements Initializable {

	@FXML
    private Button lancerJeu;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button regles;
    
    protected static Media musiqueDuJeu;
    protected static Media bruitSelectionMenu;
    public static MediaPlayer mpMusiqueDuJeu;
    protected static MediaPlayer mpBruitSelectionMenu;

    @FXML
    void commencerPartie(ActionEvent event) {
		Pane root = null;
		try {
			mpBruitSelectionMenu.play();
			root = FXMLLoader.load(getClass().getClassLoader().getResource("application/vue/gameScreen.fxml"));
		} catch (IOException e) {
			System.out.println("vue1.fxml est introuvable");
			e.printStackTrace();
		}
		rootPane.getChildren().setAll(root);
    }

    @FXML
    void afficherRegles(ActionEvent event){
    	Pane root = null;
		try {
			mpBruitSelectionMenu.play();
			root = FXMLLoader.load(getClass().getClassLoader().getResource("application/vue/regles.fxml"));
		} catch (IOException e) {
			System.out.println("regles.fxml est introuvable");
			e.printStackTrace();
		}
    	rootPane.getChildren().setAll(root);

    }

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		rootPane.setStyle("-fx-background-image: none");
		rootPane.setStyle("-fx-background-color: #202020");
		creerMpBruitSelectionMenu();
		
	}
	
	public static void creerMpMusiqueDuJeu() {
		musiqueDuJeu = new Media(new File("src/application/vue/ressources/sounds/in-the-house-in-the-heartbeat.mp3").toURI().toString());
		mpMusiqueDuJeu = new MediaPlayer(musiqueDuJeu);
		mpMusiqueDuJeu.setVolume(1);
		
	}
	
	public static void creerMpBruitSelectionMenu() {
		bruitSelectionMenu = new Media(new File("src/application/vue/ressources/sounds/coup de feu.mp3").toURI().toString());
		mpBruitSelectionMenu = new MediaPlayer(bruitSelectionMenu);
		mpBruitSelectionMenu.setVolume(0.2);
	}

}
