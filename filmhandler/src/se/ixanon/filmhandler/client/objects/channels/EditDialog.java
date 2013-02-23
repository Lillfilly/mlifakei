package se.ixanon.filmhandler.client.objects.channels;

import java.util.ArrayList;

import se.ixanon.filmhandler.client.ChannelConfig;
import se.ixanon.filmhandler.client.services.ChannelService;
import se.ixanon.filmhandler.client.services.ChannelServiceAsync;
import se.ixanon.filmhandler.shared.Channel;
import se.ixanon.filmhandler.shared.MovieItem;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class EditDialog extends DialogBox {
	
	ChannelServiceAsync channelService = GWT.create(ChannelService.class);
	
	ChannelConfig p;
	Channel c;
	
	VerticalPanel vPanel = new VerticalPanel();
	
	HTML header = new HTML();
	ListBox videoList = new ListBox(false);
	HorizontalPanel buttonPanel = new HorizontalPanel();
	
	Button btn_Ok = new Button("Ok", new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
			channelService.editChannel(c,new AsyncCallback<Void>() {
				@Override
				public void onFailure(Throwable caught) {
					Window.alert("ChannelEdit failed");
				}
				@Override
				public void onSuccess(Void result) {
					p.UpdateChannels();
					hide();
				}
			});
		}
	});
	Button btn_Close = new Button("Cancel", new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
			hide();
		}
	});
	
	public EditDialog(final Channel channelToBeEdited, int index, ChannelConfig parent) {
		
		c = channelToBeEdited;
		p = parent;
		
		vPanel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
		
		videoList.setStyleName("EditDialog_ListBox", true);
		videoList.ensureDebugId("cwListBox-dropBox");
		
		btn_Close.setStyleName("EditDialog_DialogButtonNo", true);
		
		header.setHTML("<h3>Edit channel</h3> " + c.name);
		buttonPanel.add(btn_Ok);
		buttonPanel.add(btn_Close);
		
		videoList.addItem("Loading videos...");
		videoList.setEnabled(false);
		btn_Ok.setEnabled(false);
		
		channelService.getVideos(new AsyncCallback<ArrayList<MovieItem>>() {
			@Override
			public void onSuccess(ArrayList<MovieItem> result) {
				videoList.clear();
				videoList.setEnabled(true);
				btn_Ok.setEnabled(true);
				for (MovieItem m : result) {
					videoList.addItem(m.getName() + m.getType());
				}
			}
			@Override
			public void onFailure(Throwable caught) {
			}
		});
		
		vPanel.add(header);
		vPanel.add(videoList);
		vPanel.add(buttonPanel);
		
		setWidget(vPanel);
	}
}
