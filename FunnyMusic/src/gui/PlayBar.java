package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class PlayBar extends JPanel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JFrame frame=new JFrame();
		GlobalVars.frame=frame;
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
	
	private JLabel name;
	
	public PlayBar() {
		// TODO Auto-generated constructor stub
		GlobalVars.play_bar=this;
		setLayout(new GridLayout(1, 4));
		add(new Group());
		name=new JLabel();
		add(name);
		add("place holder", new JLabel("take up this room"));
		setBorder(new LineBorder(Color.CYAN));
	}
	public void setSong(String name) {
		this.name.setText(name);
	}
	

}

class Group extends JPanel{
	public Group() {
		setLayout(new GridLayout(1, 4));
		add(new StopButton());
		add(new PlayButton());
		add(new NextButton());
		setBorder(new LineBorder(Color.DARK_GRAY));
	}
}