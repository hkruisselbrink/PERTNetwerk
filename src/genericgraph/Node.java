package genericgraph;

import java.util.HashSet;
import java.util.Set;

public class Node<T> {

	private T t;
	private String name;
	private Set<Edge<T>> edges;
	private Set<Node<T>> parents;
	
	protected Node(String name) {
		edges = new HashSet<Edge<T>>();
		parents = new HashSet<Node<T>>();
		this.name = name;
	}
	
	protected String getName() {
		return name;
	}
	
	protected void setData(T t) {
		this.t = t;
	}
	
	protected T getData() {
		return t;
	}
	
	protected void addEdge(Edge<T> edge) {
		edge.target().addParent(this);
		edges.add(edge);
	}
	
	private void addParent(Node<T> parent) {
		parents.add(parent);
	}
	
	protected Set<Node<T>> getParents() {
		return parents;
	}
	
	protected Edge<T> getEdge(Node<T> node) {
		Edge<T> edge = null;
		for(Edge<T> e : edges) {
			if(e.target().equals(node)) {
				edge = e;
			}
		}
		return edge;
	}
	
	protected int getWeight(Node<T> node) {
		int weight = -1;
		for(Edge<T> edge : edges) {
			if(edge.target().equals(node)) {
				weight = edge.weight();
			}
		}
		return weight;
	}
	
	protected Set<Node<T>> getChildren() {
		Set<Node<T>> nodes = new HashSet<Node<T>>();
		for(Edge<T> edge : edges) {
			nodes.add(edge.target());
		}
		return nodes;
	}
	
	protected boolean isConnectedTo(Node<T> node) {
		boolean isConnected = false;
		if(getEdge(node) != null) {
			isConnected = true;
		} else {
			for(Edge<T> e : edges) {
				if(e.target().isConnectedTo(node)) {
					isConnected = true;
				}
			}
		}
		return isConnected;
	}
}
