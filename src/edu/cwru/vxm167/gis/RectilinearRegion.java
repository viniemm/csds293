package edu.cwru.vxm167.gis;

import java.math.BigDecimal;
import java.util.*;

public final class RectilinearRegion {

	public final Set<Rectangle> rectangles;

	private RectilinearRegion(Set<Rectangle> rectangles) {
		this.rectangles = new HashSet<>(rectangles);
	}

	public Set<Rectangle> getRectangles() {
		return rectangles;
	}

	private BiDimensionalMap<Rectangle> rectangleMap() {
		Set<BigDecimal> xCoord = new HashSet<>();
		Set<BigDecimal> yCoord = new HashSet<>();
		for (Rectangle rect : rectangles) {
			yCoord.add(rect.bottom());
			yCoord.add(rect.top());
			xCoord.add(rect.left());
			xCoord.add(rect.right());
		}
		BiDimensionalMap<Rectangle> grid = new BiDimensionalMap<>(xCoord, yCoord);
		for (Rectangle rect : rectangles) {
			BiDimensionalMap<Rectangle> newGridSlice = grid.slice(rect);
			newGridSlice.addEverywhere(rect);
		}
		return grid;
	}

	private boolean checkInside(Rectangle box, Rectangle toTest) {
		for (Coordinate corner : toTest.allCorners()) {
			if (box.bottomLeft().compareTo(corner) <= 0 && box.topRight().compareTo(corner) >= 0) {
				return true;
			}
		}
		return false;
	}

	public boolean isOverlapping() {
		boolean result = false;
		for (Rectangle rectangle : rectangles) {
			for (Rectangle toTest : rectangles) {
				if (!Objects.equals(rectangle, toTest)) {
					result = checkInside(rectangle, toTest);
				}
			}
		}
		return result;
	}

	public static final RectilinearRegion of(Set<Rectangle> rectangles) {
		Objects.requireNonNull(rectangles, "set of rectangles cannot be null");
		RectilinearRegion rr = new RectilinearRegion(rectangles);
		if (rr.isOverlapping()) {
			throw new IllegalArgumentException("Set of rectangles cannot intercept");
		}
		return rr;
	}
}