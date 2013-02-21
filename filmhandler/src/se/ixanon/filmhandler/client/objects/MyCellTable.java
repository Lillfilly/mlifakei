package se.ixanon.filmhandler.client.objects;
import se.ixanon.filmhandler.shared.MovieItem;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;

public class MyCellTable extends CellTable<MovieItem> {	
	
	/* COLUMNS ----------------------------*/
	private TextColumn<MovieItem> nameCol = new TextColumn<MovieItem>() {

		@Override
		public String getValue(MovieItem item) {
			// TODO Auto-generated method stub
			return item.getName();
		}
	};
	private TextColumn<MovieItem> convertedCol = new TextColumn<MovieItem>() {

		@Override
		public String getValue(MovieItem item) {
			
			if(item.isConverted())
			{
				return "Converted";
			}
			return "Not converted";
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
		//addColumn(convertedCol);
	}
}
