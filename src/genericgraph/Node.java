package genericgraph;

import java.util.HashSet;
import java.util.Set;

public class Node<T> {

	private String name;
	private T t;
	
	private Set<Edge<T>> edges;
	private Set<Node<T>> parents;
	
	/**
	 * Constructor voor een Node
	 * @param name de naam
	 */
	protected Node(String name) {
		edges = new HashSet<Edge<T>>();
		parents = new HashSet<Node<T>>();
		this.name = name;
	}
	
	/**
	 * Geeft de naam van deze Node terug
	 * @return de naam
	 */
	protected String getName() {
		return name;
	}
	
	/**
	 * Zet het data-object van Type T in de data van deze Node
	 * 
	 * @param t het data-object van Type T
	 */
	protected void setData(T t) {
		this.t = t;
	}
	
	/**
	 * Geeft het data-object van Type T uit de data van deze Node terug
	 * 
	 * @return het data-object van Type T
	 */
	protected T getData() {
		return t;
	}
	
	/**
	 * Voegt de meegegeven Edge toe aan de edges-Set van deze Node. 
	 * Voegt zichzelf ook toe aan de parents van de target van de meegegeven Edge.
	 * 
	 * @param edge de toe te voegen Edge
	 */
	protected void addEdge(Edge<T> edge) {
		edge.target().addParent(this);
		edges.add(edge);
	}
	
	/**
	 * Voegt de meegegeven Node toe aan de parents-Set van deze Node
	 * 
	 * @param parent de toe te voegen Node
	 */
	private void addParent(Node<T> parent) {
		parents.add(parent);
	}
	
	/**
	 * Geeft de parents-Set van deze Node terug
	 * 
	 * @return de parents-Set
	 */
	protected Set<Node<T>> getParents() {
		return parents;
	}
	
	/**
	 * Geeft een Edge terug uit de edges-Set als Edge.target() gelijk is aan de meegegeven Node. 
	 * Meer plat, geeft de edge die zich "tussen" deze Node en de meegegeven Node bevindt 
	 * 
	 * @param node de Node om te zoeken in de Set van Edges in deze Node
	 * @return voor iedere edge uit de edges-Set van deze Node: als edge.target().equals(node), dan return edge; anders return null
	 */
	protected Edge<T> getEdge(Node<T> node) {
		Edge<T> edge = null;
		for(Edge<T> e : edges) {
			if(e.target().equals(node)) {
				edge = e;
			}
		}
		return edge;
	}
	
	/**
	 * Haalt de Edge op die de meegegeven Node bevat en geeft hier de Edge.weight() van terug
	 * 
	 * @param node de Node om te zoeken in de Set van Edges in deze Node
	 * @return de weight-variabele van de Edge wiens Edge.target().equals(node)
	 */
	protected int getWeight(Node<T> node) {
		return getEdge(node).weight();
	}
	
	/**
	 * Slaat elke Edge.target() van iedere Edge uit de edges-Set van deze Node op in een nieuwe Node-Set en geeft deze terug
	 * 
	 * @return een Set bestaande uit iedere Edge.target() uit de edges-Set van deze Node
	 */
	protected Set<Node<T>> getChildren() {
		Set<Node<T>> nodes = new HashSet<Node<T>>();
		for(Edge<T> edge : edges) {
			nodes.add(edge.target());
		}
		return nodes;
	}
	
	/**
	 * Een recursieve methode die berekent of deze Node "gekoppeld" is aan de meegegeven Node. 
	 * Gekoppeld wil zeggen: wanneer de meegegeven Node zich bevindt in de edges-Set van deze Node. De methode geeft dan true terug.
	 * Als de meegegeven Node niet voorkomt in deze edges-Set maar de Set.size() > 0, dan wordt child.isConnectedTo(node) aangeroepen op iedere child van deze Node.
	 * Als de meegegeven Node helemaal niet gevonden wordt, dan wordt false terug gegeven.
	 * 
	 * @param node de op te zoeken node
	 * @return als deze Node "gekoppeld" is aan de meegegeven Node, dan true; anders false
	 */
	protected boolean isConnectedTo(Node<T> node) {
		boolean isConnected = false;
		if(getEdge(node) != null) {
			isConnected = true;
		} else {
			for(Node<T> n : getChildren()) {
				if(n.isConnectedTo(node)) {
					isConnected = true;
				}
			}
		}
		return isConnected;
	}
}
