package at.int32.sweaty.examples;


public class Result  {
	
	private int asset1;
	private int asset2;

	public Result() {
		// TODO Auto-generated constructor stub
	}
	
	public static double dancing(double gemeinsamRen, double erwartetRendA, double erwartetRendB){
		double dancingqueen = 0;
		if (erwartetRendB > erwartetRendA){
			double obereG = gemeinsamRen - erwartetRendB;
			double untereG = erwartetRendA - erwartetRendB;
			System.out.println("obereG: " + obereG);
			System.out.println("untereG: " + untereG);
			dancingqueen = obereG/untereG;
		}
		if (erwartetRendA > erwartetRendB) {
			double obereG = gemeinsamRen - erwartetRendA;
			double untereG = erwartetRendB - erwartetRendA;
			dancingqueen = obereG/untereG;
		}
		if (erwartetRendA == erwartetRendB){
			System.out.println("A und B sind gleich, geht nicht Nigga!");
		}
		if (dancingqueen == 0){
			System.out.println("Ergebnis darf nicht 0 sein, Nigga");
		}
		System.out.println("dancingQueen ist " + dancingqueen);
		dancingqueen*=100;
		return dancingqueen;	
	}
	
	public  double MVPMinimierteXA(double korre, double volalitaetA, double volalitaetB){
		double corre = 0;
		corre = (korre*volalitaetA*volalitaetB);
		
		double obereGleichungxaMVP = ((volalitaetB*volalitaetB)-corre);;
		double untereGleichungxaMVP = ((volalitaetA*volalitaetA)+(volalitaetB*volalitaetB)-2*corre);
		double ergebnisxaMVP = obereGleichungxaMVP/untereGleichungxaMVP;
		//ergebnisxaMVP (zeigt das minimierte Risiko von Asset A
		//ergebnisxbMVP (zeigt das minimierte Risiko von Asset B
		
		double ergebnisxbMVP = 1 - ergebnisxaMVP;
		ergebnisxbMVP*=100;
		return ergebnisxbMVP;
	}
	
	public static double mischungsrisiko(double portfolioA, double portfolioB,double volalitaetA, double volalitaetB, double korre){
		portfolioA/=100;
		portfolioB/=100;

		
		
		double risikoA=((portfolioA*portfolioA)*(volalitaetA*volalitaetA));
		double risikoB=((portfolioB*portfolioB)*(volalitaetB*volalitaetB));
		double mischungsrisiko=(2*(portfolioA*portfolioB*volalitaetA*volalitaetB*korre));
		double ergebnis = risikoA+risikoB+mischungsrisiko;
		double wurzelergebnis = Math.sqrt(ergebnis);
		
		return wurzelergebnis;	
	}
}
