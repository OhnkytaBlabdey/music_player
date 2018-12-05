package gui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class PlayBar extends JPanel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JFrame frame=new JFrame();
		
		frame.add(new PlayBar());
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(300, 130);
		frame.setSize(300, 300);
		frame.setVisible(true);
	}
	
	public PlayBar() {
		// TODO Auto-generated constructor stub
		setLayout(new GridLayout(1, 4));
		add(new PlayButton());
		add(new PlayButton());
		add(new PlayButton());
		
		setBorder(new LineBorder(Color.CYAN));
	}

}
