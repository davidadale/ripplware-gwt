package com.rippleware.gwt.client.ui;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.HTML;

public abstract class ListItem extends ComplexPanel implements Comparable<ListItem>{

	HTML descrHtml = new HTML();
	LinkBar links = new LinkBar();

	protected ListItem() {
		setElement( DOM.createElement("li") );
		add( descrHtml, getElement() );
		add( links, getElement() );
	}
	
	protected void setHtml(String html){
		descrHtml.setHTML( html );
	}
	
	protected void addActionLink( Anchor anchor ){
		links.addLink( anchor );
	}
	
	public abstract Long getId();
	
	@Override
	public boolean equals(Object obj) {
		return getId().equals( ((ListItem)obj).getId() );
	}
	
	@Override
	public int hashCode() {
		int value = 31 * getId().hashCode();
		return value;
	}
	
}

