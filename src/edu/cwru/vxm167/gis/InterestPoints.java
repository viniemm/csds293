package edu.cwru.vxm167.gis;

import java.util.*;

public class InterestPoints<M> {

	private final BiDimensionalMap<InterestPoint> points;

	private InterestPoints(Builder builder){
		this.points = builder.points;
	}

	public final Collection<InterestPoint> get(Coordinate  coordinate){
		Objects.requireNonNull(coordinate);
		try {
			return points.get(coordinate);
		}
		catch (NullPointerException e){
			System.out.println("The value of either x or y in the coordinate is null");
			return null;
		}
	}

	public final List<Collection<InterestPoint>> interestPoints(){
		List<Coordinate> sortedCoordinates = points.coordinateSet();
		List<Collection<InterestPoint>> result = new ArrayList<>();
		for(Coordinate c:sortedCoordinates){
			result.add(get(c));
		}
		return result;
	}

	public final long count(RectilinearRegion region, M marker){
		long result = 0;
		// TODO : Not sure what RectilinearRegion does here.
		return result;
	}

	@Override
	public String toString() {
		return "InterestPoints{" +
			"points=" + points +
			'}';
	}

	public static class Builder{
		private final BiDimensionalMap<InterestPoint> points = new BiDimensionalMap<>();

		public final boolean add(InterestPoint interestPoint){
			InterestPoint.validate(interestPoint);
			BiDimensionalMap.Updater up = points.getUpdater();
			up.setCoordinate(interestPoint.coordinate());
			up.addValue(interestPoint.marker());
			up.add();
			return true;
		}

		public final InterestPoints  build(){
			return new InterestPoints(this);
		}
	}
}
