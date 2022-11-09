package tetris;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class AllShapes {
	public List<Point> shapes = new ArrayList<>();
	public List<Color> colors = new ArrayList<>();
	public void add(Shape s){
		for (int i = 0;i<s.list.size();i++){
			shapes.add(new Point(s.list.get(i).x,s.list.get(i).y));
			colors.add(s.color);
		}
	}
	public void showCoords(){
		for (int i = 0;i<shapes.size();i++){
			System.out.println(shapes.get(i).x+";"+shapes.get(i).y);
		}
		System.out.println("---");
	}
	public int size(){
		return shapes.size();
	}
	public void moveDown(){
		for (int i = 0;i<shapes.size();i++){
			shapes.set(i, new Point(shapes.get(i).x,shapes.get(i).y+Shape.blockSize));
		}
	}
	public void moveLeft(){
		for (int i = 0;i<shapes.size();i++){
			shapes.set(i, new Point(shapes.get(i).x-Shape.blockSize,shapes.get(i).y));
		}
	}
	public void moveRight(){
		for (int i = 0;i<shapes.size();i++){
			shapes.set(i, new Point(shapes.get(i).x+Shape.blockSize,shapes.get(i).y));
		}
	}
}
