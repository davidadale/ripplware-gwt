package com.rippleware.gwt.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class LinkBar extends Composite {

	private static LinkBarUiBinder uiBinder = GWT.create(LinkBarUiBinder.class);

	interface LinkBarUiBinder extends UiBinder<Widget, LinkBar> {
	}
	
	@UiField
	FlowPanel linkBar;

	public LinkBar() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public void addLink(Anchor link){
		linkBar.add( link );
	}

}
