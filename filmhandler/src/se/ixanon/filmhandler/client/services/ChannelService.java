package se.ixanon.filmhandler.client.services;

import java.util.ArrayList;

import se.ixanon.filmhandler.shared.Channel;
import se.ixanon.filmhandler.shared.MovieItem;

import com.google.gwt.user.client.rpc.RemoteService;

public interface ChannelService extends RemoteService {
	
	ArrayList<Channel> getChannels();
	void editChannel(Channel editedChannel);
	
	ArrayList<MovieItem> getVideos();
	void deleteVideos(ArrayList<MovieItem> deleteList);
	
	boolean fileExists(String fileName);
}
