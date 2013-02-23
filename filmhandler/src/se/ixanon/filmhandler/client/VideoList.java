package se.ixanon.filmhandler.client;

import java.util.ArrayList;

import se.ixanon.filmhandler.client.objects.DeleteFileDialog;
import se.ixanon.filmhandler.client.objects.MyCellTable;
import se.ixanon.filmhandler.client.objects.UploadDialog;
import se.ixanon.filmhandler.client.services.ChannelService;
import se.ixanon.filmhandler.client.services.ChannelServiceAsync;
import se.ixanon.filmhandler.client.services.FileListService;
import se.ixanon.filmhandler.client.services.FileListServiceAsync;
import se.ixanon.filmhandler.shared.Channel;
import se.ixanon.filmhandler.shared.MovieItem;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SelectionModel;

public class VideoList {
	VideoList me = this;
	
	final ChannelServiceAsync channelService = GWT.create(ChannelService.class);
	public ArrayList<MovieItem> cellFileList = new ArrayList<MovieItem>();
	
	VerticalPanel vPanel = new VerticalPanel();
	HorizontalPanel menuPanel = new HorizontalPanel();
	Label tipLabel = new Label("Tip: You can select multiple objects by holding CTRL or SHIFT");
	
	public MyCellTable cellTable = new MyCellTable();
	public SelectionModel<MovieItem> cellSelectionModel = new MultiSelectionModel<MovieItem>(new ProvidesKey<MovieItem>() {

		@Override
		public Object getKey(MovieItem item) {
			return item;
		}	
	});
	private ListDataProvider<MovieItem> cellDataProvider = new ListDataProvider<MovieItem>();
	
	private Timer updateTimer = new Timer() {
		
		@Override
		public void run() {
			updateVideoList();
		}
	};
	
	Button btn_Upload = new Button("Upload", new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			
			UploadDialog ud = new UploadDialog(me);
			ud.center();
		}
	});
	
	Button btn_Delete = new Button("Delete selected", new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
			
			ArrayList<MovieItem> deleteList = new ArrayList<MovieItem>();
			
			for (MovieItem item : cellFileList) {
				
				if(cellSelectionModel.isSelected(item))
				{
					deleteList.add(new MovieItem(item.getName(), item.getType()));
				}
			}
			
			if(!deleteList.isEmpty())
			{
				
				DeleteFileDialog deleteDialog = new DeleteFileDialog(deleteList, me);
				deleteDialog.center();
				
				updateVideoList();
			}
		}
	});
	
	Button btn_Test = new Button("Test shit", new ClickHandler(){
		@Override
		public void onClick(ClickEvent event) {
			updateChannelService();
		}
	});
	
	//Constructor
	public VideoList() {
		btn_Delete.setStyleName("FileListButton", true);
		
		menuPanel.add(btn_Upload);
		menuPanel.add(btn_Delete);
		menuPanel.add(btn_Test);
		
		updateTimer.scheduleRepeating(30 * 1000);
		
		cellTable.setStyleName("cellTable");
		cellTable.setSelectionModel(cellSelectionModel);
		cellDataProvider.addDataDisplay(cellTable);
		
		updateVideoList();
		
		vPanel.add(new HTML("<h1>Available videos</h1>"));
		vPanel.add(menuPanel);
		vPanel.add(cellTable);
		vPanel.add(tipLabel);
	}
	
	public VerticalPanel getWidget(){
		return vPanel;
	}
	
	public void updateVideoList()
	{
		cellFileList.clear();
		channelService.getVideos(new AsyncCallback<ArrayList<MovieItem>>() {

			@Override
			public void onSuccess(ArrayList<MovieItem> result) {
				if (result == null)
					return;
				
				for (MovieItem item : result) {
					cellFileList.add(new MovieItem(item.getName(),item.getType()));
				}
				
				if(!cellFileList.isEmpty()){
					cellTable.setVisible(true);
					btn_Delete.setVisible(true);
					cellDataProvider.setList(cellFileList);
					tipLabel.setText("Tip: You can select multiple objects by holding CTRL or SHIFT");
				}
				else{
					cellTable.setVisible(false);
					btn_Delete.setVisible(false);
					tipLabel.setText("No videos have been uploaded yet");
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}
		});
	}
	
	public void updateChannelService(){
		/*
		 * Implementation of the fileExists method
		 */
		channelService.fileExists("derp.mp4", new AsyncCallback<Boolean>(){
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(Boolean result) {
				
			}
		});
		
		/*
		 * Implementation of the deleteVideos method
		 */
		ArrayList<MovieItem> movies = new ArrayList<MovieItem>();
		MovieItem itm = new MovieItem();
		itm.setName("derp");
		itm.setType("mp4");
		movies.add(itm);
		channelService.deleteVideos(movies, new AsyncCallback<Void>() {
			@Override
			public void onSuccess(Void result) {
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}
		});
		
		/*
		 * Implementation of the getVideos method
		 */
		channelService.getVideos(new AsyncCallback<ArrayList<MovieItem>>() {
			@Override
			public void onSuccess(ArrayList<MovieItem> result) {
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}
		});
		
		/*
		 * Implementation of the getChannels method
		 */
		channelService.getChannels(new AsyncCallback<ArrayList<Channel>>() {
			@Override
			public void onSuccess(ArrayList<Channel> result) {
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}
		});
		
		/*
		 * Implementation of the editChannel method
		 */
		Channel toedit = new Channel();
		toedit.name = "channel1";
		toedit.streaming = true;
		toedit.video = "gtfo.mp4";
		channelService.editChannel(toedit, new AsyncCallback<Void>() {
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
