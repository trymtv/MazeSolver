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
    	MazeImage test = new MazeImage("C:\\Users\\Trym\\Pictures\\maze\\maze.png");
    	ImagePixelArray pixelArray = new ImagePixelArray(test.img);
//    	System.out.println(Arrays.toString(pixelArray.getRBG(5, 16)));
//    	System.out.println(pixelArray.isWhite(5, 16));
//    	System.out.println(pixelArray.getPixelArray().length);
//    	test.prettyPrint();
//    	System.out.println(pixelArray.getPixel(5, 0));
//    	System.out.println(pixelArray.isWhite(5, 0));
    	MazeGraph graph = new MazeGraph();
    	graph.makeNodes(pixelArray);
//    	System.out.println(graph);
    	File outputFile = new File("C:\\Users\\Trym\\Pictures\\maze\\test.png");
    	for(PositionNode node : graph.getGraph()) {
    		test.img.setRGB(node.getX(), node.getY(), PixelColorParser.getARGB(255, 255, 0,0));
    	}
    	ImageDrawer drawer = new ImageDrawer(test.img);
    	drawer.drawPath(SolveGraph.solveDepthFirst(graph.getGraph().get(0), PositionNode::isTarget), Color.GRAY.getRGB());
    	System.out.println(SolveGraph.getPath());
//		BufferedImage img = MazeMaker.drawMaze(1000, 1000);

    	ImageIO.write(test.img, "png", outputFile);
	}
}
