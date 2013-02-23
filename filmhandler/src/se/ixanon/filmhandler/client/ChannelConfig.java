package se.ixanon.filmhandler.client;

import java.util.ArrayList;

import se.ixanon.filmhandler.client.objects.channels.ChannelCellTable;
import se.ixanon.filmhandler.shared.Channel;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;

public class ChannelConfig {
	VerticalPanel vPanel = new VerticalPanel();
	
	ChannelCellTable table = new ChannelCellTable();
	ArrayList<Channel> cs = new ArrayList<Channel>();
	
	public ChannelConfig() {
		
		cs.add(new Channel());
		ListDataProvider<Channel> l = new ListDataProvider<Channel>(cs);
		l.addDataDisplay(table);
		
		vPanel.add(new HTML("<h1>Channel Configuration</h1>"));
		vPanel.add(table);
	}
	public VerticalPanel getWidget()
	{
		return vPanel;
	}
}
