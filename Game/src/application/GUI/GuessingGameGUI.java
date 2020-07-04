package application.GUI;


import application.data.GuessingGame;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class GuessingGameGUI {

	private final int GUESSING_NUMBERS = 6;
	private final int HIGHEST_NUMBER = 100;
	private int starsWon = 0;
	private GuessingGame gg;
	private TextField tfInputNum;
	private Label lblName, lblAttemps, lblLH;
	private Button btnGuess;
	private Tab tab1,tab2,tab3, tab4;
	
	public Node content(Tab tab1, Tab tab2, Tab tab3, Tab tab4) {
		
		this.tab1 = tab1;
		this.tab2 = tab2;
		this.tab3 = tab3;
		this.tab4 = tab4;
		gg = new GuessingGame(GUESSING_NUMBERS , HIGHEST_NUMBER);

		GridPane grid = new GridPane();
	    grid.setHgap(10);
	    grid.setVgap(12);
	    grid.setStyle("-fx-background-color:#00ffff;");
	    HBox hbButtons = new HBox();
	    hbButtons.setSpacing(10.0);

	    btnGuess = new Button("Guess");
	    
	    lblName = new Label("Enter the number 1 - "+gg.getEndNo());
	    lblName.setStyle("-fx-font-size: 12pt;");
	    Label lblHeader = new Label("Guessing Game");
	    lblHeader.setStyle("-fx-font-size: 20pt;");
	    
	    tfInputNum = new TextField();
	    tfInputNum.textProperty().addListener(new ChangeListener<String>() {//Observation makes sure that correct value is set
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            	try {
					gg.setInputNum( Integer.parseInt(newValue) );
					if(gg.getInputNum() == 0) tfInputNum.setText("");
				} catch (Exception e) {
					if(gg.getInputNum() > 0) tfInputNum.setText(""+gg.getInputNum());
					else tfInputNum.setText("");
				}
            	if(gg.getInputNum() > 100) {
            		gg.setInputNum( Integer.parseInt(oldValue) );
            		tfInputNum.setText(""+gg.getInputNum()); 
            	}
            	if(newValue.length() == 0) {
            		gg.setInputNum(0); 
            		tfInputNum.setText("");
            		btnGuess.setDisable(true);
            	} else if(gg.getAttempsLeft() > 0) btnGuess.setDisable(false);

            }
        });
	    lblAttemps = new Label("You have "+gg.getAttempsLeft()+" attemps");
	    lblAttemps.setStyle("-fx-font-size: 15pt;");
	    lblLH = new Label();
	    lblLH.setStyle("-fx-font-size: 18pt;");
	    
	    Button btnReset = new Button("Reset");
	    btnReset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	reset();
            }
        });
	       
	    btnGuess.setDisable(true);
	    btnGuess.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	checkForWin();
            }
        });
	    
	    tfInputNum.setOnKeyPressed(new EventHandler<KeyEvent>() {
	        
	        @Override
	        public void handle(KeyEvent event) {
	            if(event.getCode().equals(KeyCode.ENTER) &&
	            		tfInputNum.getText().length() > 0 && gg.getAttempsLeft() > 0) {
	                checkForWin();
	            }
	        }
	    });

	    Button btnQuit = new Button("Quit");
	    btnQuit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
                System.exit(0);
            }
        });

	    hbButtons.getChildren().addAll(btnReset, btnGuess, btnQuit);
	    grid.add(lblHeader, 0, 0);
	    grid.add(lblName, 0, 1);
	    grid.add(tfInputNum, 1, 1);
	    grid.add(lblAttemps, 0, 2);
	    grid.add(lblLH, 1, 2);
	    grid.add(hbButtons, 0, 3, 3, 1);
		return grid;
	}
	
	/**method for checking for*/
	protected void checkForWin() {

        gg.decrementAttemp();
        if(gg.getRandomNum() > gg.getInputNum()) {
        	lblLH.setText("Number "+ gg.getInputNum() + " is to LOW");
        	if(gg.getAttempsLeft() == 0) lblLH.setText("You LOOSE");
        }
        else if(gg.getRandomNum() < gg.getInputNum()) {
        	lblLH.setText("Number "+ gg.getInputNum() + " is to High");
        	if(gg.getAttempsLeft() == 0) lblLH.setText("You LOOSE");
        }  else {
        	lblLH.setText("You WIN ****\n please withdraw your prize ");
        	lblLH.setTextFill(Color.RED);
        	starsWon = 4;
        	btnGuess.setDisable(true);
        	tab1.setDisable(true);
        	tab2.setDisable(true);
        	tab4.setDisable(true);
        	tab3.setDisable(false);
        }
        
        if(gg.getAttempsLeft() == 1) lblAttemps.setText("You have "+  gg.getAttempsLeft() +" attemp");
        else lblAttemps.setText("You have "+ gg.getAttempsLeft() +" attemps");
        if(gg.getAttempsLeft() == 0) {
        	btnGuess.setDisable(true);
        }
        tfInputNum.setText("");
	}
	
	/**Method for reseting the TAB*/
	public void reset() {
		gg.resetRanndomNumber();
    	gg.setInputNum(0);
		tfInputNum.setText("");
		
		gg.resetAttemps();
        lblAttemps.setText("You have "+ gg.getAttempsLeft() +" attemps");
        lblLH.setText("");
		btnGuess.setDisable(true);
	}
	/**method returns starts, 0 - default*/
	public int getStarsWon() { return starsWon; }
	/**Setter for stars*/
	public void setStarsWon(int starsWon) { this.starsWon = starsWon; }

}
