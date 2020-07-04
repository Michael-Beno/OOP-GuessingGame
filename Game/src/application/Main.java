package application;
/**
 * @author R00155443
 * Michael Beno
 * 23/02/2018
 */

import application.GUI.GuessingGameGUI;
import application.GUI.LotoCureGUI;
import application.GUI.PrizesGUI;
import application.GUI.WinnersGUI;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.image.Image;


public class Main extends Application {
	WinnersGUI win;
	
	@Override
	public void start(Stage primaryStage) {
		
		TabPane layout = new TabPane();
		layout.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		
		Tab tab4 = new Tab("Winners");
		layout.getTabs().add(tab4);
		win = new WinnersGUI();
		tab4.setOnSelectionChanged(new EventHandler<Event>() {
			
			@Override
			public void handle(Event event) {
				win = new WinnersGUI();
				tab4.setContent(win.content(tab4));
			}
		});
		
		Tab tab1 = new Tab("Guessing Game");
		layout.getTabs().add(tab1);
		GuessingGameGUI gg = new GuessingGameGUI();
		
		Tab tab2 = new Tab("Loto Cure");
		layout.getTabs().add(tab2);
		LotoCureGUI lc = new LotoCureGUI();
		
		Tab tab3 = new Tab("Prizes");
		layout.getTabs().add(tab3);
		tab3.setDisable(true);
		PrizesGUI prizes = new PrizesGUI();
		
		tab3.setOnSelectionChanged(new EventHandler<Event>() {
			
			@Override
			public void handle(Event event) {
				prizes.setStars(""+(gg.getStarsWon()+lc.getStarsWon())); //setting numbers of stars
				lc.reset();
				gg.reset();
				gg.setStarsWon(0);lc.setStarsWon(0); //reseting stars back to 0
			}
		});

		try {
			Scene scene = new Scene(layout, 600, 480);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			tab1.setContent(gg.content(tab1, tab2, tab3, tab4));
			tab2.setContent(lc.content(tab1, tab2, tab3, tab4));
			tab3.setContent(prizes.content(tab1, tab2, tab3, tab4));
			tab4.setContent(win.content(tab4));
			primaryStage.getIcons().add(new Image("file:drawable/icon.png"));
			primaryStage.setTitle("Game app");
		
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	 
	public static void main(String[] args) {
		launch(args);
		
	}
}
