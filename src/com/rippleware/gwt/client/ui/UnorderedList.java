package com.rippleware.gwt.client.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Widget;

public class UnorderedList extends ComplexPanel {
	
	List<ListItem> items = new ArrayList<ListItem>();
	
	public UnorderedList() {
		setElement(DOM.createElement("ul"));
	}

	public void add(SimpleListItem item){
		super.add( item, getElement() );
	}
	
	public void add(ListItem w) {
		
		if( !items.contains(w ) ){
			items.add( w );
			Collections.sort( items );
			int index = items.indexOf( w );
			insert( w, index );
		}else{
			
			Collections.sort( items );
			int index = items.indexOf( w );
			items.add( w );
			insert( w, index );
			remove( index );
			
		}
			
	}

	@Override
	public boolean remove(Widget w) {
		items.remove( w );
		return super.remove(w);
	}
	
	public void insert(ListItem w, int beforeIndex) {
		super.insert(w, getElement(), beforeIndex, true);
	}
	
	public boolean isEmpty(){
		return (getChildren() == null) || (getChildren().size()==0);
	}
}
