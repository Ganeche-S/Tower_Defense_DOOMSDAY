package application.modele.ennemis;

import application.modele.Environnement;

public class Sprinteur extends Zombie{

	public Sprinteur(Environnement env) {
		super(env, 75, 2);
	}
	
	public String toString() {
		return this.getClass().getSimpleName() + super.toString(); 
	}

	public void agit() {
		this.seDeplacer();
	}

}
