package maze;

import image.ImageDrawer;
import image.ImagePixelArray;
import image.PixelColorParser;
import search.PositionNode;
import search.SolveGraph;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class MazeImage {
	private BufferedImage img = null; 
    private File f = null;
    int[][] pixelArray;
	
    public MazeImage(String path) {
    	try {
    		f = new File(path);
    		img = ImageIO.read(f);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	pixelArray = new ImagePixelArray(img).getPixelArray();
    }
    
    public void prettyPrint() {
		for (int[] ints : pixelArray) {
			System.out.println(Arrays.toString(ints));
		}
    }
    
    public int[][] getPixelArray() {
		return pixelArray;
	}
    
    public static void main(String[] args) throws IOException {
		final long startTime = System.currentTimeMillis();


//		Maze solver
//    	MazeImage test = new MazeImage("./mazes/make.png");
//    	ImagePixelArray pixelArray = new ImagePixelArray(test.img);
//    	MazeGraph graph = new MazeGraph();
//		graph.makeNodes(pixelArray);
//		for(PositionNode node : graph.getGraph()) {
//    		test.img.setRGB(node.getX(), node.getY(), PixelColorParser.getARGB(255, 255, 0,0));
//    	}
//    	ImageDrawer drawer = new ImageDrawer(test.img);
//		drawer.drawPath(SolveGraph.solveRecDepthFirst(graph.getGraph().get(0), PositionNode::isTarget), Color.BLUE.getRGB());
//		drawer.drawPath(SolveGraph.stackDepthFirst(graph.getGraph().get(0), PositionNode::isTarget), Color.GREEN.getRGB());
//
//		File outputFile = new File("./mazes/maze100x100.png");
//		ImageIO.write(test.img, "png", outputFile);


//		Maze maker
		File makeOutputFile = new File("./mazes/make.png");
		BufferedImage img = MazeMaker.drawMaze(20, 20);
		ImageIO.write(img, "png", makeOutputFile);
//
		final long endTime = System.currentTimeMillis();
		System.out.println("Total execution time: " + (endTime - startTime));
	}
}
