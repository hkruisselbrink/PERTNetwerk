package genericgraph;

public class Edge<T> {

	private Node<T> target;
	private int weight;
	
	protected Edge(Node<T> target, int weight) {
		this.target = target;
		this.weight = weight;
	}
	
	protected Node<T> target() {
		return target;
	}
	
	protected int weight() {
		return weight;
	}
	
	protected void setWeight(int weight) {
		this.weight = weight;
	}
}
