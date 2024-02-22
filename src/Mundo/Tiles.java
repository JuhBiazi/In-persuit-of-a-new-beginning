package Mundo;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Janela;

public class Tiles {

	
	
	public int x,y;
	public BufferedImage sprite;
	
	public Tiles(int x, int y, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite, x-Camera.x, y-Camera.y, null);
	}
	
}
