package at.int32.sweaty.examples;


import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import at.int32.sweaty.ui.Colors;
import at.int32.sweaty.ui.Control;
import at.int32.sweaty.ui.Grid;
import at.int32.sweaty.ui.View;
import at.int32.sweaty.ui.annotations.OnClick;
import at.int32.sweaty.ui.annotations.OnClickEvent;
import at.int32.sweaty.ui.controls.Button;
import at.int32.sweaty.ui.controls.Dropdown;
import at.int32.sweaty.ui.controls.Label;
import at.int32.sweaty.ui.controls.TextBox;

public class MainView extends View {
	
	public MainView(Control parent) {
		super(parent);
	}
	
	private ArrayList<TextBox> assetNames  ;
	private ArrayList<TextBox> assetAllocation;
	private ArrayList<TextBox> risiko;
	private ArrayList<TextBox> minWeights ;
	
	private Grid assetsPortfolio;
	private Grid root ;
	private Grid window;
	
	private TextBox totalAlloc;
	private TextBox return1;
	
	@Override
	public void onInit() {
		assetNames = new ArrayList<>() ;
		assetAllocation = new ArrayList<>();
		risiko = new ArrayList<>();
		minWeights = new ArrayList<>();
		
		
		root = new Grid(this).background(Colors.get(255, 255, 255));
		window = new Grid(root).background(Colors.get(7, 135, 166));
		
		Grid grid = new Grid(window, false, true).columns(4);
		
		initiateGrid(grid);
		
		Grid assets =  new Grid(window, false, true).span(2).columns(1);
		new Label(assets, SWT.BOLD).text("Portfolio Assets").foreground(Colors.get(255, 255, 255)).size(50).center();
		new Grid(assets).span(2);
		
		assetsPortfolio = new Grid(window, false, true).span(2).columns(3);
		
		fillAssetsDefault(assetsPortfolio);
		
		Grid total= new Grid(window, false, true).span(2).columns(3);		
		
		initiateTotalGrid(total);
		
		Grid buttons = new Grid(window, false, true).span(2).columns(2);
		
		initiateButtonsGrid(buttons);
	}
	
	private void initiateButtonsGrid(Grid buttons) {
		Button redirect = new Button(buttons, SWT.BUTTON1);
		redirect.right();
		redirect.text("Optimize");
		redirect.selection(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				ArrayList<String> names = new ArrayList<>();
				for (int i = 0; i < assetNames.size(); i++) {
					names.add(assetNames.get(i).text());
				}
				
				Result berechnung = new Result();
				double resa;
				double resb;
				double bartekistnervig = berechnung.dancing(Double.valueOf(return1.text()),Double.valueOf(assetAllocation.get(0).text()), Double.valueOf(assetAllocation.get(1).text()));
				if(Integer.valueOf(assetAllocation.get(0).text()) < Integer.valueOf(assetAllocation.get(1).text())){
					resa = bartekistnervig;
					resb = 100 - bartekistnervig;
					
					
				}else{
					resb = bartekistnervig;
					resa = 100 - bartekistnervig;
					
				}
				
				double mvp = berechnung.MVPMinimierteXA(Double.valueOf(totalAlloc.text()), Double.valueOf(risiko.get(0).text()), Double.valueOf(risiko.get(1).text()));
				double vol = berechnung.mischungsrisiko(resa, resb, Double.valueOf(risiko.get(0).text()), Double.valueOf(risiko.get(1).text()), Double.valueOf(totalAlloc.text()));
				
				window.dispose();
				root.background(Colors.get(56, 78, 123));
				Grid newGrid = new Grid(root, false, true).columns(2);
				for (int i = 0; i < assetNames.size(); i++) {
					new Label(newGrid, SWT.BOLD).text(names.get(i) + ": ").foreground(Colors.get(255, 255, 255)).size(30).right();
					if(i == 0){
						new Label(newGrid, SWT.BOLD).text(String.valueOf(resa) + " %").foreground(Colors.get(255, 255, 255)).size(30).left();
					}else{
						new Label(newGrid, SWT.BOLD).text(String.valueOf(resb) + " %").foreground(Colors.get(255, 255, 255)).size(30).left();
					}
					
					new Grid(newGrid).span(2);
				}
				
				for (int i = 0; i < assetNames.size(); i++) {
					if(i == 0){
						new Label(newGrid, SWT.BOLD).text( "MVP für den 1 Asset: ").foreground(Colors.get(255, 255, 255)).size(30).right();

						new Label(newGrid, SWT.BOLD).text(String.valueOf(100 - mvp) + " %").foreground(Colors.get(255, 255, 255)).size(30).left();
					}else{
						new Label(newGrid, SWT.BOLD).text( "MVP für den 2 Asset: ").foreground(Colors.get(255, 255, 255)).size(30).right();

						new Label(newGrid, SWT.BOLD).text(String.valueOf(mvp)+ " %" ).foreground(Colors.get(255, 255, 255)).size(30).left();
					}
					
					new Grid(newGrid).span(2);
				}
				
				new Label(newGrid, SWT.BOLD).text( "Portfoliovolatilität: ").foreground(Colors.get(255, 255, 255)).size(30).right();
				
				new Label(newGrid, SWT.BOLD).text(String.valueOf(vol) + " %" ).foreground(Colors.get(255, 255, 255)).size(30).left();

				root.ctrl().layout();
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		Button cancel = new Button(buttons, SWT.BUTTON1);
		cancel.left();
		cancel.text("Cancel");
		cancel.selection(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				
				for (int i = 0; i < 2; i++) {
					assetNames.get(i).size(25).text("Asset " + (i+1)).ctrl().layout();;
					assetAllocation.get(i).text("").ctrl().layout();;
					risiko.get(i).text("").ctrl().layout();;
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				
			}
		});		
	}

	private void initiateGrid(Control grid){
//		new Label(grid, SWT.BOLD).text("Start year: ").foreground(Colors.get(255, 255, 255)).left();
//		Dropdown startYear = new Dropdown(grid);
//		for(int i = 1985; i < 2018; i++){
//			startYear.add(String.valueOf(i));
//		}
//		startYear.select(0);
//		new Grid(grid).span(2);
//		
//		new Label(grid, SWT.BOLD).text("End year: ").foreground(Colors.get(255, 255, 255)).left();
//		Dropdown endYear = new Dropdown(grid);
//		for(int i = 1985; i < 2018; i++){
//			endYear.add(String.valueOf(i));
//		}
//		endYear.select(0);
//		new Grid(grid).span(2);
//		
//		new Label(grid, SWT.BOLD).text("Performance Goal: ").foreground(Colors.get(255, 255, 255)).left();
//		Dropdown goal = new Dropdown(grid);
//		goal.add("Minimize Risk");
//		goal.add("Maximize Sharp Ratio");
//		goal.select(0);
//		new Grid(grid).span(2);
		
		
		new Label(grid, SWT.BOLD).text("Erwartete Rendite: ").foreground(Colors.get(255, 255, 255)).left();
		return1  = new TextBox(grid, SWT.BORDER );
		return1.background(Colors.get(255, 255, 255)).width(250);
		return1.right();
		new Label(grid, SWT.BOLD).text("%").foreground(Colors.get(255, 255, 255)).left();
		new Grid(grid).span(2);
	}
	
	private void fillAssetsDefault(Control assetsPortfolio){
		new Label(assetsPortfolio, SWT.BOLD).text("Assets").foreground(Colors.get(255, 255, 255)).size(20).left();
		new Label(assetsPortfolio, SWT.BOLD).text("Erwartete Rendite").foreground(Colors.get(255, 255, 255)).size(20).left();
		new Label(assetsPortfolio, SWT.BOLD).text("Risiko").foreground(Colors.get(255, 255, 255)).size(20).left();
		
		
		for (int i = 0; i < 2; i++) {
			assetNames.add(new TextBox(assetsPortfolio, SWT.BORDER).foreground(Colors.get(255, 255, 255)).text("Asset " + (i+1)));
			assetAllocation.add(new TextBox(assetsPortfolio, SWT.BORDER).foreground(Colors.get(255, 255, 255)));
			risiko.add(new TextBox(assetsPortfolio, SWT.BORDER).foreground(Colors.get(255, 255, 255)));
		}
	}
	
	private void initiateTotalGrid(Control total){
		new Label(total, SWT.BOLD).text("Korrelation:").foreground(Colors.get(255, 255, 255)).size(18).left();
		int totalSum = 0;
		for (int i = 0; i < assetAllocation.size(); i++) {
				try{
				totalSum = totalSum + Integer.valueOf(assetAllocation.get(i).text());
				}catch(Exception e){
					
				}
		}
		totalAlloc = new TextBox(total, SWT.BORDER).foreground(Colors.get(255, 255, 255)).size(18).text(String.valueOf(totalSum));
			}
	@OnClick
	public void onClick(OnClickEvent e) {
		System.out.println("clicked control = " + e.source());
	}

}
