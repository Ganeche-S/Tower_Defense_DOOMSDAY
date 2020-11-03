package application.modele.ennemis;

import java.util.ArrayList;

import java.util.Random;


import application.modele.Environnement;
import application.modele.bfs.Coordonnes;
import application.modele.bfs.Sommet;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Zombie {
	private double pv;
	private int vitesse;
	private IntegerProperty x,y;
	private Environnement env;
	
	
	public Zombie (Environnement env, double pv, int vitesse) {
		Coordonnes coord = this.Lieuxspawn();
		this.x = new SimpleIntegerProperty(coord.getX());
		this.y = new SimpleIntegerProperty(coord.getY());

		this.pv = pv;
		this.vitesse = vitesse;
		this.env = env;
	}

	public double getPv() {
		return pv;
	}

	public void setPv(double pv) {
		this.pv = pv;
	}

	public int getX() {
		return x.getValue();
	}
	
	public IntegerProperty getXProperty() {
		return this.x;
	}

	public void setX(int x) {
		this.x.setValue(x);
	}

	public double getY() {
		return y.getValue();
	}
	
	public IntegerProperty getYProperty() {
		return this.y;
	}

	public void setY(double y) {
		this.y.setValue(y);;
	}

	public int getVitesse() {
		return vitesse;
	}
	
	public void setVitesse(int vitesse) {
		this.vitesse = vitesse;
	}
	
	


	public void seDeplacer() {
		Coordonnes coordZombie = new Coordonnes((this.x.getValue()/32)*32, (this.y.getValue()/32)*32);
		

		Sommet sommetCourant = this.env.getGraphe().chercheSommet(coordZombie);
		
		//mettre sommetDest la valeur associee et sommetCourant dans la HashMap
		
		Sommet SommetsDest = this.env.getGraphe().getBfs().getAssociationPereFils().get(sommetCourant);
		if(SommetsDest != null) {
			//erreur blesse vient peut-etre de getX();
			if(this.getX() < SommetsDest.getCoordonnes().getX()) {
				this.setX(getX() + this.getVitesse());
			}
			else if(this.getX() > SommetsDest.getCoordonnes().getX()) {
				this.setX(getX() - this.getVitesse());
			}
			if(this.getY() < SommetsDest.getCoordonnes().getY()) {
				this.setY(getY() + this.getVitesse());
			}
			else if(this.getY() > SommetsDest.getCoordonnes().getY()) {
				this.setY(getY() - this.getVitesse());
			}
		}
		else {
			// si retirer point de vie au bunker, faire ici
			if(this.env.getPvBunkerProperty().getValue() > 0) {
				this.env.getPvBunkerProperty().setValue(this.env.getPvBunkerProperty().getValue()-1);
			}
			this.pv = 0;
		}
	}
	
	public abstract void agit();
	
	public Environnement getEnvironnement() {
		return this.env;
	}
	
	public Coordonnes Lieuxspawn() {
		ArrayList<Coordonnes> listeSpawn = new ArrayList<Coordonnes>();
		listeSpawn.add(new Coordonnes(0, 96));
		listeSpawn.add(new Coordonnes(896, 96));
		listeSpawn.add(new Coordonnes(448, 32));
		
		Random rand = new Random();
		int i = rand.nextInt(3);
		
		return listeSpawn.get(i);
	}
	
	
	public boolean estEnVie() {
		return this.pv > 0;
	}
	
	public void suprimerZombie() {
		this.env.getListeZombies().remove(this);
	}
	
	
}
