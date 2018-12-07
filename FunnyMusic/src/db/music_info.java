package db;

public class music_info {

	public String song;
	public int sheet_id;
	public String md5;
	public String path;
	public music_info(String song,int sheet_id,String md5,String path) {
		this.song=song;
		this.sheet_id=sheet_id;
		this.md5=md5;
		this.path=path;
	}
	public music_info() {
//		this.song=null;
//		this.sheet_id=0;
//		this.md5=null;
//		this.path=null;
		}

	@Override
	public String toString() {
		return " song name  "+song +"\n sheet_id  "+sheet_id +"\n md5  "+md5 +"\n path  "+path+"\n";
	}
}
