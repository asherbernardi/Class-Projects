package q5string;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Test;

public abstract class SetTest {

	protected Set<String> testSet;

	protected static Random randy = new Random(System.currentTimeMillis());
	
	protected void populate() {
		for (int i = 0; i < data.length; i++)
			testSet.add(data[i]);
	}

	
	protected abstract void reset();
	
	protected String[] data = 
		{ "CONSTANCE", "HELEN", "JUSTIN", "JON", "JOHN", "CONSTANTIUS",
			"HELENA", "HELLA", "JONATHAN", "CONSTANTINE", "JUSTINIAN",
			"CLEMENS", "HEROD", "ANN", "JOHANA", "JUSTINIANUS",
			"CONSTANTINUS", "ELLEN", "ELAINE", "ELLIE", "ELLA",
			"HERODIAS", "HERODIAN", "JOHNA", "ANNALISE", "ANNETTE",
			"ANNIKA", "CLEMENT", "CAESAR", "AUGUSTUS", "ANNE", "ANNMARIE",
			"JUSTINMARTYR", "JOHNBAPTIST", "ANNIE", "ANNA", "CAESARION",
			"CAESAREA", "AUGUSTA", "ANNMARGARET", "AUGUST", "AUGUSTINE",
			"CLEMENZO", "JONATHANIAN", "CAESARINA", "AUGUSTINUS", "CONSTANS",
			"HELENE" };
	
	// let's hope that the implicit static initializer statements
	// are executed in the order they appear in the file
	protected int[] marks = new int[data.length];
	
	protected void clearMarks() {
		for (int i = 0; i < marks.length; i++)
			marks[i] = 0;
	}

	protected int indexForDatum(String datum) {
		int index = -1;
		for (int i = 0; i < data.length && index == -1; i++)
			if (data[i].equals(datum))
				index = i;
		return index;
	}
	
	@Test
	public void initialEmpty() {
		reset();
		assertTrue(testSet.isEmpty());
	}

	@Test
	public void initialSize() {
		reset();
		assertEquals(0, testSet.size());
	}
	
	@Test
	public void addNotEmpty() {
		reset();
		testSet.add(data[0]);
		assertFalse(testSet.isEmpty());
	}

	@Test
	public void addSize() {
		reset();
		testSet.add(data[0]);
		assertEquals(1, testSet.size());
	}

	@Test
	public void twoAddsUniqueSize() {
		reset();
		testSet.add(data[0]);
		testSet.add(data[1]);
		assertFalse(testSet.isEmpty());
		assertEquals(2, testSet.size());
	}

	@Test
	public void twoAddsIdenticalSize() {
		reset();
		testSet.add(data[0]);
		testSet.add(data[0]);
		assertFalse(testSet.isEmpty());
		assertEquals(1, testSet.size());
	}

	@Test
	public void addsUnique() {
		reset();
		for (int i = 0; i < 8; i++) 
			testSet.add(data[i]);
		assertFalse(testSet.isEmpty());
		assertEquals(8, testSet.size());
	}
	
	@Test
	public void addsIdentical() {
		reset();
		for (int i = 0; i < 8; i++) 
			testSet.add(data[0]);
		assertFalse(testSet.isEmpty());
		assertEquals(1, testSet.size());
	}

	@Test
	public void addsMix() {
		reset();
		for (int i = 0; i < 50; i++)
			testSet.add(data[randy.nextInt(data.length)]);
		assertFalse(testSet.isEmpty());
		//assertEquals(data.length, testSet.size());
	}

	@Test
	public void addsLots() {
		reset();
		populate();
		assertFalse(testSet.isEmpty());
		assertEquals(data.length, testSet.size());
	}
	
	@Test
	public void containsInitial() {
		reset();
		assertFalse(testSet.contains(data[0]));
	}
	
	@Test
	public void containsOne() {
		reset();
		testSet.add(data[0]);
		assertTrue(testSet.contains(data[0]));
	}

	@Test
	public void containsTwoUnique() {
		reset();
		testSet.add(data[0]);
		testSet.add(data[1]);
		assertTrue(testSet.contains(data[0]));
		assertTrue(testSet.contains(data[1]));
	}

	@Test
	public void containsTwoIdentical() {
		reset();
		testSet.add(data[0]);
		testSet.add(data[0]);
		assertTrue(testSet.contains(data[0]));
	}

	
	@Test
	public void containsLots() {
		reset();
		populate();
		for (int i = 0; i < data.length; i++)
			assertTrue(testSet.contains(data[i]));
	}

	
}
