package gallery;

import java.util.*;

public record GalleryMap(SortedSet<Painting> picassos, SortedSet<Painting> dalis) {
	public SortedSet<Column> columns() {
		SortedSet<Column> allColumns = new TreeSet<>();
		for (Painting picasso : picassos) {
			for (Painting dali : dalis) {
				if (picasso.size().compareTo(dali.size()) < 0) {
					allColumns.add(new Column(picasso, dali));
				}
			}
		}
		return allColumns;
	}
}