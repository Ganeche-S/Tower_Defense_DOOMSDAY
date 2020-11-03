package application.modele.tourelles;

import java.util.ArrayList;

import application.modele.Environnement;
import application.modele.ennemis.Zombie;

public abstract class Bombardiers extends Tourelle {

	
	private final static int porteeLimite = 108;
	private static int porteeDegatZone = 64;
	public Bombardiers(int x, int y, Environnement env, int degat, int vitesseAttack, int portee, int valeurAchat) {
		super(x, y, env, degat, vitesseAttack, portee, valeurAchat);
	}

	@Override
	public Zombie detecter(Environnement env) {
		ArrayList<Zombie> zombies = env.getListeZombies();
		for (Zombie zombie : zombies) {
			int posXZombie =  zombie.getXProperty().getValue();
			int posYZombie =  zombie.getYProperty().getValue();
			int differenceXZombieTourelle = posXZombie - this.getX();
			int differenceYZombieTourelle = posYZombie - this.getY();
			int distance = (int) Math.sqrt((differenceXZombieTourelle * differenceXZombieTourelle) + (differenceYZombieTourelle * differenceYZombieTourelle));
			System.out.println(distance);
			if(zombie.estEnVie() && distance <= this.getPortee() && distance >= porteeLimite) {
				
				return zombie;
			}
		}
		return null;
	}

	public ArrayList<Zombie> degatsZone(Environnement env) {
		//version1
		ArrayList<Zombie> zombies = env.getListeZombies();
		ArrayList<Zombie> targets = new ArrayList<Zombie>();
		Zombie z = this.detecter(env);
		if (z != null) {
			
			targets.add(z);
			int posXFirstTarget = z.getXProperty().getValue();
			int posYFirstTarget = z.getYProperty().getValue();
			for (Zombie zombie : zombies) {
				if (zombie != z) {
					int posXSecondTarget = zombie.getXProperty().getValue();
					int posYSecondTarget = zombie.getYProperty().getValue();				
					int differenceXZombies = posXSecondTarget - posXFirstTarget;
					int differenceYZombies = posYSecondTarget - posYFirstTarget;
					int distance = (int) Math.sqrt((differenceXZombies * differenceXZombies) + (differenceYZombies * differenceYZombies));
					if(zombie.estEnVie() && distance <= porteeDegatZone) {
						targets.add(zombie);
					}
				}
			}
			return targets;
		}
		return null;
	}
	
	@Override
	public void agir() {
		ArrayList<Zombie> targets = this.degatsZone(getEnv());
		if (targets != null) {
			if(this.tpsRechargement == 0) {
				for (Zombie zombie : targets) {	
					this.attaquer(zombie);
					this.tpsRechargement = this.getVitesseAttack();
				}
			}
			else {
				this.tpsRechargement -= 1;
			}	
		}
	}
	
	public abstract void attaquer(Zombie target);
}
