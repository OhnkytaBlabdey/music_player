package gui;

//import java.awt.BorderLayout;
//import java.awt.GridLayout;


import javax.swing.ImageIcon;
import javax.swing.JLabel;

//import javax.swing.Icon;
//import javax.swing.JFrame;
//import javax.swing.JTextArea;

public class InfoItem extends JLabel {

//	public static void main(String[] args) {
//		JFrame frame=new JFrame();
//		GlobalVars.frame=frame;
//		MainFrame.init();
//		frame.setLayout(new BorderLayout());
//		frame.setIconImage(new ImageIcon("conf/textures/logo.png").getImage());
//		
//		frame.add(new PlayBar(),BorderLayout.PAGE_END);
//		frame.add(new TitleBar(),BorderLayout.PAGE_START);
//		frame.add(new SceneBG(),BorderLayout.CENTER);
//		frame.add(new InfoItem(), BorderLayout.LINE_START);
//		
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setLocation(300, 130);
//		frame.setSize(300, 300);
//		frame.setUndecorated(true);
//		frame.setVisible(true);
//	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ImageIcon album;
//	JLabel text1;
	String song;
	
	public InfoItem() {
		// TODO Auto-generated constructor stub
		
//		setLayout(new GridLayout(1, 2));
		
		setAlbum("album/album1.jpg");
		setSongName("name - song1");
		
		
	}
	
	public void setAlbum(String path) {
		setIconTextGap(4);
//		setIcon(new ImageIcon(path));
		album=new ImageIcon(path);
		setIcon(album);
//		add(album);
	}
	public void setSongName(String s) {
		setText(s);
//		text1.setText(s);
	}
}
