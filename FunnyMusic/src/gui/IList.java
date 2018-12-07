package gui;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.swing.ImageIcon;

import javax.swing.JFrame;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import db.music_info;
import other.MP3Info;


public class IList extends JPanel{
	
	private static final long serialVersionUID = 1L;

	protected JList list;
	protected JScrollPane listScro;
	protected Vector<IListItemData> items ;

	public IList(){

		try {

			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		} catch (Exception e) {

			e.printStackTrace();

		} 
		list=new JList();
		list.setCellRenderer(new IListCellRender());
		items = new Vector<IListItemData>();
		list.setListData(items);
		
		listScro = new JScrollPane(list);
		listScro.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(listScro);
		setOpaque(false);
//		init();

//		showMe();

	}
	
	public void addItem(String picpath,String text,String path,int w,int h) {
		ImageIcon icon= new ImageIcon(picpath);//getImage().getScaledInstance(w, h, java.awt.Image.SCALE_DEFAULT)
		icon.setImage(icon.getImage().getScaledInstance(w, h, java.awt.Image.SCALE_DEFAULT));
		items.add(new IListItemData(text,icon,path));
		list.setListData(items);
	}

	

	public void init(){

		list = new JList();
		list.setCellRenderer(new IListCellRender());
		Vector<IListItemData> items = new Vector<IListItemData>();

		
//
//		IListItemData data1 = new IListItemData("1",new ImageIcon("tmp/5.jpg"));
//		IListItemData data2 = new IListItemData("2",new ImageIcon("tmp/6.jpg"));
//		IListItemData data3 = new IListItemData("3",new ImageIcon("tmp/7.jpg"));

//		items.add(data1);
//		items.add(data2);
//		items.add(data3);
		
		list.setListData(items);
		listScro = new JScrollPane(list);
		listScro.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(listScro);

	}

	

//	public void showMe(){

//		this.setSize(200,400);

//		this.setVisible(true);

//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//	}

	

	public static void main(String[] args) {

		IList ilist =new IList();
		ilist.addItem("tmp/5.jpg", "125","music/3.mp3", 20, 20);
//		new Frame();  

	}

}
