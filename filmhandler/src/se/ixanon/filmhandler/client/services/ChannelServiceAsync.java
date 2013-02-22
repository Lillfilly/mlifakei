package se.ixanon.filmhandler.client.services;

import java.util.ArrayList;

import se.ixanon.filmhandler.shared.Channel;
import se.ixanon.filmhandler.shared.MovieItem;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ChannelServiceAsync {

	void getChannels(AsyncCallback<ArrayList<Channel>> callback);
	void editChannel(Channel editedChannel, AsyncCallback<Void> callback);
	void getVideos(AsyncCallback<ArrayList<MovieItem>> callback);
	void deleteVideos(ArrayList<MovieItem> deleteList,
			AsyncCallback<Void> callback);
	void fileExists(String fileName, AsyncCallback<Boolean> callback);

}
