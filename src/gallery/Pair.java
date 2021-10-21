package gallery;

import java.util.Objects;

public record Pair(Painting picasso, Painting dali) implements Comparable<Pair> {
	public final Pair validate() {
		Objects.requireNonNull(picasso, "Picasso cannot be null");
		Objects.requireNonNull(dali, "Dali cannot be null");
		if (!(picasso.compareSize(dali) < 0)){
			throw new IllegalArgumentException("The Dali behind has to be taller than the Picasso in front");
		}
		return this;
	}

	public static final Pair validate(Pair pair) {
		Objects.requireNonNull(pair, "Column cannot be null");
		return pair.validate();
	}

	public boolean equals(Pair c) {
		return dali.equals(c.dali()) && picasso.equals(c.picasso());
	}

	@Override
	public int compareTo(Pair c) {
		return (dali.comparePrice(c.dali()) + picasso.comparePrice(c.picasso())) / 2;
	}

	@Override
	public String toString(){
		return "P"+picasso.toString()+" : D"+dali.toString();
	}
}