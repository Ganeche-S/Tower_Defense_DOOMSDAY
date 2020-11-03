package application.vue;

import java.io.FileInputStream;


import java.io.FileNotFoundException;
import application.modele.Environnement;
import application.modele.tourelles.Archer;
import application.modele.tourelles.Grenadier;
import application.modele.tourelles.Militaire;
import application.modele.tourelles.Sniper;
import application.modele.tourelles.SniperPenetrant;
import application.modele.tourelles.Tourelle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
	
public class SpriteTourelle {
	private Tourelle tourelle;
	private Environnement env;
	private Image image;
	private ImageView sprite;
	private int posX, posY;
		
	public SpriteTourelle(Tourelle tourelle, Environnement env, int posX, int posY) throws FileNotFoundException {
		this.tourelle = tourelle;
		this.env = env;
		this.env.getListeTourelles().add(tourelle);
		this.posX = posX;
		this.posY = posY;
		if(this.tourelle instanceof Militaire) {
			this.image = new Image(new FileInputStream("src/application/vue/ressources/tourelles/Militaire.png"));
		}
		else if(this.tourelle instanceof Archer) {
			this.image = new Image(new FileInputStream("src/application/vue/ressources/tourelles/Archer.png"));
		}
		else if(this.tourelle instanceof Sniper) {
			this.image = new Image(new FileInputStream("src/application/vue/ressources/tourelles/TireurDePrecision.png"));
		}
		else if(this.tourelle instanceof SniperPenetrant) {
			this.image = new Image(new FileInputStream("src/application/vue/ressources/tourelles/TireurDePrecisionPenetrant.png"));
		}
		else if(this.tourelle instanceof Grenadier) {
			this.image = new Image(new FileInputStream("src/application/vue/ressources/tourelles/Grenadier.png"));
		}
		else {
			this.image = new Image(new FileInputStream("src/application/vue/ressources/tourelles/Bourrin.png"));
		}

		this.sprite = new ImageView(this.image);
		this.sprite.setX(this.posX);
		this.sprite.setY(this.posY);
	}
		
	public void creerSpriteTourelle(Pane pane) {
	       pane.getChildren().add(this.sprite);
	}
	
	public void supprimerSpriteTourelle(Pane pane) {
	       pane.getChildren().remove(this.sprite);
	}
			
}