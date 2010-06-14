package com.rippleware.gwt.client.ui;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.MenuBar;

public class MenuItem extends com.google.gwt.user.client.ui.MenuItem implements
		HasCommand {

	private static final Command DEFAULT_COMMAND = new Command(){
		public void execute() {};
	};
	
	public MenuItem(String text){
		super( text, DEFAULT_COMMAND );
	}
	
	public MenuItem(java.lang.String text,
            Command cmd){
		super(text, cmd);
	}
	
	public MenuItem(java.lang.String text,
            boolean asHTML,
            MenuBar subMenu){
		super(text,asHTML,subMenu);
	}
	
	public MenuItem(String text, MenuBar subMenu) {
		super(text, subMenu);
	}

	public MenuItem(java.lang.String text,
            boolean asHTML,
            Command cmd){
		super( text, asHTML, cmd);
	}
	
	
}
