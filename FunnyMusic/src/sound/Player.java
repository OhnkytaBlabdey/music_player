package sound;

public interface Player {
	
	public void Start() ;
	public void Pause() ;
	public void Resume() ;
	public void Stop() ;
	public void Release() ;
	public boolean isPlaying() ;
	
	public void setFile(String file) ;
	public javazoom.jl.player.Player getInnerPlayer() ;
//	public void setLoop(boolean b);
}
