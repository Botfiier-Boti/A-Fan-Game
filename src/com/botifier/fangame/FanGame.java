package com.botifier.fangame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FanGame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public FanGame() {
		
	}
	public static void main(String[] args) throws Exception {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		FanGame g = new FanGame();

		g.setTitle("Fan Game");
		g.setDefaultCloseOperation(EXIT_ON_CLOSE);
		FanPanel fp = new FanPanel();
		g.add(fp);
		g.addKeyListener(fp);
		g.pack();
		g.setSize(480, 480);
		g.setLocation((dim.width/2)-(g.getWidth()/2), (dim.height/2)-(g.getHeight()/2));
		g.setResizable(false);
		g.setIconImage(ImageIO.read(g.getClass().getResourceAsStream("Icon.png")));
		g.setVisible(true);
		while (true) {
			g.repaint();
			Thread.sleep(20);
		}
	}

	public static class FanPanel extends JPanel implements KeyListener {
		double mom = 0;
		double rotation = 0;
		boolean keyDown = false;
		Image i, i2;
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		public FanPanel() {
			this.setVisible(true);
			try {
				i = ImageIO.read(getClass().getResourceAsStream("Fan.png"));
				i2 = ImageIO.read(getClass().getResourceAsStream("Face.png"));
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}

			setBackground(new Color(0, 0, 0, 0));
			repaint();
		}
		
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D)g;
			g2d.setFont(g2d.getFont().deriveFont(Font.BOLD));
			g2d.drawString("This is the fan game... Knock yourself out", 24, 24);	
			g2d.drawString("Use left and right arrow keys to spin fan", 24, 48);
			AffineTransform af = AffineTransform.getRotateInstance(rotation, 220, 220);
			af.translate(60, 60);
			g2d.drawImage(i, af, this);
			g2d.drawImage(i2, (getWidth()/2)-i2.getWidth(this), (getHeight()/2)-i2.getHeight(this), this);
			rotation += mom;
			if (rotation > 359)
				rotation = 0;
			if (rotation < 0)
				rotation = 359;
			if (mom > 0.01) {
				mom -= 0.01;
			} else if (mom < -0.01) {
				mom += 0.01;
			} else if (mom < 0.01 && mom > -0.01){
				mom = 0;
			}
			if (mom > 10)
				mom = 10;
		}

		@Override
		public void keyPressed(KeyEvent arg0) {
			if (arg0.getKeyCode() == KeyEvent.VK_LEFT) {
				mom-=0.1;
				keyDown = true;
			} else if (arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
				mom+=0.1;
				keyDown = true;
			}
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			
		}
	}
}
