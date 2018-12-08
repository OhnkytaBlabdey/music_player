package sound;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import gui.GlobalVars;


//import javazoom.jl.player.advanced.PlaybackListener;


public class MusicManager{
	
	public enum LoopMode{
		SINGLE,
		LIST,
		RAND,
		NONLOOP_SINGLE,
		NONLOOP_LIST,
	}
	
	public enum CMD{
		WAITING,
		PLAY,
		STOP,
		PAUSE,
		CHANGE,
		EXIT,
		
	}
	
	public enum PlayerStatus{
		UNCREATED,
		UNLOADED,
		UNSTARTED,
		PLAYING,
		PAUSED,
//		STOPPED
		
	}
	
	static public sound.MP3Player player;
//	private Collection<Player> history;
//	private sound.Player last;
	
	private File[] musics;
	private File current;
	public File next_song;
//	private boolean changing;
	
	private LoopMode mode;
	private PlayerStatus status;
//	private CMD cmd;
	private int index;
	private boolean ended;
	
	public static void main(String[] args) {

		MusicManager manager ;

		Scanner scan= new Scanner(System.in);

		String[] files = new String[6];
		for(int i=0;i<6;++i) {
			files[i]="./music/"+(i+1)+".mp3";
		}
		manager= new MusicManager(files);
//		manager.Play();
		
		
		int i=scan.nextInt();
		
		while(i!=0) {
			if(i<7) {
				manager.Pause();
				manager.Change(i-1);
			}
			else if(i==8) {
				manager.Pause();
			}
			else if(i==9) {
				manager.Resume();
			}else if(i==7) {
				manager.Stop();
			}
			i=scan.nextInt();
		}
		scan.close();
		manager.Stop();
		manager.Exit();
		System.out.println("Main end");
		System.exit(0);
	}
	
	public MusicManager(String[] filenames) {

		mode=LoopMode.LIST;
		status=PlayerStatus.UNCREATED;
		new PlayerListener().start();

		musics=new File[filenames.length];
		for(int i=0;i<filenames.length;i++) {
			musics[i]=new File(filenames[i]);
		}
		current=musics[0];

		player=new MP3Player(musics[0].getAbsolutePath(),null);
		index=0;
		ended=false;
	}

	public PlayerStatus getStatus() {
		return status;
	}
	public void setStatus(PlayerStatus s) {
		status=s;
	}
	
	public boolean isPlaying() {
		return player.isPlaying();
	}
	
	public boolean hasEnded() {
		return ended;
	}
	
	public void setCurrent(File f) {
		current=f;
		//TODO
		if(status==PlayerStatus.UNLOADED || status==PlayerStatus.UNSTARTED) {
		status=PlayerStatus.UNCREATED;
		}
		GlobalVars.play_b.setPauseIcon();
		Change(current);

	}
	
	
	public void Play() {
		System.out.println("\n\nLoopMode is : "+mode);
		
		switch (mode) {
		case NONLOOP_SINGLE:
			player.Stop();
			player.Release();
			break;
		case SINGLE:
			System.out.println("[single loop] : "+current.getName()+" index "+index);
			Change(current);
			break;
		case LIST:
		case NONLOOP_LIST:
			index++;
			if(mode==LoopMode.NONLOOP_LIST && index==musics.length) break;

			index=index%musics.length;
			current=musics[index];
			System.out.println("[Music]: current playing is "+current.getName()+" - index "+index);
			Change(current);
			break;

		default:
			break;
		}
//		player.Start();
	}
	
	public void Change(File f) {
		if(status==PlayerStatus.PLAYING||status==PlayerStatus.PAUSED) {
			Stop();
		}
		if(status==PlayerStatus.UNCREATED) {
		System.out.println(f.getName()+"\tis going to start.");
		player=new MP3Player(f.getAbsolutePath(),null);
		status=PlayerStatus.UNSTARTED;
		Start();
		}
		else if(status==PlayerStatus.UNLOADED) {
			player.setFile(f.getAbsolutePath());
			Start();
		}
		for(int i=0;i<musics.length;i++) {
			if(musics[i].getAbsolutePath().equals(current.getAbsolutePath())) {
				index=i;
			}
		}
	}
	
	public void Next() {
		Change((index+1)%musics.length);
	}
	
	public void Change(int i) {
		index=i;
		Change(musics[index]);
	}

	public sound.Player getPlayer() {
		return player;
	}
	
	private void Start() {
		if(status==PlayerStatus.UNSTARTED) {
			player.start();
			status=PlayerStatus.PLAYING;
		}else {
			System.err.println("[Music]: current status is "+status+" cannot START.");
		}
	}
	public void Pause() {
		if(status==PlayerStatus.PLAYING) {
			player.suspend();
			status=PlayerStatus.PAUSED;
		}else {
			System.err.println("[Music]: current status is "+status+" cannot PAUSE.");
		}
	}
	public void Resume() {
		if(status==PlayerStatus.PAUSED) {
			player.resume();
			status=PlayerStatus.PLAYING;
		}else {
			System.err.println("[Music]: current status is "+status+" cannot RESUME.");
		}
	}
	public void Stop() {
		if(status==PlayerStatus.PLAYING || status == PlayerStatus.PAUSED) {
//			player.interrupt();
			player.stop();
			player.getInnerPlayer().close();
//			player.Release();
			status=PlayerStatus.UNCREATED;
			player=null;
		}else {
			System.err.println("[Music]: current status is "+status+" cannot STOP.");
		}
	}
	
	public void Exit() {
	}
	public LoopMode getLoopMode() {
		return mode;
	}
	public void setLoopMode(LoopMode loopMode) {
		mode=loopMode;
	}
	public void setMusicList(File[] files) {
		if(files.length<1) {

			return;
		}
		musics=files;
		current=files[0];
		System.out.println("[Music]: setMusicList, first file is "+current.getAbsolutePath());
	}
	public File getCurrentMusic() {
		return current;
	}
}

class Mycb implements CallBacker{

	private MusicManager manager;
	public Mycb(MusicManager m) {
		manager=m;
	}
	
	@Override
	public void callback() {
		System.out.println("call back has started.");
		manager.Play();
		System.out.println("call back has ended.");
		// if last thread is stopped, then this object is freed. or so?
	}
	
}

