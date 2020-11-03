package application.controleur;

import java.io.File;
import java.io.FileNotFoundException;



import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import application.modele.Environnement;
import application.modele.TabMap1;
import application.modele.bfs.Coordonnes;
import application.modele.ennemis.Zombie;
import application.modele.ennemis.ZombieDeTroie;
import application.modele.tourelles.Archer;
import application.modele.tourelles.Bourrin;
import application.modele.tourelles.Militaire;
import application.modele.tourelles.PlacementTourelle;
import application.modele.tourelles.Sniper;
import application.modele.tourelles.SniperPenetrant;
import application.modele.tourelles.Grenadier;
import application.modele.tourelles.Tourelle;
import application.vue.ChargementMap;
import application.vue.SpriteTourelle;
import application.vue.SpriteZombie;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class ControleurMap implements Initializable {

	@FXML
	private TilePane Tpane;
	@FXML
	private Pane paneCentrale;

    @FXML
    private Label labelMoney;

	@FXML
	private ImageView imageMilitaire;
	
	@FXML
	private Button boutonAchatOn;
	
	@FXML
	private Button boutonAchatOff;
	
	@FXML
	private Button boutonVenteOn;
	
	@FXML
	private Button boutonVenteOff;
	
	@FXML
	private ImageView imageTireurPrecision;
	
	@FXML
	private HBox Archer;
	
	@FXML
	private HBox Militaire;
	
	@FXML
	private HBox Sniper;
	
	@FXML
	private HBox SniperPenetrant;
	
	@FXML
	private HBox Grenadier;
	
	@FXML
	private HBox Bourrin;
	
	@FXML
	private ImageView imageBourrin;

	@FXML
	private ImageView imageGrenadier;
	
	@FXML
	private ImageView target;
	
	@FXML
    private Label PVBunker;
	
	@FXML 
	private BorderPane borderPane;
	
	private Media musiqueVue1;
	private MediaPlayer mpMusiqueVue1;
	private Environnement env;
	private Timeline gameloop;
	private ChargementMap mapAGenener;
	private int cycle;
	private int cycleSpawnZombie;
	private boolean modeAchat;
	private boolean modeVente;
	private String tourelle;
	private HashMap<Zombie, SpriteZombie> linkSpriteZombie;
	private Map<Tourelle, SpriteTourelle> linkSpriteTourelle;
	private ArrayList<PlacementTourelle> listePlacementsTourelles;
	private Map<Tourelle, PlacementTourelle> linkPlacementTourelles;
	private ArrayList<HBox> listeHBox;
	private MediaPlayer mpDeathZombie;
	private Media deathZombie;
	
	TabMap1 map1 = new TabMap1();
	int[][] matriceMap1 = map1.getTab();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ControlerMainMenu.mpMusiqueDuJeu.setAutoPlay(false);
		ControlerMainMenu.mpMusiqueDuJeu.stop();
		this.creerMediaPlayerVue1();
		this.mpMusiqueVue1.setAutoPlay(true);
		
		linkSpriteZombie = new HashMap<Zombie, SpriteZombie>();
		linkSpriteTourelle = new HashMap<Tourelle, SpriteTourelle>();
		listePlacementsTourelles = new ArrayList<PlacementTourelle>();
		linkPlacementTourelles = new HashMap<Tourelle, PlacementTourelle>();
		listeHBox = new ArrayList<HBox>();
		listeHBox.add(Archer);
		listeHBox.add(Militaire);
		listeHBox.add(Sniper);
		listeHBox.add(SniperPenetrant);
		listeHBox.add(Grenadier);
		listeHBox.add(Bourrin);
		modeAchat = false;
		modeVente = false;
		tourelle = "";
		this.cycle = 400;
		this.cycleSpawnZombie = 0;
		mapAGenener = new ChargementMap(map1);
		mapAGenener.genererMap(Tpane);
		// env.initSommets();
		this.env = new Environnement(960, 704);
		this.disableModeAchat();
		this.disableModeVente();
		
		for(int ligne = 0; ligne<matriceMap1.length; ligne++) {
			for(int colonne = 0; colonne < matriceMap1[ligne].length; colonne++) {
				if (matriceMap1[ligne][colonne] == 4) {
					Coordonnes c = new Coordonnes(colonne, ligne);
					PlacementTourelle pt = new PlacementTourelle(c);
					listePlacementsTourelles.add(pt);
				}
			}
		}
		paneCentrale.setStyle("-fx-background-image: none");
		paneCentrale.setStyle("-fx-background-color: #202020");
		
		
		this.labelMoney.textProperty().bind(this.env.getMoneyProperty().asString());
		this.PVBunker.textProperty().bind(this.env.getPvBunkerProperty().asString());
		this.env.getPvBunkerProperty().addListener(e -> {
			if (this.env.getPvBunkerProperty().getValue() == 4) {
				PVBunker.setStyle("-fx-text-fill: #1f7f1f");
			}
			else if(this.env.getPvBunkerProperty().getValue() == 3) {
				PVBunker.setStyle("-fx-text-fill: orange");
			}
			else if(this.env.getPvBunkerProperty().getValue() == 2) {
				PVBunker.setStyle("-fx-text-fill: red");
			}
			else if(this.env.getPvBunkerProperty().getValue() == 1) {
				PVBunker.setStyle("-fx-text-fill: #7f0000");
			}
			else if(this.env.getPvBunkerProperty().getValue() == 0) {
				gameloop.stop();
				try {
					this.mpMusiqueVue1.setAutoPlay(false);
					this.mpMusiqueVue1.stop();
					Pane root = FXMLLoader.load(getClass().getClassLoader().getResource("application/vue/gameOver.fxml"));
			    	borderPane.getChildren().setAll(root);
				} catch (IOException e1) {
					System.out.println("gameOver.fxml est introuvable");
					e1.printStackTrace();
				}
			}
		});
		animation();
		gameloop.play();

	}

	private void animation() {
		this.gameloop = new Timeline();
		this.gameloop.setCycleCount(Timeline.INDEFINITE);

		KeyFrame keyframe = new KeyFrame(Duration.seconds(0.017), (ev -> {

			if(this.cycle == 0) {
				if(this.cycleSpawnZombie == 0) {
					try {
						creerZombieAleatoire();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					this.cycleSpawnZombie = 250;
				}
				else {
					this.cycleSpawnZombie--;
				}
			}
			else {
				this.cycle--;
			}
			env.unTour();
			try {
				tuerZombiesMorts();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}));
		gameloop.getKeyFrames().add(keyframe);
	}
	
	void enableModeAchat() {
		modeAchat = true;
		boutonAchatOn.setStyle("-fx-background-color: Green");
		boutonAchatOff.setStyle("-fx-background-color: Red");
	}
	
	void disableModeAchat() {
		modeAchat = false;
		boutonAchatOff.setStyle("-fx-background-color: Green");
		boutonAchatOn.setStyle("-fx-background-color: Red");
		for (HBox hBox : this.listeHBox) {
			hBox.setStyle("-fx-border-color: #f0a110");
		}
		tourelle = "";
	}
	
	
	public void onMouseClickedHbox() {
		for (HBox hBox : this.listeHBox) {
			if (hBox.getId().equals(tourelle)) {
				hBox.setStyle("-fx-border-color: red");
			}
			else {
				hBox.setStyle("-fx-border-color: #f0a110");
			}
		}
		return;
	}
	
	@FXML
	void onMouseClickedOnAchat(MouseEvent event) {
		this.enableModeAchat();
		if (modeVente) {
			this.disableModeVente();
		}
	}

	@FXML
	void onMouseClickedOffAchat(MouseEvent event) {
		this.disableModeAchat();
		
	}
	
	void enableModeVente() {
		modeVente = true;
		boutonVenteOn.setStyle("-fx-background-color: Green");
		boutonVenteOff.setStyle("-fx-background-color: Red");
	}
	
	void disableModeVente() {
		modeVente = false;
		boutonVenteOff.setStyle("-fx-background-color: Green");
		boutonVenteOn.setStyle("-fx-background-color: Red");
	}
	
	@FXML
	void onMouseClickedOnSell(MouseEvent event) {
		this.enableModeVente();
		if (modeAchat) {
			this.disableModeAchat();
		}
	}

	@FXML
	void onMouseClickedOffSell(MouseEvent event) {
		this.disableModeVente();

	}
	
	@FXML
	void onMouseClickedArcher(MouseEvent event) {
		if (modeAchat) {
			tourelle = "Archer";
			this.onMouseClickedHbox();

		}
	}
	
	@FXML
	void onMouseClickedMilitaire(MouseEvent event) {
		if (modeAchat) {
			tourelle = "Militaire";
			this.onMouseClickedHbox();
			
		}
	}
	
	@FXML
	void onMouseClickedSniper(MouseEvent event) {
		if (modeAchat) {
			tourelle = "Sniper";
			this.onMouseClickedHbox();
		}
	}
	
	@FXML
	void onMouseClickedSniperPenetrant(MouseEvent event) {
		if (modeAchat) {
			tourelle = "SniperPenetrant";
			this.onMouseClickedHbox();
		}
	}
	
	@FXML
	void onMouseClickedBourrin(MouseEvent event) {
		if (modeAchat) {
			tourelle = "Bourrin";
			this.onMouseClickedHbox();
		}
	}

	@FXML
	void onMouseClickedGrenadier(MouseEvent event) {
		if (modeAchat) {
			tourelle = "Grenadier";
			this.onMouseClickedHbox();
		}
	}

	@FXML
	void onMouseClickedPane(MouseEvent event) {
		int posX = (int) event.getSceneX()/32;
		int posY = (int) event.getSceneY()/32;
		
		if (modeAchat && !modeVente) {
			for (PlacementTourelle pt : listePlacementsTourelles) {
				if (pt.getIsAvailable() && !tourelle.equals("") && posX == pt.getTileX() && posY == pt.getTileY()) {
					this.creerTourelle(tourelle, pt);
					return;
				}
			}
		}
		if(modeVente && !modeAchat) {
			for (Map.Entry<Tourelle, PlacementTourelle> entree : this.linkPlacementTourelles.entrySet()) {
				Tourelle t = entree.getKey();
				PlacementTourelle pt = entree.getValue();
				if (!pt.getIsAvailable() && pt.getTileX() == posX && pt.getTileY() == posY && t.getX()/32 == posX && t.getY()/32 == posY) {
					this.detruireTourelle(t, pt);
					this.linkPlacementTourelles.remove(t, pt);
					return;
				}
			}
		}
		if (modeVente && modeAchat) {
			this.disableModeAchat();
			this.disableModeVente();
			return;
		}
	}
	
	public void creerZombieAleatoire() throws FileNotFoundException {
		// 7 zombies
		
		SpriteZombie sp;
		
		Zombie z = null;
		
		z = this.env.creerTypeZombieAleatoire();
		
		sp = new SpriteZombie(z);
		sp.ajouterSpriteZombie(paneCentrale);
		linkSpriteZombie.put(z, sp);
	}
	
	public void tuerZombie(Zombie target) throws FileNotFoundException {
		if (!target.estEnVie()) {
			creerMediaPlayerZombieMort();
			this.mpDeathZombie.play();
			// if target instanceof Zombie de troies ----> creerZombieAléatoire;
			if(target instanceof ZombieDeTroie) {
				for(int i = 0; i < 3; i++) {
					creerZombieAleatoire();
				}
			}
			target.suprimerZombie();
			SpriteZombie sz = linkSpriteZombie.get(target);
			sz.suprimerSpriteZombie(paneCentrale);
			this.env.updateMoneyUp(target);
		}
	}
	
	public void tuerZombiesMorts() throws FileNotFoundException{
		ArrayList<Zombie> listeZombies = this.env.getListeZombies();
		for (int i = 0; i < listeZombies.size(); i++) {
			if (!listeZombies.get(i).estEnVie()) {
				this.tuerZombie(listeZombies.get(i));
			}
		}
		return;
	}
	
	public void creerTourelle(String type, PlacementTourelle pt) {
		//test
		Tourelle tour = null;
		int posX = (pt.getTileX()*32)+4;
		int posY = (pt.getTileY()*32)+2;
		switch (type) {
			case "Militaire":
				tour = new Militaire(posX, posY, env);
				break;
			
			case "Archer":
				tour = new Archer(posX, posY, env);
				break;
			
			case "Sniper":
				tour = new Sniper(posX, posY, env);
				break;
			
			case "SniperPenetrant":
				tour = new SniperPenetrant(posX, posY, env);
				break;
			case "Grenadier":
				tour = new Grenadier(posX, posY, env);
				break;
				
			case "Bourrin":
				tour = new Bourrin(posX, posY, env);
				break;

		}
		if (this.env.checkMoneyDown(tour)) {
			tour = null;
		}
		if (tour != null) {	
			SpriteTourelle spt = null;
			try {
				spt = new SpriteTourelle(tour, env, posX, posY);
				spt.creerSpriteTourelle(paneCentrale);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			this.linkSpriteTourelle.put(tour, spt);
			this.linkPlacementTourelles.put(tour, pt);
			this.env.moneyDesc(tour);
			pt.setIsAvailable(false);	
		}		
	}
	
	public void detruireTourelle(Tourelle target, PlacementTourelle pt) {
		target.suprimerTourelle();
		this.linkSpriteTourelle.get(target).supprimerSpriteTourelle(paneCentrale);
		this.linkPlacementTourelles.remove(target, pt);
		this.env.moneyAsc(target);
		pt.setIsAvailable(true);
		return;
	}
	
	public void creerMediaPlayerZombieMort() {
		this.deathZombie = new Media(new File("src/application/vue/ressources/sounds/death.mp3").toURI().toString());
		this.mpDeathZombie = new MediaPlayer(this.deathZombie);
		this.mpDeathZombie.setVolume(0.2);
	}
	
	public void creerMediaPlayerVue1() {
		this.musiqueVue1 = new Media(new File("src/application/vue/ressources/sounds/most-epic-battle-music-ever-deadwood-by-really-slow-motion.mp3").toURI().toString());
		this.mpMusiqueVue1 = new MediaPlayer(this.musiqueVue1);
		this.mpMusiqueVue1.setVolume(0.1);
	}
}
