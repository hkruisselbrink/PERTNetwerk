package pertnetwerk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import genericgraph.GenericGraph;


public class PERTNetwerk extends GenericGraph<int[]> {
	
	private ArrayList<String> route;
	
	public PERTNetwerk() {
		super();
		route = new ArrayList<String>();
	}
	
	public void addActivity(String from, String to, int weight) {
		if(weight > 0) {
			if(!contains(from) && !contains(to)) {
				if(getNodes().size() == 0) {
					addNode(to);
					addNode(from);
					addEdge(from, to, weight);
				} else {
					System.err.println("PERTNetwerk addActivity(): gescheiden grafen niet toegestaan");
				}
			} else {
				if(!contains(from)) {
					addNode(from);
					addEdge(from, to, weight);
				} else if(!contains(to)) {
					addNode(to);
					addEdge(from, to, weight);
				} else {
					if(!areConnected(to, from)) {
						addEdge(from, to, weight);
					} else {
						System.err.println("PERTNetwerk addActivity(): loops niet toegestaan");
					}
				}
			}
		} else {
			System.err.println("PERTNetwerk addActivity(): weight moet hoger zijn dan 0");
		}
		route.clear();
	}
	
	public void vroegsteTijden() {
		route.clear();
		//Sla alle indegrees van het netwerk op in een map
		Map<String, Integer> indegrees = new HashMap<String, Integer>();
		Set<String> nextNodes = new HashSet<String>();
		
		for(String key : getNodes()) {
			int parentsSize = getParents(key).size();
			if(parentsSize == 0) {
				nextNodes.add(key);
				setData(key, new int[]{0, 0});
			} else {
				indegrees.put(key, parentsSize);
			}
		}
		
		while(nextNodes.size() > 0) {	
			
			for(String key : nextNodes) {
				route.add(0, key);
				for(String child : getChildren(key)) {
					//uitrekenen
					int[] data = getData(child);
					int vroegsteTijd = getData(key)[0] + getWeight(key, child);
					if(data != null) {
						if(data[0] < vroegsteTijd) {
							data[0] = vroegsteTijd;
						}
					} else {
						setData(child, new int[]{vroegsteTijd, 0});
					}
					
					indegrees.put(child, indegrees.get(child)-1);
				}
			}
				
			nextNodes.clear();
			
			for(String key : indegrees.keySet()) {
				if(indegrees.get(key) == 0) {
					nextNodes.add(key);
				}
			}
			
			for(String key : nextNodes) {
				indegrees.remove(key);
			}
		}
	}
	
	public void laatsteTijden() {
		if(route.size() != 0) {
			
			for(String key : route) {
				if(getChildren(key).size() != 0) {
					int[] data = getData(key);
					if(data != null) {
						data[1] = Integer.MAX_VALUE;
					}
				} else {
					int[] data = getData(key);
					if(data != null) {
						data[1] = data[0];
					}
				}
			}
			
			for(String key : route) {
				
				for(String parent : getParents(key)) {
					int[] data = getData(parent);
					int laatsteTijd = getData(key)[1] - getWeight(parent, key);
					if(data[1] > laatsteTijd) {
						data[1] = laatsteTijd;
					}
				}
			}
			
		} else {
			
			System.err.println("Voer eerst vroegste tijden uit.");
			
		}
	}
	
	public String toString() {
		String s = "{";
		for(String key : getNodes()) {
			if(s.length() > 1) {
				s = s + ", ";
			}
			s = s + key + "=" + Arrays.toString(getData(key));
		}
		s = s + "}";
		return s;
	}
}
