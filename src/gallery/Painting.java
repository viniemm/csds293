package gallery;

import java.util.*;
import java.math.BigDecimal;

/**
 * The type Painting.
 *
 * @author Vinayak Mathur vxm167 Painting is a record class that stores the price, size and artist for a given painting.
 */
public record Painting(BigDecimal price, BigDecimal size) implements Comparable<gallery.Painting> {
	/**
	 * Validate painting.
	 *
	 * @return the painting
	 */
	public final Painting validate() {
		if (Objects.isNull(price) || Objects.isNull(size)) {
			throw new NullPointerException("Arguments cannot be null");
		}
		return this;
	}


	/**
	 * Accepts an object of class Painting and validate that Painting.
	 *
	 * @param painting is the painting to be validated.
	 * @return the painting
	 */
	public static final Painting validate(Painting painting) {
		Objects.requireNonNull(painting, "Coordinate cannot be null");
		return painting.validate();
	}

	/**
	 * compareTo is used to sort paintings on the basis of the given condition.
	 *
	 * @param p is the painting with which to compare this painting.
	 */
	@Override
	public int compareTo(Painting p) {
		BigDecimal price = p.price();
		BigDecimal size = p.size();
		int result = 0;
		if (this.price.compareTo(price) > 0) {
			result = 1;
		} else if (this.price.compareTo(price) < 0) {
			result = -1;
		} else if (this.price.compareTo(price) == 0) {
			if (this.size.compareTo(size) < 0) {
				result = -1;
			} else if (this.size.compareTo(size) > 0) {
				result = 1;
			}
		}
		return result;
	}

	/**
	 * @return a simple String that describes the coordinate.
	 */
	@Override
	public String toString() {
		return "($" + price.toPlainString() + "," + size.toPlainString() + ")";
	}

	public boolean equals(Painting p) {
		return price.equals(p.price()) && size.equals(p.size());
	}

}
