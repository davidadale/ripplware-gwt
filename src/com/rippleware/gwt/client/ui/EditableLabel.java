package com.rippleware.gwt.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.InlineHTML;
import com.google.gwt.user.client.ui.TextArea;

public class EditableLabel extends Composite implements HasValue<String> {

	DeckPanel panel;
	
	FlowPanel readOnlyView;
	FlowPanel editableView;

	InlineHTML label;
	Anchor edit;
	
	TextArea textArea;
	
	LinkBar linkBar;
	
	Anchor save;
	Anchor cancel;	

	String emptyMessage = "";
	
	public EditableLabel(){
		
		panel = new DeckPanel();
		panel.setHeight("100px");
		readOnlyView = new FlowPanel();
		editableView = new FlowPanel();
		editableView.setHeight("75px");

		label = new InlineHTML();
		
		readOnlyView.add( label );
		
		editableView.add(textArea = new TextArea());
		textArea.setWidth("100%");
		textArea.setHeight("75px");

		save = new Anchor("Save");
		cancel = new Anchor("Cancel");
		
		linkBar = new LinkBar();
		linkBar.addLink( save );
		linkBar.addLink( cancel );
		editableView.add( linkBar );
		
		panel.add(readOnlyView );
		panel.add(editableView) ;
		
		initWidget( panel );
		
		save.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				setValue( textArea.getText() );
				panel.showWidget( 0 );
			}
		});
		
		cancel.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				textArea.setText( label.getText() );
				panel.showWidget( 0 );
			}
		});		
		
		panel.showWidget( 0 );
		
	}
	
	public void edit(){
		panel.showWidget( 1 );
		textArea.setFocus(true);		
	}
	
	public HasClickHandlers getEditButton(){
		return edit;
	}
	
	public HasClickHandlers getSaveButton(){
		return save;
	}
	
	public HasClickHandlers getCancelButton(){
		return cancel;
	}

	public String getValue() {
		return textArea.getValue();
	}

	public void setValue(String value) {
		if( value==null || value.length()==0 ){
			label.setText( emptyMessage );
		}else{
			label.setText( value );
			textArea.setValue( value );
		}
	}

	public void setValue(String value, boolean fireEvents) {
		
		if( value==null || value.length()==0 ){
			label.setText( emptyMessage );
		}else{
			label.setText( value );
			textArea.setValue( value, fireEvents );
		}
		
		
		
	}

	public void setEmptyMessage(String message){
		this.emptyMessage = message;
	}
	
	public HandlerRegistration addValueChangeHandler(
			ValueChangeHandler<String> handler) {
		return textArea.addValueChangeHandler(handler);
	}
	
	
}
