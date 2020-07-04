package application.data;
/**
 * @author R00155443
 * Michael Beno
 * 23/02/2018
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
//import java.util.HashMap;

import ie.mycit.MyTree;


public class Prizes {
	
	//private HashMap<String,Prize> map;
	private MyTree<String,Prize> tree;
	
	
	public Prizes() { textReader();}
	
//	public HashMap<String,Prize> getMap() {return map; }
//	public void setMap(HashMap<String,Prize> map) {this.map = map; }
	
	public MyTree<String,Prize> getTree() { return tree; }
	public void setTree(MyTree<String,Prize> tree) { this.tree = tree; }
	
	private void textReader() {
		//map=new HashMap<String,Prize>();   
		tree = new MyTree<>();
		
		try {
			File file = new File("text/test.txt");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				String linePart[]= line.split(":", 0);
				Prize prize = new Prize(linePart[0], linePart[2]);
				//map.put(linePart[1], p);
				tree.put(linePart[1], prize);
			}
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**class Prize is used for HashMap*/
	public class Prize {
		private String stars;
		private String prize;
		
		public Prize(String stars, String prize) { this.stars = stars; this.prize = prize; }
		public String getStars() { return stars; }
		public String getPrize() { return prize; }
	}
}
