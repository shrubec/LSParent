package object;

import java.util.ArrayList;
import java.util.List;

public class Simulacija {

	
	public static final Integer SIMULACIJA_VRSTA_645=1;
	public static final Integer SIMULACIJA_VRSTA_739=2;
	public static final Integer SIMULACIJA_VRSTA_590=3;
	public static final Integer SIMULACIJA_VRSTA_CUSTOM=10;
	
	public static final Integer SIMULACIJA_TIPIGRE_LOTOKLAS=1;
	public static final Integer SIMULACIJA_TIPIGRE_LOTOSIS=2;
	public static final Integer SIMULACIJA_TIPIGRE_LOTOKLA=3;
	
	public static final Integer SIMULACIJA_TIPBROJEVA_AUTOMATSKI=1;
	public static final Integer SIMULACIJA_TIPBROJEVA_UNOS=2;
	
	
	private Integer vrsta;
	private Integer tipIgre;
	private Integer tipBrojeva;
	private Integer brojeva=7;
	private Integer odBrojeva=39;
	private Integer brojevaZaOdabrati;
	
	private List<String> izvlacenja=new ArrayList<String> (); //0 ponedjeljak, 6 nedjelja
	private OdigraniBrojevi odigraniBrojevi=new OdigraniBrojevi();
	
	
	public Simulacija() {
		
		tipBrojeva=SIMULACIJA_TIPBROJEVA_UNOS;
		
		izvlacenja.add("1");
		
		odigraniBrojevi.getKombinacija1().getBrojeviK3().add(3);
		odigraniBrojevi.getKombinacija1().getBrojeviK3().add(6);
		odigraniBrojevi.getKombinacija1().getBrojeviK3().add(9);
		odigraniBrojevi.getKombinacija1().getBrojeviK3().add(12);
		odigraniBrojevi.getKombinacija1().getBrojeviK3().add(15);
		odigraniBrojevi.getKombinacija1().getBrojeviK3().add(18);
		odigraniBrojevi.getKombinacija1().getBrojeviK3().add(21);
		

	}
	
	public Integer getVrsta() {
		return vrsta;
	}
	public void setVrsta(Integer vrsta) {
		this.vrsta = vrsta;
	}
	public Integer getTipIgre() {
		return tipIgre;
	}
	public void setTipIgre(Integer tipIgre) {
		this.tipIgre = tipIgre;
	}
	public Integer getTipBrojeva() {
		return tipBrojeva;
	}
	public void setTipBrojeva(Integer tipBrojeva) {
		this.tipBrojeva = tipBrojeva;
	}
	public List<String> getIzvlacenja() {
		return izvlacenja;
	}
	public void setIzvlacenja(List<String> izvlacenja) {
		this.izvlacenja = izvlacenja;
	}

	public Integer getBrojeva() {
		return brojeva;
	}
	public void setBrojeva(Integer brojeva) {
		this.brojeva = brojeva;
	}
	public Integer getOdBrojeva() {
		return odBrojeva;
	}
	public void setOdBrojeva(Integer odBrojeva) {
		this.odBrojeva = odBrojeva;
	}

	public Integer getBrojevaZaOdabrati() {
		return brojevaZaOdabrati;
	}

	public void setBrojevaZaOdabrati(Integer brojevaZaOdabrati) {
		this.brojevaZaOdabrati = brojevaZaOdabrati;
	}

	public OdigraniBrojevi getOdigraniBrojevi() {
		return odigraniBrojevi;
	}

	public void setOdigraniBrojevi(OdigraniBrojevi odigraniBrojevi) {
		this.odigraniBrojevi = odigraniBrojevi;
	}

	
}
