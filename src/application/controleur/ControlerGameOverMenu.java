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

public class ControlerGameOverMenu implements Initializable {
	
	@FXML
	Button menuPrincipalButton;
	
	@FXML
	private AnchorPane aPane;
	
	private Media musiqueGameOver;
	private MediaPlayer mpMusiqueGameOver;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ControlerMainMenu.creerMpBruitSelectionMenu();
		ControlerMainMenu.creerMpMusiqueDuJeu();
		this.creerMpMusiqueGameOver();
		this.mpMusiqueGameOver.play();
	}
	
	@FXML
	public void chargerMenuPrincipal(ActionEvent event){
		Pane root = null;
		try {
			this.mpMusiqueGameOver.stop();
			ControlerMainMenu.mpBruitSelectionMenu.play();
			ControlerMainMenu.mpMusiqueDuJeu.setAutoPlay(true);
			root = FXMLLoader.load(getClass().getClassLoader().getResource("application/vue/menuPrincipal.fxml"));
		} catch (IOException e) {
			System.out.println("menuPrincipal.fxml est introuvable");
			e.printStackTrace();
		}
		aPane.getChildren().setAll(root);
	}
	
	public void creerMpMusiqueGameOver() {
		this.musiqueGameOver = new Media(new File("src/application/vue/ressources/sounds/call-of-duty-black-ops-2-zombies-green-run-game-over-song.mp3").toURI().toString());
		this.mpMusiqueGameOver = new MediaPlayer(this.musiqueGameOver);
		this.mpMusiqueGameOver.setVolume(0.1);
	}

}
