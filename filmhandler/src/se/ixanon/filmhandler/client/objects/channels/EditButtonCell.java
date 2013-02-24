package se.ixanon.filmhandler.client.objects.channels;

import se.ixanon.filmhandler.client.ChannelConfig;
import se.ixanon.filmhandler.shared.Channel;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;

public class EditButtonCell extends AbstractCell<String> {

	ChannelConfig parent;
	Channel c;
	
	
	private static final SafeHtml DefaultHtml = SafeHtmlUtils.fromSafeConstant(
			"<input class=\"gwt-Button\" type=\"submit\" value=\"Edit\"> </input>");
	
	public EditButtonCell(){
		super("click");
		
	}
	
	@Override
	public void render(com.google.gwt.cell.client.Cell.Context context,
			String value, SafeHtmlBuilder sb) {
		sb.append(DefaultHtml);
	}
	
	public void setup(Channel c, ChannelConfig channelConfig)
	{
		this.parent = channelConfig;
		this.c = c;
	}
	
	@Override
	public void onBrowserEvent(Context context, Element parent, String value, NativeEvent event, ValueUpdater<String> valueUpdater){
		super.onBrowserEvent(context, parent, value, event, valueUpdater);
		
		if("click".equals(event.getType())){
			//Ignore events that occur outside the element
			EventTarget eventTarget = event.getEventTarget();
			if(parent.getFirstChildElement().isOrHasChild(Element.as(eventTarget))){
				
				EditDialog dialog = new EditDialog(c, this.parent);
				dialog.center();
			}
		}
		valueUpdater.update(value);
	}
}
