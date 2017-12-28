package com.mop.qa.Utilities;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;

public class ImgDiffPercent {
	static File f1 = null;
	static File f2 = null;

	public void ImgDiff(String file1, String file2) {
		BufferedImage img1 = null;
		BufferedImage img2 = null;
		try {
			f1 = new File(file1); // image file path
			f2 = new File(file2); // image file path
			// URL url1 = new
			// URL("http://rosettacode.org/mw/images/3/3c/Lenna50.jpg");
			// URL url2 = new
			// URL("http://rosettacode.org/mw/images/b/b6/Lenna100.jpg");
			img1 = ImageIO.read(f1);
			img2 = ImageIO.read(f2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		int width1 = img1.getWidth(null);
		int width2 = img2.getWidth(null);
		int height1 = img1.getHeight(null);
		int height2 = img2.getHeight(null);
		if ((width1 != width2) || (height1 != height2)) {
			System.err.println("Error: Images dimensions mismatch");
			System.exit(1);
		}
		long diff = 0;
		for (int y = 0; y < height1; y++) {
			for (int x = 0; x < width1; x++) {
				int rgb1 = img1.getRGB(x, y);
				int rgb2 = img2.getRGB(x, y);
				int r1 = (rgb1 >> 16) & 0xff;
				int g1 = (rgb1 >> 8) & 0xff;
				int b1 = (rgb1) & 0xff;
				int r2 = (rgb2 >> 16) & 0xff;
				int g2 = (rgb2 >> 8) & 0xff;
				int b2 = (rgb2) & 0xff;
				diff += Math.abs(r1 - r2);
				diff += Math.abs(g1 - g2);
				diff += Math.abs(b1 - b2);
			}
		}
		double n = width1 * height1 * 3;
		double p = diff / n / 255.0;
		if ((p * 100.0) == 0) {
			System.out.println("No video playback. Possible buffering or playback stoppage.");
		} else {
			System.out.println("diff percent: " + (p * 100.0));
		}
	}
}
