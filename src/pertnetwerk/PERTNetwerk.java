package pertnetwerk;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import genericgraph.GenericGraph;


public class PERTNetwerk extends GenericGraph<int[]> {
	
	private Set<String> startPoints;
	private Set<String> endPoints;
	
	public PERTNetwerk() {
		super();
		startPoints = new HashSet<String>();
		endPoints = new HashSet<String>();
	}
	
	public void addStartingPoint(String name) {
		addNode(name);
		startPoints.add(name);
	}
	
	public void addActivity(String from, String to, int weight) {
		if(!contains(to) && weight > 0) {
			addNode(to);
			addEdge(from, to, weight);
			if(endPoints.contains(from)) {
				endPoints.remove(from);
			}
			if(!endPoints.contains(to)) {
				endPoints.add(to);
			}
		} else {
			if(weight <= 0) {
				System.err.println("PERTNetwerk addActivity(): weight moet hoger dan 0 zijn");
				return;
			}
			if(!this.areConnected(to, from)) {			//Checken of er niet een loop ontstaat
				addEdge(from, to, weight);
				if(endPoints.contains(from)) {
					endPoints.remove(from);
				}
			} else {
				System.err.println("PERTNetwerk addActivity(): loops niet toegestaan");
				return;
			}
		}
	}
	
	
	
	public void vroegsteTijden() {
		Set<String> nodesLeft = new HashSet<String>();
		Set<String> nodesToAdd = new HashSet<String>();
		
		//nodesLeft vullen met starting points en data van starting points op [0, 0] zetten
		for(String start : startPoints) {
			setData(start, new int[]{0, 0});
			nodesLeft.add(start);
		}
		
		while(nodesLeft.size() > 0) {
			
			for(String node : nodesLeft) {
				
				for(String child : getChildren(node)) {
					nodesToAdd.add(child);
					int[] data = getData(child);
					int vroegsteTijd = getData(node)[0] + getWeight(node, child);
					if(data != null) {
						if(data[0] < vroegsteTijd) {
							data[0] = vroegsteTijd;
						}
					} else {
						setData(child, new int[]{vroegsteTijd, 0});
					}
				}
			}
			nodesLeft.clear();
			for(String s : nodesToAdd) {
				nodesLeft.add(s);
			}
			nodesToAdd.clear();
		}
	}
	
	
	
	public void laatsteTijden() {
		Set<String> nodesLeft = new HashSet<String>();
		Set<String> nodesToAdd = new HashSet<String>();
		
		//Startingpoints data[1] vast instellen naar Integer.MAX_VALUE
		for(String start : startPoints) {
			getData(start)[1] = Integer.MAX_VALUE;
		}
		
		//nodesLeft vullen met endPoints en vroegste- en laatsteTijd gelijk maken
		for(String end : endPoints) {
			nodesLeft.add(end);
			int[] data = getData(end);
			if(data != null) {
				data[1] = data[0];
			} else {
				System.err.println("PERTNetwerk laatsteTijden(): Eerst vroegste tijden uitrekenen");
				return;
			}
		}
		
		while(nodesLeft.size() > 0) {
		
			for(String node : nodesLeft) {
		
				for(String parent : getParents(node)) {
					nodesToAdd.add(parent);
					int[] data = getData(parent);
					int laatsteTijd = getData(node)[1] - getWeight(parent, node);
					if(data[1] == 0 && !startPoints.contains(parent)) {
						data[1] = Integer.MAX_VALUE;
					}
					if(data[1] > laatsteTijd) {
						data[1] = laatsteTijd;
					}
				}
			}
			nodesLeft.clear();
			for(String s : nodesToAdd) {
				nodesLeft.add(s);
			}
			nodesToAdd.clear();
		}
	}
	
	public String getTimes(String name) {
		return Arrays.toString(getData(name));
	}
}
