package loto;

import java.util.List;

public class LotoThread extends Thread{

	Loto loto;
	List<List<Integer>> listaKombinacija;
	
	public LotoThread(Loto loto, List<List<Integer>> listaKombinacija) {
		this.listaKombinacija=listaKombinacija;
		this.loto=loto;
	}
	
	 public void run() {
	        System.out.println("Hello from a thread!");
	        
	        loto.kombinacije(listaKombinacija);
	        
	        
	        
	  }
	
}
