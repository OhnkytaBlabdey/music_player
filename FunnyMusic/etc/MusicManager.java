package sound;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.midi.*;
import javax.sound.sampled.*;

import java.util.*;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
//
//import sun.audio.AudioPlayer;
//import sun.audio.AudioStream;

public class MusicManager extends Thread {

	private int playmethod;
	private String filename;
	private boolean isplaying;
	
	public MusicManager(int p,String file) {
		// TODO Auto-generated constructor stub
		playmethod=p;
		filename=file;
		isplaying=true;
	}
	
	public static void main(String[] args) {
//		new Thread(new MusicManager(2,"23.mp3")).start();
//		Thread t1= new Thread(new MusicManager(2,"12.mp3"));
//		t1.start();
//		Thread t2=new Thread(new MusicManager(2,"23.mp3"));
//		t2.start();
		MusicManager music = new MusicManager(2, "../pracSound/23.mp3");
		music.start();
		music.pauseOrContinue();

		int play=0;
		Scanner scan= new Scanner(System.in);
		while(play<3) {
			play=scan.nextInt();
			if(play==1) {
				music.pauseOrContinue();
//				t2.suspend();
//				t1.resume();
//			}else if(play==2) {
//				t1.suspend();
//				t2.resume();
			}
			else {
				play=5;
				music.stop();
//				t1.stop();
//				t2.stop();
			}
		}
	}
	
	public boolean isPlaying() {
		return isplaying;
	}
	
	public void setFile(String file) {
		filename=file;
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
	
	
	public void play() {
        try {
//        	System.setProperty("javax.sound.midi.Sequencer","com.sun.media.sound.RealTimeSequencerProvider");
            // Get a sequencer and open it
            Sequencer player = MidiSystem.getSequencer();
            player.open();

//            Sequence seq = new Sequence(Sequence.PPQ, 4); //Treat the arguments as Ready-bake arguments

            Sequence seq=MidiSystem.getSequence(new File(filename));
            Track track = seq.createTrack(); // Ask the sequence for a track

            // Put some MidiEvents into the Track, the setMessage() method is what we should really care
            ShortMessage a = new ShortMessage();
            a.setMessage(144, 1, 44, 100);
            MidiEvent noteOn = new MidiEvent(a, 1);
            track.add(noteOn);

            ShortMessage b = new ShortMessage();
            b.setMessage(128, 1, 44, 100);
            MidiEvent noteOff = new MidiEvent(b, 16);
            track.add(noteOff); 

            player.setSequence(seq); // Give the sequence to the Sequencer
                                     // like pushing a CD to a CD player

            System.out.println("midi is starting playing.");
            player.start();  // Start the sequencer like pushing PLAY
            System.out.println("midi is being played.");
            
        }

        catch(Exception ex) {
            ex.printStackTrace();
        }
    }  // Close play

	public void play2() {
		Player player = null ;
		File file = new File(filename);
		BufferedInputStream buffer = null;
		try {
			buffer = new BufferedInputStream(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			player = new Player(buffer);
		} catch (JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
        	System.out.println("mp3 is starting playing.");
			player.play();
			System.out.println("mp3 is being played.");
		} catch (JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
//		if(!isplaying) {
//			this.suspend();
//		}
		isplaying=true;
		switch (playmethod) {
		case 1:
			play();
			break;

		case 2:
			play2();
			break;
			
		default:
			return;
		}
		
	}
}

