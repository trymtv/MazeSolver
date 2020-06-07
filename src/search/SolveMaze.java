package search;

import java.util.ArrayList;
import java.util.LinkedList;

public class SolveMaze {
	private static ArrayList<Node> path = new ArrayList<>();
	private static LinkedList<Node> queue = new LinkedList<>();
	
	public static ArrayList<Node> solveDepthFirst(Node root) {
		SolveMaze.depthFirst(root);
		return SolveMaze.path;
	}
	
	private static void depthFirst(Node current){
		current.visited();
		if(current.isTarget()) {
			path.add(current);
			return;
		}
		for(Node next:current.getNeighbors()) {
			if(!next.isVisited()) {
				depthFirst(next);
				if(!path.isEmpty()) {
					path.add(current);
					return;
				}
			}
		}
	}


	//TODO-add breadth first search
	/*private static void breadthFirst(Node current) {
		current.visited();
		if(current.isTarget()) {
			path.add(current);
			return;
		}else if(queue.isEmpty()) {
			for(Node n:current.getNeighbors()) {
				if(!n.isVisited())
					queue.add(n);
			}
		}else {
			
		}
		
		
	}*/
	
	
	public static ArrayList<Node> getPath() {
		return path;
	}
}
