package db;

public class sheet_info {
	
	public int id;
	public String name;
	public String date;
	public String user;
	public String path;
	public sheet_info() {
	}
	public sheet_info(int id,String name,String date,String user,String path){
		this.id=id;
		this.name=name;
		this.date=date;
		this.user=user;
		this.path=path;
	}
	@Override
	public String toString() {
		return " sheet_name  "+name +"\n id  "+id +"\n create_date  "+date +"\n sheet_user  "+user +"\n path  "+path+"\n";
	}
}
