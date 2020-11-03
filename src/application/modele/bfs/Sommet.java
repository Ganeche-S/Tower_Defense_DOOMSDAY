package application.modele.bfs;

import java.util.ArrayList;

public class Sommet {
	private Coordonnes coordonnes;
	private boolean explore;
	private ArrayList<Sommet> listeSommetsAccessible;
	
	public Sommet(Coordonnes coord) {
		this.coordonnes = coord;
		this.explore = false;
		this.listeSommetsAccessible = new ArrayList<Sommet>();
	}
	
	public void ajouterListeSommetAccessible(Sommet sommet) {
		this.listeSommetsAccessible.add(sommet);
	}

	public Coordonnes getCoordonnes() {
		return coordonnes;
	}

	public void setCoordonnes(Coordonnes coordonnes) {
		this.coordonnes = coordonnes;
	}

	public boolean isExplore() {
		return this.explore;
	}

	public void setExplore(boolean explore) {
		this.explore = explore;
	}

	public ArrayList<Sommet> getListeSommetsAccessible() {
		return listeSommetsAccessible;
	}
	
	public void ajouterSommet(Sommet s) {
		if(!this.listeSommetsAccessible.contains(s)) {
			this.listeSommetsAccessible.add(s);
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coordonnes == null) ? 0 : coordonnes.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sommet other = (Sommet) obj;
		if (coordonnes == null) {
			if (other.coordonnes != null)
				return false;
		} else if (!coordonnes.equals(other.coordonnes))
			return false;
		return true;
	}

	public boolean estAdjacent(Sommet s) {
		if(this.coordonnes.estAdjacent(s.getCoordonnes())) {
			return true;
		}
		return false;
	}
	
	
}
