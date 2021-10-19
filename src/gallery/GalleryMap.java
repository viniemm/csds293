package gallery;

import java.util.*;

public record GalleryMap(SortedSet<Painting> picassos, SortedSet<Painting> dalis) {
	private static SortedMap<Integer, List<Pair>> gallery;
	public SortedMap<Integer, List<Pair>> makeGallery() {
		int i = 1;
		for (Painting picasso : picassos) {
			ArrayList<Pair> column = new ArrayList<>();
			column.add(null);
			for (Painting dali : dalis) {
				if (picasso.size().compareTo(dali.size()) < 0) {
					column.add(new Pair(picasso, dali));
				}
			}
			gallery.put(i++,column);
		}
		return gallery;
	}
}