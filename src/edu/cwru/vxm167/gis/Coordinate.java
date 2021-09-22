
package edu.cwru.vxm167.gis;

import java.util.*;
import java.math.BigDecimal;

public record Coordinate(BigDecimal x, BigDecimal y) implements Comparable<Coordinate> {

	public static final Coordinate ORIGIN = new Coordinate(BigDecimal.ZERO,BigDecimal.ZERO);

	public final Coordinate validate() {
		if (Objects.isNull(x) || Objects.isNull(y)) {
			throw new NullPointerException("The values of x or y cannot be null");
		}
		else {
			return this;
		}
	}

	public static final Coordinate validate(Coordinate coordinate) {
		if (Objects.isNull(coordinate)) {
			throw new NullPointerException("The argument is null");
		}
		else {
			return coordinate.validate();
		}
	}

	@Override
	public int compareTo(Coordinate c) {
		BigDecimal x2 = c.x();
		BigDecimal y2 = c.y();

		if (this.x.compareTo(x2) > 0 && this.y.compareTo(y2) > 0) {
			return 1;
		}
		else if (this.x.compareTo(x2) <= 0 && this.y.compareTo(y2) <= 0) {
			return -1;
		}
		else {
			return 0;
		}
	}

	public String toSimpleString() {
		String xs = this.x.toPlainString();
		String ys = this.y.toPlainString();
		return "(" + xs + "," + ys + ")";
	}
}
