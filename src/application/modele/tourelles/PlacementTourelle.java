package application.modele.tourelles;

import application.modele.bfs.Coordonnes;

public class PlacementTourelle {

	private Coordonnes c;
	private boolean isAvailable;
	
	public PlacementTourelle(Coordonnes c) {
		this.c = c;
		this.isAvailable = true;
	}
	
	public int getTileX() {
		return this.c.getX();
	}
	
	public int getTileY() {
		return this.c.getY();
	}
	
	public boolean getIsAvailable() {
		return this.isAvailable;
	}
	
	public void setIsAvailable(boolean etat) {
		this.isAvailable = etat;
	}
}
