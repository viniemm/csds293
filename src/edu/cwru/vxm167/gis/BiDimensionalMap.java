
package edu.cwru.vxm167.gis;

import java.util.*;
import java.math.*;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author Vinayak Mathur vxm167
 * @param <T> generic object to be stored in the collection.
 */
public final class BiDimensionalMap<T> {

	private final SortedMap<BigDecimal, SortedMap<BigDecimal, Collection<T>>> points = new TreeMap<>();

	BiDimensionalMap(Collection<BigDecimal> xCoord, Collection<BigDecimal> yCoord) {
		if (Objects.isNull(xCoord) || Objects.isNull(yCoord)) {
			throw new NullPointerException("xCoord or yCoord cannot be null");
		}
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
		if (Objects.isNull(x) || Objects.isNull(y)) {
			throw new NullPointerException("Argument cannot be null");
		} else {
			return points.get(x).get(y);
		}
	}

	public final Collection<T> get(Coordinate coordinate) {
		if (Objects.isNull(coordinate)) {
			throw new NullPointerException("Coordinate cannot be null");
		} else {
			return get(coordinate.x(), coordinate.y());
		}
	}

	public final Set<BigDecimal> xSet() {
		return points.keySet();
	}

	public final Set<BigDecimal> ySet(BigDecimal x) {
		if (Objects.isNull(x) || !points.containsKey(x)) {
			throw new IllegalArgumentException("x is either null or is not in the map");
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
		Collections.sort(result, Coordinate::compareTo);
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
		if (Objects.isNull(filter)) {
			throw new NullPointerException("filter cannot be null");
		}
		Collection<T> fullC = fullCollection();
		return fullC.stream().filter(filter).count();
	}

	public final Updater getUpdater() {
		return new Updater();
	}

	public final BiDimensionalMap<T> slice(Rectangle rectangle) {
		if (Objects.isNull(rectangle)) {
			throw new NullPointerException("rectangle cannot be null");
		}
		BiDimensionalMap<T> result = new BiDimensionalMap<>();
		BiDimensionalMap.Updater up = result.getUpdater();
		for (BigDecimal x : xSet()) {
			if (x.compareTo(rectangle.left()) >= 0 &&
				x.compareTo(rectangle.right()) < 0) {
				for (BigDecimal y : ySet(x)) {
					if (y.compareTo(rectangle.bottom()) >= 0 &&
						x.compareTo(rectangle.top()) < 0) {
						up.setCoordinate(new Coordinate(x, y)).setValues(points.get(x).get(y)).add();
					}
				}
			}
		}
		return result;
	}

	public final void addEverywhere(T value) {
		if (Objects.isNull(value)) {
			throw new NullPointerException("value cannot be null");
		}
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

		private Supplier<Collection<T>> collectionFactory = HashSet::new;

		private Collection<T> values = collectionFactory.get();

		public final Updater setX(BigDecimal x) {
			if (Objects.isNull(x)) {
				throw new NullPointerException("x cannot be null");
			}
			this.x = x;
			return this;
		}

		public final Updater setY(BigDecimal y) {
			if (Objects.isNull(y)) {
				throw new NullPointerException("y cannot be null");
			}
			this.y = y;
			return this;
		}

		// TODO Setter for collectionFactory and values???

		public final Updater setCoordinate(Coordinate coordinate) {
			if (Objects.isNull(coordinate)) {
				throw new NullPointerException("Coordinate cannot be null");
			}
			coordinate.validate();
			x = coordinate.x();
			y = coordinate.y();
			return this;
		}


		public final Updater addValue(T value) {
			if (Objects.isNull(value)) {
				throw new NullPointerException("value cannot be null");
			}
			values.add(value);
			return this;
		}

		public final Updater setValues(Collection<T> values) {
			if (Objects.isNull(values)) {
				throw new NullPointerException("new values cannot be null");
			}
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
