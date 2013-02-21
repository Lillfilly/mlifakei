package se.ixanon.filmhandler.server;

import javax.servlet.http.HttpSession;

import se.ixanon.filmhandler.client.services.UploadProgressService;
import se.ixanon.filmhandler.server.objects.MyProgressListener;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class UploadProgressServlet extends RemoteServiceServlet implements UploadProgressService {

	@Override
	public int getProgressPercent() {
		HttpSession session = getThreadLocalRequest().getSession();
		
		if(session == null)
		{
			return -35505;
		}
		if(session.getAttribute("ProgressListener") == null)
		{
			return 0;
		}
		MyProgressListener listener = (MyProgressListener) session.getAttribute("ProgressListener");
		
		return listener.getPercentDone();
	}

}
