package application.modele.tourelles;

import java.util.ArrayList;
import application.modele.Environnement;
import application.modele.ennemis.Zombie;

//Classe non opérationnelle (enlevée de la VBox pour cette même raison)
public class GrenadierDesorientant extends Bombardiers {
	
	private static int delay = 5;
	private ArrayList<Zombie> listeTargets;
	
	
	public GrenadierDesorientant(int x, int y, Environnement env) {
		super(x, y, env, 1, 10, 600, 100);
		listeTargets = new ArrayList<Zombie>();
	}
	
	@Override
	public void attaquer(Zombie target) {
		if(!listeTargets.contains(target)) {
			if (delay > 0) {
				
				
				delay--;
			}
			else if(delay == 0) {
				target.setVitesse(1);
				this.listeTargets.add(target);
				delay = 5;
			}
					
		}
		else {
			System.out.println("IL EXISTE PAS");
			target.setVitesse(0);
		}
	}
}
