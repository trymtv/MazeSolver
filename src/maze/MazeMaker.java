package maze;

import image.ImageDrawer;
import search.Node;
import search.PositionNode;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Stack;

public class MazeMaker {

	//draws the a maze on the given BufferedImage
	public static BufferedImage drawMaze(int colNum, int rowNum){
		int width = colNum * 2 + 1;
		int height = rowNum * 2 + 1;
		Stack<PositionNode> stack = new Stack<>();
		PositionNode[][] nodes = new PositionNode[colNum][rowNum];
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) img.getGraphics();
		g.setColor(Color.BLACK);
		g.fill(new Rectangle(0, 0, width, height));
		ImageDrawer drawer = new ImageDrawer(img);
		//generating nodes
		for (int i = 0; i < nodes.length; i++) {
			for (int j = 0; j < nodes[0].length; j++) {
				nodes[i][j] = new PositionNode(i*2 + 1, j*2 + 1);
				if(i > 0) {
					nodes[i][j].connect(nodes[i-1][j]);
				}
				if(j > 0) {
					nodes[i][j].connect(nodes[i][j-1]);
				}
			}
		}
		//add initial node
		PositionNode init = nodes[(int) (Math.random()*colNum)][(int) (Math.random()*rowNum)];
		init.visit();
		stack.push(init);

//		depth first implementation of maze generation
		while(!stack.isEmpty()){
			PositionNode current = stack.pop();
			List<Node> candidates = current.getNeighbors();
			while (!current.getNeighbors().isEmpty()){
				//gets random neighbor
				PositionNode next = (PositionNode) candidates.remove((int) (Math.random()*candidates.size()));
				//checks if there are an unvisited neighbor
				if(!next.isVisited()) {
					//steps the search one level down and draws a path
					stack.push(current);
					stack.push(next);
					next.visit();
					current.disconnect(next);
					drawer.drawLine(current.getCoordinate(), next.getCoordinate(), Color.WHITE.getRGB());
					break;
				}else{
					//if a node is visited it gets removed as a potential path
					current.disconnect(next);
				}
			}
		}

		//get start and end point
		int[] start = nodes[(int) (Math.random()*nodes.length)][0].getCoordinate();
		int[] end = nodes[(int) (Math.random()*nodes.length)][rowNum-1].getCoordinate();
		//create start and end point
		img.setRGB(start[0], start[1]-1, Color.WHITE.getRGB());
		img.setRGB(end[0], end[1]+1, Color.WHITE.getRGB());
		return img;
	}

}