package maze;

import image.ImagePixelArray;
import search.PositionNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MazeGraph {
	private List<PositionNode> graph = new ArrayList<>();
	
	
	public void makeNodes(ImagePixelArray array){
		/*
		 * Storing the former made node that can be considered a neighbor
		 * for a node below.
		 */
		PositionNode[] verticalCandidates = new PositionNode[array.getWidth()];
		//finding the start and end nodes
		//setting first two nodes to start(0) and end(1)
		PositionNode start = null, end = null;
		for(int x=0; x < array.getWidth(); x++) {
			if(array.isWhite(x, 0)) {
				start = new PositionNode(x, 0);
				start.setRoot();
				verticalCandidates[x] = start;
			}
			if(array.isWhite(x, array.getHeight() - 1)) {
				end = new PositionNode(x, array.getHeight()-1);
				end.setTarget();
//				if(graph.size() == 2)
//					break;
			}
		}
		graph.add(start);
		graph.add(end);
		boolean wasBlack = false;
		//Iterates over the whole array of pixels, not considering the border
		for(int y=1; y < array.getHeight()-1; y++) {
			PositionNode horizontalCandidate = null;
			if(y%2 == 0)
				continue;
			for(int x=0; x < array.getWidth()-1; x++) {
				//if the pixel is black there is no potential node
				if(array.isBlack(x, y))
					wasBlack = true;
				/*
				 * If the pixel is white and it was black there is a potential node.
				 * If the next pixel in the x-direction is white the pixel could
				 * be considered a node. The check if either the pixel above or
				 * below is white ensures a junction, not a dead end.
				 * Failing the check means the node is either a path or a dead end.
				 */
				else if(wasBlack) {
					horizontalCandidate = null;
					if(array.isWhite(x+1, y)) {
						if(array.isWhite(x, y-1)||array.isWhite(x, y+1)) {
							graph.add(new PositionNode(x,y));
						}
						if(array.isWhite(x, y-1)) {
							this.connect(graph.get(graph.size()-1), verticalCandidates[x]);
							horizontalCandidate = graph.get(graph.size()-1);
						}
						if(array.isWhite(x, y+1)) {
							verticalCandidates[x] = graph.get(graph.size()-1);
							horizontalCandidate = graph.get(graph.size()-1);
						}else {
							verticalCandidates[x] = null;
						}
					}
					wasBlack = false;
				}else{
					/*
					 * If the pixel is white and the former pixel was
					 * white means the pixel is eligible to be a node.
					 * The check ensures that the pixel is not a path.
					 */
					if(array.isWhite(x, y-1)||array.isWhite(x, y+1)) {
						graph.add(new PositionNode(x,y));
						this.connect(graph.get(graph.size()-1), horizontalCandidate);
						if(array.isWhite(x+1, y)) {
							horizontalCandidate = graph.get(graph.size()-1);
						}
					}
					if(array.isWhite(x, y-1)) {
						this.connect(graph.get(graph.size()-1), verticalCandidates[x]);
					}
					if(array.isWhite(x, y+1)){
						verticalCandidates[x] = graph.get(graph.size()-1);
					}else {
						verticalCandidates[x] = null;
					}
				}
			}
		}
		//Attaching the end node
		end.connect(verticalCandidates[end.getX()]);
	}
	public List<PositionNode> getGraph() {
		return graph;
	}
	
	private void connect(PositionNode from, PositionNode to) {
		if(from != null && to != null) {
			from.connect(to);
		}
	}
	
	@Override
	public String toString() {
		return graph.stream().map(n -> Arrays.toString(n.getCoordinate())).collect(Collectors.joining(","));
	}
}
