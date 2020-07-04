package application.data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import application.GUI.Winner;

public class Winners implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Winner> aWin;

	public Winners() {
		aWin = new ArrayList<>();
	}
	/**creating dummy winners**/
	public ArrayList<Winner> getArray(int count) {
		for (long i= 0; i < count;i++ ) {
			Winner w = new Winner("Dummy" + i, "?" + i + "000");
			aWin.add(w);
		}
		return aWin;
	}

	public void saveList(ArrayList<Winner> arrWinner) {
		ObjectOutputStream os;
		try {
			os = new ObjectOutputStream(new FileOutputStream("text/winners.ser"));
			os.writeObject(arrWinner);
			os.close();
		} catch (Exception ex) {
			System.err.println("could not save");
			ex.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Winner> loadList() {
		aWin = new ArrayList<>();
		try {
			ObjectInputStream is = new ObjectInputStream(new FileInputStream("text/winners.ser"));
			aWin = (ArrayList<Winner>) is.readObject();
			is.close();
		} catch (Exception ex) {
			System.err.println("could not load");
			ex.printStackTrace();
		}
		return aWin;
	}

}
