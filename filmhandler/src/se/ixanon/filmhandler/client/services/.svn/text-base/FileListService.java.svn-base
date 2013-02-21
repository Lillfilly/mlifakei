package se.ixanon.filmhandler.client.services;


import java.util.ArrayList;

import se.ixanon.filmhandler.shared.MovieItem;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("fileservice")
public interface FileListService extends RemoteService {
	ArrayList<MovieItem> getFiles();
	
	void deleteItems(ArrayList<MovieItem> deleteList);
	boolean fileExists(String fileName);
}
