package rocket_1;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;

public class GameCanvas extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Screen set up objects
	BufferedImage starImage;
	BufferedImage enemy;
	BufferedImage player;
	BufferedImage backBuffered;
	Graphics graphics;
	// Size-modifying objects
	final int speed = 7;
	int speedXEnemy= speed, speedYEnemy= speed;
	int height = 600, width = 1024, half_height = height / 2, half_width = width / 2;
	public int positionXPlayer = 400, positionYPlayer = 200;
	public int positionXEnemy = half_height, positionYEnemy = half_width;
	int enemyRadius= 25;
	int positionXStar1 = width, positionYStar1 = randomize(height + 1);
	int positionXStar2 = width, positionYStar2 = randomize(height + 1);
	public int positionXStar3 = width, positionYStar3 = randomize(height + 1);
	public int positionXStar4 = width, positionYStar4 = randomize(height + 1);

	public GameCanvas() {
		this.setSize(width, height);
		this.setVisible(true);
		this.backBuffered = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
		this.graphics = this.backBuffered.getGraphics();
		try {
			this.starImage = ImageIO.read(new File("resources/images/star.png"));
			this.enemy = ImageIO.read(new File("resources/images/circle.png"));
			this.player = ImageIO.read(new File("resources/images/pacman.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void paintComponent(Graphics g) { // run only once
		g.drawImage(this.backBuffered, 0, 0, null);
	}

	public void renderAll() {
		this.graphics.setColor(Color.BLACK);
		this.graphics.fillRect(0, 0, width, height);
				
		this.graphics.drawImage(this.enemy, this.positionXEnemy, this.positionYEnemy, 50, 50, null);
		this.positionXStar1 -= randomize(speed + 1);
		if (this.positionXStar1 <= -10)
			positionXStar1 = width;
		this.positionXStar2 -= randomize(speed + 1);
		if (this.positionXStar2 <= -10)
			positionXStar2 = width;
		this.positionXStar3 -= randomize(speed + 1);
		if (this.positionXStar3 <= -10)
			positionXStar3 = width;
		this.positionXStar4 -= randomize(speed + 1);
		if (this.positionXStar4 <= -10)
			positionXStar4 = width;
		
		this.graphics.drawImage(this.player, this.positionXPlayer, this.positionYPlayer, 50, 50, null);
		this.graphics.drawImage(this.starImage, this.positionXStar1, this.positionYStar1, 10, 10, null);
		this.graphics.drawImage(this.starImage, this.positionXStar2, this.positionYStar2, 10, 10, null);
		this.graphics.drawImage(this.starImage, this.positionXStar3, this.positionYStar3, 10, 10, null);
		this.graphics.drawImage(this.starImage, this.positionXStar4, this.positionYStar4, 10, 10, null);
		this.repaint();
	}

	public static int randomize(int value) {
		Random rand = new Random();
		return rand.nextInt(value);
	}
}
