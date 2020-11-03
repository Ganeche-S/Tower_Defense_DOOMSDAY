package application.modele.tourelles;

import application.modele.Environnement;
import application.modele.ennemis.Zombie;
import application.modele.ennemis.ZombieMilitaire;

public class Grenadier extends Bombardiers {

	public Grenadier(int x, int y, Environnement env) {
		super(x, y, env, 70, 412, 180, 100);
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
	
}
