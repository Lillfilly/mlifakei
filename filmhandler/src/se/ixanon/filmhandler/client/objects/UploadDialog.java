package se.ixanon.filmhandler.client.objects;

import se.ixanon.filmhandler.client.VideoList;
import se.ixanon.filmhandler.client.services.FileListService;
import se.ixanon.filmhandler.client.services.FileListServiceAsync;
import se.ixanon.filmhandler.client.services.UploadProgressService;
import se.ixanon.filmhandler.client.services.UploadProgressServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;

public class UploadDialog extends DialogBox {
	
	UploadProgressServiceAsync uploadProgress = GWT.create(UploadProgressService.class);
	FileListServiceAsync fileListService = GWT.create(FileListService.class);
	
	boolean uploading = true;
	
	ProgressBar progressBar = new ProgressBar(200, 20, 100);
	Label l = new Label();
	
	FormPanel formpanel = new FormPanel();
	VerticalPanel vPanel = new VerticalPanel();
	FileUpload fileUpload = new FileUpload();
	HorizontalPanel horPanel = new HorizontalPanel();
	
	Button btn_Submit = new Button("Upload", new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			
			//See if the file already exists
			fileListService.fileExists(fileUpload.getFilename(), new AsyncCallback<Boolean>() {
				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Oops. Something went wrong on the server.");
				}
	
				@Override
				public void onSuccess(Boolean result) {
					
					if(result == true)
					{
						Window.alert("File already exists.");
					}
					else
					{
						
						if(!fileUpload.getFilename().isEmpty())
						{
							if(fileUpload.getFilename().endsWith(".mp4") || fileUpload.getFilename().endsWith(".ts"))
							{
								getProgress();
								formpanel.submit();
								changeToProgress();
								btn_Close.setEnabled(false);
							}
							else
							{
								Window.alert("You can only upload .mp4 and .ts files");
							}
						}
						else
						{
							Window.alert("No file selected");
						}
					}
				}
				
			});
		}
	});
	Button btn_Close = new Button("Close", new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			hide();
			
		}
	});
	
	public UploadDialog(final VideoList parent) {
		
		btn_Submit.setStyleName("deleteDialogButtonOK", true);
		btn_Close.setStyleName("deleteDialogButtonNO", true);
		
		horPanel.add(btn_Submit);
		horPanel.add(btn_Close);
		
		vPanel.add(new HTML("<h1>Upload video</h1>"));
		vPanel.add(fileUpload);
		vPanel.add(new HTML("<br />"));
		vPanel.add(horPanel);
		
		fileUpload.setName("uploadElement");
		
		formpanel.setWidget(vPanel);
		//formpanel.setAction("/fileupload");
		formpanel.setAction("fileupload");
		formpanel.setEncoding(FormPanel.ENCODING_MULTIPART);
		formpanel.setMethod(FormPanel.METHOD_POST);
		
		
		formpanel.addSubmitCompleteHandler(new SubmitCompleteHandler() {
			
			@Override
			public void onSubmitComplete(SubmitCompleteEvent event) {
				//Window.alert(event.getResults());
				SubmitComplete();
				
				parent.updateVideoList();
			}
		});
		
		setWidget(formpanel);
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
		
		t.scheduleRepeating(500);
		t.run();	
	}
	private void changeToProgress()
	{	
		vPanel.clear();
		vPanel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
		vPanel.add(new HTML("<h2>Upload</h2>"));
		vPanel.add(progressBar.getWidget());
		vPanel.add(l);
		vPanel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
		btn_Close.setStyleName("deleteDialogButtonNO", false);
		vPanel.add(btn_Close);
	}
	public void SubmitComplete()
	{
		uploading = false;
		btn_Close.setEnabled(true);
	}
}