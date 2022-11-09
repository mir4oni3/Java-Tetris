package tetris;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
public class Shape {
	public List<Coord> list = new ArrayList<>();
	public static int blockSize;
	public Color color;
	public char type;
	public int orientation;
	public static void setBlockSize(int size) {
		blockSize = size;
	}

	public void showCoords() {
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).x + ";" + list.get(i).y);
		}
		System.out.println("--------------");
	}

	public void moveDown() {
		//proverqva dali e legalno da se premesti
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).y + 3 > Main.frame.getHeight() - blockSize) {
				return;
			}
			for (int j = 0;j<Main.shapes.shapes.size();j++){
				if (list.get(i).x==Main.shapes.shapes.get(j).x && list.get(i).y+blockSize==Main.shapes.shapes.get(j).y){
					return;
				}
			}
		}
		//mesti
		for (int i = 0; i < list.size(); i++) {
			list.set(i, list.get(i).add(0, blockSize));
		}
		Main.frame.repaint();
	}

	public void moveUp() {
		//proverqva dali e legalno da se premesti
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).y - 3 < 0) {
				return;
			}
			for (int j = 0;j<Main.shapes.shapes.size();j++){
				if (list.get(i).x==Main.shapes.shapes.get(j).x && list.get(i).y-blockSize==Main.shapes.shapes.get(j).y){
					return;
				}
			}
		}
		//mesti
		for (int i = 0; i < list.size(); i++) {
			list.set(i, list.get(i).add(0, -blockSize));
		}
		Main.frame.repaint();
	}

	public void moveLeft() {
		//proverqva dali e legalno da se premesti
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).x - 3 < 0) {
				return;
			}
			for (int j = 0;j<Main.shapes.shapes.size();j++){
				if (list.get(i).x-blockSize==Main.shapes.shapes.get(j).x && list.get(i).y==Main.shapes.shapes.get(j).y){
					return;
				}
			}
		}
		//mesti
		for (int i = 0; i < list.size(); i++) {
			list.set(i, list.get(i).add(-blockSize, 0));
		}
		Main.frame.repaint();
	}

	public void moveRight() {
		//proverqva dali e legalno da se premesti
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).x + 3 > Main.frame.getWidth() - blockSize) {
				return;
			}
			for (int j = 0;j<Main.shapes.shapes.size();j++){
				if (list.get(i).x+blockSize==Main.shapes.shapes.get(j).x && list.get(i).y==Main.shapes.shapes.get(j).y){
					return;
				}
			}
		}
		//mesti
		for (int i = 0; i < list.size(); i++) {
			list.set(i, list.get(i).add(blockSize, 0));
		}
		Main.frame.repaint();
	}

	public boolean touches() {
		try {
				// proverqva dali shape e nai dolu
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).y + 3 > Main.frame.getHeight() - blockSize) {
					return true;
				}
				// proverqva dali pod tozi shape ima drug shape
				for (int j = 0; j < Main.shapes.shapes.size(); j++) {
					if (Math.abs(list.get(i).y - (Main.shapes.shapes.get(j).y - blockSize)) < 3
							&& list.get(i).x == Main.shapes.shapes.get(j).x) {
						return true;
					}
				}
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	public void rotate() {
		switch (this.type) {
		case 'O':
			break;
		case 'I':
			if (orientation == 0) {
				if (list.get(0).x > 0 && list.get(0).x < Main.frame.getWidth() - 2 * blockSize - 3) {
					for (int i = 2; i >= -1; i--) {
						list.add(new Coord(list.get(0).x + i * blockSize, list.get(0).y + i * blockSize));
						list.remove(0);
					}
					orientation = 1;
				}
				Main.frame.repaint();
			} else if (orientation == 1) {
				for (int i = -2; i <= 1; i++) {
					list.add(new Coord(list.get(0).x + i * blockSize, list.get(0).y + i * blockSize));
					list.remove(0);
				}
				orientation = 0;
				Main.frame.repaint();
			}
			break;
		case 'S':
			if (orientation == 0) {
				list.add(new Coord(list.get(0).x - blockSize, list.get(0).y - blockSize));
				list.remove(0);
				list.add(new Coord(list.get(0).x, list.get(0).y));
				list.remove(0);
				list.add(new Coord(list.get(0).x + blockSize, list.get(0).y - blockSize));
				list.remove(0);
				list.add(new Coord(list.get(0).x + 2 * blockSize, list.get(0).y));
				list.remove(0);
				orientation = 1;
				Main.frame.repaint();
			} else if (orientation == 1 && list.get(0).x - 3 > 0) {
				list.add(new Coord(list.get(0).x + blockSize, list.get(0).y + blockSize));
				list.remove(0);
				list.add(new Coord(list.get(0).x, list.get(0).y));
				list.remove(0);
				list.add(new Coord(list.get(0).x - blockSize, list.get(0).y + blockSize));
				list.remove(0);
				list.add(new Coord(list.get(0).x - 2 * blockSize, list.get(0).y));
				list.remove(0);
				orientation = 0;
				Main.frame.repaint();
			}
			break;
		case 'Z':
			if (orientation == 0) {
				list.add(new Coord(list.get(0).x + blockSize, list.get(0).y));
				list.remove(0);
				list.add(new Coord(list.get(0).x, list.get(0).y + blockSize));
				list.remove(0);
				list.add(new Coord(list.get(0).x - blockSize, list.get(0).y));
				list.remove(0);
				list.add(new Coord(list.get(0).x - 2 * blockSize, list.get(0).y + blockSize));
				list.remove(0);
				orientation = 1;
				Main.frame.repaint();
			} else if (orientation == 1 && list.get(2).x - 3 > 0) {
				list.add(new Coord(list.get(0).x - blockSize, list.get(0).y));
				list.remove(0);
				list.add(new Coord(list.get(0).x, list.get(0).y - blockSize));
				list.remove(0);
				list.add(new Coord(list.get(0).x + blockSize, list.get(0).y));
				list.remove(0);
				list.add(new Coord(list.get(0).x + 2 * blockSize, list.get(0).y - blockSize));
				list.remove(0);
				orientation = 0;
				Main.frame.repaint();
			}
			break;
		case 'L':
			if (orientation == 0) {
				list.add(new Coord(list.get(0).x, list.get(0).y - 2 * blockSize));
				list.remove(0);
				list.add(new Coord(list.get(0).x + blockSize, list.get(0).y - blockSize));
				list.remove(0);
				list.add(new Coord(list.get(0).x, list.get(0).y));
				list.remove(0);
				list.add(new Coord(list.get(0).x - blockSize, list.get(0).y + blockSize));
				list.remove(0);
				orientation = 1;
				Main.frame.repaint();
			} else if (orientation == 1 && list.get(1).x + 3 < Main.frame.getWidth() - blockSize) {
				list.add(new Coord(list.get(0).x + 2 * blockSize, list.get(0).y));
				list.remove(0);
				list.add(new Coord(list.get(0).x + blockSize, list.get(0).y + blockSize));
				list.remove(0);
				list.add(new Coord(list.get(0).x, list.get(0).y));
				list.remove(0);
				list.add(new Coord(list.get(0).x - blockSize, list.get(0).y - blockSize));
				list.remove(0);
				orientation = 2;
				Main.frame.repaint();
			} else if (orientation == 2) {
				list.add(new Coord(list.get(0).x, list.get(0).y + 2 * blockSize));
				list.remove(0);
				list.add(new Coord(list.get(0).x - blockSize, list.get(0).y + blockSize));
				list.remove(0);
				list.add(new Coord(list.get(0).x, list.get(0).y));
				list.remove(0);
				list.add(new Coord(list.get(0).x + blockSize, list.get(0).y - blockSize));
				list.remove(0);
				orientation = 3;
				Main.frame.repaint();
			} else if (orientation == 3 && list.get(1).x - 3 > 0) {
				list.add(new Coord(list.get(0).x - 2 * blockSize, list.get(0).y));
				list.remove(0);
				list.add(new Coord(list.get(0).x - blockSize, list.get(0).y - blockSize));
				list.remove(0);
				list.add(new Coord(list.get(0).x, list.get(0).y));
				list.remove(0);
				list.add(new Coord(list.get(0).x + blockSize, list.get(0).y + blockSize));
				list.remove(0);
				orientation = 0;
				Main.frame.repaint();
			}
			break;
		case 'J':
			if (orientation == 0) {
				list.add(new Coord(list.get(0).x + 2 * blockSize, list.get(0).y));
				list.remove(0);
				list.add(new Coord(list.get(0).x + blockSize, list.get(0).y - blockSize));
				list.remove(0);
				list.add(new Coord(list.get(0).x, list.get(0).y));
				list.remove(0);
				list.add(new Coord(list.get(0).x - blockSize, list.get(0).y + blockSize));
				list.remove(0);
				orientation = 1;
				Main.frame.repaint();
			} else if (orientation == 1) {
				list.add(new Coord(list.get(0).x, list.get(0).y + 2 * blockSize));
				list.remove(0);
				list.add(new Coord(list.get(0).x + blockSize, list.get(0).y + blockSize));
				list.remove(0);
				list.add(new Coord(list.get(0).x, list.get(0).y));
				list.remove(0);
				list.add(new Coord(list.get(0).x - blockSize, list.get(0).y - blockSize));
				list.remove(0);
				orientation = 2;
				Main.frame.repaint();
			} else if (orientation == 2) {
				list.add(new Coord(list.get(0).x - 2 * blockSize, list.get(0).y));
				list.remove(0);
				list.add(new Coord(list.get(0).x - blockSize, list.get(0).y + blockSize));
				list.remove(0);
				list.add(new Coord(list.get(0).x, list.get(0).y));
				list.remove(0);
				list.add(new Coord(list.get(0).x + blockSize, list.get(0).y - blockSize));
				list.remove(0);
				orientation = 3;
				Main.frame.repaint();
			} else if (orientation == 3) {
				list.add(new Coord(list.get(0).x, list.get(0).y - 2 * blockSize));
				list.remove(0);
				list.add(new Coord(list.get(0).x - blockSize, list.get(0).y - blockSize));
				list.remove(0);
				list.add(new Coord(list.get(0).x, list.get(0).y));
				list.remove(0);
				list.add(new Coord(list.get(0).x + blockSize, list.get(0).y + blockSize));
				list.remove(0);
				orientation = 0;
				Main.frame.repaint();
			}
			break;
		case 'T':
			if (orientation == 0) {
				list.add(new Coord(list.get(0).x + blockSize, list.get(0).y - blockSize));
				list.remove(0);
				list.add(new Coord(list.get(0).x, list.get(0).y));
				list.remove(0);
				list.add(new Coord(list.get(0).x - blockSize, list.get(0).y + blockSize));
				list.remove(0);
				list.add(new Coord(list.get(0).x - blockSize, list.get(0).y - blockSize));
				list.remove(0);
				orientation = 1;
				Main.frame.repaint();
			} else if (orientation == 1) {
				list.add(new Coord(list.get(0).x + blockSize, list.get(0).y + blockSize));
				list.remove(0);
				list.add(new Coord(list.get(0).x, list.get(0).y));
				list.remove(0);
				list.add(new Coord(list.get(0).x - blockSize, list.get(0).y - blockSize));
				list.remove(0);
				list.add(new Coord(list.get(0).x + blockSize, list.get(0).y - blockSize));
				list.remove(0);
				orientation = 2;
				Main.frame.repaint();
			} else if (orientation == 2) {
				list.add(new Coord(list.get(0).x - blockSize, list.get(0).y + blockSize));
				list.remove(0);
				list.add(new Coord(list.get(0).x, list.get(0).y));
				list.remove(0);
				list.add(new Coord(list.get(0).x + blockSize, list.get(0).y - blockSize));
				list.remove(0);
				list.add(new Coord(list.get(0).x + blockSize, list.get(0).y + blockSize));
				list.remove(0);
				orientation = 3;
				Main.frame.repaint();
			} else if (orientation == 3) {
				list.add(new Coord(list.get(0).x - blockSize, list.get(0).y - blockSize));
				list.remove(0);
				list.add(new Coord(list.get(0).x, list.get(0).y));
				list.remove(0);
				list.add(new Coord(list.get(0).x + blockSize, list.get(0).y + blockSize));
				list.remove(0);
				list.add(new Coord(list.get(0).x - blockSize, list.get(0).y + blockSize));
				list.remove(0);
				orientation = 0;
				Main.frame.repaint();
			}
		}
	}

	public Shape(char a) {
		switch (a) {
		case 'O':
			list.add(new Coord(0, 0));
			list.add(new Coord(blockSize, 0));
			list.add(new Coord(0, blockSize));
			list.add(new Coord(blockSize, blockSize));
			for (int i = 0; i < 11; i++) {
				for (int j = 0;j<list.size();j++){
					list.set(j, list.get(j).add(blockSize, 0));
				}
			}
			type = 'O';
			color = Color.YELLOW;
			break;
		case 'I':
			list.add(new Coord(0, 0));
			list.add(new Coord(0, blockSize));
			list.add(new Coord(0, 2 * blockSize));
			list.add(new Coord(0, 3 * blockSize));
			for (int i = 0; i < 12; i++) {
				for (int j = 0;j<list.size();j++){
					list.set(j, list.get(j).add(blockSize, 0));
				}
			}
			type = 'I';
			color = new Color(66, 245, 224);
			break;
		case 'S':
			list.add(new Coord(2 * blockSize, 0));
			list.add(new Coord(blockSize, 0));
			list.add(new Coord(blockSize, blockSize));
			list.add(new Coord(0, blockSize));
			for (int i = 0; i < 10; i++) {
				for (int j = 0;j<list.size();j++){
					list.set(j, list.get(j).add(blockSize, 0));
				}
			}
			type = 'S';
			color = Color.GREEN;
			break;
		case 'Z':
			list.add(new Coord(0, 0));
			list.add(new Coord(blockSize, 0));
			list.add(new Coord(blockSize, blockSize));
			list.add(new Coord(2 * blockSize, blockSize));
			for (int i = 0; i < 10; i++) {
				for (int j = 0;j<list.size();j++){
					list.set(j, list.get(j).add(blockSize, 0));
				}
			}
			type = 'Z';
			color = Color.RED;
			break;
		case 'L':
			list.add(new Coord(0, blockSize));
			list.add(new Coord(0, 0));
			list.add(new Coord(blockSize, 0));
			list.add(new Coord(2 * blockSize, 0));
			color = Color.ORANGE;
			for (int i = 0; i < 11; i++) {
				for (int j = 0;j<list.size();j++){
					list.set(j, list.get(j).add(blockSize, 0));
				}
			}
			type = 'L';
			break;
		case 'J':
			list.add(new Coord(0, 0));
			list.add(new Coord(0, blockSize));
			list.add(new Coord(blockSize, blockSize));
			list.add(new Coord(2 * blockSize, blockSize));
			color = Color.BLUE;
			for (int i = 0; i < 10; i++) {
				for (int j = 0;j<list.size();j++){
					list.set(j, list.get(j).add(blockSize, 0));
				}
			}
			type = 'J';
			break;
		case 'T':
			list.add(new Coord(0, 0));
			list.add(new Coord(blockSize, 0));
			list.add(new Coord(2 * blockSize, 0));
			list.add(new Coord(blockSize, blockSize));
			color = new Color(203, 55, 245);
			for (int i = 0; i < 11; i++) {
				for (int j = 0;j<list.size();j++){
					list.set(j, list.get(j).add(blockSize, 0));
				}
			}
			type = 'T';
			break;
		}
		orientation = 0;
	}

	class Coord {
		int x;
		int y;

		public Coord(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public Coord add(int x, int y) {
			return new Coord(this.x + x, this.y + y);
		}
	}
}
