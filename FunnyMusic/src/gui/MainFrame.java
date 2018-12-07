package gui;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import other.ConfKit;


public class MainFrame {

	public static void main(String[] args) {
		initUI();
	}
	public MainFrame() {
		// TODO Auto-generated constructor stub
	}
	
	public static void initUI() {
		JFrame frame=GlobalVars.getFrame();
		
		MainFrame.init();
		SceneBG bg=new SceneBG();
		bg.setLayout(new BorderLayout());
//		frame.add(bg,BorderLayout.CENTER);
//		frame.setLayout(new BorderLayout());
		frame.setIconImage(new ImageIcon("conf/textures/logo.png").getImage());
		
		bg.add(new PlayBar(),BorderLayout.PAGE_END);
		bg.add(new TitleBar(),BorderLayout.PAGE_START);
//		frame.add(new SceneBG(),BorderLayout.CENTER);
		bg.add(new SongsList(),BorderLayout.LINE_END);
		bg.add(new FavLists(), BorderLayout.LINE_START);
		
		bg.set((int)ConfKit.getScreenSize().getWidth(), (int)ConfKit.getScreenSize().getHeight());
		frame.add(bg);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(300, 130);
		frame.setSize(600, 400);
		frame.setUndecorated(true);
		frame.setVisible(true);
		GlobalVars.frame=frame;
	}
	
	public static void init() {
//		initUI();
		GlobalVars.getMusic();
		
		GlobalVars.getFrame().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				GlobalVars.x=e.getX();
				GlobalVars.y=e.getY();
//				System.out.println("pressed ");
			}
		});
		
		GlobalVars.getFrame().addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				GlobalVars.getFrame().setLocation(GlobalVars.frame.getLocation().x + e.getX() - GlobalVars.x , GlobalVars.frame.getLocation().y + e.getY() -GlobalVars.y);
			}
		});
		
		
	}
}
