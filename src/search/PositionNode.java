package search;

import java.util.ArrayList;

public class PositionNode extends Node{
	private boolean target, root;
	private int x, y;


	public void setRoot() {
		this.root = true;
	}

	public PositionNode(int x, int y) {
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
	public void setTarget() {
		target = true;
	}
	public boolean isTarget() {
		return target;
	}

	@Override
	public String toString() {
		return "["+ x + ", " + y + "]";
	}
}
