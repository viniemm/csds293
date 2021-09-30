package edu.cwru.vxm167.gis;

import java.util.*;
import java.util.function.Predicate;

public class InterestPoints<M> {

	private final BiDimensionalMap<InterestPoint<M>> points;

	private InterestPoints(Builder<M> builder) {
		this.points = builder.points;
	}

	public final Collection<InterestPoint<M>> get(Coordinate coordinate) {
		Objects.requireNonNull(coordinate);
		try {
			return points.get(coordinate);
		} catch (NullPointerException e) {
			System.out.println("The value of either x or y in the coordinate is null");
			return null;
		}
	}

	public final List<Collection<InterestPoint<M>>> interestPoints() {
		List<Coordinate> sortedCoordinates = points.coordinateSet();
		List<Collection<InterestPoint<M>>> result = new ArrayList<>();
		for (Coordinate c : sortedCoordinates) {
			result.add(get(c));
		}
		return result;
	}

	public final long count(RectilinearRegion region, M marker) {
		long result = 0;
		Predicate<InterestPoint<M>> predicate = x -> x.hasMarker(marker);

		for (Rectangle rectangle : region.getRectangles()) {
			BiDimensionalMap<InterestPoint<M>> bd = points.slice(rectangle);
			result += bd.collectionSize(predicate);
		}
		return result;
	}

	@Override
	public String toString() {
		return "InterestPoints{" +
			"points=" + points +
			'}';
	}

	public static class Builder<M> {
		private final BiDimensionalMap<InterestPoint<M>> points = new BiDimensionalMap<>();

		public final boolean add(InterestPoint<M> interestPoint) {
			interestPoint.validate();
			BiDimensionalMap<M>.Updater up = (BiDimensionalMap<M>.Updater) points.getUpdater();
			up.setCoordinate(interestPoint.coordinate());
			up.addValue(interestPoint.marker());
			up.add();
			return true;
		}

		public final InterestPoints<M> build() {
			return new InterestPoints<M>(this);
		}
	}
}
