package loto;

import java.util.ArrayList;
import java.util.List;

public class Main {

	/**
	 * @param args
	 */
	
	
	Loto loto=null;
	
	public static void main(String[] args) {
	
		//sistem(new Loto(6,45));
//		sistemRandom(new Loto("123",6,45));
		//kombinacijaRandom(new Loto(7,39));
		//kombinacija(new Loto(7,39));
		
	}

	private static void kombinacijaRandom(Loto loto) {
		loto.kombinacijeRandom(1000,1,10);
	}
	
	
	private static void sistemRandom(Loto loto) {
		
		loto.sistemRandom(50000, 1, 7);
	}
	
	private static  void sistem(Loto loto) {
		

		List<List<Integer>> listaKombinacija=new ArrayList<List<Integer>>();
		
		
		List<Integer> lista=new ArrayList<Integer>();
		lista.add(5);
		lista.add(7);
		lista.add(9);
		lista.add(25);
		lista.add(35);
		lista.add(43);
		lista.add(44);
		listaKombinacija.add(lista);
		
		
		lista=new ArrayList<Integer>();
		lista.add(8);
		lista.add(11);
		lista.add(13);
		lista.add(16);
		lista.add(15);
		lista.add(36);
		lista.add(45);
		listaKombinacija.add(lista);
		

		
		loto.sistem(1, listaKombinacija);
	}
	
	private static  void kombinacija(Loto loto) {
		
		List<List<Integer>> listaKombinacija=new ArrayList<List<Integer>>();	
		List<Integer> lista=new ArrayList<Integer>();
		
		
		lista.add(5);
		lista.add(9);
		lista.add(25);
		lista.add(26);
		lista.add(35);
		lista.add(36);
		lista.add(42);
		listaKombinacija.add(lista);
		
		
		lista=new ArrayList<Integer>();
		lista.add(1);
		lista.add(3);
		lista.add(5);
		lista.add(20);
		lista.add(21);
		lista.add(24);
		lista.add(30);
		listaKombinacija.add(lista);
		
		
		lista=new ArrayList<Integer>();
		lista.add(5);
		lista.add(10);
		lista.add(11);
		lista.add(23);
		lista.add(27);
		lista.add(39);
		lista.add(41);
		listaKombinacija.add(lista);
		
		lista=new ArrayList<Integer>();
		lista.add(1);
		lista.add(10);
		lista.add(13);
		lista.add(14);
		lista.add(37);
		lista.add(38);
		lista.add(39);
		listaKombinacija.add(lista);
		
		lista=new ArrayList<Integer>();
		lista.add(6);
		lista.add(18);
		lista.add(19);
		lista.add(25);
		lista.add(33);
		lista.add(34);
		lista.add(40);
		listaKombinacija.add(lista);
	
		
		lista=new ArrayList<Integer>();
		lista.add(2);
		lista.add(3);
		lista.add(7);
		lista.add(23);
		lista.add(24);
		lista.add(35);
		lista.add(44);
		listaKombinacija.add(lista);
		
		lista=new ArrayList<Integer>();
		lista.add(6);
		lista.add(9);
		lista.add(15);
		lista.add(16);
		lista.add(17);
		lista.add(38);
		lista.add(39);
		listaKombinacija.add(lista);

		
		lista=new ArrayList<Integer>();
		lista.add(1);
		lista.add(9);
		lista.add(10);
		lista.add(13);
		lista.add(17);
		lista.add(18);
		lista.add(26);
		listaKombinacija.add(lista);

		
		lista=new ArrayList<Integer>();
		lista.add(2);
		lista.add(4);
		lista.add(6);
		lista.add(10);
		lista.add(11);
		lista.add(21);
		lista.add(43);
		listaKombinacija.add(lista);

		
		lista=new ArrayList<Integer>();
		lista.add(7);
		lista.add(10);
		lista.add(11);
		lista.add(24);
		lista.add(26);
		lista.add(33);
		lista.add(40);
		listaKombinacija.add(lista);
		

//		loto.kombinacije(1000, listaKombinacija);
	}
	
	
}
