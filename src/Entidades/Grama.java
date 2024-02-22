package Entidades;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Mundo.Camera;
import main.Janela;

public class Grama extends Entidade {

	public double velocidade = 0.5;
	public int movimentacao = 1;
	public int frame = 0, maximoFrames = 15, index = 0, maximoIndex = 2;
	public BufferedImage[] gramaA;

	public Grama(int x, int y, int Altura, int Largura, BufferedImage sprite) {
		super(x, y, Altura, Largura, sprite);
		gramaA = new BufferedImage[3];

		for (int i = 0; i < 3; i++) {
			gramaA[i] = Janela.sprite.getSprite(112 + (i * 16), 0, 16, 16);
		}

	}

	public void tick() {

		if (movimentacao == 1) {
			frame++;
			if (frame == maximoFrames) {
				index++;
				frame = 0;
				if (index > maximoIndex)
					index = 0;
			}

		}
	}

	public void renderizar(Graphics g) {
		g.drawImage(gramaA[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
	}

}
