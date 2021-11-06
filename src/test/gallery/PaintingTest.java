package test.gallery;

import gallery.Painting;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

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
//
		Painting painting = new Painting(new BigDecimal(3), new BigDecimal(2));
		assertEquals(painting, painting.validate());

		painting = new Painting(new BigDecimal(0), new BigDecimal(5));
		assertThrows(IllegalArgumentException.class, painting::validate);

		painting = new Painting(new BigDecimal(0), new BigDecimal(0));
		assertThrows(IllegalArgumentException.class, painting::validate);

	}

	/**
	 * Method: compareTo(Painting painting)
	 */
	@Test
	public void testCompareTo() {
//TODO: Test goes here... 
	}

	/**
	 * Method: comparePrice(Painting p)
	 */
	@Test
	public void testComparePrice() {
//TODO: Test goes here... 
	}

	/**
	 * Method: compareSize(Painting p)
	 */
	@Test
	public void testCompareSize() {
//TODO: Test goes here... 
	}

	/**
	 * Method: toString()
	 */
	@Test
	public void testToString() {
//TODO: Test goes here... 
	}

	/**
	 * Method: equals(Painting p)
	 */
	@Test
	public void testEquals() {
//TODO: Test goes here... 
	}

	/**
	 * Method: price()
	 */
	@Test
	public void testPrice() {
//TODO: Test goes here... 
	}

	/**
	 * Method: size()
	 */
	@Test
	public void testSize() {
//TODO: Test goes here... 
	}


} 
