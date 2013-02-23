package se.ixanon.filmhandler.client.objects.channels;

import se.ixanon.filmhandler.client.services.ChannelService;
import se.ixanon.filmhandler.client.services.ChannelServiceAsync;
import se.ixanon.filmhandler.shared.Channel;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class EditDialog extends DialogBox {
	
	ChannelServiceAsync channelService = GWT.create(ChannelService.class);
	
	VerticalPanel vPanel = new VerticalPanel();
	
	HTML header = new HTML();
	ListBox videoList = new ListBox(false);
	
	Button btn_Close = new Button("Close", new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
			hide();
		}
	});
	
	public EditDialog(Channel channelToBeEdited, int index) {
		
		header.setHTML("<h3>Edit channel: " + channelToBeEdited.name + "</h3>");
		
		videoList.ensureDebugId("cwListBox-dropBox");
		
		vPanel.add(header);
		vPanel.add(videoList);
		vPanel.add(btn_Close);
		
		setWidget(vPanel);
	}
}
