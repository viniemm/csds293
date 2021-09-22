
package edu.cwru.vxm167.gis;

import java.util.*;
import java.math.*;
import java.util.function.Predicate;
import java.util.function.Supplier;

public final class BiDimensionalMap<T> {

	private final SortedMap<BigDecimal, SortedMap<BigDecimal, Collection<T>>> points = new TreeMap<>();

	public final Collection<T> get(BigDecimal x, BigDecimal y) {
		if (Objects.isNull(x) || Objects.isNull(y)) {
			throw new NullPointerException("Argument cannot be null");
		} else {
			return points.get(x).get(y);
		}
	}

	public final Collection<T> get(Coordinate coordinate) {
		if (Objects.isNull(coordinate)) {
			throw new NullPointerException("Argument cannot be null");
		}
		else {
			return get(coordinate.x(), coordinate.y());
		}
	}

	public final Set<BigDecimal> xSet(){
		return points.keySet();
	}

	public final Set<BigDecimal> ySet(BigDecimal x){
		return points.get(x).keySet();
	}

	public final List<Coordinate> coordinateSet(){
		List<Coordinate> result= new ArrayList<>();
		Set<BigDecimal> xKeys = xSet();
		for(BigDecimal xKey:xKeys){
			Set<BigDecimal> yKeys = ySet(xKey);
			for(BigDecimal yKey:yKeys) {
				Coordinate c = new Coordinate(xKey, yKey);
				result.add(c);
			}
		}
		Collections.sort(result, Coordinate::compareTo);
		return result;
	}

	@Override
	public String toString(){
		StringBuilder result = new StringBuilder();
		Set<BigDecimal>xSet = this.xSet();
		for(BigDecimal x:xSet){
			Set<BigDecimal> ySet = this.ySet(x);
			for(BigDecimal y:ySet){
				result.append("(").append(x.toPlainString()).append(",").append(y.toPlainString()).append("):").append(points.get(x).get(y).toString()).append("\n");
			}
		}
		return result.toString();
	}

	// Helper method that returns the entire collection
	private Collection<T> fullCollection(){
		Collection<T> result = new ArrayList<>();
		Set<BigDecimal> xKeys = xSet();
		for(BigDecimal xKey:xKeys){
			Set<BigDecimal> yKeys = ySet(xKey);
			for(BigDecimal yKey:yKeys) {
				result.addAll(points.get(xKey).get(yKey));
			}
		}
		return result;
	}

	public  final  long  collectionSize(){
//		long result = 0;
//		Set<BigDecimal> xKeys = xSet();
//		for(BigDecimal xKey:xKeys){
//			Set<BigDecimal> yKeys = ySet(xKey);
//			for(BigDecimal yKey:yKeys) {
//				result+=points.get(xKey).get(yKey).size();
//			}
//		}
		Collection<T> fullC = fullCollection();
		return fullC.size();
	}

	public final long collectionSize(Predicate<? super T> filter){
		Collection<T> fullC = fullCollection();
		return fullC.stream().filter(filter).count();
	}

	public final BiDimensionalMap<T> slice(Rectangle rectangle) throws CloneNotSupportedException {
		BiDimensionalMap<T> result = (BiDimensionalMap<T>)super.clone();
		// TODO remove the points that are not in the rectangle
		return result;
	}

	public final class Updater {

		private BigDecimal x = new BigDecimal("0.0");
		private BigDecimal y = new BigDecimal("0.0");

		private Supplier<Collection<T>> collectionFactory = HashSet::new;

		private Collection<T> values = collectionFactory.get();

		public final Updater setX(BigDecimal x) {
			this.x = x;
			return this;
		}

		public final Updater setY(BigDecimal y) {
			this.y = y;
			return this;
		}

		// TODO Setter for collectionFactory and values???

		public final Updater setCoordinate(Coordinate coordinate) {
			coordinate.validate();
			x = coordinate.x();
			y = coordinate.y();
			return this;
			// TODO Will this return the outer class BiDimensionalMap or nested class Updater???
		}


		public final Updater addValue(T value) {
			values.add(value);
			return this;
		}

		public final Collection<T> set() {
			if(points.containsKey(x)){
				SortedMap<BigDecimal, Collection<T>> temp = points.get(x);
				if(temp.containsKey(y)) {
					temp.put(y, values);
					points.put(x, temp);
					return temp.get(y);
				}
			}

			return null;
		}

		public final boolean add() {
			boolean flag = false;
			if(points.containsKey(x)){
				if(points.get(x).containsKey(y)){
					Collection<T> prevValues = points.get(x).get(y);
					points.get(x).get(y).addAll(values);
					Collection<T> newValues = points.get(x).get(y);
					flag = !newValues.equals(prevValues);
				}
				else{
					points.get(x).put(y,collectionFactory.get());
					points.get(x).get(y).addAll(values);
				}
			}
			else{
				SortedMap<BigDecimal,Collection<T>> s = new TreeMap<>();
				Collection<T> newValues = collectionFactory.get();
				newValues.addAll(values);
				s.put(y,newValues);
				points.put(x,s);
			}
			return flag;
		}

		public final Updater getUpdater(){
			return new Updater();
		}
	}
}