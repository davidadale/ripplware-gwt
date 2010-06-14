package com.rippleware.gwt.client.ui;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class MessageBar extends Composite {

	private static MessageBarUiBinder uiBinder = GWT
			.create(MessageBarUiBinder.class);

	interface MessageBarUiBinder extends UiBinder<Widget, MessageBar> {
	}
	 
	interface MessageBarStyle extends CssResource{
		String error();
		String info();
	}

	@UiField
	UnorderedList list;
	
	@UiField
	MessageBarStyle style;
	
	public MessageBar() {
		initWidget( uiBinder.createAndBindUi(this) );
	}
	public void error( List<String> errors ){
		
		list.clear();
		for(String error: errors){
			list.add( new SimpleListItem( error,false ) );
		}
		getElement().removeClassName( style.info() );
		getElement().addClassName( style.error() );
		showMessageBar( getElement() );
	}
	
	public void info(List<String> msgs ){
		list.clear();
		for( String msg: msgs ){
			list.add( new SimpleListItem( msg,false ) );
		}
		getElement().removeClassName( style.error() );
		getElement().addClassName( style.info() );
		showMessageBar( getElement() );
	}
	
	
	public void error( String message ){
		list.clear();
		list.add( new SimpleListItem(message,false) );
		getElement().removeClassName( style.info() );
		getElement().addClassName( style.error() );
		showMessageBar( getElement() );
	}
	
	public void info( String message ){
		
		list.clear();
		list.add( new SimpleListItem( message,false ) );
		getElement().removeClassName( style.error() );
		getElement().addClassName( style.info() );
		showMessageBar( getElement() );
	}

	protected native void showMessageBar(Element e) /*-{
		$wnd.jQuery(e).fadeIn(700).delay(2000).fadeOut()
	}-*/;

}
