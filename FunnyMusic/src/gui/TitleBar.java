package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class TitleBar extends JPanel{
	public static void main(String[] args) {
		JFrame frame=new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setIconImage(new ImageIcon("conf/textures/logo.png").getImage());
		
		frame.add(new PlayBar(),BorderLayout.PAGE_END);
		frame.add(new TitleBar(),BorderLayout.PAGE_START);
		frame.add(new SceneBG(),BorderLayout.CENTER);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(300, 130);
		frame.setSize(300, 300);
		frame.setUndecorated(true);
		frame.setVisible(true);
	}
	public TitleBar() {
		// TODO Auto-generated constructor stub
		setLayout(new GridLayout(1, 5));
		add(new TitleLogo());
		add("place holder", new JLabel("take up this room"));
		add(new WindowBar());
		
	}
}



class WindowBar extends JPanel{
	public WindowBar() {
		// TODO Auto-generated constructor stub
		setLayout(new GridLayout(1, 3));
		add(new MinBar());
		add(new MaxBar());
		add(new CloseBar());
	}
}
