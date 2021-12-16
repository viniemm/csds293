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

	private Pair validate(Pair pair) {
		Objects.requireNonNull(pair, "Pair cannot be null");
		return pair.validate();
	}

	public boolean equals(Pair p) {
		return picasso.equals(p.picasso) && dali.equals(p.dali);
	}

	@Override
	public int compareTo(Pair pair) {
		validate(pair);
//		if (equals(pair)) {
//			throw new IllegalArgumentException("Pair repetition");
//		}
		int coef = dali.comparePrice(pair.dali) + picasso.comparePrice(pair.picasso);
		return coef == 0 ? 0 : coef / Math.abs(coef);
	}

	@Override
	public String toString() {
		return "P" + picasso.toString() + " : D" + dali.toString();
	}
}