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
	
	ToggleButtonCell toggleTest = new ToggleButtonCell();
	Column<Channel, String> toggleCol = new Column<Channel, String>(toggleTest) {
		@Override
		public String getValue(Channel object) {
			toggleTest.setChannel(object);
			return object.name;
		}
	};
	
	public ChannelCellTable() {
		editCol.setFieldUpdater(new FieldUpdater<Channel, String>() {	
			@Override
			public void update(int index, Channel object, String value) {			
				EditDialog editDialog = new EditDialog(object, index);
				editDialog.center();
			}
		});
		
		toggleCol.setFieldUpdater(new FieldUpdater<Channel, String>(){
			@Override
			public void update(int index, Channel object, String value) {
				redraw();
			}
		});
		
		addColumn(nameCol);
		addColumn(videoCol);
		addColumn(runningCol);
		addColumn(editCol);
		addColumn(toggleCol);
	}
}
