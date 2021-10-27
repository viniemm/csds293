package test.gallery;

import gallery.Painting;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

/** 
* Painting Tester. 
* 
* @author <Authors name> 
* @since <pre>Oct 26, 2021</pre> 
* @version 1.0 
*/ 
public class PaintingTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: validate()
* 
*/ 
@Test (expected= NullPointerException.class)
public void testValidate() throws Exception {
	Painting painting = new Painting(new BigDecimal(0),new BigDecimal(0));
	painting.validate();
//TODO: Test goes here... 
}

/** 
* 
* Method: validate(Painting painting) 
* 
*/ 
@Test
public void testValidatePainting() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: compareTo(Painting painting) 
* 
*/ 
@Test
public void testCompareTo() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: comparePrice(Painting p) 
* 
*/ 
@Test
public void testComparePrice() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: compareSize(Painting p) 
* 
*/ 
@Test
public void testCompareSize() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: toString() 
* 
*/ 
@Test
public void testToString() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: equals(Painting p) 
* 
*/ 
@Test
public void testEquals() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: price() 
* 
*/ 
@Test
public void testPrice() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: size() 
* 
*/ 
@Test
public void testSize() throws Exception { 
//TODO: Test goes here... 
} 


} 
