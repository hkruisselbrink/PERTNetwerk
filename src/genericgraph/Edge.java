package genericgraph;

public class Edge<T> {

	private Node<T> target;
	private int weight;
	
	/**
	 * Constructor voor een Edge
	 * @param target Node waar edge naar wijst
	 * @param weight weight van deze Edge
	 */
	protected Edge(Node<T> target, int weight) {
		this.target = target;
		this.weight = weight;
	}
	
	/**
	 * Geeft de target-Node van deze Edge terug
	 * @return de target-Node
	 */
	protected Node<T> target() {
		return target;
	}
	
	/**
	 * Geeft de weight van deze Edge terug
	 * @return de weight
	 */
	protected int weight() {
		return weight;
	}
	
	/**
	 * Stelt de weight van deze Edge in
	 * @param weight de in te stellen weight
	 */
	protected void setWeight(int weight) {
		this.weight = weight;
	}
}
