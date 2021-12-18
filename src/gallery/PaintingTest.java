package gallery;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Painting Tester.
 *
 * @author Vinayak Mathur
 * @version 1.0
 * @since Dec 16, 2021
 */
public class PaintingTest {

	/**
	 * Method: validate()
	 */
	@Test
	public void testValidate() throws Exception {
		Painting p1 = new Painting(new BigDecimal(3), new BigDecimal(3));
		//TEST: Code Coverage CC1, Good Data GD1
		assertEquals(p1, p1.validate());
		Painting p2 = new Painting(new BigDecimal(-3), new BigDecimal(-3));
		//TEST: Code Coverage CC1, Good Data GD1
		assertThrows(IllegalArgumentException.class, p2::validate);
		Painting p3 = new Painting(new BigDecimal(0), new BigDecimal(5));
		//TEST: Code Coverage CC1, Good Data GD1
		assertThrows(IllegalArgumentException.class, p3::validate);
		Painting p4 = new Painting(new BigDecimal(5), new BigDecimal(0));
		//TEST: Code Coverage CC1, Good Data GD1
		assertThrows(IllegalArgumentException.class, p4::validate);
		Painting p5 = new Painting(null, null);
		//TEST: Code Coverage CC1, Good Data GD1
		assertThrows(IllegalArgumentException.class, p3::validate);
	}

	/**
	 * Method: compareTo(Painting painting)
	 */
	@Test
	public void testCompareTo() throws Exception {
		Painting p1 = new Painting(new BigDecimal(3), new BigDecimal(3));
		Painting p2 = new Painting(new BigDecimal(2), new BigDecimal(2));
		assertEquals(1, p1.compareTo(p2));
		assertEquals(-1, p2.compareTo(p1));
		Painting p3 = new Painting(new BigDecimal(3), new BigDecimal(2));
		assertEquals(1, p1.compareTo(p3));
		assertEquals(-1, p3.compareTo(p1));
		Painting p4 = new Painting(new BigDecimal(3), new BigDecimal(3));
		assertEquals(0, p4.compareTo(p1));
		assertThrows(NullPointerException.class, () -> {
			int i = p1.compareTo(null);
		});
	}

	/**
	 * Method: comparePrice(Painting p)
	 */
	@Test
	public void testComparePrice() throws Exception {
		Painting p1 = new Painting(new BigDecimal(3), new BigDecimal(3));
		Painting p2 = new Painting(new BigDecimal(2), new BigDecimal(2));
		//TEST: CODE COVERAGE CC1
		assertEquals(1, p1.comparePrice(p2));
		//TEST: CODE COVERAGE CC2
		assertEquals(-1, p2.comparePrice(p1));
		Painting p3 = new Painting(new BigDecimal(3), new BigDecimal(3));
		//TEST: CODE COVERAGE CC3
		assertEquals(0, p1.comparePrice(p3));
		//TEST: BOUNDARY B1
		assertThrows(NullPointerException.class, () -> {
			int i = p1.comparePrice(null);
		});
	}

	/**
	 * Method: compareSize(Painting p)
	 */
	@Test
	public void testCompareSize() throws Exception {
		Painting p1 = new Painting(new BigDecimal(3), new BigDecimal(3));
		Painting p2 = new Painting(new BigDecimal(2), new BigDecimal(2));
		//TEST: CODE COVERAGE CC1
		assertEquals(1, p1.compareSize(p2));
		//TEST: CODE COVERAGE CC2
		assertEquals(-1, p2.compareSize(p1));
		Painting p3 = new Painting(new BigDecimal(3), new BigDecimal(3));
		//TEST: CODE COVERAGE CC3
		assertEquals(0, p1.compareSize(p3));
		//TEST: BOUNDARY B1
		assertThrows(NullPointerException.class, () -> {
			int i = p1.compareSize(null);
		});
	}

	/**
	 * Method: toString()
	 */
	@Test
	public void testToString() throws Exception {
		Painting p1 = new Painting(new BigDecimal(3), new BigDecimal(3));
		assertEquals("(3,3)", p1.toString());
	}

	/**
	 * Method: equals(Painting p)
	 */
	@Test
	public void testEquals() throws Exception {
		Painting p1 = new Painting(new BigDecimal(3), new BigDecimal(3));
		Painting p2 = new Painting(new BigDecimal(2), new BigDecimal(2));
		//TEST: CODE COVERAGE CC1
		assertFalse(p1.equals(p2));
		Painting p3 = new Painting(new BigDecimal(2), new BigDecimal(2));
		//TEST: CODE COVERAGE CC2
		assertTrue(p2.equals(p3));
	}
} 
