package image;

import java.util.Arrays;

public class PixelColorParser {
	private static final int[] white = {255,255,255} ,black = {0,0,0};
	//Returning the encoded argb value from the alpha, red, green and blue values
	public static int getARGB(int a, int r, int g, int b) {
		a = ((a & 0xff)<<24);
		r = ((r & 0xff)<<16);
		g = ((g & 0xff)<<8);
		b = (b & 0xff);
		return a|r|g|b;
	}
	//Returning the encoded argb value from the alpha, red, green and blue values from array
	public static int getARGB(int[] argb) {
		if(argb.length != 4)
			throw new IllegalArgumentException("The argb array must contain 4 values not" + argb.length +".");
		return PixelColorParser.getARGB(argb[0], argb[1], argb[2], argb[3]);
	}
	public static int[] getRBGArray(int pixel){
		return new int[]{PixelColorParser.getRed(pixel), PixelColorParser.getGreen(pixel), PixelColorParser.getBlue(pixel)};
	}
	//Returning the alpha value of an encoded argb value
	public static int getAlpha(int pixel) {
		return ((pixel >> 24) & 0xff);
	}
	//Returning the red value of an encoded argb value
	public static int getRed(int pixel) {
		return ((pixel >> 16) & 0xff);
	}
	//Returning the green value of an encoded argb value
	public static int getGreen(int pixel) {
		return ((pixel >> 8) & 0xff);
	}
	//Returning the blue value of an encoded argb value
	public static int getBlue(int pixel) {
		return (pixel & 0xff);
	}
	//Check if pixel value is black
	public static boolean isBlack(int pixel) {
		return Arrays.equals(PixelColorParser.getRBGArray(pixel), black);
	}
	//Check if pixel value is white
	public static boolean isWhite(int pixel) {
		return Arrays.equals(PixelColorParser.getRBGArray(pixel), white);
	}
}
