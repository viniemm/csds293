package edu.cwru.vxm167.gis;

import java.util.*;
import java.math.BigDecimal;

/**
 * @author Vinayak Mathur vxm167
 * Coordinate is a record class that stores the x and y values for a given coordinate.
 */

public record Coordinate(BigDecimal x, BigDecimal y) implements Comparable<Coordinate> {

	public static final Coordinate ORIGIN = new Coordinate(BigDecimal.ZERO, BigDecimal.ZERO);

	/**
	 * Makes sure that the x and y values of the coordinate are non-null. Throws an exception is either is.
	 *
	 * @return this class.
	 */
	public final Coordinate validate() {
		if (Objects.isNull(x) || Objects.isNull(y)) {
			throw new NullPointerException("The values of x or y cannot be null");
		}
		return this;
	}

	/**
	 * Accepts an object of class Coordinate and validate that coordinate.
	 *
	 * @param coordinate is the coordinate to be validated.
	 * @return
	 */
	public static final Coordinate validate(Coordinate coordinate) {
		Objects.requireNonNull(coordinate, "Coordinate cannot be null");
		return coordinate.validate();
	}

	/**
	 * compareTo is used to sort coordinates on the basis of the given condition.
	 *
	 * @param c is the coordinate with which to compare this coordinate.
	 * @return
	 */
	@Override
	public int compareTo(Coordinate c) {
		BigDecimal x2 = c.x();
		BigDecimal y2 = c.y();
		int result = 0;
		if (this.x.compareTo(x2) > 0 && this.y.compareTo(y2) > 0) {
			result = 1;
		} else if (this.x.compareTo(x2) <= 0 && this.y.compareTo(y2) <= 0) {
			result = -1;
		}
		return result;
	}

	/**
	 * @return a simple String that describes the coordinate.
	 */
	public String toSimpleString() {
		String xs = this.x.toPlainString();
		String ys = this.y.toPlainString();
		return "(" + xs + "," + ys + ")";
	}
}
