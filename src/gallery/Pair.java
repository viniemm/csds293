package gallery;

import java.math.BigDecimal;
import java.util.Objects;

public record Pair(Painting picasso, Painting dali) implements Comparable<Pair> {
	public final Pair validate() {
		picasso.validate();
		dali.validate();
		if (!(picasso.compareSize(dali) < 0)){
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
		int result = 0;
		Painting picasso = pair.picasso();
		Painting dali = pair.dali();
		double coef = Integer.sum(this.dali.comparePrice(dali),this.picasso.comparePrice(picasso))/2;

		int comp =
		if(equals(pair)){
			throw new IllegalArgumentException("Pair repetition");
		}


	}

	@Override
	public String toString(){
		return "P"+picasso.toString()+" : D"+dali.toString();
	}
}