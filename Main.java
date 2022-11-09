package tetris;

import java.awt.Point;
import java.util.Random;

public class Main {
	public static MyFrame frame;
	public static MyPanel panel;
	public static AllShapes shapes = new AllShapes();
	public static Shape s;
	public static boolean alive;
	public static void main(String[] args) throws InterruptedException {
		frame = new MyFrame(500,600);
		panel = new MyPanel();
		frame.add(panel);
		frame.setVisible(true);
		Shape.setBlockSize(25);
		s = new Shape(randomShape());
		alive = true;
		while (alive){
			panel.repaint();
			if (s.touches()){
				Thread.sleep(500);
				if (!s.touches()){
					continue;
				}
				shapes.add(s);
				s = new Shape(randomShape());
				if (checkCollision()){
					alive = false;
				}
				tetrisCheck();
				panel.repaint();
			}else {
				Thread.sleep(200);
				s.moveDown();
			}
		}
	}
	public static void tetrisCheck(){
		double expectedBlocks = Math.round((double)frame.getWidth()/Shape.blockSize);
		int currentBlocks = 0;
		int y = 0;
		loop:
		for (int i = 0;i<shapes.shapes.size();i++){
			for (int j = 0;j<shapes.shapes.size();j++){
				if (shapes.shapes.get(i).y==shapes.shapes.get(j).y){
					currentBlocks++;
					if (currentBlocks == expectedBlocks){
						y = shapes.shapes.get(i).y;
						break loop;
					}
				}
			}
			currentBlocks = 0;
		}
		if (currentBlocks == expectedBlocks){
			for (int i = 0;i<shapes.shapes.size();i++){
				if (shapes.shapes.get(i).y==y){
					shapes.shapes.remove(i);
					shapes.colors.remove(i);
					i--;
				}
			}
			for (int i = 0;i<shapes.shapes.size();i++){
				if (shapes.shapes.get(i).y<y){
					shapes.shapes.set(i, new Point(shapes.shapes.get(i).x,shapes.shapes.get(i).y+Shape.blockSize));
				}
			}
			tetrisCheck();
		}
	}
	public static boolean checkCollision(){
		for (int i = 0;i<s.list.size();i++){
			for (int j = 0;j<shapes.shapes.size();j++){
				if (s.list.get(i).x==shapes.shapes.get(j).x && s.list.get(i).y==shapes.shapes.get(j).y){
					return true;
				}
			}
		}
		return false;
	}
	public static char randomShape(){
		int a = new Random().nextInt(7)+1;
		switch (a){
		case 1:
			return 'O';
		case 2:
			return 'I';
		case 3:
			return 'S';
		case 4:
			return 'Z';
		case 5:
			return 'L';
		case 6:
			return 'J';
		case 7:
			return 'T';
		}
		return '0';
	}
}
