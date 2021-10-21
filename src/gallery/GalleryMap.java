package gallery;

import java.util.*;

public record GalleryMap(SortedSet<Painting> picassos, SortedSet<Painting> dalis) {
	private static SortedMap<Integer, List<Pair>> gallery;

	private void makeColumn() {
		int i = 1;
		for (Painting picasso : picassos) {
			ArrayList<Pair> column = new ArrayList<>();
			column.add(null);
			for (Painting dali : dalis) {
				try {
					column.add(new Pair(picasso, dali));
				} catch (IllegalArgumentException ignored) {
				}
			}
			gallery.put(i++, column);
		}
	}

	public SortedMap<Integer, List<Pair>> makeGallery() {
		for (Painting picasso : picassos) {
			picasso.validate();
		}
		for (Painting dali : dalis) {
			dali.validate();
		}

		makeColumn();
		return gallery;
	}
}