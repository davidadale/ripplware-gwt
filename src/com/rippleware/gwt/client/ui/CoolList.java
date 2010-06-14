package com.rippleware.gwt.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.Widget;

public class CoolList extends ResizeComposite {

	private static CoolListUiBinder uiBinder = GWT
			.create(CoolListUiBinder.class);

	interface CoolListUiBinder extends UiBinder<Widget, CoolList> {
	}
	
	interface CoolListCss extends CssResource {
		String coolList();
	}

	@UiField
	CoolListCss style;
	
	@UiField
	UnorderedList ul;

	public CoolList() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void add(ListItem item){
		ul.add( item );
	}
	
	public void remove(ListItem item){
		ul.remove( item );		
	}

}
