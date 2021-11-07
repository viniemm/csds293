package test.gallery;

import gallery.Painting;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class PaintingTest {

	long startTime;

	@Before
	public void before() {
		System.out.println("Starting test");
		this.startTime = System.currentTimeMillis();
	}

	@After
	public void after() {
		long elapsed = System.currentTimeMillis() - startTime;
		System.out.println("Test complete. Elapsed time: " + elapsed);
	}

	/**
	 * Method: validate()
	 */

	@Test
	public void testValidate() {
//		Test 1: CC1, GD1
		Painting painting = new Painting(new BigDecimal(3), new BigDecimal(3));
		assertEquals(painting, painting.validate());
//		Test 2: CC2, BD1
		painting = new Painting(new BigDecimal(-3), new BigDecimal(-3));
		assertThrows(IllegalArgumentException.class, painting::validate);
//		Test 3: B1
		painting = new Painting(new BigDecimal(0), new BigDecimal(5));
		assertThrows(IllegalArgumentException.class, painting::validate);
//		Test 4: B2
		painting = new Painting(new BigDecimal(5), new BigDecimal(0));
		assertThrows(IllegalArgumentException.class, painting::validate);
//		Test 5: BD2
		painting = new Painting(null, null);
		assertThrows(NullPointerException.class, painting::validate);
	}

	/**
	 * Method: compareTo(Painting painting)
	 */
	@Test
	public void testCompareTo() {
		Painting painting = new Painting(new BigDecimal(3), new BigDecimal(3));
//		Test 1: CC1
		Painting p1 = new Painting(new BigDecimal(2), new BigDecimal(2));
		assertEquals(painting.compareTo(p1), 1);
//		Test 2: CC2
		Painting p2 = new Painting(new BigDecimal(4), new BigDecimal(2));
		assertEquals(painting.compareTo(p2), -1);
//		Test 3: CC3
		Painting p3 = new Painting(new BigDecimal(3), new BigDecimal(2));
		assertEquals(painting.compareTo(p3), 1);
//		Test 4: CC4
		Painting p4 = new Painting(new BigDecimal(3), new BigDecimal(4));
		assertEquals(painting.compareTo(p4), -1);
//		Test 5: CC5, B1
		Painting p5 = new Painting(new BigDecimal(3), new BigDecimal(3));
		assertEquals(painting.compareTo(p5), 0);

	}

	/**
	 * Method: compareTo(null)
	 */
	@Test(expected = NullPointerException.class)
	public void testCompareToIllegalArgumentException() {
//		Test 6: CC6
		Painting painting = new Painting(new BigDecimal(3), new BigDecimal(3));
		Painting p6 = new Painting(null, null);
		int j = painting.compareTo(p6);
	}

//	/**
//	 * Method: comparePrice(Painting p)
//	 */
//	@Test
//	public void testComparePrice() {
//		Painting painting = new Painting(new BigDecimal(3), new BigDecimal(3));
////		Test 1: CC1
//		Painting p1 = new Painting(new BigDecimal(2), new BigDecimal(3));
//		assertEquals(painting.comparePrice(p1), 1);
////		Test 2: CC2
//		Painting p2 = new Painting(new BigDecimal(4), new BigDecimal(3));
//		assertEquals(painting.comparePrice(p2), -1);
////		Test 3: CC3
//		Painting p3 = new Painting(new BigDecimal(3), new BigDecimal(2));
//		assertEquals(painting.comparePrice(p3), 0);
//
//	}
//
//	/**
//	 * Method: compareTo(null)
//	 */
//	@Test(expected = NullPointerException.class)
//	public void testComparePriceIllegalArgumentException() {
////		Test 4: B1
//		Painting painting = new Painting(new BigDecimal(3), new BigDecimal(3));
//		Painting p6 = new Painting(null, null);
//		int j = painting.comparePrice(p6);
//	}

//	/**
//	 * Method: compareSize(Painting p)
//	 */
//	@Test
//	public void testCompareSize() {
////TODO: Test goes here...
//	}

	/**
	 * Method: toString()
	 */
	@Test
	public void testToString() {
		Painting p = new Painting(new BigDecimal(3), new BigDecimal(3));
		assertEquals("($3,3)", p.toString());
	}

	/**
	 * Method: equals(Painting p)
	 */
	@Test
	public void testEquals() {
		Painting p1 = new Painting(new BigDecimal(3), new BigDecimal(3));
		Painting p2 = new Painting(new BigDecimal(3), new BigDecimal(3));
		assertTrue(p1.equals(p2));
		Painting p3 = new Painting(new BigDecimal(3), new BigDecimal(2));
		assertFalse(p1.equals(p3));
	}
} 
