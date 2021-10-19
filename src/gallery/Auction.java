package gallery;

import java.util.*;

public class Auction {
	private final SortedMap<Integer, List<Pair>> gallery;
	private final List<List<Pair>> validGalleries;

	public Auction(SortedSet<Painting> picassos, SortedSet<Painting> dalis) {
		GalleryMap galleryMap = new GalleryMap(picassos, dalis);
		gallery = galleryMap.makeGallery();
		validGalleries = allValidGalleries();
	}

	public List<Pair> longestGallery(){
		return validGalleries.get(maxIndex());
	}

	public int maxIndex() {
		int result = 0;
		int i = 0;
		for (List<Pair> l : validGalleries) {
			if (l.size() > result) {
				result = i;
			}
			i++;
		}
		return result;
	}


	private boolean validateOrder(List<Pair> gallery) {
		for (int i = 0; i < gallery.size() - 1; i++) {
			if (gallery.get(i).compareTo(gallery.get(i + 1)) > 0) {
				return false;
			}
		}
		return true;
	}

	private List<List<Pair>> allValidGalleries() {
		List<List<Pair>> result = new ArrayList<>();
		int i = (int) Math.pow(10, gallery.size() - 1);
		while (i >= 0) {
			List<Pair> candidate = new ArrayList<>();
			String stri = String.valueOf(i);
			for (int j = 1; j < gallery.size(); j++) {
				try {
					candidate.add(gallery.get(j).get(stri.charAt(j - 1)));
				} catch (Exception ignored) {
				}
			}
			if (validateOrder(candidate)) {
				result.add(candidate);
			}
			i--;
		}
		return result;
	}
}