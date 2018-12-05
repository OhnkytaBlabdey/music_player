package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class TitleBar extends JPanel{
	public static void main(String[] args) {
		JFrame frame=new JFrame();
		frame.setLayout(new BorderLayout());
		
		frame.add(new PlayBar(),BorderLayout.PAGE_END);
		frame.add(new TitleBar(),BorderLayout.PAGE_START);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(300, 130);
		frame.setSize(300, 300);
		frame.setVisible(true);
	}
	public TitleBar() {
		// TODO Auto-generated constructor stub
		setLayout(new GridLayout(1, 5));
		add(new TitleLogo());
		add("place holder", new JLabel("take up this room"));
		add(new MinBar());
		add(new MaxBar());
	}
}
