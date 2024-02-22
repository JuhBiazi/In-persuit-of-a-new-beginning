package Mundo;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Entidades.Ceu;
import Entidades.Entidade;
import Entidades.Grama;
import Entidades.Jogador;
import Entidades.Lixo;
import Entidades.Obstaculo;
import Entidades.Solido;
import Entidades.Texto;
import Graficos.SpriteSheet;
import Entidades.ContLixo;
import main.Janela;

public class Mundo {

	public static int LARGURA, ALTURA;
	public Tiles[] tile;

	public Mundo(String caminho) {
		try {
			BufferedImage level = ImageIO.read(getClass().getResource(caminho));
			int[] pixels = new int[level.getWidth() * level.getHeight()];
			tile = new Tiles[level.getWidth() * level.getHeight()];
			LARGURA = level.getWidth();
			ALTURA = level.getHeight();
			level.getRGB(0, 0, level.getWidth(), level.getHeight(), pixels, 0, level.getWidth());

			// tudo que esta na horizontal
			for (int x = 0; x < level.getWidth(); x++) {
				// tudo que esta na vertical
				for (int y = 0; y < level.getHeight(); y++) {
					int pixelAtual = pixels[x + (y * level.getWidth())];
					tile[x + (y * LARGURA)] = new Vazio(x * 16, y * 16, Entidade.empty);
					if (pixelAtual == 0xFF00ff36) {
						// Jogador
						Janela.Eco.setX(x * 16);
						Janela.Eco.setY(y * 16);
					} else if (pixelAtual == 0xFF231e1e) {
						// Chão
						Solido solido = new Solido(x * 16, y * 16, 16, 16, Entidade.chao);
						Janela.entidades.add(solido);
					} else if (pixelAtual == 0xFF0d0c0c) {
						// Chão Cimento
						Solido solido = new Solido(x * 16, y * 16, 16, 16, Entidade.chaoCimento);
						Janela.entidades.add(solido);
					} else if (pixelAtual == 0xFFffe100) {
						// Barreira invisivel
						Solido solido = new Solido(x * 16, y * 16, 16, 16, Entidade.empty);
						Janela.entidades.add(solido);
					} else if (pixelAtual == 0xFF006bff) {
						// Ceu
						Ceu ceu = new Ceu(x * 16, y * 16, 16, 16, Entidade.ceu);
						Janela.ceuvetor.add(ceu);
					} else if (pixelAtual == 0xFF9731bc) {
						// Obstaculo
						Obstaculo a = new Obstaculo(x * 16, y * 16, 16, 16, Entidade.obstaculo);
						Janela.obstaculo.add(a);
					} else if (pixelAtual == 0xFFc00707) {
						// Lixo
						Lixo a = new Lixo(x * 16, y * 16, 16, 16, Entidade.lixo);
						Janela.lixo.add(a);
					} else if (pixelAtual == 0xFF36c070) {
						// Grama
						Grama a = new Grama(x * 16, y * 16, 16, 16, Entidade.grama);
						Janela.grama.add(a);
					} else if (pixelAtual == 0xFF76b9c5) {
						// LixoCount
						ContLixo a = new ContLixo(x * 16, y * 16, 16, 16, Entidade.ContLixo);
						Janela.contlixo.add(a);

					} else if (pixelAtual == 0xFFf98bff) {
						// texto
						Texto a = new Texto(x * 16, y * 16, 16, 16, Entidade.texto);
						Janela.texto.add(a);

					}

				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void renderizar(Graphics g) {
		// i = inicial
		// f = final
		int xi = Camera.x / 16;
		int yi = Camera.y / 16;
		int xf = xi + (Janela.Largura / 16);
		int yf = yi + (Janela.Altura / 16);

		for (int x = xi; x <= xf; x++) {
			for (int y = yi; y <= yf; y++) {
				if (x < 0 || y < 0 || x >= LARGURA || y >= ALTURA)
					continue;
				Tiles tiles = tile[x + (y * LARGURA)];
				tiles.render(g);

			}
		}

	}

}
