package search;

import java.util.ArrayList;

public class Node {
	private boolean root, target, visited;
	private int x, y;
	private ArrayList<Node> neighbors = new ArrayList<>();
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int[] getCoordinate() {
		return new int[] {x, y};
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public ArrayList<Node> getNeighbors(){
		return this.neighbors;
	}
	public void setRoot() {
		root = true;
	}
	public void setTarget() {
		target = true;
	}
	public void visited() {
		visited = true;
	}
	public boolean isVisited(){
		return visited;
	}
	public void connect(Node node) {
		this.neighbors.add(node);
		node.neighbors.add(this);
	}
	public boolean isTarget() {
		return target;
	}
	
	@Override
	public String toString() {
		return "["+ x + ", " + y + "]";
	}
}
