package application.modele.tourelles;

import java.util.ArrayList;

import application.modele.Environnement;
import application.modele.ennemis.Blesses;
import application.modele.ennemis.Zombie;
import application.modele.ennemis.ZombieMilitaire;

public abstract class TireursDeBase extends Tourelle {

	public TireursDeBase(int x, int y, Environnement env, int degat, int vitesseAttack, int portee, int valeurAchat) {
		super(x, y, env, degat, vitesseAttack, portee, valeurAchat);
	}
	
	@Override
	public Zombie detecter(Environnement env) {
		ArrayList<Zombie> zombies = env.getListeZombies();
		for (Zombie zombie : zombies) {
			if(zombie instanceof Blesses) {
			}
			else {
				int differenceXZombieTourelle = zombie.getXProperty().getValue() - this.getX();
				int differenceYZombieTourelle = zombie.getYProperty().getValue() - this.getY();
				int distance = (int) Math.sqrt((differenceXZombieTourelle * differenceXZombieTourelle) + (differenceYZombieTourelle * differenceYZombieTourelle));
				
				if((zombie.estEnVie() && distance <= this.getPortee())) {	
					return zombie;
				}
			}
		}
		return null;
	}

	@Override
	public void agir() {
		Zombie target = this.detecter(this.getEnv());
		if (target != null) {
			if(this.tpsRechargement == 0) {
				this.attaquer(target);
				this.tpsRechargement = this.getVitesseAttack();
			}
			else {
				this.tpsRechargement -= 1;
			}
		}
	}

	@Override
	public void attaquer(Zombie target) {
		if(target instanceof ZombieMilitaire) {
			boolean tirAbsorbe = ((ZombieMilitaire) target).tirAbsorbe();
			if(!tirAbsorbe) {
				target.setPv(target.getPv() - this.getDegat());
			}
		}
		else {
			target.setPv(target.getPv() - this.getDegat());
		}
	}
	
	public String toString() {
		return "Type Tourelle : " + this.getClass().getName() + super.toString();
	}
}
