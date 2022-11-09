package tetris;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class MyPanel extends JPanel implements KeyListener {
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// paint bottom shapes
		for (int i = 0; i < Main.shapes.size(); i++) {
			g.setColor(Main.shapes.colors.get(i));
			g.fillRect(Main.shapes.shapes.get(i).x, Main.shapes.shapes.get(i).y, Shape.blockSize, Shape.blockSize);
		}
		// paint current shape
		g.setColor(Main.s.color);
		for (int i = 0; i < Main.s.list.size(); i++) {
			g.fillRect(Main.s.list.get(i).x, Main.s.list.get(i).y, Shape.blockSize, Shape.blockSize);
		}
		// show death text
		if (Main.alive == false) {
			g.setColor(new Color(52, 235, 216));
			g.setFont(new Font("TimesRoman", Font.BOLD, 100));
			g.drawString("DEAD", 100, 300);
			this.removeKeyListener(this);
		}

	}

	public MyPanel() {
		setBounds(0, 0, Main.frame.getWidth(), Main.frame.getHeight());
		setBackground(Color.BLACK);
		setFocusable(true);
		addKeyListener(this);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if (Main.alive == true) {
			switch (arg0.getKeyCode()) {
			case KeyEvent.VK_UP:
				Main.s.rotate();
				break;
			case KeyEvent.VK_DOWN:
				Main.s.moveDown();
				break;
			case KeyEvent.VK_LEFT:
				Main.s.moveLeft();
				break;
			case KeyEvent.VK_RIGHT:
				Main.s.moveRight();
				break;
			}
		} else {
		System.exit(0);
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}
