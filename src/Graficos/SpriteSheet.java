package Graficos;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Mundo.Camera;

public class SpriteSheet {

	
	public BufferedImage SpriteSheet;
	
	
	public SpriteSheet(String path) {
		try {
			SpriteSheet = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public BufferedImage getSprite(int x, int y, int Largura, int Altura) {
		return SpriteSheet.getSubimage(x-Camera.x, y-Camera.y, Largura, Altura);
	}
}
