package gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;


public class MainFrame {

	public static void main(String[] args) {
		
	}
	public MainFrame() {
		// TODO Auto-generated constructor stub
	}
	public static void init() {
		
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
