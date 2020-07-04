package application.data;

import java.util.Random;
/**
 * @author R00155443
 * Michael Beno
 * 23/02/2018
 */
public class GuessingGame {


	private int iRan;
	private int iEndNo;
	private int inputNum;
	private final int ATTEMPS;
	private int iAttemps;
	
	/**Constructor with default game<br>
	 * With 6 attempts and number guessing 1 - 100*/
	public GuessingGame() {
		ATTEMPS = 6;
		setAttemps(ATTEMPS);
		iEndNo = 100;
		resetRanndomNumber();
	}
	/**Constructor with optional game<br>
	 * with elective <b>attempts</b> and numbers 1 - <b>upto</b>...*/
	public GuessingGame(int attemps, int upto) {
		ATTEMPS = attemps;
		setAttemps(ATTEMPS);
		iEndNo = upto;
		resetRanndomNumber();
	}
	/**Returns end range of guessing numbers*/
	public int getEndNo() { return iEndNo; }
	
	/**Returns last number the user guessed*/
	public int getInputNum() { return inputNum;}
	
	/**User input, a number the user guessed*/
	public void setInputNum(int inputNum) { this.inputNum = inputNum; }
	
	/**Returns generated random number*/
	public int getRandomNum() { return iRan; }
	
	/**Method which reseting/generating new random number*/
	public void resetRanndomNumber() {
		Random ranGen = new Random();
		this.iRan = ranGen.nextInt(iEndNo)+1;
		System.out.println("Guessing game: "+iRan);
	}
	
	/**Returns how many attempts left*/
	public int getAttempsLeft() { return iAttemps; }
	
	/**Used for initializing attempts, sets in constructor*/
	private void setAttemps(int iAttemps) { this.iAttemps = iAttemps; }
	
	/**Method which decrementing attempts by one*/
	public void decrementAttemp() { this.iAttemps--; }
	
	/**Reseting attempts back*/
	public void resetAttemps() { this.iAttemps = ATTEMPS; }
}
