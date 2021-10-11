package gallery;

import java.util.*;

/**
 * The type Auction.
 */
public class Auction {
	private final List<Painting> picassos;
	private final List<Painting> dalis;
	private final GalleryMap galleryMap;

	/**
	 * Dalis list.
	 *
	 * @return the list
	 */
	public List<Painting> dalis() {
		return dalis;
	}

	/**
	 * Picassos list.
	 *
	 * @return the list
	 */
	public List<Painting> picassos() {
		return picassos;
	}

	/**
	 * Gallery map gallery map.
	 *
	 * @return the gallery map
	 */
	public GalleryMap galleryMap() {
		return galleryMap;
	}

	/**
	 * Instantiates a new Auction.
	 *
	 * @param picassos the picassos
	 * @param dalis    the dalis
	 */
	public Auction(List<Painting> picassos, List<Painting> dalis) {
		this.picassos = picassos;
		this.dalis = dalis;
		this.galleryMap = new GalleryMap(picassos, dalis);
	}

	/**
	 * Start auction list.
	 *
	 * @return the list
	 */
	public Set<GalleryMap> startAuction() {

		Set galleries = galleryMap.validGallery();
	}

}