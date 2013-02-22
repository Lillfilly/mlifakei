package se.ixanon.filmhandler.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class MovieItem implements Serializable {
	private String m_name;
	private String m_type;
	
	
	public MovieItem(String name,String type) {
		m_name = name;
		m_type = type;
	}
	public MovieItem() {
		m_name = "";
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
}
