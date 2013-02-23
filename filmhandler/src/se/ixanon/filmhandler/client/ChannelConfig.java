package se.ixanon.filmhandler.client;

import java.util.ArrayList;

import se.ixanon.filmhandler.client.objects.channels.ChannelCellTable;
import se.ixanon.filmhandler.client.services.ChannelService;
import se.ixanon.filmhandler.client.services.ChannelServiceAsync;
import se.ixanon.filmhandler.shared.Channel;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;

public class ChannelConfig {
	
	ChannelServiceAsync channelService = GWT.create(ChannelService.class);
	
	VerticalPanel vPanel = new VerticalPanel();
	ChannelCellTable table = new ChannelCellTable(this);
	ListDataProvider<Channel> l = new ListDataProvider<Channel>();
	
	private Timer updateTimer = new Timer() {
		
		@Override
		public void run() {
			UpdateChannels();
		}
	};
	
	public ChannelConfig() {
		updateTimer.scheduleRepeating(30 * 1000);
		l.addDataDisplay(table);
		
		UpdateChannels();
		
		table.setStyleName("cellTable", true);
		
		vPanel.add(new HTML("<h1>Channel Configuration</h1>"));
		vPanel.add(table);
	}
	public void UpdateChannels()
	{
		channelService.getChannels(new AsyncCallback<ArrayList<Channel>>() {
			@Override
			public void onSuccess(ArrayList<Channel> result) {
				
				l.setList(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
			}
		});
	}
	public VerticalPanel getWidget()
	{
		return vPanel;
	}
}
