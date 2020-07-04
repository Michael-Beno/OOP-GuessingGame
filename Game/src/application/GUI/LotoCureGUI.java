package application.GUI;

import application.data.LotoData;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class LotoCureGUI {

	private final int GENERATED_NUMBERS = 6; //How many numbers to be generated
	private final int PICKED_NUMBERS = 6; //How many numbers to be select by user
	private final int HIGHEST_NUMBERS = 35;//59;//47; 
	private final String WINING_COLOR = "FF0000";//"FF9900";
	private final String NOT_SELECTED_COLOR = "000000";
	private final String NOT_PICKED_COLOR = "FFFF00";
	private final String PICKED_COLOR = "90EE90";
	private final String MATCHED_COLOR = "ffffff";
	private final String SELECTED_COLOR ="00AAff";
	private int selected = 0; 
	private GridPane grid;
	private Button btnPicked[];
	private Button btnGenerated[];
	private Button btnPlay;
	private Label lblResult;
	private int starsWon = 0;
	private LotoData ld;
	 
	/**Setting content*/
	public Node content(Tab tab1, Tab tab2, Tab tab3, Tab tab4) {
		btnPicked = new Button[PICKED_NUMBERS];
		btnGenerated = new Button[GENERATED_NUMBERS];

		ld = new LotoData(GENERATED_NUMBERS, HIGHEST_NUMBERS);
		
		 grid = new GridPane();
		 grid.setStyle("-fx-background-color:#00ff00;");
		 Label lblHeader = new Label("Loto Cure");
		 lblHeader.setStyle("-fx-font-size: 20pt;");
		 grid.add(lblHeader, 0, 0);

		 Label lblOption = new Label("Select your numbrs");
		 lblOption.setStyle("-fx-font-size: 12pt;");
		 grid.add(lblOption, 0, 1);
		
		 grid = numberButtonLayout(ld, grid);
		 
		 
		 Label lblSelected = new Label("You have selected:   ");
		 lblSelected.setStyle("-fx-font-size: 12pt;");
		 grid.add(lblSelected, 0, 12);
		 
		 //Initializing black circles for picked numbers
		 for (int i = 0; i < btnPicked.length; i++) {
			btnPicked[i] = new Button(" ");
			btnPicked[i].setMinWidth(30);
			btnPicked[i].setShape(new Circle(1.5));
			btnPicked[i].setStyle("-fx-background-color:#"+NOT_SELECTED_COLOR+";");
			grid.add(btnPicked[i], i+1, 12);
		}
		 
		 Button reset = new Button("Reset");
		 reset.setOnAction(new EventHandler<ActionEvent>() {
			 @Override
			 public void handle(ActionEvent event) {
				 reset();
			 }});
		 grid.add(reset, 11, 11);
		
		 
		 btnPlay = new Button("Play");
		 btnPlay.setDisable(true);
		 btnPlay.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	btnPlay.setDisable(true);
	            	 for (int j = 0; j < btnGenerated.length; j++) {
	        			 btnGenerated[j] = new Button(""+ld.getGeneratedNo(j));
	        			 btnGenerated[j].setMinWidth(30);
	        			 btnGenerated[j].setShape(new Circle(1.5));
	        			 btnGenerated[j].setStyle("-fx-background-color:#"+WINING_COLOR+";");
	        			 grid.add(btnGenerated[j], j+1, 14);
	        		}
	            	displayWin(tab1,tab2,tab3,tab4);
	            	
	            }});
		 grid.add(btnPlay, 11, 13);
		 
		 Label lblWinning = new Label("Winning numbers are:  ");
		 lblWinning.setStyle("-fx-font-size: 12pt;");
		 grid.add(lblWinning, 0, 14);
		 
		 lblResult = new Label();
		 lblResult.setStyle("-fx-font-size: 15pt;");
		 grid.add(lblResult, 0, 15);
		 
		return grid;
	}

	/**returns numbers of stars, 0 - default*/
	public int getStarsWon() { return starsWon; }
	/**setter to set stars*/
	public void setStarsWon(int starsWon) { this.starsWon = starsWon; }
	
	/**motehod which reseting tam*/
	public void reset() {
		btnPlay.setDisable(true);
    	grid = numberButtonLayout(ld, grid);
    	for (int i = 0; i < btnPicked.length; i++) {
    		btnPicked[i].setText(" ");
			btnPicked[i].setStyle("-fx-background-color:#"+NOT_SELECTED_COLOR+";");
		}
    	for (int i = 0; i < btnGenerated.length; i++) {
			grid.getChildren().remove(btnGenerated[i]);
		}
    	ld.generateNumbers(GENERATED_NUMBERS, HIGHEST_NUMBERS);
    	selected = 0;
    	lblResult.setText("");
	}
	/**method which set tab for win<br>
	 * comparing numbers with setting colors<br>
	 * locking tab<br>
	 * informing about win*/
	protected void displayWin(Tab tab1, Tab tab2, Tab tab3, Tab tab4) {
		int matchedCount = 0;
		for (int i = 0; i < btnGenerated.length; i++) { //matching winnig numbers
			for (int j = 0; j < btnPicked.length; j++) {	
				if(btnGenerated[i].getText().compareTo(btnPicked[j].getText()) == 0) {
					btnGenerated[i].setStyle("-fx-background-color:#"+MATCHED_COLOR+";");
					btnPicked[j].setStyle("-fx-background-color:#"+MATCHED_COLOR+";");
					matchedCount++;
				}
			}
		}
		
		if(matchedCount >= 4) { //if more or 4 stars, game offers a prize
			lblResult.setText("\nYOU MATCHED\n  " + matchedCount + " NUMBERS\n  YOU WIN!!!");
			lblResult.setTextFill(Color.RED);
			starsWon = matchedCount;
			tab1.setDisable(true);
        	tab2.setDisable(true);
        	tab4.setDisable(true);
        	tab3.setDisable(false);
		}//One number(condition for correct spelling) 
		else if(matchedCount == 1) lblResult.setText("\nYOU MATCHED\n  " + matchedCount + " NUMBER\n    no win");
		else lblResult.setText("\nYOU MATCHED\n  " + matchedCount + " NUMBERS\n    no win");
	}
/**method which which draw all numbers*/
	private GridPane numberButtonLayout(LotoData ld, GridPane grid) {
		 int row = 0;
		 int col = 1;
		 for (int i = 1; i <= ld.getHighstNo(); i++) {
			 Button btn = new Button(""+i);
			 btn.setMinWidth(30);
			 btn.setShape(new Circle(1.5));
			 btn.setStyle("-fx-background-color:#"+NOT_PICKED_COLOR+";");
			 btn.setOnAction(new EventHandler<ActionEvent>() {
		            @Override
		            public void handle(ActionEvent event) {
		            	if(btn.getStyle() == "-fx-background-color:#"+PICKED_COLOR+";") {//is already selected
		            	}else if(selected < PICKED_NUMBERS) { //select new
		            		btn.setStyle("-fx-background-color:#"+PICKED_COLOR+";");
		            		btnPicked[selected].setText(btn.getText());
		        			btnPicked[selected].setMinWidth(30);
		        			btnPicked[selected].setShape(new Circle(1.5));
		        			btnPicked[selected].setStyle("-fx-background-color:#"+SELECTED_COLOR+";");
		        			selected++;
		            	}
		            	if(selected == PICKED_NUMBERS) btnPlay.setDisable(false);
		            }
		        });
			 col++;
			 if((i-1)%10 == 0) {
				 row++;
				 col = 1;
			 }
			 grid.add(btn, col, row+2);
		}
		return grid;
	}


}
