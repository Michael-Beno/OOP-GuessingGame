package application.GUI;


import java.util.ArrayList;
import java.util.Optional;

//import java.util.HashMap;

import application.data.Prizes;
import application.data.Prizes.Prize;
import application.data.Winners;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class PrizesGUI {

	private GridPane grid;
	private Tab tab1,tab2,tab3,tab0;
	
	
	public Node content(Tab tab1, Tab tab2, Tab tab3, Tab tab4) {
		this.tab1 = tab1;
		this.tab2 = tab2;
		this.tab3 = tab3;
		this.tab0 = tab4;
		grid = new GridPane();
	    grid.setHgap(10);
	    grid.setVgap(12);
		return grid;
	}

	public void setStars(String prizeStars) {
		Prizes prizes = new Prizes();
		String[] set = prizes.getTree().getSet(); 		//Obtaining key set from MyTree
		int btnCount = 0;
		
		grid.getChildren().clear();
		Label lblHeader = new Label("Prizes");
		lblHeader.setStyle("-fx-font-size: 20pt;");
		grid.add(lblHeader, 0, 0);
		
		Label lblInfo = new Label("Choose one from " + prizeStars+"* prizes.");
		lblInfo.setStyle("-fx-font-size: 15pt;");
		grid.add(lblInfo, 0, 1);
		grid.setStyle("-fx-background-color:#ffff00;");
		
//		for(HashMap.Entry<String, Prize> entry: prizes.getMap().entrySet()){
//   	 String key = entry.getKey();  
//        Prize p = entry.getValue();  
        	
		for (int iSet = 0; iSet < set.length; iSet++) {
		
			 String key = (String) set[iSet];  				//using key to find Prize in MyTree
	         Prize p = prizes.getTree().getObject(key); 	//getting prize from key
	         
	         if(p.getStars().compareTo(prizeStars) == 0) {
				Button btn = new Button(key);
				btn.setId(""+iSet);
			    btn.setOnAction(new EventHandler<ActionEvent>() {
		            @Override
		            public void handle(ActionEvent event) {
		            	 	
		            	TextInputDialog dialog = new TextInputDialog();
		            	dialog.setTitle("Congratulations");
		            	dialog.setHeaderText("Congratulations!\n"
		            			+ "Under the " + key + " is a "
		            			+ ""+p.getPrize());
		            	dialog.setContentText("Enter our name: ");

		            	Optional<String> result = dialog.showAndWait();// Traditional way to show dialog
		            	if (result.isPresent() && result.get().length() > 0){
		         
		            	    tab0.setDisable(false);
		            	    tab1.setDisable(false);
		                	tab2.setDisable(false);
		                	tab3.setDisable(true);
		                	
		                	Winners w = new Winners();										//Winner entered name
		                	ArrayList<Winner> arr = w.loadList();							//loading list
		                	arr.add(new Winner(result.get(), prizeStars+"* "+p.getPrize())); //adding Winner
		                	w.saveList(arr);												//saving with new result
		                	
		            	}else {
		            		tab1.setDisable(true);											//User did NOT enter name
		                	tab2.setDisable(true);											//disabling everything
		                	tab0.setDisable(true);
		                	
		                	for (int i = 2; i < grid.getChildren().size(); i++) 			
		                		if(((Button) grid.getChildren().get(i)).getText().compareTo(btn.getText()) != 0)
		                			((Button) grid.getChildren().get(i)).setDisable(true);

		                	Label warning = new Label("Collect your prize and enter your name!");	//message appears
		                	warning.setStyle("-fx-font-size: 18pt;"); warning.setTextFill(Color.RED);
		                	grid.add(warning,0, 2+ grid.getChildren().size());
		            	}
		            	
		            }//end of event handler
		        }); //end of OnAction
				grid.add(btn,0, 2+ ++btnCount);
	         }// end IF
	    }//end of for loop
	} //end of star method  
}//end of class
