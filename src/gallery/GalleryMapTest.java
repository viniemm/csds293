package gallery;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.SortedSet;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;

/**
 * GalleryMap Tester.
 *
 * @author Vinayak Mathur
 * @version 1.0
 * @since <pre>Dec 17, 2021</pre>
 */
public class GalleryMapTest {

	@Before
	public void before() throws Exception {
	}

	@After
	public void after() throws Exception {
	}

	private GalleryMap gmap;

	private void init() {
		Painting p1 = new Painting(new BigDecimal(3), new BigDecimal(1));
		Painting p2 = new Painting(new BigDecimal(4), new BigDecimal(7));
		Painting p3 = new Painting(new BigDecimal(16), new BigDecimal(3));
		Painting p4 = new Painting(new BigDecimal(4), new BigDecimal(8));
		Painting p5 = new Painting(new BigDecimal(6), new BigDecimal(14));
		Painting p6 = new Painting(new BigDecimal(12), new BigDecimal(3));
		Painting p7 = new Painting(new BigDecimal(2), new BigDecimal(2));
		Painting p8 = new Painting(new BigDecimal(9), new BigDecimal(19));
		Painting p9 = new Painting(new BigDecimal(7), new BigDecimal(16));

		SortedSet<Painting> picassos = new TreeSet<>();
		picassos.add(p1);
		picassos.add(p2);
		picassos.add(p3);
		picassos.add(p4);
		picassos.add(p5);
		picassos.add(p6);
		picassos.add(p7);
		picassos.add(p8);
		picassos.add(p9);

		Painting d1 = new Painting(new BigDecimal(5), new BigDecimal(8));
		Painting d2 = new Painting(new BigDecimal(6), new BigDecimal(15));
		Painting d3 = new Painting(new BigDecimal(8), new BigDecimal(6));
		Painting d4 = new Painting(new BigDecimal(10), new BigDecimal(3));
		Painting d5 = new Painting(new BigDecimal(7), new BigDecimal(5));
		Painting d6 = new Painting(new BigDecimal(12), new BigDecimal(10));
		Painting d7 = new Painting(new BigDecimal(2), new BigDecimal(9));
		Painting d8 = new Painting(new BigDecimal(7), new BigDecimal(7));
		Painting d9 = new Painting(new BigDecimal(13), new BigDecimal(4));

		SortedSet<Painting> dalis = new TreeSet<>();
		dalis.add(d1);
		dalis.add(d2);
		dalis.add(d3);
		dalis.add(d4);
		dalis.add(d5);
		dalis.add(d6);
		dalis.add(d7);
		dalis.add(d8);
		dalis.add(d9);

		gmap = new GalleryMap(picassos, dalis);
	}

	/**
	 * Method: validate()
	 */
	@Test
	public void testValidate() throws Exception {
		init();
		gmap.validate();
	}

	/**
	 * Method: maxLength()
	 */
	@Test
	public void testMaxLength() throws Exception {
		init();
		assertEquals(4, gmap.maxLength());
	}

	/**
	 * Method: maxLength()
	 */
	@Test
	public void testMaxGallery() throws Exception {
		init();
		assertEquals("[P(2,2):D(2,9), P(3,1):D(5,8), P(6,14):D(6,15), P(16,3):D(7,5)]", gmap.maxGallery().toString());
	}
} 
