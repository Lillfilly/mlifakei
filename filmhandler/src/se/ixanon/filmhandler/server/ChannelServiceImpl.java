package se.ixanon.filmhandler.server;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import se.ixanon.filmhandler.client.services.ChannelService;
import se.ixanon.filmhandler.shared.Channel;
import se.ixanon.filmhandler.shared.MovieItem;

public class ChannelServiceImpl extends RemoteServiceServlet implements ChannelService {
	XmlReader xmlReader = new XmlReader();
	
	public ChannelServiceImpl(){
		buildXml("./config.xml");
	}
	
	public void buildXml(String path){
		xmlReader.parseFile(path);
		xmlReader.build();
		//xmlReader.printChannelInformation();
	}
	
	@Override
	public ArrayList<Channel> getChannels() {
		return xmlReader.getChannels();
	}

	@Override
	public void editChannel(Channel editedChannel) {
		xmlReader.editChannel(editedChannel);
	}

	@Override
	public ArrayList<MovieItem> getVideos() {
		String videoDir = xmlReader.getVideoDirectory();
		ArrayList<MovieItem> movies = new ArrayList<>();
		
		//ts, mp4
		String extension = "";
		File dir = new File(videoDir);
		for(File path : dir.listFiles()){
			extension = path.getName().substring(path.getName().lastIndexOf('.'));
			
			if(extension.equals(".mp4") || extension.equals(".ts")){
				MovieItem mvi = new MovieItem();
				mvi.setName(path.getName().substring(0, path.getName().lastIndexOf('.')));
				mvi.setType(extension);
				movies.add(mvi);
			}
		}
		return movies;
	}

	@Override
	public void deleteVideos(ArrayList<MovieItem> deleteList) {
		String videoDir = xmlReader.getVideoDirectory();
		
		for(MovieItem i : deleteList){
			File f = new File(videoDir + "/" + i.getName() + "." + i.getType());
			
			//Only attempt the removal if it acually exists
			if(f.exists()){
				//If it failed to delete, print an error message
				if(f.delete() == false){
					System.err.println("Kunde inte ta bort filmen " + videoDir + "/" + i.getName());
				}
			}
		}
	}

	@Override
	public boolean fileExists(String fileName) {
		String videoDir = xmlReader.getVideoDirectory();
		File dir = new File(videoDir);
		
		if(dir.listFiles() != null){
			for(File f : dir.listFiles()){
				if(f.getName().equals(fileName)){
					return true;
				}
			}
		}
		return false;
	}

}
