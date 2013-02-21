package se.ixanon.filmhandler.client.objects;

import se.ixanon.filmhandler.client.services.UploadProgressService;
import se.ixanon.filmhandler.client.services.UploadProgressServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ProgressDialog extends DialogBox {
	
	UploadProgressServiceAsync uploadProgress = GWT.create(UploadProgressService.class);
	boolean uploading = true;
	
	VerticalPanel panel = new VerticalPanel();
	
	ProgressBar progressBar = new ProgressBar(200, 20, 100);
	Label l = new Label();
	
	Button btn_Close = new Button("Close", new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) 
		{
			hide();
		}
	});
	public ProgressDialog() {
		panelSetup();
		
		btn_Close.setEnabled(false);
		
	}
	private void panelSetup()
	{	
		panel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
		panel.add(new HTML("<h2>Upload</h2>"));
		panel.add(progressBar.getWidget());
		panel.add(l);
		panel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
		panel.add(btn_Close);
		
		setWidget(panel);
		
		getProgress();
	}

	private void getProgress() {
		
		Timer t = new Timer() {
			
			@Override
			public void run() {
				if(uploading == false)
				{
					l.setText("Upload complete!");
					progressBar.setValue(100);
					cancel();
					return;
				}
				else
				{
					uploadProgress.getProgressPercent(new AsyncCallback<Integer>() {
						
						@Override
						public void onSuccess(Integer result) {
							
							if(result > 90)
								result = 90;
							
							l.setText("Please wait... " + result.toString() + "%");
							progressBar.setValue(result);
						}
						
						@Override
						public void onFailure(Throwable caught) {
							l.setText(caught.getMessage());
							progressBar.setValue(0);
						}
					});
				}
				
			}
		};
		
		t.scheduleRepeating(100);
		t.run();
		
	}
	public void SubmitComplete()
	{
		uploading = false;
		btn_Close.setEnabled(true);
	}
}
