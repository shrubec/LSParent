package loto;

import java.util.Set;

import listic.Broj;

public class Util {

	public static boolean isOdabrani(Set<Broj> brojevi,Integer odabrani) {
		
		for (Broj broj:brojevi) {
			if (broj.getBroj().intValue() == odabrani.intValue()) return true;
		}
		return false;
		
	}
	
}
