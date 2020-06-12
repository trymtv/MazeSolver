package search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public class SolveMaze {

	private static LinkedList<Node> queue = new LinkedList<>();
	private static List<Node> path = new ArrayList<>();


	/**
	 * Solve the graph from the root given, assuming all Nodes in the graph
	 * are of the same type.
	 *
	 * @param root - the given root of the graph
	 * @param targetPred - the given predicate for finding the target node
	 *
	 * @return {@code List<Node>} of the path from target to root, in that order
	 */
	public static <T extends Node> List<T> solveDepthFirst(T root, Predicate<T> targetPred) {
		SolveMaze.depthFirst(root, targetPred);
		return (List<T>)SolveMaze.path;
	}
	
	private static <T extends Node> void depthFirst(T current, Predicate<T> targetPred){
		current.visit();
		if(targetPred.test(current)) {
			path.add(current);
			return;
		}
		for(Node next:current.getNeighbors()) {
			if(!next.isVisited()) {
				depthFirst((T) next, targetPred);
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

	/**
	 * debugging method
	 * @return solved path
	 */
	public static List<Node> getPath() {
		return path;
	}
}
