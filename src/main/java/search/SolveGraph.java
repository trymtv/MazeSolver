package search;

import java.util.*;
import java.util.function.Predicate;

public class SolveGraph {

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
	public static <T extends Node> List<T> solveRecDepthFirst(T root, Predicate<T> targetPred) {
		recDepthFirst(root, targetPred);
		//handled in node connection encapsulation
		//noinspection unchecked
		return (List<T>) SolveGraph.path;
	}
	
	private static <T extends Node> void recDepthFirst(T current, Predicate<T> targetPred){
		current.visit();
		if(targetPred.test(current)) {
			path.add(current);
			return;
		}
		for(Node next:current.getNeighbors()) {
			if(!next.isVisited()) {
				//handled in node connection encapsulation
				//noinspection unchecked
				recDepthFirst((T) next, targetPred);
				if(!path.isEmpty()) {
					path.add(current);
					return;
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static <T extends Node> List<T> stackDepthFirst(T start, Predicate<T> targetPred){
		Stack<T> stack = new Stack<>();
		start.visit();
		stack.push(start);
		while(!stack.isEmpty()){
			T current = stack.pop();
			if (targetPred.test(current)) {
				stack.push(current);
				break;
			}
			if(!current.getNeighbors().isEmpty()){
				stack.push(current);
				T next = (T) current.getNeighbors().remove(0);
				next.visit();
				current.disconnect(next);
				stack.push(next);
			}
		}
		return new ArrayList<>(stack);
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
