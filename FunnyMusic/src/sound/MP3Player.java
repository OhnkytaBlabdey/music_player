package sound;

import java.io.File;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


import gui.GlobalVars;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class MP3Player extends Thread implements sound.Player {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		// TODO Auto-generated method stub


		sound.Player player = new MP3Player( "../pracSound/23.mp3",new CallBacker() {
			
			@Override
			public void callback() {
			}
		});
		player.Start();
		
		
		int play=0;
		Scanner scan= new Scanner(System.in);
		while(play<10) {
			play=scan.nextInt();
			System.out.println(play);
			if(play==1) {
				player.Pause();
			}
			else if(play==2) {
				player.Resume();
			}else if (play<8) {
				player.Stop();
				player=new MP3Player("./music/"+(play-2)+".mp3",new CallBacker() {
					
					@Override
					public void callback() {						
					}
				});
				player.Start();

			}
			else if(play>=8){
				play=11;
				player.Stop();
			}
		}
	}
	
	
	@SuppressWarnings("unused")
	private String filename;
	private boolean isplaying;
	private boolean used=false;

	
	private Player player;
	private File file;
	@SuppressWarnings("unused")
	private CallBacker callBacker;
	
	BufferedInputStream buffer;
	
	public MP3Player(String path,CallBacker cb) {
		filename=path;
		isplaying=true;
		callBacker=cb;
//		setName(file);
		
		file = new File(path);
		buffer = null;
		
		try {
			buffer = new BufferedInputStream(new FileInputStream(file));
			player = new Player(buffer);
		} catch (JavaLayerException | FileNotFoundException e) {
			e.printStackTrace();
		}
		MusicManager.player=this;
	}
	
	public MP3Player(CallBacker cb) {
		callBacker=cb;
	}

	public boolean isPlaying() {
		return isplaying;
	}
	
	public void setFile(String file) {
		filename=file;
		try {
			buffer = new BufferedInputStream(new FileInputStream(file));
			player = new Player(buffer);
		} catch (FileNotFoundException | JavaLayerException e) {
			e.printStackTrace();
		}
	}
	public Player getInnerPlayer() {
		return player;
	}
	
	@SuppressWarnings("deprecation")
	public void pauseOrContinue() {
		if(isplaying) {
			isplaying=false;
			this.suspend();
		}
		else {
			isplaying=true;
			this.resume();
		}
	}
	
	@Override
	public void run() {
		if(used) return;
		used=true;
		synchronized (GlobalVars.music_ended) {
			if(GlobalVars.music_ended[0]) {
				try {
					throw new Exception("step error");
				} catch (java.lang.Exception e) {
					e.printStackTrace();
				}
			}
			GlobalVars.music_ended[0]=false;

			play();

			GlobalVars.music_ended[0]=true;
			GlobalVars.music_ended.notify();
		}
		
	}
	
	public void play() {

		try {
			if(player!=null) {
        	System.out.println("[Mp3Player]: mp3 is starting playing.");
			player.play();
			player.close();
			System.out.println("[Mp3Player]: mp3 has beed played over.");
			}
//			callBacker.callback();
			isplaying=false;
//			interrupt();
        }
        catch (JavaLayerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void Start() {
		this.start();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void Pause() {
		if(this.isplaying)
		this.suspend();
		isplaying=false;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void Resume() {
		isplaying=true;
		this.resume();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void Stop() {
//		shouldLoop=false;
		if(player==null) return;
		if(this.isAlive())
		player.close();
		player=null;
		isplaying=false;
		if(this.isAlive())
		this.stop();
	}
	@Override
	public void Release() {
		// TODO Auto-generated method stub
//		try {
//			throw new InterruptedException();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}

}
