package gui;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class Mp3Chooser extends JFileChooser {

	public Mp3Chooser() {
		// TODO Auto-generated constructor stub
		addChoosableFileFilter(new FileFilter() {
			
			@Override
			public String getDescription() {
				// TODO Auto-generated method stub
				return "*.mp3";
			}
			
			@Override
			public boolean accept(File f) {
				// TODO Auto-generated method stub
				if(f.isDirectory()) return true;
				return f.getName().endsWith("mp3");
			}
		});
	}
}
