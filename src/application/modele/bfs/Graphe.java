package application.modele.bfs;

import java.util.ArrayList;

public class Graphe {
	private ArrayList<Sommet> listeSommets;
	private int[][] matrice;
	private Bfs bfs;

	public Graphe(int[][] matrice) {
		this.listeSommets = new ArrayList<Sommet>();
		this.matrice = matrice;
		initSommets();
		this.bfs = new Bfs(this);
	}

	public void initSommets() {


		for (int ligne = 0; ligne < matrice.length; ligne++) {

			for (int colonne = 0; colonne < matrice[ligne].length; colonne++) {


				if (matrice[ligne][colonne] == 2) {

					Sommet s = new Sommet(new Coordonnes(32 * colonne, 32 * ligne));

					this.listeSommets.add(s);
				}
			}
		}
		for (Sommet s : this.listeSommets) {
			for (Sommet sommetTest : this.listeSommets) {
				if (s.estAdjacent(sommetTest)) {
					s.ajouterSommet(sommetTest);
				}

			}
		}
	}

	

	public Sommet getSommetDeDepart(Coordonnes coord) {
		Sommet depart = chercheSommet(coord);
		return depart;
	}

	

	public Bfs getBfs() {
		return this.bfs;
	}

	public Sommet chercheSommet(Coordonnes coord) {
		for (Sommet s : this.listeSommets) {
			if (s.getCoordonnes().equals(coord)) {
				return s;
			}
		}
		return null;
	}
}
