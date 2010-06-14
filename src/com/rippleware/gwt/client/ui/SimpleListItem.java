package com.rippleware.gwt.client.ui;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.ComplexPanel;

public class SimpleListItem extends ComplexPanel {
	
	public SimpleListItem(){
		setElement( DOM.createElement("li") );
	}
	
	public SimpleListItem(String text, boolean html){
		setElement( DOM.createElement("li") );
		if( html ){
			getElement().setInnerHTML( text );
		}else{
			getElement().setInnerText( text );	
		}
		
	}
	


}
