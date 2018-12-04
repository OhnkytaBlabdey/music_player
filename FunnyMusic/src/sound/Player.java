package sound;


public interface Player {
	
	public void Start() ;
	public void Pause() ;
	public void Resume() ;
	public void Stop() ;
	public void Release() ;
	public boolean isPlaying() ;
//	public void setLoop(boolean b);
}
