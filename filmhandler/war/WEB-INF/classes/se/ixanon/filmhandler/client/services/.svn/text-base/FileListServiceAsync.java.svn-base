package se.ixanon.filmhandler.client.services;

import java.util.ArrayList;

import se.ixanon.filmhandler.shared.MovieItem;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface FileListServiceAsync {

	void getFiles(AsyncCallback<ArrayList<MovieItem>> callback);

	void deleteItems(ArrayList<MovieItem> deleteList, AsyncCallback<Void> callback);

	void fileExists(String fileName, AsyncCallback<Boolean> callback);

}
