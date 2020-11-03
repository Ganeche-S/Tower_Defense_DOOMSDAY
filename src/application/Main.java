package application;

	
import application.controleur.ControlerMainMenu;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			ControlerMainMenu.creerMpMusiqueDuJeu();
			ControlerMainMenu.mpMusiqueDuJeu.setAutoPlay(true);
			Pane root = FXMLLoader.load(getClass().getClassLoader().getResource("application/vue/menuPrincipal.fxml"));
			Scene scene = new Scene(root,1190,730);

			scene.getStylesheets().add(getClass().getResource("vue/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
