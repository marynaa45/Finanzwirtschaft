package at.int32.sweaty.examples;

import at.int32.sweaty.ui.Colors;
import at.int32.sweaty.ui.Control;
import at.int32.sweaty.ui.Grid;
import at.int32.sweaty.ui.View;

public class ResultView extends View {

	public ResultView(Control parent) {
		super(parent);
	}

	@Override
	public void onInit() {
		Grid window = new Grid(this).background(Colors.get(7, 135, 166));
	}

}
