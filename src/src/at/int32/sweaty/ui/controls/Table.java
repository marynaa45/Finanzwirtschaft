package at.int32.sweaty.ui.controls;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import at.int32.sweaty.ui.Control;

public class Table extends Widget<org.eclipse.swt.widgets.Composite> {

	ArrayList<String> headers;

	public Table(Control parent, ArrayList<String> headres) {
		super(parent);

		this.headers = headres;
		this.ctrl.setLayout (new RowLayout (SWT.VERTICAL));
	
		
		TableColumn col = new TableColumn((org.eclipse.swt.widgets.Table) this.ctrl, SWT.NONE);
		col.setWidth(200);
		TableItem it = new TableItem((org.eclipse.swt.widgets.Table) this.ctrl, SWT.NONE);
		it.setText(0, "text");
		
		TableColumn col1 = new TableColumn((org.eclipse.swt.widgets.Table) this.ctrl, SWT.NONE);
		col.setWidth(200);
		TableItem it1 = new TableItem((org.eclipse.swt.widgets.Table) this.ctrl, SWT.NONE);
		it.setText(1, "text");
		// for (int loopIndex = 0; loopIndex < 10; loopIndex++) {
		// TableItem item = new TableItem(this.ctrl, SWT.NULL);
		// item.setText(0, "Asset " + (loopIndex + 1));
		// item.setText(1, "Yes");
		// item.setText(2, "No");
		// item.setText(3, "A table item");
		// }
		// this.ctrl.setBackground(Colors.get(45, 65, 134));;

	}
	
	public void test() {
		
		final org.eclipse.swt.widgets.Table table = new org.eclipse.swt.widgets.Table(
				this.ctrl, SWT.BORDER | SWT.MULTI);
		
		 for (int i=0; i<4; i++) {
			    TableColumn column = new TableColumn (table, SWT.NONE);
			    column.setText ("Column " + i);
			  }
			  final TableColumn [] columns = table.getColumns ();
			  for (int i=0; i<12; i++) {
			    TableItem item = new TableItem (table, SWT.NONE);
			    for (int j=0; j<columns.length; j++) {
			      item.setText (j, "Item " + i);
			    }
			  }
			  for (int i=0; i<columns.length; i++) columns [i].pack ();
			  Button button = new Button (this.ctrl, SWT.PUSH);
			  final int index = 1;
			  button.setText ("Insert Column " + index + "a");
			  button.addListener (SWT.Selection, new Listener () {
			    public void handleEvent (Event e) {
			      TableColumn column = new TableColumn (table, SWT.NONE, index);
			      column.setText ("Column " + index + "a");
			      TableItem [] items = table.getItems ();
			      for (int i=0; i<items.length; i++) {
			        items [i].setText (index, "Item " + i + "a");
			      }
			      column.pack ();
			    }
			  });
			  

	}

	@Override
	public org.eclipse.swt.widgets.Table getBaseControl(Composite parent,
			int style) {
		return new org.eclipse.swt.widgets.Table(parent, SWT.MULTI | SWT.BORDER);
	}

}
