package sound;

import java.io.File;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.jlap;

public class MP3Player extends Thread implements sound.Player {

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
	
	
	private String filename;
	private boolean isplaying;

	
	private Player player;
	private File file;
	private CallBacker callBacker;
	
	BufferedInputStream buffer;
	
	public MP3Player(String file,CallBacker cb) {
		filename=file;
		isplaying=true;
		callBacker=cb;
//		setDaemon(true);
		setName(file);
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
		play();
//		callBacker.callback();
	}
	
	public void play() {
		
		file = new File(filename);
		buffer = null;
		try {
			buffer = new BufferedInputStream(new FileInputStream(file));
			
			player = new Player(buffer);

			if(player!=null) {
        	System.out.println("mp3 is starting playing.");
			player.play();
			player.close();
			System.out.println("mp3 has beed played over.");
			}
			callBacker.callback();
			isplaying=false;
//			Release();
			interrupt();
        }
        catch (JavaLayerException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void Start() {
		this.start();
	}

	@Override
	public void Pause() {
		if(this.isplaying)
		this.suspend();
		isplaying=false;
	}

	@Override
	public void Resume() {
		isplaying=true;
		this.resume();
	}

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
		try {
			
			this.interrupt();
			this.stop();
			this.finalize();
			System.out.println("finalized.");
			
		} catch (Throwable e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
