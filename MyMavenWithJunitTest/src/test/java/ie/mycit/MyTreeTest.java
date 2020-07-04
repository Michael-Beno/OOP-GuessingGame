package ie.mycit;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

public class MyTreeTest {

	static MyTree< Integer , Prize> tree = new MyTree<>();
	int valMin 		= 10;
	int valMax 		= 99;
	static int itemsCount	= 10;
	static int[] iArrTesting = new int[itemsCount]; //array must be static for testing
		

	@Test /**adding to tree*/
	public void test() {
		int value;
        Random randomNumber = new Random();
        
        System.out.println( "Test1 - Inserting values: " );
        
        for ( int i = 0; i < itemsCount; i++ ) {
        	value = randomNumber.nextInt( valMax-valMin)+valMin;							//generating random number
        	iArrTesting[i] = value;															//adding this number to array
        	
        	tree.add( value, new Prize(""+value, "key value in string: "+value, "0"));		//storing Value to the tree
        	
        	String receivedValue = tree.getObject(value).getKey();							// receiving value from tree
        	
        	assertEquals(""+value, receivedValue);											//Test pass when stored value match received value
        	System.out.printf("Inserted: %d = Received: %s\n", value, receivedValue);
        }
	}
	
	@Test /**checking if all my keys matching my objects*/
	public void test2() {
		System.out.println( "\nTest2 - checking all values: " );
		int receivedcount = 0;
		for (int i = 0; i < iArrTesting.length; i++) {
			int testingValue = iArrTesting[i];
			Prize p = tree.getObject(testingValue);
			String receivedValue = p.getKey();
			assertEquals(""+testingValue, receivedValue);	//test if array[] values are in MyTree
			
			System.out.printf("test[%d] = %d  ==> MyTree.key = %s, %s \n", i, testingValue, receivedValue, p.getPrize());
			receivedcount++;
		}
		System.out.printf("Testing array has [%d] items Received %d values.\n", itemsCount, receivedcount);
	}
	
	@Test		/**testing my SET*/
	public void test3() {
		System.out.println( "\nTest3 - My SET has to be same lenght as added and is ordered: " );
		String[] set = tree.getSet();
		assertEquals(set.length, iArrTesting.length);
		
		for (int i = 0; i < set.length-1; i++) {
			assertTrue(set[i]+" is not smaller or equal than "+set[i+1],set[i].compareTo(set[i+1]) <= 0);
			System.out.println(set[i]+" < "+set[i+1]);
		}
		
		System.out.printf("Testing array = %d items. MyTree set = %d items.\n", iArrTesting.length, set.length);
	}

     
	
	public class Prize {
		private String stars;
		private String prize;
		private String key;
		
		public Prize(String stars, String prize) { 
			this.stars = stars; this.prize = prize; }
		public Prize(String key, String prize, String stars) {
			this.key = key;this.stars = stars; this.prize = prize;
		}
		public String getStars() { return stars; }
		public String getPrize() { return prize; }
		public String getKey() { return key; }
		public void setKey(String key) { this.key = key; }
	}

}
