package gallery;

import java.util.*;

public record GalleryMap(SortedSet<Painting> picassos, SortedSet<Painting> dalis) {
	private static SortedMap<Painting, List<Painting>> columns;
	private static List<List<Pair>> gallery;

	GalleryMap validate() {
		picassos.forEach(Painting::validate);
		dalis.forEach(Painting::validate);
		return this;
	}

	private void makeColumn() {
		columns = new TreeMap<>();
		picassos.forEach(picasso -> {
			columns.put(picasso, new ArrayList<>());
			dalis.forEach(dali -> {
				if (new Pair(picasso, dali).validPair()) {
					columns.get(picasso).add(dali);
				}
			});
		});
	}


	private List<List<Pair>> makeGallery() {
		makeColumn();
		Set<Set<Painting>> powerSet = powerSet(columns.keySet());
		gallery = new ArrayList<>();
		powerSet.forEach(set -> {
			Set<Painting> existingDalis = new TreeSet<>();
			List<Pair> res = new ArrayList<>();
			set.forEach(picasso -> {
				for (Painting dali : columns.get(picasso)) {
					if (!existingDalis.contains(dali)) {
						res.add(new Pair(picasso, dali));
						existingDalis.add(dali);
						break;
					}
				}
			});
			gallery.add(res);
		});
		return gallery;
	}

	private static Set<Set<Painting>> powerSet(Set<Painting> originalSet) {
		Set<Set<Painting>> sets = new HashSet<>();
		if (originalSet.isEmpty()) {
			sets.add(new TreeSet<>());
			return sets;
		}
		List<Painting> list = new ArrayList<>(originalSet);
		Painting head = list.get(0);
		Set<Painting> rest = new TreeSet<>(list.subList(1, list.size()));
		powerSet(rest).forEach(set -> {
			Set<Painting> newSet = new TreeSet<>();
			newSet.add(head);
			newSet.addAll(set);
			sets.add(newSet);
			sets.add(set);
		});
		return sets;
	}

	public List<Pair> maxGallery(){
		makeGallery();
		int max = 0;
		int val = 0;
		for (int i = 0; i<gallery.size(); i++){
			if(gallery.get(i).size()>max){
				val = i;
			}
		}
		return gallery.get(val);
	}

	public int maxLength(){
		return maxGallery().size();
	}


}