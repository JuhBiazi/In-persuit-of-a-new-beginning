package Entidades;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Mundo.Camera;
import Entidades.Lixo;
import Entidades.Obstaculo;
import main.Janela;



public class Jogador extends Entidade{
	
	public boolean direita,esquerda,baixo,cima;
	public double velocidade = 1.5;
	public static int vida = 1, vidaMax = 1;
	public static int Lixo = 0, ContLixomax = 5;
	
	public int dir =1, esq =0;
	public int direcaoAtual = dir;
	
	public int movimentacao = 0;
	public int frame = 0, maximoFrames = 5, index = 0, maximoIndex = 3;
	public int maskx= 0, masky = 0, maskw = 12, maskh = 16;
	
	public BufferedImage[] jogadorDireita;
	public BufferedImage[] jogadorEsquerda;
	
	public boolean pulo = false;
	public boolean ePulo = false;
	public int alturaPulo = 36;
	public int framesPulo = 0;
	public Obstaculo pl;
	public Lixo contLixo;
	
	public int posx, posy;

	public Jogador(int x, int y, int Largura, int Altura, BufferedImage sprite) {
		super(x, y, Largura, Altura, sprite);
		jogadorDireita = new BufferedImage[4];
		jogadorEsquerda = new BufferedImage[4];
		
		for(int i = 0; i < 4; i++) {
			jogadorDireita[i] = Janela.sprite.getSprite(32 + (i*16), 0, 16, 16);
		}
		
		for(int i = 0; i < 4; i++) {
			jogadorEsquerda[i] = Janela.sprite.getSprite(80 - (i*16), 16, 16, 16);
		}
			
	}
	
	
	
	public void tick() {
		movimentacao = 0;
		
		if(!colisao((int)x,(int)(y+1)) && ePulo ==  false) {
			y+=2;
		}
		//não usei porque o personagem só vai para frente e para trás.
		
		if(baixo)
			y+=velocidade;
		if(cima)
			y-=velocidade;
		
		
		if(direita && !colisao((int)(x+velocidade), this.getY())) { 
			x+=velocidade;
			movimentacao = 1;
			direcaoAtual = dir;
		}
		
		if(esquerda && !colisao((int)(x-velocidade), this.getY())) {
			x-=velocidade;
			movimentacao = 1;
			direcaoAtual = esq;
		}
		
		if(movimentacao == 1) {
			frame++;
			if(frame == maximoFrames) {
				index++;
				frame = 0;
				if(index > maximoIndex)
					index = 0;
			}
			
		}
		
		if(pulo) {
			if(colisao(this.getX(), this.getY()+1)) {
				ePulo = true;
			}
		}
		
		if(ePulo){
			if(!colisao(this.getX(), this.getY()-2)) {
				y-=2;
				framesPulo +=2;
				if(framesPulo == alturaPulo) {
					ePulo = false;
					pulo = false;
					framesPulo = 0;
				}
			}else {
				ePulo = false;
				pulo = false;
				framesPulo = 0;
			}
			
		}
		
		if(dano((int)(x+velocidade), this.getY() - 10)) {
			vida -= 1;
		}
		
		if(lixo(this.getX(), this.getY())) {
			Lixo += 1;
			Janela.lixo.remove(contLixo);
		}
		
		if(check(this.getX(), this.getY())){
			posx = this.getX();
			posy = this.getY();
		}
		
		
		//Ficar no meio da camera
		Camera.x = Camera.Clamp(this.getX() - (Janela.Largura/2), 0, Mundo.Mundo.LARGURA*16 - Janela.Largura);
		Camera.y = Camera.Clamp(this.getY() - (Janela.Altura/2), 0, Mundo.Mundo.ALTURA*16 - Janela.Altura);	
	}
	//colisão
	public boolean colisao(int nextx, int nexty) {
		Rectangle Jogador = new Rectangle(nextx + maskx, nexty + masky, maskw, maskh);
		for(int i = 0; i < Janela.entidades.size(); i++) {
			Entidade entidade = Janela.entidades.get(i);
			if(entidade instanceof Solido) {
				Rectangle solido = new Rectangle (entidade.getX() + maskx, entidade.getY() + masky, maskw, maskh);
				if (Jogador.intersects(solido)) {
					return true;
				}
			}
			
		}
		return false;		
	}
	
	
	public boolean dano(int nextx, int nexty) {
		Rectangle Jogador = new Rectangle(nextx + maskx, nexty + masky, maskw, maskh);
		for(int i = 0; i < Janela.obstaculo.size(); i++) {
			Obstaculo entidade = Janela.obstaculo.get(i);
			if(entidade instanceof Obstaculo) {
				Rectangle obstaculo = new Rectangle (entidade.getX() + maskx, entidade.getY() + masky, maskw, maskh);
				if (Jogador.intersects(obstaculo)) {
					return true;
				}
			}
			
		}
		return false;		
	}
	



	public boolean check(int nextx, int nexty) {
		Rectangle Jogador = new Rectangle(nextx + maskx, nexty + masky, maskw, maskh);
		for(int i = 0; i < Janela.entidades.size(); i++) {
			Entidade entidade = Janela.entidades.get(i);
			if(entidade instanceof Solido) {
				Rectangle solido = new Rectangle (entidade.getX() + maskx, entidade.getY() + masky, maskw, maskh);
				if (Jogador.intersects(solido)) {
					return true;
				}
			}
			
		}
		return false;		
	}
	
	public boolean lixo(int nextx, int nexty) {
		Rectangle Jogador = new Rectangle(nextx + maskx, nexty+masky,maskw,maskh);
		for(int i = 0; i < Janela.lixo.size(); i++) {
			Lixo lixo = Janela.lixo.get(i);
			if(lixo instanceof Lixo) {
				Rectangle solido = new Rectangle (lixo.getX() + maskx, lixo.getY() + masky,maskw,maskh);	
				if(Jogador.intersects(solido)) {
					contLixo = lixo;
					return true;
					
				}
			}
			
		}
		return false;
	}
	
	
	
	

	

	public void renderizar(Graphics g) {
		if(direcaoAtual == dir && movimentacao == 1) {
			g.drawImage(jogadorDireita[index], this.getX()-Camera.x, this.getY()-Camera.y, null);
		}
		
	
		if(direcaoAtual == dir && movimentacao == 0) {
			g.drawImage(jogadorDireita[0], this.getX()-Camera.x, this.getY()-Camera.y, null);
		}
		
		
		if(direcaoAtual == esq && movimentacao == 1) {
			g.drawImage(jogadorEsquerda[index], this.getX()-Camera.x, this.getY()-Camera.y, null);
		}
		
		if(direcaoAtual == esq && movimentacao == 0) {
			g.drawImage(jogadorEsquerda[0], this.getX()-Camera.x, this.getY()-Camera.y, null);
		}
		
	}

}
