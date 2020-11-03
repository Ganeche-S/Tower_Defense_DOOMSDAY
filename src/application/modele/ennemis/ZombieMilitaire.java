package application.modele.ennemis;

import java.util.Random;
import application.modele.Environnement;

public class ZombieMilitaire extends Zombie{

	private int probaAbsorbe;
	
	public ZombieMilitaire(Environnement env) {

		super(env, 150, 1);
		this.probaAbsorbe = 20;
	}
	
	public String toString() {
		return this.getClass().getSimpleName() + super.toString() + "probaAbsorbe = " + this.probaAbsorbe; 
	}

	public boolean tirAbsorbe() {
		Random rand = new Random();
		int nbRandom = rand.nextInt(101);
		
		if(nbRandom <= this.probaAbsorbe) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	public void agit() {
		this.seDeplacer();
	}
	
	

	
}
