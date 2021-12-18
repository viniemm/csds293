package gallery;

import java.util.Objects;
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
		Objects.requireNonNull(price);
		Objects.requireNonNull(size);
		if (price.compareTo(BigDecimal.ZERO) < 1 || size.compareTo(BigDecimal.ZERO) < 1) {
			throw new IllegalArgumentException("Price or size cannot be 0 or less");
		}
		return this;
	}

	/**
	 * compareTo is used to sort paintings on the basis of the given condition.
	 *
	 * @param painting is the painting with which to compare this painting.
	 */
	@Override
	public int compareTo(Painting painting) {
		Objects.requireNonNull(painting.validate());
		if(comparePrice(painting)!=0)
			return comparePrice(painting);
		return compareSize(painting);
	}

	int comparePrice(Painting p) {
		Objects.requireNonNull(p.validate());
		return price.compareTo(p.price);
	}

	int compareSize(Painting p) {
		Objects.requireNonNull(p.validate());
		return size.compareTo(p.size);
	}

	/**
	 * @return a simple String that describes the coordinate.
	 */
	@Override
	public String toString() {
		return "(" + price().toPlainString() + "," + size().toPlainString() + ")";
	}

	public boolean equals(Painting p) {
		Objects.requireNonNull(p.validate());
		return compareTo(p) == 0;
	}
}
