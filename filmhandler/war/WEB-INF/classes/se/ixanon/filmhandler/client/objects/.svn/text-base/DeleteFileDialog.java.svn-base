package se.ixanon.filmhandler.client.objects;


import java.util.ArrayList;

import se.ixanon.filmhandler.client.FileList;
import se.ixanon.filmhandler.client.services.FileListService;
import se.ixanon.filmhandler.client.services.FileListServiceAsync;
import se.ixanon.filmhandler.shared.MovieItem;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class DeleteFileDialog extends DialogBox {
	
	final FileListServiceAsync fileservice = GWT.create(FileListService.class);
	
	VerticalPanel vPanel = new VerticalPanel();
	HorizontalPanel buttonPanel = new HorizontalPanel();
	
	public DeleteFileDialog(final ArrayList<MovieItem> deleteList, final FileList parent) {
		
		Button btn_OK = new Button("Delete", new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				fileservice.deleteItems(deleteList, new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Oops, någon gick fel på servern");
					}

					@Override
					public void onSuccess(Void result) {
						parent.updateFileList();
					}
				});
				
				hide();
			}
		});
		Button btn_NO = new Button("Cancel",new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				hide();
			}
		});
		
		btn_OK.setStyleName("deleteDialogButtonOK", true);
		btn_NO.setStyleName("deleteDialogButtonNO", true);
		
		buttonPanel.add(btn_OK);
		buttonPanel.add(btn_NO);
		
		vPanel.setWidth("250px");
		
		vPanel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
		vPanel.add(new HTML("<h2>Are you sure?</h2>"));
		vPanel.setHorizontalAlignment(VerticalPanel.ALIGN_DEFAULT);
		vPanel.add(buttonPanel);
		setWidget(vPanel);
	}
}
