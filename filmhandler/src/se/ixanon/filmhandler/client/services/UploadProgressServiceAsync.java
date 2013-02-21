package se.ixanon.filmhandler.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UploadProgressServiceAsync {
	
	void getProgressPercent(AsyncCallback<Integer> callback);
}
