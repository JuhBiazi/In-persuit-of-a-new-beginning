package Entidades;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Mundo.Camera;
import main.Janela;

public class ContLixo extends Entidade {

	public double velocidade = 0.5;
	public int movimentacao = 1;
	public int frame = 0, maximoFrames = 6, index = 0, maximoIndex = 5;
	public BufferedImage[] ContLixoO;

	public ContLixo(int x, int y, int Altura, int Largura, BufferedImage sprite) {
		super(x, y, Altura, Largura, sprite);
		ContLixoO = new BufferedImage[6];

		for (int i = 0; i < 6; i++) {
			ContLixoO[i] = Janela.sprite.getSprite(80 - (i * 16), 32, 16, 16);
		}

	}

	public void tick() {

		if (Jogador.Lixo == 1) {
			index = 1;
		}

		if (Jogador.Lixo == 2) {
			index = 2;

		}

		if (Jogador.Lixo == 3) {
			index = 3;
		}

		if (Jogador.Lixo == 4) {
			index = 4;
		}

		if (Jogador.Lixo == 5) {
			index = 5;
		}
	}

	public void renderizar(Graphics g) {
		g.drawImage(ContLixoO[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
	}

}
