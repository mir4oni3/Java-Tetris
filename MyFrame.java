package tetris;

import javax.swing.JFrame;

public class MyFrame extends JFrame{
	public MyFrame(int width,int height){
		setBounds(1920/2-width/2,1080/2-height/2,width,height);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
	}
}
