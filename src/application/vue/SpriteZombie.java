package application.vue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import application.modele.Environnement;
import application.modele.ennemis.Blesses;
import application.modele.ennemis.Kamikaze;
import application.modele.ennemis.Majin;
import application.modele.ennemis.Sprinteur;
import application.modele.ennemis.Tank;
import application.modele.ennemis.Zombie;
import application.modele.ennemis.ZombieDeTroie;
import application.modele.ennemis.ZombieMilitaire;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class SpriteZombie {
	private Zombie zombie;
	private Environnement env;
	private Image image;
	private ImageView sprite;
	
	public SpriteZombie(Zombie zombie) throws FileNotFoundException {
		this.zombie = zombie;
		this.env = zombie.getEnvironnement();
		this.env.getListeZombies().add(zombie);
		
		if(this.zombie instanceof Sprinteur) {
			this.image = new Image(new FileInputStream("src/application/vue/ressources/zombies/Sprinteur.png"));
		}
		
		else if(this.zombie instanceof Blesses) {
			this.image = new Image(new FileInputStream("src/application/vue/ressources/zombies/Blesse.png"));
		}
		
		else if(this.zombie instanceof Kamikaze) {
			this.image = new Image(new FileInputStream("src/application/vue/ressources/zombies/Kamikaze.png"));
		}
		
		else if(this.zombie instanceof Majin) {
			this.image = new Image(new FileInputStream("src/application/vue/ressources/zombies/Majin.png"));
		}
		
		else if(this.zombie instanceof Tank) {
			this.image = new Image(new FileInputStream("src/application/vue/ressources/zombies/Tank.png"));
		}
		
		else if(this.zombie instanceof ZombieDeTroie) {
			this.image = new Image(new FileInputStream("src/application/vue/ressources/zombies/ZombieDeTrois.png"));
		}
		
		else if(this.zombie instanceof ZombieMilitaire) {
			this.image = new Image(new FileInputStream("src/application/vue/ressources/zombies/ZombieMilitaire.png"));
		}
		this.sprite = new ImageView(this.image);

	}
	
	public void ajouterSpriteZombie(Pane pane) {
		this.sprite.translateXProperty().bind(zombie.getXProperty());
        this.sprite.translateYProperty().bind(zombie.getYProperty());
        pane.getChildren().add(this.sprite);
	}
	
	public void suprimerSpriteZombie(Pane pane) {
		pane.getChildren().remove(this.sprite);
	}
	
}
