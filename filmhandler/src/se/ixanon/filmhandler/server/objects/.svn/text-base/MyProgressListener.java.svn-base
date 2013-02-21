package se.ixanon.filmhandler.server.objects;

import org.apache.commons.fileupload.ProgressListener;

public class MyProgressListener implements ProgressListener {

	
	private long num100Ks = 0;
	
	private long theBytesRead = 0;
	private long theContentLenght = -1;
	private int percentDone = 0;
	private boolean contentLengthKnown = false;
	
	@Override
	public void update(long bytesRead, long contentLength, int items) {
		
		if(contentLength > -1)
		{
			contentLengthKnown = true;
		}
		theBytesRead = bytesRead;
		theContentLenght = contentLength;
		
		long nowNum100Ks = bytesRead / 100000;
		if(nowNum100Ks > num100Ks)
		{
			num100Ks = nowNum100Ks;
			if(contentLengthKnown)
			{
				percentDone = (int) Math.round(100.0 * bytesRead / contentLength);
			}
		}
		
		
	}

	public long getNum100Ks() {
		return num100Ks;
	}

	public long getTheBytesRead() {
		return theBytesRead;
	}

	public long getTheContentLenght() {
		return theContentLenght;
	}

	public int getPercentDone() {
		return percentDone;
	}

}
