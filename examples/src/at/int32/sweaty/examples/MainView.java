package at.int32.sweaty.examples;


import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Display;

import at.int32.sweaty.ui.Colors;
import at.int32.sweaty.ui.Control;
import at.int32.sweaty.ui.Grid;
import at.int32.sweaty.ui.View;
import at.int32.sweaty.ui.annotations.OnClick;
import at.int32.sweaty.ui.annotations.OnClickEvent;
import at.int32.sweaty.ui.controls.Button;
import at.int32.sweaty.ui.controls.Dropdown;
import at.int32.sweaty.ui.controls.Label;
import at.int32.sweaty.ui.controls.Table;
import at.int32.sweaty.ui.controls.TextBox;
import at.int32.sweaty.ui.controls.ToolbarItem;

public class MainView extends View {

	public MainView(Control parent) {
		super(parent);
	}
	
	private ArrayList<TextBox> assetNames  ;
	private ArrayList<TextBox> assetAllocation;
	private ArrayList<TextBox> maxWeights;
	private ArrayList<TextBox> minWeights ;
	private Grid assetsPortfolio;
	
	private TextBox totalAlloc;
	
	@Override
	public void onInit() {
		assetNames = new ArrayList<>() ;
		assetAllocation = new ArrayList<>();
		maxWeights = new ArrayList<>();
		minWeights = new ArrayList<>();
		
		
		Grid root = new Grid(this).background(Colors.get(255, 255, 255));
		Grid window = new Grid(root).background(Colors.get(7, 135, 166));
		
		Grid grid = new Grid(window, false, true).columns(4);
		
		initiateGrid(grid);
		
		Grid assets =  new Grid(window, false, true).span(2).columns(1);
		new Label(assets, SWT.BOLD).text("Portfolio Assets").foreground(Colors.get(255, 255, 255)).size(50).center();
		new Grid(assets).span(2);
		
		assetsPortfolio = new Grid(window, false, true).span(2).columns(4);
		
		fillAssetsDefault(assetsPortfolio);
		
		Grid total= new Grid(window, false, true).span(2).columns(4);		
		
		initiateTotalGrid(total);
		
		Grid buttons = new Grid(window, false, true).span(2).columns(2);
		
		initiateButtonsGrid(buttons);
	}
	
	private void initiateButtonsGrid(Grid buttons) {
		Button redirect = new Button(buttons);
		redirect.right();
		redirect.text("Optimize");
		redirect.selection(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		Button cancel = new Button(buttons);
		cancel.left();
		cancel.text("Cancel");
		cancel.selection(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				for (int i = 0; i < assetAllocation.size(); i++) {
					System.out.println(assetAllocation.get(i).text());
				}
				
				assetsPortfolio.update();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});		
	}

	private void initiateGrid(Control grid){
		new Label(grid, SWT.BOLD).text("Start year: ").foreground(Colors.get(255, 255, 255)).left();
		Dropdown startYear = new Dropdown(grid);
		for(int i = 1985; i < 2018; i++){
			startYear.add(String.valueOf(i));
		}
		startYear.select(0);
		new Grid(grid).span(2);
		
		new Label(grid, SWT.BOLD).text("End year: ").foreground(Colors.get(255, 255, 255)).left();
		Dropdown endYear = new Dropdown(grid);
		for(int i = 1985; i < 2018; i++){
			endYear.add(String.valueOf(i));
		}
		endYear.select(0);
		new Grid(grid).span(2);
		
		new Label(grid, SWT.BOLD).text("Performance Goal: ").foreground(Colors.get(255, 255, 255)).left();
		Dropdown goal = new Dropdown(grid);
		goal.add("Minimize Risk");
		goal.add("Maximize Sharp Ratio");
		goal.select(0);
		new Grid(grid).span(2);
		
		
		new Label(grid, SWT.BOLD).text("Expected Annual Return").foreground(Colors.get(255, 255, 255)).left();
		TextBox return1  = new TextBox(grid, SWT.BORDER );
		return1.background(Colors.get(255, 255, 255)).width(250);
		return1.right();
		new Label(grid, SWT.BOLD).text("%").foreground(Colors.get(255, 255, 255)).left();
		new Grid(grid).span(2);
	}
	
	private void fillAssetsDefault(Control assetsPortfolio){
		new Label(assetsPortfolio, SWT.BOLD).text("Assets").foreground(Colors.get(255, 255, 255)).size(20).left();
		new Label(assetsPortfolio, SWT.BOLD).text("Allocation").foreground(Colors.get(255, 255, 255)).size(20).left();
		new Label(assetsPortfolio, SWT.BOLD).text("Min.weight").foreground(Colors.get(255, 255, 255)).size(20).left();
		new Label(assetsPortfolio, SWT.BOLD).text("Max.weight").foreground(Colors.get(255, 255, 255)).size(20).left();
		
		SelectionListener listener = new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				int totalSum = 0;
				for (int i = 0; i < assetAllocation.size(); i++) {
						try{
						totalSum = totalSum + Integer.valueOf(assetAllocation.get(i).text());
						}catch(Exception e){
							
						}	
				}
				totalAlloc.text(String.valueOf(totalAlloc));
				totalAlloc.ctrl().layout();;
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		};

		
		for (int i = 0; i < 10; i++) {
			assetNames.add(new TextBox(assetsPortfolio, SWT.BORDER).foreground(Colors.get(255, 255, 255)).selection(listener).text("Asset " + (i+1)));
			assetAllocation.add(new TextBox(assetsPortfolio, SWT.BORDER).foreground(Colors.get(255, 255, 255)));
			maxWeights.add(new TextBox(assetsPortfolio, SWT.BORDER).foreground(Colors.get(255, 255, 255)));
			minWeights.add(new TextBox(assetsPortfolio, SWT.BORDER).foreground(Colors.get(255, 255, 255)));
		}
	}
	
	private void initiateTotalGrid(Control total){
		new Label(total, SWT.BOLD).text("Total:").foreground(Colors.get(255, 255, 255)).size(12).left();
		int totalSum = 0;
		for (int i = 0; i < assetAllocation.size(); i++) {
				try{
				totalSum = totalSum + Integer.valueOf(assetAllocation.get(i).text());
				}catch(Exception e){
					
				}
		}
		totalAlloc = new TextBox(total, SWT.BORDER).foreground(Colors.get(255, 255, 255)).size(12).text(String.valueOf(totalSum));
	}
	@OnClick
	public void onClick(OnClickEvent e) {
		System.out.println("clicked control = " + e.source());
	}

}
