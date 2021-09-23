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
		BiDimensionalMap<Rectangle>.Updater up = grid.getUpdater();
		//for();
		// TODO : I have no idea what this does. Documentation is vague on this. Discussion board slightly better.
		return grid;
	}

	public boolean isOverlapping(){
		boolean result = false;
		// TODO : I have no idea what this does.
		return result;
	}

	public static final RectilinearRegion of(Set<Rectangle>  rectangles) {
		if(Objects.isNull(rectangles)) {
			throw new IllegalArgumentException("Set of rectangles cannot be null");
		}
		RectilinearRegion rr  = new RectilinearRegion(rectangles);
		if(!rr.isOverlapping()){

		}
		return rr;
	}
}
