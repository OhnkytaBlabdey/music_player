package gui;

import sound.*;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileFilter;


public class PlayScene extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private MusicSheet musicSheet;
	private Choser choser;
	private PlayButton playButton;
	private MusicManager music;
	
	/*
	 * player inner state
	 * 
	 * */
	private boolean changed;
	private boolean inited=false;
	
	public static void main(String[] args) {
		new PlayScene();
	}
	public PlayScene() {
		// TODO Auto-generated constructor stub
//		JFrame frame = new JFrame("Devil Music");
		
		this.setTitle("Devil Music");
		musicSheet=new MusicSheet();
		this.add(musicSheet);
		music=new MusicManager(new String[]{"./music/1.mp3"});
//		music.start();
//		music.pauseOrContinue();
		
		choser=new Choser();
		choser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String cmd=e.getActionCommand();
				switch (cmd) {
				case "CancelSelection":
					System.exit(0);
					break;

				case "ApproveSelection":
					String name=choser.getSelectedFile().getAbsolutePath();
					System.out.println("Selected "+name);
					musicSheet.addMusic(name);
					changed=true;
//					inited=false;
					break;
				default:
					break;
				}
			}
		});
		this.add(choser);
		
		playButton=new PlayButton();
		this.add(playButton);
		playButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				System.out.println(e.getSource()+"\n****\n"+e.getActionCommand());
				String cmd=e.getActionCommand();
				switch (cmd) {
				case "PlayMusic":
					if(music.isPlaying()&&!changed) {
						music.Pause();
						break;
					}else {
						
						if(music.hasEnded()||!inited && !changed) {
					String name=musicSheet.getMusicName();
					System.out.println("PLay "+name);
					music.setMusicList(new File[] {new File(name)});
					music.Play();
					changed=false;
						}
						
						else {
							if(!changed)
							{
								music.Resume();
							}
							else {
								changed=false;
								music.Change(choser.getSelectedFile());
							}
						}
					}
					break;
				default:
					break;
				}
			}
		});
		
//		playButton.setMaximumSize(new Dimension(50, 20));
//		music.stop();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(200, 150);
		this.setSize(800,500);
		this.setLayout(new FlowLayout(15));
//		this.setLayout(new SpringLayout());
//		playButton.setLocation(166, 255);
		this.setVisible(true);
	}
}

class MusicSheet extends JPanel{
	private static final long serialVersionUID = 1L;
	protected JScrollPane scroll;
	protected String[] musics;
	protected DefaultListModel<String> model;
	protected JList<String> list;
	
	public void loadMusic() {
		int num=5;
		musics = new String[num];
		for(int i=0;i<musics.length;i++) {
			musics[i]= ""+i;
		}
	}
	public static DefaultListModel<String> getListModel(String[] strings)
	{
		DefaultListModel<String> model = new DefaultListModel<String>();
		model.addElement("                                ");
		for(String str:strings) {
			model.addElement(str);
		}
		
		return model;
	}
	public void addMusic(String name) {
		model.addElement(name);
	}
	public String getMusicName() {
		return list.getSelectedValue();
	}
	public MusicSheet() {
		// TODO Auto-generated constructor stub
		loadMusic();
		model=getListModel(musics);
		list = new JList<String>(model);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setSelectedIndex(2);
		list.setVisibleRowCount(4);
		
		scroll = new JScrollPane(list);
		this.add(scroll);
		
		list.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				JPopupMenu popupMenu=new JPopupMenu();
				popupMenu.add(new JMenuItem("添加歌曲"));
				
				System.out.println(e.getModifiers());
				if(e.getModifiers()==4) {
					popupMenu.show(list, 20, 20);
				}
			}
		});
		
		this.setBorder( new LineBorder(new Color(255, 128, 64)));
//		list.setSize(100, 60);
	}
}

class Choser extends JFileChooser{

	private static final long serialVersionUID = 1L;

	public Choser() {
		// TODO Auto-generated constructor stub
		this.addChoosableFileFilter(new FileFilter() {
			
			@Override
			public String getDescription() {
				// TODO Auto-generated method stub
				return "*.mp3";
			}
			
			@Override
			public boolean accept(File f) {
				// TODO Auto-generated method stub
				if(f.getName().endsWith(".mp3") || f.isDirectory()) return true;
				return false;
			}
		});
		setCurrentDirectory(new File("F:/peace/Music/"));
		
	}
}

class PlayButton extends JButton{
	public PlayButton() {
		// TODO Auto-generated constructor stub
		this.setActionCommand("PlayMusic");
		this.setText("Play!");
//		this.setMaximumSize(new Dimension(50, 20));
	}
	
}

