package gallery;

import edu.cwru.vxm167.gis.Coordinate;

import java.util.*;

public class Assignment6 {

	private final List<Painting> picassos;
	private final List<Painting> dalis;
	Map<Integer,Map<Integer,Painting>> result;
	private final int len;

	public Assignment6(List<Painting> picassos, List<Painting> dalis){
		this.picassos = picassos;
		this.dalis = dalis;
		len = picassos.size();
		this.result = new HashMap<>();
	}

	public boolean validateMap(Map<Integer,Map<Integer,Painting>> gallery){
		for(int i =0; i<this.len; i++){
			if(gallery.get(0).get(i).size().compareTo(gallery.get(1).get(i).size()) > 0){
				return false;
			}
		}
		return true;
	}

	private final Map<Integer,Map<Integer,Painting>> map(List<Painting> picassos, List<Painting> dalis){
		Map<Integer,Map<Integer,Painting>> result = new HashMap<>();
		int i = 0;
		int j = 0;
		Map<Integer,Painting> frontRow = new HashMap<>();
		Map<Integer,Painting> backRow = new HashMap<>();
		for(Painting picasso : picassos){
			frontRow.put(i++, picasso);
		}
		for(Painting dali : dalis){
			backRow.put(j++, dali);
		}
		result.put(0,frontRow);
		result.put(1,backRow);
		return result;
	}

	@Override
	public String toString(){
		String frontRow = "";
		String backRow = "";
		for(int i = 0; i<len; i++) {
			frontRow += 
		}
	}

	public void auction(){
		List<Painting> frontRow = new ArrayList<>(picassos);
		List<Painting> backRow = new ArrayList<>(dalis);
		frontRow.sort(Painting::compareTo);
		backRow.sort(Painting::compareTo);
		Map<Integer,Map<Integer,Painting>> result = map(frontRow, backRow);
	}


}
