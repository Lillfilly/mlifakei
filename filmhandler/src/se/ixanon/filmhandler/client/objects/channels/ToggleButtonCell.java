package se.ixanon.filmhandler.client.objects.channels;

import se.ixanon.filmhandler.client.services.ChannelService;
import se.ixanon.filmhandler.client.services.ChannelServiceAsync;
import se.ixanon.filmhandler.shared.Channel;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class ToggleButtonCell extends AbstractCell<String>{
	private static final SafeHtml pressedHtml = SafeHtmlUtils.fromSafeConstant("<input " +
																			   "class=\"gwt-Button\" " +
																			   "type=\"submit\" " +
																			   "value=\"On\" " +
																			   "style=\"color:green\" " +
																			   "aria-pressed=\"true\"> " +
																			   "</input>");
	private static final SafeHtml upHtml = SafeHtmlUtils.fromSafeConstant("<input " +
																		  "class=\"gwt-Button\" " +
																		  "type=\"submit\" " +
																		  "value=\"Off\" " +
																		  "style=\"color:red\" " +
																		  "aria-pressed=\"false\">" +
																		  "</input>");
	private Channel current = null;
	private ChannelServiceAsync channelService = GWT.create(ChannelService.class);
	
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
				channelService.editChannel(current, new AsyncCallback<Void>() {
					@Override
					public void onSuccess(Void result) {
						
					}
					
					@Override
					public void onFailure(Throwable caught) {
						Window.alert(caught.getMessage());
					}
				});
			}
		}
		valueUpdater.update(value);
	}
}
