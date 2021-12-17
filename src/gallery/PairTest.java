package gallery;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Pair Tester.
 *
 * @author Vinayak Mathur
 * @version 1.0
 * @since <pre>Dec 16, 2021</pre>
 */
public class PairTest {

	@Before
	public void before() throws Exception {
	}

	@After
	public void after() throws Exception {
	}

	/**
	 * Method: validate()
	 */
	@Test
	public void testValidate() throws Exception {
		Painting p1 = new Painting(new BigDecimal(3), new BigDecimal(3));
		Painting p2 = new Painting(new BigDecimal(2), new BigDecimal(2));
		Pair pair1 = new Pair(p2, p1);
		Pair pair2 = new Pair(p1, p2);
		assertThrows(IllegalArgumentException.class, pair2::validate);
		assertEquals(pair1, pair1.validate());
		Painting p3 = new Painting(new BigDecimal(3), new BigDecimal(3));
		Pair pair3 = new Pair(p1, p3);
		assertThrows(IllegalArgumentException.class, pair3::validate);
	}

	/**
	 * Method: equals(Pair p)
	 */
	@Test
	public void testEquals() throws Exception {
		Painting p1 = new Painting(new BigDecimal(1), new BigDecimal(1));
		Painting p2 = new Painting(new BigDecimal(2), new BigDecimal(2));
		Painting p3 = new Painting(new BigDecimal(3), new BigDecimal(3));
		Painting p4 = new Painting(new BigDecimal(4), new BigDecimal(4));
		Pair pair1 = new Pair(p1, p2);
		Pair pair2 = new Pair(p1, p2);
		Pair pair3 = new Pair(p2, p4);
		Pair pair4 = new Pair(p2, p3);
		Pair pair5 = new Pair(p3, p4);
		assertTrue(pair1.equals(pair2));
		assertFalse(pair4.equals(pair3));
		assertFalse(pair5.equals(pair3));
	}

	/**
	 * Method: compareTo(Pair pair)
	 */
	@Test
	public void testCompareTo() throws Exception {
		Painting p1 = new Painting(new BigDecimal(1), new BigDecimal(1));
		Painting p2 = new Painting(new BigDecimal(2), new BigDecimal(2));
		Painting p3 = new Painting(new BigDecimal(3), new BigDecimal(3));
		Painting p4 = new Painting(new BigDecimal(4), new BigDecimal(4));
		Pair pair1 = new Pair(p1, p2);
		Pair pair2 = new Pair(p1, p2);
		Pair pair3 = new Pair(p2, p4);
		Pair pair4 = new Pair(p2, p3);
		assertEquals(0,pair1.compareTo(pair2));
		assertEquals(1,pair3.compareTo(pair4));
		assertEquals(-1,pair4.compareTo(pair3));
	}

	/**
	 * Method: toString()
	 */
	@Test
	public void testToString() throws Exception {
		Painting p1 = new Painting(new BigDecimal(1), new BigDecimal(1));
		Painting p2 = new Painting(new BigDecimal(2), new BigDecimal(2));
		Pair pair = new Pair(p1, p2);
		assertEquals("P($1,1) : D($2,2)", pair.toString());
	}
} 
