package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import Entidades.Ceu;
import Entidades.ContLixo;
import Entidades.Entidade;
import Entidades.Grama;
import Entidades.Jogador;
import Entidades.Lixo;
import Entidades.Obstaculo;
import Entidades.Texto;
import Graficos.SpriteSheet;
import Mundo.Mundo;

public class Janela extends Canvas implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;

	// Criação das variáveis
	
	public static JFrame Tela;
	public static JFrame TelaGameOver;
	private Thread thread;
	private boolean isRunning = true;

	public static int Largura = 260;
	public static int Altura = 160;
	public static int Escala = 4;

	private BufferedImage fundoUm;
	public static List<Entidade> entidades;
	public static SpriteSheet sprite;
	public static Mundo Mundo;

	public static Jogador Eco;

	public static List<Ceu> ceuvetor;
	public static SpriteSheet ceu;

	public static List<Lixo> lixo;
	public static List<Obstaculo> obstaculo;
	public static List<Grama> grama;
	public static List<Texto> texto;

	public static List<ContLixo> contlixo;

	

	// método construtor
	public Janela() {
		addKeyListener(this);
		this.setPreferredSize(new Dimension(Largura * Escala, Altura * Escala));
		initFrame();
		// declarando o padrão de cor como RGB
		fundoUm = new BufferedImage(Largura, Altura, BufferedImage.TYPE_INT_RGB);
		entidades = new ArrayList<Entidade>();
		sprite = new SpriteSheet("/SpriteSheet.png");
		ceuvetor = new ArrayList<Ceu>();
		lixo = new ArrayList<Lixo>();
		obstaculo = new ArrayList<Obstaculo>();
		grama = new ArrayList<Grama>();
		contlixo = new ArrayList<ContLixo>();
		texto = new ArrayList<Texto>();
		ceu = new SpriteSheet("/CeuSprite.png");
		Eco = new Jogador(0, 0, 16, 16, sprite.getSprite(32, 0, 16, 16));
		entidades.add(Eco);
		Mundo = new Mundo("/mundo1.png");
		
	}

	public void initFrame() {
		Tela = new JFrame("In Persuit of a Beginning");
		Tela.add(this);
		// Para o usuário não poder mexer no tamanho da tela
		Tela.setResizable(false);
		Tela.pack();
		Tela.setLocationRelativeTo(null);
		// Comando para poder fechar a tela
		Tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Comando para fazer a tela ficar visível
		Tela.setVisible(true);
	}

	public void initGameOver() {
		
		JPanel panel = new JPanel();
		TelaGameOver = new JFrame("Game Over");
		// Para o usuário não poder mexer no tamanho da tela
		TelaGameOver.setResizable(false);
		TelaGameOver.pack();
		// Comando para fazer a tela ficar visível
		TelaGameOver.setVisible(true);
		TelaGameOver.setLocationRelativeTo(null);
		TelaGameOver.add(panel);
		TelaGameOver.setSize(Largura * Escala, Altura * Escala);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		TelaGameOver.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		// Comando para poder fechar a tela
		TelaGameOver.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void initFinal() {
		
		JPanel panel = new JPanel();
		TelaGameOver = new JFrame("Parabéns Você Ganhou");
		// Para o usuário não poder mexer no tamanho da tela
		TelaGameOver.setResizable(false);
		TelaGameOver.pack();
		// Comando para fazer a tela ficar visível
		TelaGameOver.setVisible(true);
		TelaGameOver.setLocationRelativeTo(null);
		TelaGameOver.add(panel);
		TelaGameOver.setSize(Largura * Escala, Altura * Escala);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		TelaGameOver.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		// Comando para poder fechar a tela
		TelaGameOver.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// classe com o método main
	public static void main(String[] args) {

		Janela Tela = new Janela();
		Tela.comeca();
	
		
	}
	
	public synchronized void comeca() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}

	public synchronized void parar() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		

	}

	public void tick() {

		for (int i = 0; i < entidades.size(); i++) {
			Entidade entidade = entidades.get(i);
			entidade.tick();
		}

		for (int i = 0; i < ceuvetor.size(); i++) {
			Ceu entidade = ceuvetor.get(i);
			entidade.tick();
		}

		for (int i = 0; i < lixo.size(); i++) {
			Lixo entidade = lixo.get(i);
			entidade.tick();
		}

		for (int i = 0; i < obstaculo.size(); i++) {
			Obstaculo entidade = obstaculo.get(i);
			entidade.tick();
		}

		for (int i = 0; i < grama.size(); i++) {
			Grama entidade = grama.get(i);
			entidade.tick();
		}

		for (int i = 0; i < contlixo.size(); i++) {
			ContLixo entidade = contlixo.get(i);
			entidade.tick();
		}

		for (int i = 0; i < texto.size(); i++) {
			Texto entidade = texto.get(i);
			entidade.tick();
		}
	}

	public void renderizar() {

		BufferStrategy buffer = this.getBufferStrategy();
		if (buffer == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = fundoUm.getGraphics();
		g.setColor(new Color(20, 20, 20));
		g.fillRect(0, 0, Largura, Altura);

		Mundo.renderizar(g);

		for (int i = 0; i < ceuvetor.size(); i++) {
			Ceu entidade = ceuvetor.get(i);
			entidade.renderizar(g);
		}

		for (int i = 0; i < entidades.size(); i++) {
			Entidade entidade = entidades.get(i);
			entidade.renderizar(g);
		}

		for (int i = 0; i < lixo.size(); i++) {
			Lixo entidade = lixo.get(i);
			entidade.renderizar(g);
		}

		for (int i = 0; i < obstaculo.size(); i++) {
			Obstaculo entidade = obstaculo.get(i);
			entidade.renderizar(g);
		}

		for (int i = 0; i < grama.size(); i++) {
			Grama entidade = grama.get(i);
			entidade.renderizar(g);
		}

		for (int i = 0; i < contlixo.size(); i++) {
			ContLixo entidade = contlixo.get(i);
			entidade.renderizar(g);
		}

		for (int i = 0; i < texto.size(); i++) {
			Texto entidade = texto.get(i);
			entidade.renderizar(g);
		}

		g = buffer.getDrawGraphics();
		g.drawImage(fundoUm, 0, 0, Largura * Escala, Altura * Escala, null);
		buffer.show();
	}

	
	
	
	
	public void run() {
		long tempoDuracao = System.nanoTime();
		double quantidadeTicks = 60.0f;
		double ping = 1000000000 / quantidadeTicks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		
		
		
		
		while (isRunning) {
			long agora = System.nanoTime();
			delta += (agora - tempoDuracao) / ping;
			tempoDuracao = agora;

			// verificação (se delta > que 1 então(temporizador))
			if (delta > 1) {
				tick();
				renderizar();
				frames++;
				delta--;
			}

			if (System.currentTimeMillis() - timer >= 1000) {
				System.out.println("FPS: " + frames);
				System.out.println("Lixo " + Jogador.Lixo);
				System.out.println("Vida " + Jogador.vida);
				frames = 0;
				timer += 1000;
			}
			
			if (Jogador.Lixo == 5 ) {
				System.out.println("Ganhou");
				//Tela.dispose();
				Tela.setVisible(false);
				initFinal();
				isRunning = false;
			}

			if (Jogador.vida <= 0) {
				System.out.println("zerou vida");
				//Tela.dispose();
				Tela.setVisible(false);
				initGameOver();
				
				break;
			}
			
			
		}
		parar();

	}

	public void keyTyped(KeyEvent e) {
		// Não vou trabalhar nela pois só vou mexer quando ela for precionada ou largada
		// para a movimentação do personagem

	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_D) {
			Eco.direita = true;
		} else if (e.getKeyCode() == KeyEvent.VK_A) {
			Eco.esquerda = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			Eco.pulo = true;
		}

	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_D) {
			Eco.direita = false;
		} else if (e.getKeyCode() == KeyEvent.VK_A) {
			Eco.esquerda = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_W) {
			Eco.cima = false;
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			Eco.baixo = false;
		}

	}

	

}
