package gallery;

import java.util.Objects;

public record Column(Painting picasso, Painting dali) implements Comparable<gallery.Column> {
	public final Column validate() {
		Objects.requireNonNull(picasso, "Picasso cannot be null");
		Objects.requireNonNull(dali, "Dali cannot be null");
		if (!(picasso.compareSize(dali) < 0)){
			throw new IllegalArgumentException("The Dali behind has to be taller than the Picasso in front");
		}
		return this;
	}

	public static final Column validate(Column column) {
		Objects.requireNonNull(column, "Column cannot be null");
		return column.validate();
	}

	public boolean equals(Column c) {
		return dali.equals(c.dali()) && picasso.equals(c.picasso());
	}

	@Override
	public int compareTo(Column c) {
		return (dali.comparePrice(c.dali()) + picasso.comparePrice(c.picasso())) / 2;
	}

	@Override
	public String toString(){
		return "P"+picasso.toString()+" : D"+dali.toString();
	}
}