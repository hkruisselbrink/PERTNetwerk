package pertnetwerk;

import java.util.ArrayList;

import genericgraph.GenericGraph;


public class PERTNetwerk extends GenericGraph<int[]> {
	
	private ArrayList<String> startingPoints;
	
	public PERTNetwerk() {
		super();
		startingPoints = new ArrayList<String>();
	}
	
	public void addStartingPoint(String name) {
		addNode(name);
		startingPoints.add(name);
	}
	
	public void addActivity(String from, String to, int weight) {
		if(getNode(to) == null) {
			addNode(to);
			addEdge(from, to, weight);
		} else {
			addEdge(from, to, weight);
		}
	}
	
	public int getActivityTime(String from, String to) {
		return getWeight(from, to);
	}
	
	public void setTimes(String name, int[] a) {
		if(a.length == 2) {
			setData(name, a);
		} else {
			System.err.println("Foute array.length, moet 2 zijn");
		}
	}
	
	public int[] getTimes(String name) {
		return getData(name);
	}
	
	public void vroegsteTijden() {
		ArrayList<String> nodesLeft = new ArrayList<String>();
		ArrayList<String> nodesToAdd = new ArrayList<String>();
		
		//nodesLeft vullen met starting points en data van starting points op [0, 0] zetten
		for(String start : startingPoints) {
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
}
