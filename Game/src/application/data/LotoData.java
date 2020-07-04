package application.data;
/**
 * @author R00155443
 * Michael Beno
 * 23/02/2018
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class LotoData {

		private int numCount;
		private int highstNo;
		private ArrayList<Integer> numbers;
		/**Default constructor with 6 numbers 1-47 */
		public LotoData() {
			this.numCount = 6;
			this.highstNo = 47;
			generateNumbers(numCount, highstNo);
		}

		public LotoData(int count, int maxNo) {
			this.numCount = count;
			this.highstNo = maxNo;
			generateNumbers(this.numCount, this.highstNo);
		}

		public void generateNumbers(int count, int maxNo) {

			numbers = new ArrayList<Integer>();   
			Random randomGenerator = new Random();
			while (numbers.size() < count) {

				int random = randomGenerator .nextInt(maxNo)+1;
			    if (!numbers.contains(random)) {
			        numbers.add(random);
			    }
			}
			Collections.sort(numbers);
			
			System.out.print("Loto numbers: ");
			for (int i = 0; i < numbers.size(); i++) {
				System.out.print(numbers.get(i)+" ");
			}
			System.out.println();
		}

		public int getGeneratedNo(int id) {return numbers.get(id);}
		public int getNumCount() {return numCount; }
		public void setNumCount(int numCount) { this.numCount = numCount; }
		public int getHighstNo() { return highstNo; }
		public void setHighstNo(int highstNo) { this.highstNo = highstNo; }
}
