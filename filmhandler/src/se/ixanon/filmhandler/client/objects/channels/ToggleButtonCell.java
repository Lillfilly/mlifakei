package se.ixanon.filmhandler.client.objects.channels;

import se.ixanon.filmhandler.shared.Channel;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.Window;

public class ToggleButtonCell extends AbstractCell<String>{
	private static final SafeHtml pressedHtml = SafeHtmlUtils.fromSafeConstant("<button " +
																			   "type=\"button\" " +
																			   "class=\"toggleButtonDown\" " +
																			   "tabindex=\"-1\" " +
																			   "aria-pressed=\"true\">" +
																			   "<p style=\"color:green\">Streaming</p>" +
																			   "</button>");
	private static final SafeHtml upHtml = SafeHtmlUtils.fromSafeConstant("<button " +
																		  "	type=\"button\" " +
																		  "	class=\"toggleButtonUp\" " +
																		  "	tabindex=\"-1\" " +
																		  "	aria-pressed=\"false\">" +
																		  "<p style=\"color:red;\">Offline</p>" +
																		  "</button>");
	private static boolean pressed = false;
	private Channel current = null;
	
	public ToggleButtonCell(){
		super("click");
	}
	
	public void setChannel(Channel t){
		current = t;
	}
	
	@Override
	public void render(Context context, String value, SafeHtmlBuilder sb) {
		if(value == null)
			return;
		
		if(current != null){
			if(current.streaming == true){
				sb.append(pressedHtml);
			}else{
				sb.append(upHtml);
			}
		}
	}

	@Override
	public void onBrowserEvent(Context context, Element parent, String value, NativeEvent event, ValueUpdater<String> valueUpdater){
		super.onBrowserEvent(context, parent, value, event, valueUpdater);
		
		if("click".equals(event.getType())){
			//Ignore events that occur outside the element
			EventTarget eventTarget = event.getEventTarget();
			if(parent.getFirstChildElement().isOrHasChild(Element.as(eventTarget))){
				current.streaming = !current.streaming;
			}
		}
		valueUpdater.update(value);
	}
}
