package gallery;

import edu.cwru.vxm167.gis.InterestPoint;

import java.util.*;
import java.util.function.Predicate;

public class GalleryMap {

	private final SortedMap<Painting, SortedSet<Painting>> gallery = new TreeMap<>();
	private final SortedSet<Painting> picassos;
	private final SortedSet<Painting> dalis;

	public SortedMap<Painting, SortedSet<Painting>> gallery() {
		return gallery;
	}

	public SortedSet<Painting> picassos() {
		return picassos;
	}

	public SortedSet<Painting> dalis(Painting picasso) {
		picasso.validate();
		return gallery.get(picasso);
	}

	public GalleryMap(SortedSet<Painting> picassos, SortedSet<Painting> dalis) {
		this.picassos = picassos;
		this.dalis = dalis;
		map(this.picassos, this.dalis);
	}

	public GalleryMap copy() {
		return new GalleryMap(picassos, dalis);
	}

	public int compareTo(GalleryMap galleryMap) {
		int result = 0;
		if (gallery.size() < galleryMap.gallery().size()) {
			result = -1;
		} else if (gallery.size() > galleryMap.gallery().size()) {
			result = 1;
		}
		return result;
	}

	public GalleryMap removeDali(Painting dali) {
		Set<Painting> newDalis = null;
		for (Painting p : picassos) {
			gallery.get(p).remove(dali);
		}
		dalis.remove(dali);
		return this;
	}

	public GalleryMap removePicasso(Painting picasso) {
		gallery.remove(picasso);
		picassos.remove(picasso);
		return this;
	}

	private boolean has(Painting picasso, Painting dali) {
		return gallery.get(picasso).contains(dali);
	}

	private GalleryMap pick(Painting picasso, Painting dali) {
		for (Painting p : picassos) {
			if (has(p, dali)) {
				gallery.get(p).remove(dali);
			}
		}
		// Removing all dalis whose price is less than given dali
		Predicate<Painting> cheaper = i -> (i.price().compareTo(dali.price()) < 0);
		for (Painting p : picassos.tailSet(picasso)) {
			gallery.get(picasso).removeIf(cheaper);
		}

		// Adding dali to given picasso
		SortedSet<Painting> temp = new TreeSet<>();
		temp.add(dali);
		gallery.get(picasso).retainAll(temp);

		// Removing all empty picassos
		for (Painting p : picassos) {
			if (gallery.get(p).isEmpty()) {
				removePicasso(p);
			}
		}
		return this;
	}

	public SortedMap<Painting, Painting> validGallery() {
		SortedMap<Painting, Painting> result = new TreeMap<>();
		int max = 0;
		for (Painting picasso : picassos) {
			
		}
		return result;
	}

	private boolean map(SortedSet<Painting> picassos, SortedSet<Painting> dalis) {
		for (Painting picasso : picassos) {
			SortedSet<Painting> emptyList = new TreeSet<>();
			gallery.put(picasso, emptyList);
			for (Painting dali : dalis) {
				if (picasso.size().compareTo(dali.size()) < 0) {
					gallery.get(picasso).add(dali);
				}
			}
		}
		return true;
	}
}
