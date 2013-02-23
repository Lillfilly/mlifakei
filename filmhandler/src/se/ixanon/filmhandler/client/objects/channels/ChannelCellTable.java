package se.ixanon.filmhandler.client.objects.channels;

import se.ixanon.filmhandler.shared.Channel;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Image;

public class ChannelCellTable extends CellTable<Channel> {

	private TextColumn<Channel> nameCol = new TextColumn<Channel>() {
		@Override
		public String getValue(Channel channel) {
			return channel.name;
		}
	};
	
	private TextColumn<Channel> videoCol = new TextColumn<Channel>() {

		@Override
		public String getValue(Channel channel) {
			return channel.video;
		}
	};
	
	private TextColumn<Channel> runningCol = new TextColumn<Channel>() {
		@Override
		public String getValue(Channel c) {
			if(c.streaming)
			{
				return "Streaming";
			}
			else
			{
				return "Offline";
			}
		}
	};
	
	ButtonCell editButton = new ButtonCell();
	Column<Channel, String> editCol = new Column<Channel, String>(editButton) {
		@Override
		public String getValue(Channel object) {
			return "Edit";
		}
	};
	
	public ChannelCellTable() {
		editCol.setFieldUpdater(new FieldUpdater<Channel, String>() {	
			@Override
			public void update(int index, Channel object, String value) {
				Window.alert("This is a secret message that only will appear if Oliver doesnt fail when trying to get the editbutton working!");
				EditDialog editDialog = new EditDialog(object);
			}
		});
		
		addColumn(nameCol);
		addColumn(videoCol);
		addColumn(runningCol);
		addColumn(editCol);
	
	}
}
