package edu.cwru.vxm167.gis;

import java.math.BigDecimal;
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
		Set<BigDecimal> xCoord = new HashSet<>();
		Set<BigDecimal> yCoord = new HashSet<>();
		for(Rectangle rect : rectangles){
			Set<Coordinate> corners = rect.allCorners();
			for(Coordinate cor : corners){
				xCoord.add(cor.x());
				yCoord.add(cor.y());
			}
		}
		BiDimensionalMap<Rectangle> grid = new BiDimensionalMap<>(xCoord, yCoord);
		// TODO : What do I do with slide here?
		BiDimensionalMap<Rectangle>.Updater up = grid.getUpdater();
		return grid;
	}

	private boolean checkBounds(BigDecimal upper, BigDecimal lower, BigDecimal toTest){
		return toTest.compareTo(lower)>=0 && toTest.compareTo(upper)<=0;
	}

	private boolean checkInside(Rectangle box, Coordinate toTest){
		return checkBounds(box.left(), box.right(), toTest.x()) && checkBounds(box.bottom(), box.top(), toTest.y());
	}

	// TODO : Implement this with Coordinate.compareTo()
	public boolean isOverlapping(){
		boolean result = false;
		for(Rectangle rectangle : rectangles){
			for(Rectangle toTest : rectangles){
				if(!Objects.equals(rectangle, toTest)){
					for(Coordinate corner : toTest.allCorners()){
						if(checkInside(rectangle,corner)){
							result = true;
							break;
						}
					}
				}
			}
		}
		return result;
	}

	public static final RectilinearRegion of(Set<Rectangle>  rectangles) {
		Objects.requireNonNull(rectangles, "set of rectangles cannot be null");
		RectilinearRegion rr  = new RectilinearRegion(rectangles);
		if(rr.isOverlapping()){
			throw new IllegalArgumentException("Set of rectangles cannot intercept");
		}
		return rr;
	}
}