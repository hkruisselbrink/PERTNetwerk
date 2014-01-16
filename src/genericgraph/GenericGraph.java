package genericgraph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GenericGraph<T>{

	private Map<String, Node<T>> nodes;
	
	public GenericGraph() {
		nodes = new HashMap<String, Node<T>>();
	}
	
	protected void addNode(String name){
		if(!nodes.containsKey(name)) {
			nodes.put(name, new Node<T>(name));
		} else {
			throw new RuntimeException("Node \"" + name + "\" allready exists");
		}
	}
	
	protected void addEdge(String from, String to, int weight) {
		if(nodes.containsKey(from) && nodes.containsKey(to) && nodes.get(from).getEdge(nodes.get(to)) == null) {
			Edge<T> edge = new Edge<T>(nodes.get(to), weight);
			nodes.get(from).addEdge(edge);
		} else {
			if(!nodes.containsKey(from)) {
				throw new RuntimeException("Node \"" + from + "\" does not exist");
			} else if (!nodes.containsKey(to)) {
				throw new RuntimeException("Node \"" + to + "\" does not exist");
			}
			if(nodes.get(from).getEdge(nodes.get(to)) != null) {
				nodes.get(from).getEdge(nodes.get(to)).setWeight(weight);
			}
		}
	}
	
	protected void setData(String key, T t) {
		if(nodes.containsKey(key)) {
			nodes.get(key).setData(t);
		} else {
			throw new RuntimeException("Node \"" + key + "\" does not exist");
		}
	}
	
	protected T getData(String key) {
		if(nodes.containsKey(key)) {
			return nodes.get(key).getData();
		} else {
			throw new RuntimeException("Node \"" + key + "\" does not exist");
		}	
	}
	
	protected Set<String> getChildren(String key) {
		if(nodes.containsKey(key)) {
			Set<String> children = new HashSet<String>();
			for(Node<T> node : nodes.get(key).getChildren()) {
				children.add(node.getName());
			}
			return children;
		} else {
			throw new RuntimeException("Node \"" + key + "\" does not exist");
		}	
	}
	
	protected Set<String> getParents(String key) {
		if(nodes.containsKey(key)) {
			Set<String> parents = new HashSet<String>();
			for(Node<T> node : nodes.get(key).getParents()) {
				parents.add(node.getName());
			}
			return parents;
		} else {
			throw new RuntimeException("Node \"" + key + "\" does not exist");
		}	
	}
	
	protected boolean areConnected(String from, String to) {
		if(nodes.containsKey(from) && nodes.containsKey(to)) {
			return nodes.get(from).isConnectedTo(nodes.get(to));
		} else {
			if(!nodes.containsKey(from)) {
				throw new RuntimeException("Node \"" + from + "\" does not exist");
			}
			throw new RuntimeException("Node \"" + to + "\" does not exist");
		}
	}
	
	protected int getWeight(String from, String to) {
		if(nodes.containsKey(from) && nodes.containsKey(to)) {
			return nodes.get(from).getEdge(nodes.get(to)).weight();
		} else {
			if(!nodes.containsKey(from)) {
				throw new RuntimeException("Node \"" + from + "\" does not exist");
			}
			throw new RuntimeException("Node \"" + to + "\" does not exist");
		}
	}
	
	protected int size() {
		return nodes.size();
	}
	
	protected Node<T> getNode(String key) {
		return nodes.get(key);
	}
}
