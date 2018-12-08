package gui;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class SongList extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static void main(String[] args) {
		JFrame frame=new JFrame();
		GlobalVars.frame=frame;
		MainFrame.init();
		frame.setLayout(new BorderLayout());
		frame.setIconImage(new ImageIcon("conf/textures/logo.png").getImage());
		
		frame.add(new PlayBar(),BorderLayout.PAGE_END);
		frame.add(new TitleBar(),BorderLayout.PAGE_START);
		frame.add(new SceneBG(),BorderLayout.CENTER);
		frame.add(new SongList(),BorderLayout.LINE_START);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(300, 130);
		frame.setSize(300, 300);
		frame.setUndecorated(true);
		frame.setVisible(true);
	}
	DefaultListModel<InfoItem> model;
	JList<InfoItem> list;
	JScrollPane scrollPane;
	public SongList() {
		// TODO Auto-generated constructor stub
		model=new DefaultListModel<InfoItem>();
		list=new JList<InfoItem>(model);
		scrollPane=new JScrollPane(list);
		
		add(scrollPane);
		
		addSong(new InfoItem());
		
	}
	
	public void addSong(InfoItem item) {
		model.addElement(item);
	}
	
	public void removeSong(InfoItem item) {
	//TODO		
	}
	
}
