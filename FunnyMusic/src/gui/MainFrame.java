package gui;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JFrame;


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
		frame.setLayout(new BorderLayout());
		frame.setIconImage(new ImageIcon("conf/textures/logo.png").getImage());
		
		frame.add(new PlayBar(),BorderLayout.PAGE_END);
		frame.add(new TitleBar(),BorderLayout.PAGE_START);
//		frame.add(new SceneBG(),BorderLayout.CENTER);
		frame.add(new SongsList(),BorderLayout.LINE_END);
		frame.add(new FavLists(), BorderLayout.LINE_START);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(300, 130);
		frame.setSize(300, 300);
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
