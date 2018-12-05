package sound;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;


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
	
	private sound.Player player;
	private Collection<Player> history;
//	private sound.Player last;
	
	private File[] musics;
	private File current;
//	private boolean changing;
	
	private LoopMode mode;
//	private CMD cmd;
	private int index;
	private boolean ended;
	
	public static void main(String[] args) {

		MusicManager manager ;
		//= new MusicManager(new String[] {"*.mp3"});
//		System.out.println(manager.getPlayer());
		
		Scanner scan= new Scanner(System.in);
		
		
		String[] files = new String[6];
		for(int i=0;i<6;++i) {
			files[i]="./music/"+(i+1)+".mp3";
		}
		manager= new MusicManager(files);
//		manager.setLoopMode(LoopMode.SINGLE);
		manager.Play();
		
		
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
			}
			i=scan.nextInt();
		}
		scan.close();
		manager.Stop();
		manager.Exit();
		System.out.println("Main end");
	}
	
	public MusicManager(String[] filenames) {

		mode=LoopMode.LIST;
		history=new ArrayList<Player>();

		musics=new File[filenames.length];
		for(int i=0;i<filenames.length;i++) {
			musics[i]=new File(filenames[i]);
		}
		current=musics[0];

		player=new MP3Player(musics[0].getAbsolutePath(),new CallBacker() {
			
			@Override
			public void callback() {
				Play();
			}
		});
		index=0;
		ended=false;
	}
	
	public boolean isPlaying() {
		return player.isPlaying();
	}
	
	public boolean hasEnded() {
		return ended;
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
//			System.out.println("entered play method. step 1");
			index++;
			if(mode==LoopMode.NONLOOP_LIST && index==musics.length) break;

			index=index%musics.length;
			current=musics[index];
			System.out.println("file : "+current.getName()+" index "+index);
			Change(current);
//			System.out.println("entered play method. step 3");
			break;

		default:
			break;
		}
//		player.Start();
	}
	
	public void Change(File f) {

		System.out.println("isplaying : "+player.isPlaying()); //true. ??
//		if(player!=null)
//		{
//			player.Stop();
//			player.Release();
//		}
		System.out.println(f.getName()+"\tis going to start.");
		player=new MP3Player(f.getAbsolutePath(),new CallBacker() {
			@Override
			public void callback() {
				Play();
			}
		});
		history.add(player);
		player.Start();
	}
	
	public void Change(int i) {
		index=i;
		Change(musics[index]);
	}
	
	
	public void changeMusic() {
		player.Stop();
		index++;
		player=null;
		current=musics[index];
		player=new MP3Player(current.getAbsolutePath(),new Mycb(this));
		player.Start();
	}
	
	public sound.Player getPlayer() {
		return player;
	}
	
	public void Start() {
		player.Start();
		System.out.println("player thread for "+current.getName()+" has started.");
	}
	public void Pause() {
		player.Pause();
	}
	public void Resume() {
		player.Resume();
	}
	public void Stop() {
		player.Stop();
		player.Release();
		player=null;
	}
	
	public void Exit() {
		for(Player p:history) {
//			p.Pause();
			p.Stop();
			p.Release();
		}
	}
	
	public LoopMode getLoopMode() {
		return mode;
	}
	public void setLoopMode(LoopMode loopMode) {
		mode=loopMode;
	}
	
	public void setMusicList(File[] files) {
		musics=files;
		current=files[0];
		System.out.println("setMusicList"+current.getName());
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

