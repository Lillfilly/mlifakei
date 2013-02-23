package se.ixanon.filmhandler.client.objects.channels;

import se.ixanon.filmhandler.client.services.ChannelService;
import se.ixanon.filmhandler.client.services.ChannelServiceAsync;
import se.ixanon.filmhandler.shared.Channel;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class EditDialog extends DialogBox {
	
	ChannelServiceAsync channelService = GWT.create(ChannelService.class);
	
	VerticalPanel vPanel = new VerticalPanel();
	
	HTML header = new HTML();
	ListBox videoList = new ListBox(false);
	
	public EditDialog(Channel channelToBeEdited) {
		
		header.setHTML("<h3>Configure Channel: " + channelToBeEdited.name + "</h3>");
		
		videoList.ensureDebugId("cwListBox-dropBox");
		
		vPanel.add(header);
		vPanel.add(videoList);
	}
}
