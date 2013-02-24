package se.ixanon.filmhandler.client.objects;
import se.ixanon.filmhandler.shared.MovieItem;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;

public class MyCellTable extends CellTable<MovieItem> {	
	
	/* COLUMNS ----------------------------*/
	private TextColumn<MovieItem> nameCol = new TextColumn<MovieItem>() {

		@Override
		public String getValue(MovieItem item) {
			return item.getName();
		}
	};
	private TextColumn<MovieItem> typeCol = new TextColumn<MovieItem>() {

		@Override
		public String getValue(MovieItem item) {
			
			return item.getType();
		}
	};
	/*COLUMNS -------------------------------*/
	
	
	public MyCellTable() 
	{
		addColumn(nameCol);
		addColumn(typeCol);
	}
}
