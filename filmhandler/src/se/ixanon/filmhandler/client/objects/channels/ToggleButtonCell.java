package se.ixanon.filmhandler.client.objects.channels;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.view.client.SelectionModel;

public class ToggleButtonCell<C> extends AbstractCell<String>{
	private SelectionModel<C> selectionModel;
	private static final SafeHtml pressedHtml = SafeHtmlUtils.fromSafeConstant("<button " +
																			   "type=\"button\" " +
																			   "class=\"toggleButton-down\" " +
																			   "tabindex=\"-1\" " +
																			   "aria-pressed=\"true\">");
	private static final SafeHtml upHtml = SafeHtmlUtils.fromSafeConstant("<button " +
																		  "	type=\"button\" " +
																		  "	class=\"toggleButton-up\" " +
																		  "	tabindex=\"-1\" " +
																		  "	aria-pressed=\"false\">" +
																		  "<p style=\"color:red;\">Offline</p>" +
																		  "</button>");
	private static boolean pressed = false;
	
	@Override
	public void render(Context context, String value, SafeHtmlBuilder sb) {
		if(value == null)
			return;
		
		//To avoid XSS attack!? No idea, but sounds good.
		SafeHtml safeValue = SafeHtmlUtils.fromSafeConstant(value);
		
		if(pressed){
			sb.append(pressedHtml);
		}else{
			sb.append(upHtml);
		}
	}
	
	public static interface Delegate<T> {
		
	}
}
