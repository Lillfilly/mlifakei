package se.ixanon.filmhandler.client.objects;

import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class ProgressBar extends Widget {
	
	private float m_maxValue = 100.0f;
	private float m_Value = 0.0f;
	
	private int m_width = 0, m_height = 0;
	private float m_currentWidth = 0.0f;
	
	private SimplePanel backPanel = new SimplePanel();
	private SimplePanel frontPanel = new SimplePanel();
	
	public ProgressBar(int width, int height, int max) {
		
		m_width = width;
		m_height = height;
		
		backPanel.setStyleName("backPanel");
		frontPanel.setStyleName("frontPanel");
		
		backPanel.setWidth(String.valueOf(m_width) + "px");
		frontPanel.setWidth("0px");
		
		backPanel.setHeight(String.valueOf(m_height) + "px");
		frontPanel.setHeight(String.valueOf(m_height) + "px");
		
		backPanel.setWidget(frontPanel);
		
		m_maxValue = max;
	}
	private void Update()
	{
		m_currentWidth = m_Value / m_maxValue * m_width;
		
		frontPanel.setWidth(String.valueOf(m_currentWidth) + "px");
	}
	public float getValue() {
		return m_Value;
	}
	public void setValue(int Value) {
		if(Value < 0 || Value > m_maxValue)
		{
			return;
		}
		this.m_Value = Value;
		Update();
	}
	public SimplePanel getWidget()
	{
		return backPanel;
	}
	
}
