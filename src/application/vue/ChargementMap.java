package application.vue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import application.modele.TabMap1;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class ChargementMap {
	
	private TabMap1 tab;
    int [][] map;

    public ChargementMap(TabMap1 tab) {
    	this.tab = tab;
        map = tab.getTab();
    }
	
	
	public void genererMap(TilePane tPane) {
		Image i1 = null;
		Image i2 = null;
		Image i3 = null;
		Image i4 = null;
		Image i5 = null;
		
		
		try {
			 i1 = new Image(new FileInputStream("src/application/vue/ressources/tiles/sol.png"));
			 i2 = new Image(new FileInputStream("src/application/vue/ressources/tiles/route.png"));
			 i3 = new Image(new FileInputStream("src/application/vue/ressources/tiles/lave.png"));
			 i4 = new Image(new FileInputStream("src/application/vue/ressources/tiles/solTourelle.png"));
			 i5 = new Image(new FileInputStream("src/application/vue/ressources/tiles/bunker.jpg"));
		} catch (FileNotFoundException e) {
			System.out.println("L'une des ressources est introuvable");
			e.printStackTrace();
		}
		
		ImageView img = null;
		
		
		for(int ligne = 0; ligne<map.length; ligne++) {
			for(int colonne = 0; colonne < map[ligne].length; colonne++) {

				img = new ImageView();
				
				switch (map[ligne][colonne]) {
				
					case 1:
						img.setImage(i1);
						break;

					case 2: 
						if ((colonne == 14 || colonne == 15) && ligne == 21) {
							img.setImage(i5);
						}
						else {
							img.setImage(i2);
						}
						
						break;

					case 3: 
						img.setImage(i3);
						break;
					
					case 4: 
						img.setImage(i4);
						break;
				}

				tPane.getChildren().add(img);
			}
	}
}

	public int[][] getMap() {
		return map;
	}
	
	public TabMap1 getTab() {
		return tab;
	}
}
