package rocket_1;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;
public class GameWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GameCanvas gameCanvas= new GameCanvas();
	long lastTime= 0;
	int speed= 7;
	Random rand= new Random();
	public GameWindow()	{
		this.setSize(this.gameCanvas.width, this.gameCanvas.height);
		this.add(this.gameCanvas);
		this.addKeyListener(new KeyListener()	{
			public void keyTyped(KeyEvent keyEvent)	{
			}
			
			public void keyPressed(KeyEvent keyEvent)	{
				if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT)	{
					if (gameCanvas.positionXPlayer <= 0) {
                        gameCanvas.positionXPlayer = 1024;
                        gameCanvas.positionYPlayer = rand.nextInt(601);}
                    else {gameCanvas.positionXPlayer -= speed+4;}
				}
				else if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT)	{
					if (gameCanvas.positionXPlayer >= 1024) {
                        gameCanvas.positionXPlayer = 0;
                        gameCanvas.positionYPlayer = rand.nextInt(601);}
                    else {gameCanvas.positionXPlayer += speed+4;}
				}
				else if (keyEvent.getKeyCode() == KeyEvent.VK_UP)	{
					 if (gameCanvas.positionYPlayer <= 0) {
	                        gameCanvas.positionYPlayer = 600;
	                        gameCanvas.positionXPlayer = rand.nextInt(1025);}
	                    else {gameCanvas.positionYPlayer -= speed+4;}
				}
				else if (keyEvent.getKeyCode() == KeyEvent.VK_DOWN)	{
					if (gameCanvas.positionYPlayer >= 600) {
                        gameCanvas.positionYPlayer = 0;
                        gameCanvas.positionXPlayer = rand.nextInt(1025);}
                    else {gameCanvas.positionYPlayer += speed+4;}
				}
			}
			
			public void keyReleased(KeyEvent keyEvent)	{
			}
		} );
		this.setVisible(true);
		this.addWindowListener(new WindowAdapter()	{
			public void windowClosing(WindowEvent e)	{
				System.exit(0);
			}
		});
	}
	public void gameLoop()	{
		while (true)	{
			long currentTime= System.nanoTime();
			if (currentTime-this.lastTime >= 17_000_000)	{
				
				if (this.gameCanvas.positionXEnemy==0 || this.gameCanvas.positionXEnemy
						==this.gameCanvas.width-this.gameCanvas.enemy.getWidth())	{
					this.gameCanvas.speedXEnemy=-this.gameCanvas.speedXEnemy;
					this.gameCanvas.positionXEnemy+= this.gameCanvas.speedXEnemy;
				}
				else if (this.gameCanvas.positionXEnemy+this.gameCanvas.speedXEnemy<0 || 
						this.gameCanvas.positionXEnemy+this.gameCanvas.speedXEnemy>this.gameCanvas.width-this.gameCanvas.enemy.getWidth())	{
					if (this.gameCanvas.positionXEnemy+this.gameCanvas.speedXEnemy<0) this.gameCanvas.positionXEnemy=0;
					else this.gameCanvas.positionXEnemy= this.gameCanvas.width-this.gameCanvas.enemy.getWidth();
				}
				else this.gameCanvas.positionXEnemy+= gameCanvas.speedXEnemy;
				
				if (this.gameCanvas.positionYEnemy==0 || this.gameCanvas.positionYEnemy==this.gameCanvas.height-this.gameCanvas.enemy.getHeight())	{
					this.gameCanvas.speedYEnemy=-gameCanvas.speedYEnemy;
					this.gameCanvas.positionYEnemy+= gameCanvas.speedYEnemy;
				}
				else if (this.gameCanvas.positionYEnemy+gameCanvas.speedYEnemy<0 || 
						this.gameCanvas.positionYEnemy+gameCanvas.speedYEnemy>this.gameCanvas.height-this.gameCanvas.enemy.getHeight())	{
					if (this.gameCanvas.positionYEnemy+gameCanvas.speedYEnemy<0) this.gameCanvas.positionYEnemy=0;
					else this.gameCanvas.positionYEnemy= this.gameCanvas.height-this.gameCanvas.enemy.getHeight();
				}
				else this.gameCanvas.positionYEnemy+= gameCanvas.speedYEnemy;
			
				this.gameCanvas.renderAll();
				this.lastTime= currentTime;
			}
		}
	}
}
