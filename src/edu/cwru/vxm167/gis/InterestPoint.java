
package edu.cwru.vxm167.gis;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.math.*;


public record InterestPoint<M>(Coordinate coordinate, M marker) {

	@Override
	public String toString() {
		return coordinate.toSimpleString() + " : " + marker.toString();
	}

	public final InterestPoint<M> validate() {
		if (Objects.isNull(marker)) {
			throw new NullPointerException("The marker cannot be null");
		} else {
			coordinate.validate();
			return this;
		}
	}

	public static final InterestPoint validate(InterestPoint interestpoint) {
		if (Objects.isNull(interestpoint.coordinate())) {
			throw new NullPointerException("The argument is null");
		}
		else {
			return interestpoint.validate();
		}
	}

	public boolean hasMarker(M marker) {
		return this.marker.equals(marker);
	}
}
