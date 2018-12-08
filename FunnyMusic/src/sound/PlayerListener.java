package sound;

import gui.GlobalVars;
import sound.MusicManager.LoopMode;

public class PlayerListener extends Thread {

	public static void main(String[] args) {
		PlayerListener listener = new PlayerListener();
		listener.start();
		MP3Player mp3Player = new MP3Player("music/1.mp3", null);
		mp3Player.start();
	}
	@Override
	public void run() {
		synchronized (GlobalVars.music_ended) {
			while(true) {
				if(GlobalVars.music_ended[0]) {
					System.out.println("[Listener]: playing ended, changing next song");
					GlobalVars.music_ended[0]=false;
					/*
					 * GlobalVars.getMusic().Play();
					 */
					GlobalVars.getMusic().Play();
					if(GlobalVars.getMusic().getLoopMode()==LoopMode.LIST) {
						GlobalVars.songs_list.NextSong();
					}
					
					System.out.println("[Listener]: changing completed");
					GlobalVars.music_ended.notify();
//					try {System.in.read();} catch (IOException e) {e.printStackTrace();}
				}else {
					try {
						System.out.println("[Listener]: playing not ended, waiting");
						GlobalVars.music_ended.wait();
					} catch (InterruptedException e) {

						e.printStackTrace();
					}
					
				}
			}
		}
	}
}
