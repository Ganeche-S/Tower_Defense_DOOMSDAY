package application.modele.ennemis;

import application.modele.Environnement;

public class Majin extends Zombie{

	private int cycle;
	private double pvMax;

	public Majin(Environnement env) {
		super(env, 175, 1);
		this.cycle = 0;
		this.pvMax = this.getPv();
	}

	
	public boolean seSoigner() {
		
		if(this.getPv() < this.pvMax) {
			if(this.cycle == 10) {
				this.setPv(this.getPv()+1);
				this.cycle = 0;
				return true;
			}
			else {
				this.cycle++;
			}
		}
		return false;
	}
	
	public void agit() {
		this.seSoigner();
		this.seDeplacer();
	}
	
	public void setCycle(int cycle) {
		this.cycle = cycle;
	}
	
	

}
