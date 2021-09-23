
package edu.cwru.vxm167.gis;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.math.*;

/**
 * InterestPoint is a record class that stores a coordinate and the marker at that specific coordinate.
 * @author Vinayak Mathur vxm167
 * @param <M> is the location marker
 */
public record InterestPoint<M>(Coordinate coordinate, M marker) {

	@Override
	public String toString() {
		return coordinate.toSimpleString() + " : " + marker.toString();
	}

	/**
	 * Makes sure that the x and y values of the coordinate and marker are all non-null. Throws an exception is either is.
	 * @return this class.
	 */
	public final InterestPoint<M> validate() {
		if (Objects.isNull(marker)) {
			throw new NullPointerException("The marker cannot be null");
		} else {
			coordinate.validate();
			return this;
		}
	}

	/**
	 * Makes sure that the x and y values of the coordinate and marker of interestpoint are non-null. Throws an exception is either is.
	 * @param interestpoint is the InterestPoint to be validated.
	 * @return this class.
	 */
	public static final InterestPoint validate(InterestPoint interestpoint) {
		if (Objects.isNull(interestpoint.coordinate())) {
			throw new NullPointerException("The argument is null");
		}
		else {
			return interestpoint.validate();
		}
	}

	/**
	 * Checks whether the given marker is the same as the stored marker.
	 * @param marker is the marker that is to be checked in the marker field.
	 * @return true if the given marker and stored marker are equal.
	 */
	public boolean hasMarker(M marker) {
		return this.marker.equals(marker);
	}
}