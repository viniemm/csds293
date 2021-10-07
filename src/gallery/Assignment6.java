package gallery;

import edu.cwru.vxm167.gis.Coordinate;

import java.util.*;

public class Assignment6 {

	public static void auction(List<Painting> dalis, List<Painting> picassos){
		List<Painting> frontRow = new ArrayList<>(picassos);
		List<Painting> backRow = new ArrayList<>(dalis);
		frontRow.sort(Painting::compareTo);
		backRow.sort(Painting::compareTo);
		HashMap result toMap(frontRow, backRow)
	}

}
