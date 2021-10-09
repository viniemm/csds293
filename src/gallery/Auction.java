package gallery;

import java.util.*;

public class Auction {
	private final List<Painting> picassos;
	private final List<Painting> dalis;
	private final GalleryMap galleryMap;

	public List<Painting> dalis() {
		return dalis;
	}

	public List<Painting> picassos() {
		return picassos;
	}

	public GalleryMap galleryMap() {
		return galleryMap;
	}

	public Auction(List<Painting> picassos, List<Painting> dalis) {
		this.picassos = picassos;
		this.dalis = dalis;
		this.galleryMap = new GalleryMap(picassos, dalis);
	}

	public List<GalleryMap> startAuction() {
		int max = 0;
		List<GalleryMap> result = new ArrayList<>();
		for (Painting picasso : picassos) {
		}
		return result;
	}

}