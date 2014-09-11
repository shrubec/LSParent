package object;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Kombinacija {

	private List<Integer> brojeviK1=new ArrayList<Integer>();
	private List<Integer> brojeviK2=new ArrayList<Integer>();
	private List<Integer> brojeviK3=new ArrayList<Integer>();
	
	private Set<Integer> brojeviKombinacije=new HashSet<Integer>();

	public Kombinacija() {
		
	}
	
	private void updateBrojeviKombinacije() {
		brojeviKombinacije=new HashSet<Integer>();
		brojeviKombinacije.addAll(brojeviK1);
		brojeviKombinacije.addAll(brojeviK2);
		brojeviKombinacije.addAll(brojeviK3);
	}
	
	
	public List<Integer> getBrojeviK1() {
		return brojeviK1;
	}

	public void setBrojeviK1(List<Integer> brojeviK1) {
		this.brojeviK1 = brojeviK1;
		updateBrojeviKombinacije();
	}

	public List<Integer> getBrojeviK2() {
		return brojeviK2;
	}

	public void setBrojeviK2(List<Integer> brojeviK2) {
		this.brojeviK2 = brojeviK2;
		updateBrojeviKombinacije();
	}

	public List<Integer> getBrojeviK3() {
		return brojeviK3;
	}

	public void setBrojeviK3(List<Integer> brojeviK3) {
		this.brojeviK3 = brojeviK3;
		updateBrojeviKombinacije();
	}

	public Set<Integer> getBrojeviKombinacije() {
		return brojeviKombinacije;
	}


}
