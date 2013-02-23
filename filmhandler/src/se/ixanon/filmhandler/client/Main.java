package se.ixanon.filmhandler.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;

public class Main implements EntryPoint {

	TabPanel tabPanel = new TabPanel();
	String[] tabTitles = new String[] {"Channels", "Videos"};
	
	@Override
	public void onModuleLoad() {
		
		setupTabPanel();
		RootPanel.get("ConfigHolder").add(tabPanel);


	}
	private void setupTabPanel()
	{
		tabPanel.setWidth("100%");
		
		ChannelConfig channelConfig = new ChannelConfig();
		tabPanel.add(channelConfig.getWidget(), tabTitles[0]);
		
		@SuppressWarnings("unused")
		VideoList fileList = new VideoList();
		tabPanel.add(fileList.getWidget(), tabTitles[1]);
		
		
		tabPanel.selectTab(0);
	}

}
