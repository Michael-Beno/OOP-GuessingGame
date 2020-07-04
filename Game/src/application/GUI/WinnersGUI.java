package application.GUI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import application.data.Winners;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;

public class WinnersGUI {

	GridPane grid;
	Winners winners;
	public Node content(Tab tab4) {
		winners = new Winners();
		// winners.saveList(winners.getArray(5));
		ArrayList<Winner> aWins = winners.loadList(); // loading Array from the file
		//winners.getArray(5);
		
		grid = new GridPane();
		grid.setStyle("-fx-background-color:#f9f0a7;");
		listArray(aWins);
		return grid;
	}

	int isort = 0;
	Boolean bName = false;
	Boolean bPrize = false;

	private void listArray(ArrayList<Winner> aWins) {
		grid.getChildren().clear();
		sortBy(aWins, isort);
		grid.setHgap(2);
		// grid.setVgap(12);
		Label header = new Label("Winners");
		grid.getChildren().add(header);
		header.setStyle("-fx-font-size: 20pt;");

		Button btnName = new Button("Name     ");
		if (isort < 2) { if (bName) btnName.setText("Name    ▼");
			else btnName.setText("Name    ▲");
		}

		btnName.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				bName = !bName;
				if (bName) isort = 1;
				else isort = 0;
				listArray(aWins);
			}
		});

		Button btnPrize = new Button("Prize    ");
		if (isort > 1) { if (bPrize) btnPrize.setText("Prize   ▲");
			else btnPrize.setText("Prize   ▼");
		}
		
		btnPrize.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				bPrize = !bPrize;
				if (bPrize) isort = 2;
				else isort = 3;
				listArray(aWins);
			}
		});
		
		btnName.setMinWidth(150);
		btnPrize.setMinWidth(200);

		grid.add(btnName, 1, 1);
		grid.add(btnPrize, 2, 1);
		grid.add(new Label("REM"), 3, 1);

		for (int i = 0; i < aWins.size(); i++) {

			Button del = new Button("X");
			Label name = new Label(aWins.get(i).getName());
			name.setStyle("-fx-font-size: 12pt;");
			Label prize = new Label(aWins.get(i).getPrize());
			prize.setStyle("-fx-font-size: 12pt;");
			del.setId("" + i); // setting buttons ID
			del.setOnAction(new EventHandler<ActionEvent>() { // setting buttons handlers

				@Override
				public void handle(ActionEvent event) {
					int id = Integer.parseInt(del.getId()); // getting ID
					//System.out.println(id + " clicked: " + aWins.get(id).getName() + " " + aWins.get(id).getPrize());
					aWins.remove(id);
					winners.saveList(aWins);
					listArray(aWins);
				}
			});
			grid.add(name, 1, 2 + i);
			grid.add(prize, 2, 2 + i);
			grid.add(del, 3, 2 + i);
		}
	}

	private void sortBy(ArrayList<Winner> aWins, int i) {
		Collections.sort(aWins, new Comparator<Winner>() {
			@Override
			public int compare(Winner o1, Winner o2) {
				switch (i) {
				case 0: return o1.getName().compareTo(o2.getName());
				case 1: return o2.getName().compareTo(o1.getName());
				case 2: return o1.getPrize().compareTo(o2.getPrize());
				case 3: return o2.getPrize().compareTo(o1.getPrize());
				default: return o1.getName().compareTo(o2.getName());
				}
			}
		});
	}

}
