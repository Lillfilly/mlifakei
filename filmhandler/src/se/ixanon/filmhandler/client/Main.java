package se.ixanon.filmhandler.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;

public class Main implements EntryPoint {

	TabPanel tabPanel = new TabPanel();
	String[] tabTitles = new String[] {"Channels", "Videos"};
	
	@Override
	public void onModuleLoad() {
		
//		setupTabPanel();
//		RootPanel.get("TabTest").add(tabPanel);
		
		//Add the Filehandlerthing
		@SuppressWarnings("unused")
		FileList fileList = new FileList();
		RootPanel.get("Filmhanteraren").add(fileList.getWidget());
	}
	private void setupTabPanel()
	{
		tabPanel.add(new HTML("Channel Configuration"), tabTitles[0]);
		tabPanel.add(new HTML("<h3>Video Config</h3>"), tabTitles[1]);
		
		tabPanel.selectTab(0);
	}

}
