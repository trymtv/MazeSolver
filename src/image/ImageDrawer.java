package image;

import search.PositionNode;

import java.awt.image.BufferedImage;
import java.util.List;

//TODO change implementation to 2Dgraphics
public class ImageDrawer{
	private final BufferedImage img;
	
	
	/**
	 * Constructs a {@code ImageDrawer} object storing the {@code BufferedImage} to be drawn to.
	 * 
	 * @param img the {@code BufferedImage} to draw to
	 * 
	 * @see BufferedImage
	 */
	public ImageDrawer(BufferedImage img) {
		this.img = img;
	}
	/**
	 * Takes in two coordinates and draws a line between them in the stored {@code BufferedImage}.
	 * 
	 * @param start the x, y coordinate of the start point
	 * @param end the x, y coordinate of the end point
	 * @throws IllegalArgumentException the start or end point does not have two coordinates
	 * @throws IllegalArgumentException the two points are not in line.
	 */
	public void drawLine(int[]start, int[]end, int argb) {
		if(start.length !=2 || end.length != 2) 
			throw new IllegalArgumentException("Incorrect length of coordinate parameters");
		if(start[0]==end[0]) {
			for(int i = Math.abs(start[1]-end[1]); i >= 0; i--) {
				if(start[1] > end[1])
					img.setRGB(start[0], start[1]-i, argb);
				else
					img.setRGB(start[0], start[1]+i, argb);
			}
		}else if(start[1]==end[1]) {
			for(int i = Math.abs(start[0]-end[0]); i >= 0; i--) {
				if(start[0] > end[0])
					img.setRGB(start[0]-i, start[1], argb);
				else
					img.setRGB(start[0]+i, start[1], argb);
			}
		}else {
			throw new IllegalArgumentException("The end and start point is not in the same x or y line.");
		}
	}
	public void drawLine(PositionNode start, PositionNode end, int argb){
		this.drawLine(start.getCoordinate(), end.getCoordinate(), argb);
	}
	/**
	 * Draws a line between the {@code PositionNode}s of a specified path.
	 *
	 * @param path a list of nodes in line
	 */
	public void drawPath(List<PositionNode> path, int argb) {
		for (int i = 0; i < path.size()-1; i++) {
			this.drawLine(path.get(i).getCoordinate(), path.get(i+1).getCoordinate(), argb);
		}
	}
}
