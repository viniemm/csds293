package edu.cwru.vxm167.gis;

import java.util.*;
import java.math.*;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @param <T> generic object to be stored in the collection.
 * @author Vinayak Mathur vxm167
 */
public final class BiDimensionalMap<T> {

	private final SortedMap<BigDecimal, SortedMap<BigDecimal, Collection<T>>> points = new TreeMap<>();

	BiDimensionalMap(Collection<BigDecimal> xCoord, Collection<BigDecimal> yCoord) {
		Objects.requireNonNull(xCoord, "xCoord cannot be null");
		Objects.requireNonNull(yCoord, "yCoord cannot be null");
		Updater up = new Updater();
		for (BigDecimal x : xCoord) {
			for (BigDecimal y : yCoord) {
				up.setCoordinate(new Coordinate(x, y));
				up.add();
			}
		}
	}

	BiDimensionalMap() {
	}

	public final Collection<T> get(BigDecimal x, BigDecimal y) {
		Objects.requireNonNull(x, "value of x cannot be null");
		Objects.requireNonNull(y, "value of y cannot be null");
		return points.get(x).get(y);
	}

	public final Collection<T> get(Coordinate coordinate) {
		Objects.requireNonNull(coordinate, "Coordinate cannot be null");
		return get(coordinate.x(), coordinate.y());
	}

	public final Set<BigDecimal> xSet() {
		return points.keySet();
	}

	public final Set<BigDecimal> ySet(BigDecimal x) {
		Objects.requireNonNull(x, "x cannot be null");
		if (!points.containsKey(x)) {
			throw new IllegalArgumentException("x is not in the map");
		}
		return points.get(x).keySet();
	}

	public final List<Coordinate> coordinateSet() {
		List<Coordinate> result = new ArrayList<>();
		Set<BigDecimal> xKeys = xSet();
		for (BigDecimal xKey : xKeys) {
			Set<BigDecimal> yKeys = ySet(xKey);
			for (BigDecimal yKey : yKeys) {
				Coordinate c = new Coordinate(xKey, yKey);
				result.add(c);
			}
		}
		result.sort(Coordinate::compareTo);
		return result;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		Set<BigDecimal> xSet = this.xSet();
		for (BigDecimal x : xSet) {
			Set<BigDecimal> ySet = this.ySet(x);
			for (BigDecimal y : ySet) {
				result.append("(").append(x.toPlainString()).append(",").append(y.toPlainString()).append("):").append(points.get(x).get(y).toString()).append("\n");
			}
		}
		return result.toString();
	}

	// Helper method that returns the entire collection
	private Collection<T> fullCollection() {
		Collection<T> result = new ArrayList<>();
		Set<BigDecimal> xKeys = xSet();
		for (BigDecimal xKey : xKeys) {
			Set<BigDecimal> yKeys = ySet(xKey);
			for (BigDecimal yKey : yKeys) {
				result.addAll(points.get(xKey).get(yKey));
			}
		}
		return result;
	}

	public final long collectionSize() {
		Collection<T> fullC = fullCollection();
		return fullC.size();
	}

	public final long collectionSize(Predicate<? super T> filter) {
		Objects.requireNonNull(filter, "filter cannot be null");
		Collection<T> fullC = fullCollection();
		return fullC.stream().filter(filter).count();
	}

	public final Updater getUpdater() {
		return new Updater();
	}

	private final void compareInner(BigDecimal x, BigDecimal y, Rectangle rectangle, Updater up) {
		if (y.compareTo(rectangle.bottom()) >= 0 &&
			x.compareTo(rectangle.top()) < 0) {
			up.setCoordinate(new Coordinate(x, y)).setValues(points.get(x).get(y)).add();
		}
	}

	public final BiDimensionalMap<T> slice(Rectangle rectangle) {
		Objects.requireNonNull(rectangle, "rectangle cannot be null");
		BiDimensionalMap<T> result = new BiDimensionalMap<>();
		Updater up = result.getUpdater();
		for (BigDecimal x : xSet()) {
			if (x.compareTo(rectangle.left()) >= 0 && x.compareTo(rectangle.right()) < 0) {
				for (BigDecimal y : ySet(x)) {
					compareInner(x, y, rectangle, up);
				}
			}
		}
		return result;
	}

	public final void addEverywhere(T value) {
		Objects.requireNonNull(value, "value cannot be null");
		Updater up = new Updater();
		up.addValue(value);
		for (BigDecimal x : xSet()) {
			for (BigDecimal y : ySet(x)) {
				up.setCoordinate(new Coordinate(x, y));
				up.add();
			}
		}
	}

	public final class Updater {

		private BigDecimal x = BigDecimal.ZERO;
		private BigDecimal y = BigDecimal.ZERO;

		private final Supplier<Collection<T>> collectionFactory = HashSet::new;

		private Collection<T> values = collectionFactory.get();

		public final Updater setX(BigDecimal x) {
			Objects.requireNonNull(x, "x cannot be null");
			this.x = x;
			return this;
		}

		public final Updater setY(BigDecimal y) {
			Objects.requireNonNull(y, "y cannot be null");
			this.y = y;
			return this;
		}

		public final Updater setCoordinate(Coordinate coordinate) {
			Objects.requireNonNull(coordinate, "Coordinate cannot be null");
			coordinate.validate();
			x = coordinate.x();
			y = coordinate.y();
			return this;
		}


		public final Updater addValue(T value) {
			Objects.requireNonNull(value, "value cannot be null");
			values.add(value);
			return this;
		}

		public final Updater setValues(Collection<T> values) {
			Objects.requireNonNull(values, "new values cannot be null");
			this.values = values;
			return this;
		}

		public final Collection<T> set() {
			if (points.containsKey(x)) {
				SortedMap<BigDecimal, Collection<T>> temp = points.get(x);
				if (temp.containsKey(y)) {
					temp.put(y, values);
					points.put(x, temp);
					return temp.get(y);
				}
			}

			return null;
		}

		public final boolean add() {
			boolean flag = false;
			if (points.containsKey(x)) {
				if (points.get(x).containsKey(y)) {
					Collection<T> prevValues = points.get(x).get(y);
					points.get(x).get(y).addAll(values);
					Collection<T> newValues = points.get(x).get(y);
					flag = !newValues.equals(prevValues);
				} else {
					points.get(x).put(y, collectionFactory.get());
					points.get(x).get(y).addAll(values);
				}
			} else {
				SortedMap<BigDecimal, Collection<T>> s = new TreeMap<>();
				Collection<T> newValues = collectionFactory.get();
				newValues.addAll(values);
				s.put(y, newValues);
				points.put(x, s);
			}
			return flag;
		}
	}
}
