package at.int32.sweaty.ui.controls;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;

import at.int32.sweaty.ui.Control;

public class Button extends Widget<org.eclipse.swt.widgets.Button>{

	public Button(Control parent) {
		super(parent);
	}
	
	public Button(Control parent, int style) {
		super(parent, style);	
	}
	
	public void text(String text){
		this.ctrl.setText(text);
	}
	
	public void selection(SelectionListener listener){
		this.ctrl.addSelectionListener(listener);
	}
	
	public Button background(Color color) {
		ctrl.setBackground(color);
		return this;
	}
	
	public Button width(int width) {
		data().widthHint = width;
		return this;
	}

	@Override
	public org.eclipse.swt.widgets.Button getBaseControl(Composite parent,
			int style) {
		return new org.eclipse.swt.widgets.Button(parent, SWT.None | style);
	}

}
