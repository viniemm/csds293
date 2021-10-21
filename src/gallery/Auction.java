package gallery;

import java.util.*;

public class Auction {
	private final SortedMap<Integer, List<Pair>> gallery;
	private final List<List<Pair>> validGalleries;

	public Auction(SortedSet<Painting> picassos, SortedSet<Painting> dalis) {
		Objects.requireNonNull(picassos, "The set of Picassos cannot be null");
		Objects.requireNonNull(dalis, "The set of Dalis cannot be null");
		GalleryMap galleryMap = new GalleryMap(picassos, dalis);
		gallery = galleryMap.makeGallery();
		validGalleries = allValidGalleries();
	}

	public List<Pair> longestGallery() {
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

	private List<Pair> giveCombination(int i) {
		List<Pair> candidate = new ArrayList<>();
		String stri = String.valueOf(i);
		for (int j = stri.length() - 1; j >= 0; j--) {
			try {
				if (!Objects.isNull(gallery.get(j))) {
					candidate.add(gallery.get(j).get(stri.charAt(j)));
				}
			} catch (Exception ignored) {
			}
		}
		return candidate;
	}

	private List<List<Pair>> allValidGalleries() {
		Objects.requireNonNull(gallery, "No valid galleries");
		List<List<Pair>> result = new ArrayList<>();
		int i = (int) Math.pow(10, gallery.size() - 1);
		while (i >= 0) {
			List<Pair> candidate = giveCombination(i);
			if (validateOrder(candidate)) {
				result.add(candidate);
			}
			i--;
		}
		return result;
	}

	public List<List<Pair>> validGalleries() {
		return validGalleries;
	}

	public List<Pair> maxGallery() {
		int max = 0;
		List<Pair> res = new ArrayList<>();
		for (List<Pair> gall : validGalleries) {
			if (gall.size() > max) {
				max = gall.size();
				res = gall;
			}
		}
		return res;
	}
}