package sound;


import java.util.Scanner;

import javazoom.jlgui.basicplayer.*;

public class MusicPlayer extends BasicPlayerTest{

	public static void main(String[] args) {
		MusicPlayer music = new MusicPlayer();
		music.play("music/1.mp3");
		Scanner scan= new Scanner(System.in);
		scan.nextLine();
		try {
			music.getControl().setGain(1.5);
		} catch (BasicPlayerException e) {
			e.printStackTrace();
		}
		scan.close();
	}
	
}
