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
	public static BufferedImage drawMaze(int width, int height){
		Stack<PositionNode> stack = new Stack<>();
		PositionNode[][] nodes = new PositionNode[width / 2][height / 2];
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) img.getGraphics();
		g.setColor(Color.BLACK);
		g.fill(new Rectangle(0, 0, width, height));
		ImageDrawer drawer = new ImageDrawer(img);
		//generating nodes
		for (int i = 0; i < nodes.length; i++) {
			for (int j = 0; j < nodes[0].length; j++) {
				nodes[i][j] = new PositionNode(i*2, j*2);
				if(i > 0) {
					nodes[i][j].connect(nodes[i-1][j]);
				}
				if(j > 0) {
					nodes[i][j].connect(nodes[i][j-1]);
				}
			}
		}
		//add initial node
		PositionNode init = nodes[(int) (Math.random()*nodes.length)][(int) (Math.random()*nodes.length)];
		init.visit();
		stack.push(init);

//		depth first implementation of maze generation
		while(!stack.isEmpty()){
			PositionNode current = stack.pop();
			List<Node> candidates = current.getNeighbors();
			while (!current.getNeighbors().isEmpty()){
				PositionNode next = (PositionNode) candidates.remove((int) (Math.random()*candidates.size()));
				if(!next.isVisited()) {
					stack.push(current);
					stack.push(next);
					next.visit();
					current.disconnect(next);
					drawer.drawLine(current.getCoordinate(), next.getCoordinate(), Color.WHITE.getRGB());
				}
			}
		}
//		//drawing border

		return img;
	}

}