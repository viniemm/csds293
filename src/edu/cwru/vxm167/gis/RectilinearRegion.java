package edu.cwru.vxm167.gis;

import java.util.*;

public final class RectilinearRegion {

	public final Set<Rectangle> rectangles;

	private RectilinearRegion(Set<Rectangle> rectangles) {
		this.rectangles = new HashSet<>(rectangles);
	}

	public Set<Rectangle> getRectangles(){
		return rectangles;
	}

	private BiDimensionalMap<Rectangle> rectangleMap(){
		BiDimensionalMap<Rectangle> grid = new BiDimensionalMap<>();
		return grid;
	}
}
