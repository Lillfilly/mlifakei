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
		
		Channel a = new Channel();
		a.streaming = true;
		a.name = "bert";
		
		Channel xx = new Channel();
		xx.name = "robin";
		
		Channel yy = new Channel();
		yy.name = "bob";
		cs.add(xx);
		cs.add(a);
		cs.add(yy);
		
		ListDataProvider<Channel> l = new ListDataProvider<Channel>(cs);
		l.addDataDisplay(table);
		
		int i = 0;
		for(Channel c : cs)
		{
			if(c.streaming)
			{
				table.getRowElement(i).getCells().getItem(2).setClassName("green");
			}
			else
			{
				table.getRowElement(i).getCells().getItem(2).setClassName("red");
			}
			i++;
		}
		
		vPanel.add(new HTML("<h1>Channel Configuration</h1>"));
		vPanel.add(table);
	}
	public VerticalPanel getWidget()
	{
		return vPanel;
	}
}
