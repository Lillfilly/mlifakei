package se.ixanon.filmhandler.client.objects.channels;

import se.ixanon.filmhandler.client.ChannelConfig;
import se.ixanon.filmhandler.shared.Channel;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Image;

public class ChannelCellTable extends CellTable<Channel> {

	ChannelConfig config;
	
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
	
	EditButtonCell editButton = new EditButtonCell();
	Column<Channel, String> editCol = new Column<Channel, String>(editButton) {
		@Override
		public String getValue(Channel object) {
			editButton.setup(object, config);
			return null;
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
	
	public ChannelCellTable(final ChannelConfig config) {
		
		this.config = config;
		
		toggleCol.setFieldUpdater(new FieldUpdater<Channel, String>(){
			@Override
			public void update(int index, Channel object, String value) {
				redraw();
			}
		});
		
		addColumn(nameCol);
		addColumn(videoCol);
		addColumn(editCol);
		addColumn(toggleCol);
	}
}
