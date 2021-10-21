package gallery;

import java.util.Objects;

public record Pair(Painting picasso, Painting dali) implements Comparable<Pair> {
	public final Pair validate() {
		picasso.validate();
		dali.validate();
		if (!(picasso.compareSize(dali) < 0)) {
			throw new IllegalArgumentException("The Dali behind has to be taller than the Picasso in front");
		}
		return this;
	}

	public static Pair validate(Pair pair) {
		Objects.requireNonNull(pair, "Pair cannot be null");
		return pair.validate();
	}

	public boolean equals(Pair c) {
		return dali.equals(c.dali()) && picasso.equals(c.picasso());
	}

	@Override
	public int compareTo(Pair pair) {
		validate(pair);
		if (equals(pair)) {
			throw new IllegalArgumentException("Pair repetition");
		}
		int daliCoef = this.dali.comparePrice(pair.dali());
		int picassoCoef = this.picasso.comparePrice(pair.picasso());
		if (daliCoef * picassoCoef == -1) {
			throw new IllegalArgumentException("Ordered incorrect");
		}
		return (daliCoef + picassoCoef) / Math.abs(daliCoef + picassoCoef);
	}

	@Override
	public String toString() {
		return "P" + picasso.toString() + " : D" + dali.toString();
	}
}