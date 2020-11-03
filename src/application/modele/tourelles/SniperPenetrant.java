package application.modele.tourelles;

import java.util.ArrayList;

import application.modele.Environnement;
import application.modele.ennemis.Zombie;
import application.modele.ennemis.ZombieMilitaire;

public class SniperPenetrant extends Tourelle {

	private static int tolerance = 128;
	public SniperPenetrant(int x, int y, Environnement env) {
		super(x, y, env, 249, 412, 448, 300);
	}

	@Override
	public Zombie detecter(Environnement env) {
		ArrayList<Zombie> zombies = env.getListeZombies();
		for (Zombie zombie : zombies) {
			int differenceXZombieTourelle = zombie.getXProperty().getValue() - this.getX();
			int differenceYZombieTourelle = zombie.getYProperty().getValue() - this.getY();
			int distance = (int) Math.sqrt((differenceXZombieTourelle * differenceXZombieTourelle) + (differenceYZombieTourelle * differenceYZombieTourelle));
				if(zombie.estEnVie() && distance <= this.getPortee()) {	
					return zombie;
				}
		}
		return null;
	}
	
	public ArrayList<Zombie> doubleShoot(Environnement env) {
		ArrayList<Zombie> zombies = env.getListeZombies();
		ArrayList<Zombie> targets = new ArrayList<Zombie>();
		Zombie z = this.detecter(env);
		if (z != null) {
			targets.add(z);
			int posXFirstTarget = z.getXProperty().getValue();
			int posYFirstTarget = z.getYProperty().getValue();
			for (Zombie zombie : zombies) {
				if (zombie != z) {
					System.out.println("Zombie 1:" + z);
					System.out.println("Zombie 2:" + zombie);
					int posXSecondTarget = zombie.getXProperty().getValue();
					int posYSecondTarget = zombie.getYProperty().getValue();				
					if(zombie.estEnVie() && posXSecondTarget - posXFirstTarget >= -tolerance && posXSecondTarget - posXFirstTarget <= tolerance && posYSecondTarget - posYFirstTarget >= -tolerance && posYSecondTarget - posYFirstTarget <= tolerance) {	
						targets.add(zombie);
						return targets;
					}
				}
			}
			return targets;
		}
		return null;
	}

	@Override
	public void agir() {
		ArrayList<Zombie> targets = this.doubleShoot(getEnv());
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
