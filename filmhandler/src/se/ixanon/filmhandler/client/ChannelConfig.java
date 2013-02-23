package se.ixanon.filmhandler.client;

import java.util.ArrayList;

import se.ixanon.filmhandler.client.objects.channels.ChannelCellTable;
import se.ixanon.filmhandler.client.services.ChannelService;
import se.ixanon.filmhandler.client.services.ChannelServiceAsync;
import se.ixanon.filmhandler.shared.Channel;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;

public class ChannelConfig {
	
	ChannelServiceAsync channelService = GWT.create(ChannelService.class);
	
//	ArrayList<Channel> cs = new ArrayList<Channel>();
	
	VerticalPanel vPanel = new VerticalPanel();
	ChannelCellTable table = new ChannelCellTable();
	ListDataProvider<Channel> l = new ListDataProvider<Channel>();
	
	public ChannelConfig() {
		
		l.addDataDisplay(table);
		
		channelService.getChannels(new AsyncCallback<ArrayList<Channel>>() {
			@Override
			public void onSuccess(ArrayList<Channel> result) {
				l.setList(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
			}
		});
		
		vPanel.add(new HTML("<h1>Channel Configuration</h1>"));
		vPanel.add(table);
	}
	public VerticalPanel getWidget()
	{
		return vPanel;
	}
}
