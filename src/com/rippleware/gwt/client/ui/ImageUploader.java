package com.rippleware.gwt.client.ui;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ErrorEvent;
import com.google.gwt.event.dom.client.ErrorHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;

public class ImageUploader extends Composite {

	FlowPanel formContainer;
	Image image;
	Image fullImage;

	String croppedUrl;
	String uncroppedUrl;
	static Hidden x1,y1,x2,y2,width,height;
	
	DialogBox dialog = null;

	public interface Images extends ClientBundle {
		@Source("head.png")
		ImageResource emptyPlaceHolder();
	}

	Images images;

	public ImageUploader() {
		formContainer = new FlowPanel();
		formContainer.add(image = new Image());
		image.addErrorHandler(new ErrorHandler() {
			public void onError(ErrorEvent event) {
				image.setUrl(images.emptyPlaceHolder().getURL());
			}
		});

		images = GWT.create(Images.class);
		initWidget(formContainer);
		
		x1 = new Hidden("x1","");
		x2 = new Hidden("x2","");
		y1 = new Hidden("y1","");
		y2 = new Hidden("y2","");
		width = new Hidden("width","");
		height = new Hidden("height","");

	}

	public void setCroppedImageUrl(String url) {
		this.croppedUrl = url;
	}

	public void setUnCroppedImageUrl(String url) {
		this.uncroppedUrl = url;
	}

	public void showImage() {
		if (croppedUrl != null) {
			image.setUrl(croppedUrl);
		} else {
			image.setUrl(images.emptyPlaceHolder().getURL());
		}
	}

	public void showEditor() {
		createAndShowDialog();
	}

	public HasClickHandlers getClearLink() {
		return null;
	}

	public HasClickHandlers getCancelLink() {
		return null;
	}

	public HasClickHandlers getSaveLink() {
		return null;
	}

	protected void createAndShowDialog() {
		dialog = new DialogBox();
		dialog.setTitle("Edit Image");
		dialog.setText("Edit Image");
		dialog.setGlassEnabled(true);

		DeckPanel deck = new DeckPanel();
		// deck.setWidth("300px");
		// deck.setHeight("300px");
		deck.add(createEmptyForm(deck));
		deck.add(createCroppingForm(deck));
		deck.showWidget(0);

		dialog.add(deck);
		dialog.center();
		dialog.show();

	}

	protected Widget createEmptyForm(final DeckPanel deck) {

		FlowPanel outer = new FlowPanel();

		FlowPanel formContents = new FlowPanel();
		Image empty = new Image(images.emptyPlaceHolder().getURL());

		HorizontalPanel centerPanel = new HorizontalPanel();
		centerPanel.setWidth("100%");
		centerPanel.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
		centerPanel.add(empty);
		formContents.add(centerPanel);

		FileUpload file = new FileUpload();
		file.setName("userImage");
		formContents.add(file);

		FlowPanel buttons = new FlowPanel();
		Button upload = new Button("upload");
		Button cancel = new Button("cancel");
		buttons.add(upload);
		buttons.add(cancel);

		outer.add(formContents);
		outer.add(buttons);

		final FormPanel form = new FormPanel();
		form.setMethod(FormPanel.METHOD_POST);
		form.setEncoding(FormPanel.ENCODING_MULTIPART);
		form.setAction(uncroppedUrl);
		form.add(outer);
		
		form.addSubmitCompleteHandler(new SubmitCompleteHandler() {
			public void onSubmitComplete(SubmitCompleteEvent event) {
				fullImage.setUrl(uncroppedUrl);
				deck.showWidget(1);
				clipPhoto( fullImage.getElement() );
			}
		});


		upload.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				form.submit();
			}
		});		
		
		cancel.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialog.hide();
			}
		});
		
		return form;
	}

	protected Widget createCroppingForm(final DeckPanel deck) {

		FlowPanel outer = new FlowPanel();

		FlowPanel imagePanel = new FlowPanel();
		imagePanel.setWidth("300px");
		imagePanel.setHeight("300px");

		fullImage = new Image();
		imagePanel.add( fullImage );

		FlowPanel buttons = new FlowPanel();
		Button save = new Button("Save");
		Button cancel = new Button("Cancel");
		buttons.add(save);
		buttons.add(cancel);

		outer.add(imagePanel);
		outer.add(buttons);

		final FormPanel form = new FormPanel();
		form.setMethod(FormPanel.METHOD_POST);
		form.setAction(croppedUrl);
		outer.add(x1);
		outer.add(y1);
		outer.add(x2);
		outer.add(y2);
		outer.add(width);
		outer.add(height);
		form.add(outer);

		save.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				form.submit();
			}
		});
		
		cancel.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialog.hide();
			}
		});
		
		form.addSubmitCompleteHandler(new SubmitCompleteHandler() {
			public void onSubmitComplete(SubmitCompleteEvent event) {
				dialog.hide();
				Date date = new Date();// change the url to force the browser to reload the image
				image.setUrl( croppedUrl + "&t=" + date.getTime() );
			}
		});
		
		
		
		return form;
	}

	protected static void setClippingArea(double x1, double y1, double x2, double y2, int width, int height){
		
		ImageUploader.x1.setValue( String.valueOf(x1) );
		ImageUploader.y1.setValue( String.valueOf(y1) );
		ImageUploader.x2.setValue( String.valueOf(x2) );
		ImageUploader.y2.setValue( String.valueOf(y2) );
		ImageUploader.width.setValue( String.valueOf(width ) );
		ImageUploader.height.setValue( String.valueOf(height) );
		
	}

	public native void clipPhoto(Element e)/*-{
		$wnd.jQuery(e).Jcrop({
			setSelect:[90,20,190,120],
			onSelect:function(c){
			@com.rippleware.gwt.client.ui.ImageUploader::setClippingArea(DDDDII)
			(c.x,c.y,c.x2,c.y2,e.width,e.height)
		}});
	}-*/;

}
