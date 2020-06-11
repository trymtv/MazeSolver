package search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public class SolveMaze {

	private static LinkedList<Node> queue = new LinkedList<>();
	private static List<Node> path = new ArrayList<>();
	
	public static <T extends Node> List<T> solveDepthFirst(T root, Predicate<T> pred) {
		SolveMaze.depthFirst(root, pred);
		return (List<T>)SolveMaze.path;
	}
	
	private static <T extends Node> void depthFirst(T current, Predicate<T> pred){
		current.visit();
		if(pred.test(current)) {
			path.add(current);
			return;
		}
		for(Node next:current.getNeighbors()) {
			if(!next.isVisited()) {
				depthFirst((T) next, pred);
				if(!path.isEmpty()) {
					path.add(current);
					return;
				}
			}
		}
	}


	//TODO-add breadth first search
	/*private static void breadthFirst(PositionNode current) {
		current.visited();
		if(current.isTarget()) {
			path.add(current);
			return;
		}else if(queue.isEmpty()) {
			for(PositionNode n:current.getNeighbors()) {
				if(!n.isVisited())
					queue.add(n);
			}
		}else {
			
		}
		
		
	}*/
	
	
	public static List<Node> getPath() {
		return path;
	}
}
