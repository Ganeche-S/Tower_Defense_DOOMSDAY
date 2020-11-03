package application.modele.tourelles;

import application.modele.Environnement;
import application.modele.ennemis.Zombie;

public abstract class Tourelle {
	
	private int x, y;
	private int degat;
	private int vitesseAttack;
	private int portee;
	private Environnement env;
	private int valeurAchat;
	protected int tpsRechargement;
	
	public Tourelle(int x, int y, Environnement env, int degat, int vitesseAttack, int portee, int valeurAchat) {
		this.x = x;
		this.y = y;
		this.env = env;
		this.degat = degat;
		this.vitesseAttack = vitesseAttack;
		this.portee = portee;
		this.tpsRechargement = 0;
		this.valeurAchat = valeurAchat;
	}
	
	public int getX() {
		return this.x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	
	public int getY() {
		return this.y;
	}
	
	public void setY(int y) {
		this.y = y;
	}

	public Environnement getEnv() {
		return this.env;
	}
	
	public int getDegat() {
		return this.degat;
	}
	
	public int getVitesseAttack() {
		return this.vitesseAttack;
	}

	
	public int getPortee() {
		return this.portee;
	}
	
	public int getValeurAchat() {
		return this.valeurAchat;
	}
	public abstract Zombie detecter(Environnement env);
	
	
	public abstract void agir();
	
	public abstract void attaquer(Zombie target);
	
	
	public String toString() {
		return "[ x= " + this.x + ", y= " + this.y + ", degat= " + this.degat + ", vitesse d'attaque= " + this.vitesseAttack + ", pr�cision=" + ", port�e= " + this.portee +" ]";
	}
	
	public void suprimerTourelle() {
		this.env.getListeTourelles().remove(this);
	}
}
