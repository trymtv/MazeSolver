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

	public BufferedImage getImg() {
		return img;
	}
}
