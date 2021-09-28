package edu.cwru.vxm167.gis;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public record Rectangle(Coordinate bottomLeft, Coordinate topRight) {

	public final Rectangle validate() {
		Coordinate.validate(bottomLeft);
		Coordinate.validate(topRight);

		int xVal = bottomLeft.x().compareTo(topRight.x());
		int yVal = bottomLeft.y().compareTo(topRight.y());
		if(xVal<=0 && yVal<0){
			return this;
		}
		throw new IllegalArgumentException("Illegal values of bottom left and top right");
	}

	public static final Rectangle validate(Rectangle rectangle) {
		Objects.requireNonNull(rectangle, "Argument is null");
		return rectangle.validate();
	}

	public final BigDecimal left(){
		return bottomLeft.x();
	}

	public final BigDecimal right(){
		return topRight.x();
	}

	public final BigDecimal top(){
		return topRight.y();
	}

	public final BigDecimal bottom(){
		return bottomLeft.y();
	}

	private Coordinate bottomRight() {
		return new Coordinate(right(), bottom());
	}

	private Coordinate topLeft() {
		return new Coordinate(left(), top());
	}

	final Set<Coordinate> allCorners(){
		Set<Coordinate> result = new HashSet<>();
		result.add(bottomLeft());
		result.add(bottomRight());
		result.add(topRight());
		result.add(topLeft());
		return result;
	}

	@Override
	public String toString() {
		String result = "";
		result += "topLeft : "+"("+top().toPlainString()+","+left().toPlainString()+")"
			+"topRight : "+"("+top().toPlainString()+","+right().toPlainString()+")\n";
		result += "bottomLeft : "+"("+bottom().toPlainString()+","+left().toPlainString()+")"
			+"bottomRight : "+"("+bottom().toPlainString()+","+right().toPlainString()+")\n";
		return result;
	}
}