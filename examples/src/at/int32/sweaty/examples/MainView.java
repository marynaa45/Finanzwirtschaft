package at.int32.sweaty.examples;


import java.util.ArrayList;

import org.eclipse.swt.SWT;

import at.int32.sweaty.ui.Colors;
import at.int32.sweaty.ui.Control;
import at.int32.sweaty.ui.Grid;
import at.int32.sweaty.ui.View;
import at.int32.sweaty.ui.annotations.OnClick;
import at.int32.sweaty.ui.annotations.OnClickEvent;
import at.int32.sweaty.ui.controls.Dropdown;
import at.int32.sweaty.ui.controls.Label;
import at.int32.sweaty.ui.controls.Table;
import at.int32.sweaty.ui.controls.TextBox;
import at.int32.sweaty.ui.controls.ToolbarItem;

public class MainView extends View {

	public MainView(Control parent) {
		super(parent);
	}
	
	private ToolbarItem save, edit, delete;

	@Override
	public void onInit() {
		Grid root = new Grid(this).background(Colors.get(255, 255, 255));

		Grid window = new Grid(root).background(Colors.get(213, 134, 156));
		
		Grid grid = new Grid(window, false, true).columns(4);
		
		new Label(grid).text("Start year: ").left();
		Dropdown startYear = new Dropdown(grid);
		for(int i = 1985; i < 2018; i++){
			startYear.add(String.valueOf(i));
		}
		startYear.select(0);
		new Grid(grid).span(2);
		
		new Label(grid).text("End year: ").left();
		Dropdown endYear = new Dropdown(grid);
		for(int i = 1985; i < 2018; i++){
			endYear.add(String.valueOf(i));
		}
		endYear.select(0);
		new Grid(grid).span(2);
		
		new Label(grid).text("Performance Goal: ").left();
		Dropdown goal = new Dropdown(grid);
		goal.add("Minimize Risk");
		goal.add("Maximize Sharp Ratio");
		goal.select(0);
		new Grid(grid).span(2);
		
		
		new Label(grid).text("Expected Annual Return").left();
		TextBox return1  = new TextBox(grid, SWT.BORDER);
		return1.background(Colors.get(234, 0, 145)).width(250);
		return1.right();
		new Label(grid).text("%").left();
		new Grid(grid).span(2);
		
		Grid assets =  new Grid(window, false, true).span(2).columns(1);
		new Label(assets).text("Portfolio Assets").center();
		new Grid(assets).span(2);
		
		ArrayList<String> l = new ArrayList();
		l.add("lkjj");
		l.add("lkjj");
		l.add("lkjj");
		Table tab  = new Table(assets, l);
//		tab = tab.test();
		tab.background(Colors.get(255, 0, 0));
		
//		new Label(grid).text("label1").left();
//		new Label(grid).text("label2").left();
//		new Grid(grid).span(2);
//		
//		new Label(grid).text("label1").left();
//		new Dropdown(grid).add("asdf").select(0);
//		new Grid(grid).span(2);
//		
		
		
//		Label startYear = new Label(row1);
//		startYear.text("Start year: ");
//		startYear.center();
//		TextBox  startYearBox = new TextBox(row1);
//		startYearBox.text("teeeext");
//		startYearBox.left();
//		
		
//		
//		Label ENDYear = new Label(window);
//		
//		ENDYear.text("Start year: ");
//		ENDYear.center();
//		
//		Spinner years = new Spinner(window);
//		ArrayList<String> y = new ArrayList<>();
//		y.add("11");
//		//years.create(y);
//		TextBox  endyearbox = new TextBox(window);
//		endyearbox.text("teeeext");
//		endyearbox.left();
//		
		
		
		
	}
	
	@OnClick
	public void onClick(OnClickEvent e) {
		System.out.println("clicked control = " + e.source());
	}

}
