package test;

import java.util.Random;

public class RandomGenerator {

	public static String getOdlomak() {
		
		Random generator = new Random();
		StringBuffer odlomak=new StringBuffer();
		
		int brojRecenica=(generator.nextInt(15) + 1);
		for (int i=0; i<=brojRecenica;i++) {
			
			int brojRijeci=(generator.nextInt(25) + 5);
			String recenica="";
			for (int k=0; k <=brojRijeci; k++) {	
				int brojSlova=(generator.nextInt(15) + 2);
				String rijec=getRijec(brojSlova);
				if (k > 0)
					recenica=recenica+" " + rijec;
				else
					recenica=recenica + rijec;
			}
			
			recenica=capitalize(recenica);
			if (recenica.endsWith(","))
				recenica=recenica.substring(0,recenica.length()-1);
			recenica=recenica+". ";

			odlomak.append(recenica);
		}
		
		return odlomak.toString();
		
	}
	
	private static String capitalize(String line){
		return Character.toUpperCase(line.charAt(0)) + line.substring(1);
	}
	
	private static String getRijec(int duzina) {
		
		  StringBuffer sb = new StringBuffer();  
		  for (int x = 0; x < duzina; x++)  {  
		      sb.append((char)((int)(Math.random()*26)+97));  
		  }  
		  
		  String s=sb.toString();
		  
		  Random generator = new Random();
		  int broj=(generator.nextInt(5) + 1);
		  if (broj == 1)
			  s=s+",";
		  
		 return s;
		
	}
	
}
