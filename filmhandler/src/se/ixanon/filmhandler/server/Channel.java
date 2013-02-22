package se.ixanon.filmhandler.server;
import java.io.Serializable;

//Contains information about each channel
public class Channel implements Serializable{
	private static final long serialVersionUID = 1L;
	public String name;
	public String video;
	public boolean streaming;
	
	public Channel(){
		name = "null";
		video = "null.mp4";
		streaming = false;
	}
}
