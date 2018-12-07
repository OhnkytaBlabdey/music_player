package other;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;


/**
 * ���MP3�ļ�����Ϣ
 * 
 */
public class MP3Info {
	
	public static void main(String[] args) {
		//TODO ��ʾ
		File MP3FILE = new File("test.mp3");
		try {
			MP3Info info = new MP3Info(MP3FILE);
			info.setCharset("UTF-8");
			System.out.println(info.getSongName());
			System.out.println(info.getArtist());
			System.out.println(info.getAlbum());
			System.out.println(info.getYear());
			System.out.println(info.getComment());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	private String charset = "utf-8";//����MP3��Ϣʱ�õ��ַ�����
	
	private byte[] buf;//MP3�ı�ǩ��Ϣ��byte����
	
	/**
	 * ʵ����һ�����MP3�ļ�����Ϣ����
	 * @param mp3 MP3�ļ�
	 * @throws IOException ��ȡMP3�������MP3�ļ�������
	 */
	public MP3Info(File mp3) throws IOException{
		
		buf = new byte[128];//��ʼ����ǩ��Ϣ��byte����
		
		RandomAccessFile raf = new RandomAccessFile(mp3, "r");//�����д��ʽ��MP3�ļ�
		raf.seek(raf.length() - 128);//�ƶ����ļ�MP3ĩβ
		raf.read(buf);//��ȡ��ǩ��Ϣ
		
		raf.close();//�ر��ļ�
		
		if(buf.length != 128){//�����Ƿ�Ϸ�
			throw new IOException("MP3��ǩ��Ϣ���ݳ��Ȳ��Ϸ�!");
		}
		
		if(!"TAG".equalsIgnoreCase(new String(buf,0,3))){//��Ϣ��ʽ�Ƿ���ȷ
			throw new IOException("MP3��ǩ��Ϣ���ݸ�ʽ����ȷ!");
		}
		
	}

	/**
	 * ���Ŀǰ����ʱ�õ��ַ�����
	 * @return Ŀǰ����ʱ�õ��ַ�����
	 */
	public String getCharset() {
		return charset;
	}

	/**
	 * ���ý���ʱ�õ��ַ�����
	 * @param charset ����ʱ�õ��ַ�����
	 */
	public void setCharset(String charset) {
		this.charset = charset;
	}
	
	public String getSongName(){
		try {
			return new String(buf,3,30,charset).trim();
		} catch (UnsupportedEncodingException e) {
			return new String(buf,3,30).trim();
		}
	}
	
	public String getArtist(){
		try {
			return new String(buf,33,30,charset).trim();
		} catch (UnsupportedEncodingException e) {
			return new String(buf,33,30).trim();
		}
	}
	
	public String getAlbum(){
		try {
			return new String(buf,63,30,charset).trim();
		} catch (UnsupportedEncodingException e) {
			return new String(buf,63,30).trim();
		}
	}
	
	public String getYear(){
		try {
			return new String(buf,93,4,charset).trim();
		} catch (UnsupportedEncodingException e) {
			return new String(buf,93,4).trim();
		}
	}
	
	public String getComment(){
		try {
			return new String(buf,97,28,charset).trim();
		} catch (UnsupportedEncodingException e) {
			return new String(buf,97,28).trim();
		}
	}
	
	
}
