package application.testJUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import application.modele.Environnement;
import application.modele.ennemis.Tank;
import application.modele.tourelles.Archer;
import application.modele.tourelles.Militaire;

public class testSurLesTirs {

	@Test
	//test militaire
	public void testAppliquerTir() {
		Tank t1 = new Tank(new Environnement(960, 704));
		Militaire m1 = new Militaire(0, 0, t1.getEnvironnement());
		m1.attaquer(t1);
		assertEquals(220, t1.getPv());
	}
	
	@Test
	//test archer
	public void testAppliquerTir2() {
		Tank t2 = new Tank(new Environnement(960, 704));
		Archer a1 = new Archer(0, 0, t2.getEnvironnement());
		a1.attaquer(t2);
		assertEquals(245, t2.getPv());
	}
}
