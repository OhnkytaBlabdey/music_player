package gui;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class InfoItem extends JLabel {

	public static void main(String[] args) {
		JFrame frame=new JFrame();
		GlobalVars.frame=frame;
		MainFrame.init();
		frame.setLayout(new BorderLayout());
		frame.setIconImage(new ImageIcon("conf/textures/logo.png").getImage());
		
		frame.add(new PlayBar(),BorderLayout.PAGE_END);
		frame.add(new TitleBar(),BorderLayout.PAGE_START);
		frame.add(new SceneBG(),BorderLayout.CENTER);
		frame.add(new InfoItem(), BorderLayout.LINE_START);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(300, 130);
		frame.setSize(300, 300);
		frame.setUndecorated(true);
		frame.setVisible(true);
	}
	
	public InfoItem() {
		// TODO Auto-generated constructor stub
		setAlbum("album/album1.jpg");
		setSongName("name - name");
	}
	
	public void setAlbum(String path) {
		setIconTextGap(4);
		setIcon(new ImageIcon(path));
	}
	public void setSongName(String s) {
		setText(s);
	}
}
