package gallery;

import java.util.*;

public class Auction{
	GalleryMap gallerymap;
	SortedSet<Column> columns= new TreeSet<>();

	public Auction(SortedSet<Painting> picassos, SortedSet<Painting> dalis) {
		gallerymap = new GalleryMap(picassos,dalis);
		columns = gallerymap.columns();
	}

	private boolean validateGallery(List<Column> gallery){
		for(Column column : columns){

		}
	}

}