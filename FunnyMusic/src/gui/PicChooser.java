package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class PicChooser extends JFileChooser {
	public PicChooser() {
		// TODO Auto-generated constructor stub
		addChoosableFileFilter(new FileFilter() {
			
			@Override
			public String getDescription() {
				// TODO Auto-generated method stub
				return " *.jpeg, *.png";
			}
			
			@Override
			public boolean accept(File f) {
				// TODO Auto-generated method stub
				if(f.getName().endsWith(".jpg") || f.isDirectory()||f.getName().endsWith(".png")||f.getName().endsWith(".jpeg") ) return true;
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
				if(!(f.getName().endsWith(".jpg") || f.isDirectory()||f.getName().endsWith(".png")||f.getName().endsWith(".jpeg") )) {
					setSelectedFile(null);
				}
			}
		});
	}
}
