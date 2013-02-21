package se.ixanon.filmhandler.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class MovieItem implements Serializable {
	private String m_name;
	private String m_type;
	private boolean m_converted;
	
	
	public MovieItem(String name,String type, boolean converted) {
		m_name = name;
		m_type = type;
		m_converted = converted;
	}
	public MovieItem() {
		m_name = "";
		m_converted = false;
	}

	public String getName() {
		return m_name;
	}

	public void setName(String name) {
		this.m_name = name;
	}
	public String getType()
	{
		return m_type;
	}
	public void setType(String type)
	{
		m_type = type;
	}

	public boolean isConverted() {
		return m_converted;
	}

	public void setConverted(boolean converted) {
		this.m_converted = converted;
	}
	
}
