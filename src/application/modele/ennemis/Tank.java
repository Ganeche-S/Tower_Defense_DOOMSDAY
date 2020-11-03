package application.modele.ennemis;

import application.modele.Environnement;

public class Tank extends Zombie{

	public Tank(Environnement env) {
		super(env, 300, 1);
	}
	public String toString() {
		return this.getClass().getSimpleName() + super.toString(); 
	}
	
	public void agit() {
		this.seDeplacer();
	}
}
