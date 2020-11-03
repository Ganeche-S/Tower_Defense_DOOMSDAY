package application.modele;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

import application.modele.bfs.Graphe;
import application.modele.ennemis.Blesses;
import application.modele.ennemis.Kamikaze;
import application.modele.ennemis.Majin;
import application.modele.ennemis.Sprinteur;
import application.modele.ennemis.Tank;
import application.modele.ennemis.Zombie;
import application.modele.ennemis.ZombieDeTroie;
import application.modele.ennemis.ZombieMilitaire;
import application.modele.tourelles.Tourelle;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Environnement {
	
	private int width,height;
	
	private boolean mancheEnCours;
	
	private ArrayList<Zombie> listeZombie;
	private ArrayList<Tourelle> listeTourelle;
	private Graphe graphe;
	private TabMap1 tab;
	private IntegerProperty money;
	private IntegerProperty pvBunker;
	
	public Environnement(int width, int height) {
		this.width = width;
		this.height = height;
		this.tab = new TabMap1();
		this.mancheEnCours = false;
		listeZombie = new ArrayList<Zombie>();
		listeTourelle = new ArrayList<Tourelle>();
		this.money = new SimpleIntegerProperty(200);
		this.pvBunker = new SimpleIntegerProperty(5);
		this.graphe = new Graphe(tab.getTab());
				
	}
	
	public void unTour() {
		this.mancheEnCours = true;
		for(Zombie currentZombie : this.listeZombie) {
			
			currentZombie.agit();
						
		}
		
		for(Tourelle currentTourelle : this.listeTourelle) {
			currentTourelle.agir();
		}
		this.mancheEnCours = false;
	}
	

	public boolean getMancheEnCours() {
		return this.mancheEnCours;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public ArrayList<Zombie> getListeZombies(){
		return this.listeZombie;
	}
	
	public void deleteZombie(Zombie target) {
		this.listeZombie.remove(target);
	}
	
	

	public ArrayList<Tourelle> getListeTourelles(){
		return this.listeTourelle;
	}
	
	

	
	public Graphe getGraphe() {
		return this.graphe;
	}
	public IntegerProperty getMoneyProperty() {
        return money;
    }
    
    public int getMoney() {
        return this.money.getValue();
    }
    
    public void setMoney(int money) {
        this.money.setValue(money);
    }
    
    public void updateMoneyUp(Zombie zombie) {
        if(zombie instanceof Sprinteur) {
             this.setMoney((this.getMoney() + 10));
        }
        else if(zombie instanceof ZombieMilitaire) {
        	this.setMoney((this.getMoney() + 15));
        }
        else if(zombie instanceof Blesses) {
        	this.setMoney((this.getMoney() + 20));
        }
        else if(zombie instanceof Kamikaze) {
        	this.setMoney((this.getMoney() + 20));
        }
        else if(zombie instanceof Tank) {
        	this.setMoney((this.getMoney() + 25));
        }
        else if(zombie instanceof ZombieDeTroie) {
        	this.setMoney((this.getMoney() + 30));
        }
        else {
        	this.setMoney((this.getMoney() + 30));  
        }
    }
    
    public boolean checkMoneyDown(Tourelle tourelle) {
        if(this.getMoney() - tourelle.getValeurAchat() < 0) {
            return true;
        }
        return false;
    }
    
    public void moneyAsc(Tourelle tourelle) {
    	this.setMoney((this.getMoney() + (tourelle.getValeurAchat()/2)));
    }
    
    public void moneyDesc(Tourelle tourelle) {
    	this.setMoney((this.getMoney() - tourelle.getValeurAchat()));
    }
    
    public IntegerProperty getPvBunkerProperty() {
    	return this.pvBunker;
    }
    
	public Zombie creerTypeZombieAleatoire() throws FileNotFoundException {
		Random rand = new Random();
		// mettre a  l interieur de rand.nextInt() le nombre de type de zombie
		int valRand = rand.nextInt(13);
		
		Zombie z = null;
		
		switch(valRand) {
		case 0:
			z = new Sprinteur(this);
		break;
		
		case 1:
			z = new Blesses(this);
		break;
		
		case 2:
			z = new Kamikaze(this);
		break;
		
		case 3:
			z = new Majin(this);
		break;
		
		case 4:
			z = new Tank(this);
		break;
		
		case 5:
			z = new ZombieDeTroie(this);
		break;
		
		case 6:
			z = new ZombieMilitaire(this);
		break;
		
		case 7:
			z = new Sprinteur(this);
		break;
		
		case 8:
			z = new Kamikaze(this);
		break;
		
		case 9:
			z = new Majin(this);
		break;
		
		case 10:
			z = new Tank(this);
		break;
		
		case 11:
			z = new ZombieDeTroie(this);
		break;
		
		case 12:
			z = new ZombieMilitaire(this);
		break;
		}
		return z;
	}
}
