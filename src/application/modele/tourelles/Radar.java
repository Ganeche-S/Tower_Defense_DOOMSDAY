package application.modele.tourelles;

import java.util.ArrayList;

import application.modele.Environnement;
import application.modele.ennemis.Blesses;
import application.modele.ennemis.Zombie;


//Classe non opérationnelle (enlevée de la VBox pour cette même raison)
public class Radar extends Tourelle {
	
	public Radar(int x, int y, Environnement env) {
		super(x, y, env, 0, 10, 100, 80);
	}
	
	@Override
	public Zombie detecter(Environnement env) {
		ArrayList<Zombie> zombies = env.getListeZombies();
		for (Zombie zombie : zombies) {
			if(zombie instanceof Blesses) {
				int posXZombie =  zombie.getXProperty().getValue();
				int posYZombie =  zombie.getYProperty().getValue();
				if(zombie.estEnVie() && posXZombie - this.getX() >= -this.getPortee() && posXZombie - this.getX() <= this.getPortee() && posYZombie - this.getY() >= -this.getPortee() && posYZombie - this.getY() <= this.getPortee()) {
					((Blesses) zombie).setEstDetecter(true);
					return zombie;
				}
			}
		}
		return null;
	}

	@Override
	public void agir() {
		this.detecter(this.getEnv());
	}

	@Override
	public void attaquer(Zombie target) {
	}

}
