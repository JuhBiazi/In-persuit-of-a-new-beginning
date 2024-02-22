package Entidades;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Mundo.Camera;
import main.Janela;
import Entidades.Obstaculo;

public class Entidade {
	
	public static BufferedImage chao = Janela.sprite.getSprite(0, 0, 16, 16);
	public static BufferedImage chaoCimento = Janela.sprite.getSprite(16, 0, 16, 16);
	public static BufferedImage empty = Janela.sprite.getSprite(0, 16, 16, 16);
	public static BufferedImage ceu = Janela.ceu.getSprite(0, 0, 1278, 406);
	public static BufferedImage texto = Janela.sprite.getSprite(0, 47, 159, 30);
	
	public static BufferedImage obstaculo = Janela.sprite.getSprite(96, 16, 16, 16);
	public static BufferedImage lixo = Janela.sprite.getSprite(16, 16, 16, 16);
	public static BufferedImage grama = Janela.sprite.getSprite(112, 0, 16, 16);
	public static BufferedImage ContLixo = Janela.sprite.getSprite(0, 32, 16, 16);

	protected double x;
	protected double y;
	protected int Altura;
	protected int Largura;
	protected BufferedImage sprite;
	
	public Entidade(int x, int y, int Altura, int Largura, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.Largura = Largura;
		this.Altura = Altura;
		this.sprite = sprite;
	}
	
	public void setX(int newX) {
		this.x = newX;
	}
	
	public void setY(int newY) {
		this.y = newY;
	}
	
	public int getX() {
		return (int)this.x;
	}
	
	public int getY() {
		return (int)this.y;
	}
	
	public int getLargura() {
		return this.Largura;
	}
	
	public int getAltura() {
		return this.Altura;
	}
	
	public void tick() {
		
	}
	
	public void renderizar(Graphics g) {
		g.drawImage(sprite, this.getX() - Camera.x, this.getY() - Camera.y, null);
	}
}
