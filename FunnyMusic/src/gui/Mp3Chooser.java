package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class Mp3Chooser extends JFileChooser {

	public Mp3Chooser() {
		// TODO Auto-generated constructor stub
		setCurrentDirectory(new File("."));
		addChoosableFileFilter(new FileFilter() {
			
			@Override
			public String getDescription() {
				// TODO Auto-generated method stub
				return " *.mp3";
			}
			
			@Override
			public boolean accept(File f) {
				// TODO Auto-generated method stub
				if(f.getName().endsWith(".mp3") || f.isDirectory()||f.getName().endsWith(".MP3")||f.getName().endsWith(".Mp3") ) return true;
				return false;
				
			}
		});
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String cmd=e.getActionCommand();
				switch (cmd) {
				case "CancelSelection":
					setSelectedFile(null);
					break;
				default:
					break;
				}
				File f=getSelectedFile();
				if(!(f.getName().endsWith(".mp3") || f.isDirectory()||f.getName().endsWith(".MP3")||f.getName().endsWith(".Mp3") )) {
					setSelectedFile(null);
				}
			}
		});
	}
}
