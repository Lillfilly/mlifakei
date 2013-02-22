package se.ixanon.filmhandler.server;

import java.util.ArrayList;

import se.ixanon.filmhandler.client.services.ChannelService;
import se.ixanon.filmhandler.shared.Channel;
import se.ixanon.filmhandler.shared.MovieItem;

public class ChannelServiceImpl implements ChannelService {

	@Override
	public ArrayList<Channel> getChannels() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void editChannel(Channel editedChannel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<MovieItem> getVideos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteVideos(ArrayList<MovieItem> deleteList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean fileExists(String fileName) {
		// TODO Auto-generated method stub
		return false;
	}

}
