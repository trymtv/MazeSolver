package image;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class ImagePixelArray{
	private int[][] pixelArray;
	private final int imageHeight, imageWidth;
	
	public ImagePixelArray(BufferedImage img) {
		final byte[] pixels = ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
		imageHeight = img.getHeight();
		imageWidth = img.getWidth();
		pixelArray = new int[imageWidth][imageHeight];
		
		for (int pixel=0 , column=0 , row=0; pixel + 3 < pixels.length; pixel+=4) {
			pixelArray[column][row] = PixelColorParser.getARGB(pixels[pixel],pixels[pixel+1],pixels[pixel+2],pixels[pixel+3]);
			column++;
			if(column == imageWidth) {
				column = 0;
				row++;
			}
		}
	}
	
	public int[][] getPixelArray() {
		return pixelArray;
	}

	public int getPixel(int x, int y) {
		return pixelArray[x][y];
	}
	public void setPixel(int x, int y, int argb){ pixelArray[y][x] = argb; }

	public int getRed(int x, int y) {
		return (PixelColorParser.getRed(this.getPixel(x, y)));
	}
	public int getGreen(int x, int y) {
		return (PixelColorParser.getGreen(this.getPixel(x, y)));
	}
	public int getBlue(int x, int y) {
		return (PixelColorParser.getBlue(this.getPixel(x, y)));
	}
	public int[] getRBG(int x, int y){
		return PixelColorParser.getRBGArray(this.getPixel(x, y));
	}
	public boolean isBlack(int x, int y) {
		return PixelColorParser.isBlack(this.getPixel(x, y));
	}
	public boolean isWhite(int x, int y) {
		return PixelColorParser.isWhite(this.getPixel(x, y));
	}
	public int getHeight() {
		return this.imageHeight;
	}
	public int getWidth() {
		return this.imageWidth;
	}
}

