package application.testJUnit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import application.modele.Environnement;
import application.modele.ennemis.Majin;

public class TestSurMajin {
		
	@Test
	//test avec pv inferieur à pvMax
	public void testSoinsMajin() {
		Majin m1 = new Majin(new Environnement(960, 704));
		m1.setPv(1);
		m1.setCycle(10);
		assertTrue(m1.seSoigner());
	}
	
	@Test
	//test avec pv == à pvMax
	public void testSoinsMajin2() {
		Majin m2 = new Majin(new Environnement(960, 704));
		m2.setCycle(10);
		assertFalse(m2.seSoigner());
	}
	@Test
	//test avec pv inferieur à pvMax et cycle =! 10
	public void testSoinsMajin3() {
		Majin m3 = new Majin(new Environnement(960, 704));
		m3.setPv(1);
		assertFalse(m3.seSoigner());
	}
	
	@Test
	//test avec pv == à pvMax et cycle != 10
	public void testSoinsMajin4() {
		Majin m4 = new Majin(new Environnement(960, 704));
		assertFalse(m4.seSoigner());
	}
	
	//2 conditions à vérifier donc 2^2 test à faire, tous les tests ont été réalisés.

}
