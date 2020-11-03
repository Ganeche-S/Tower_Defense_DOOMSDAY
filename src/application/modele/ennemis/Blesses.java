package application.modele.ennemis;

import application.modele.Environnement;

public class Blesses extends Zombie{
	
	boolean estDetecter;

	public Blesses(Environnement env) {
		super(env, 150, 1);
		this.estDetecter = false;
	}

	
	public void agit() {
		this.seDeplacer();
	}


	public boolean isEstDetecter() {
		return estDetecter;
	}


	public void setEstDetecter(boolean estDetecter) {
		this.estDetecter = estDetecter;
	}

}
