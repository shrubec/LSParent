package facade;

import hr.shrubec.simulacija.bean.SimulacijaBean;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import object.SimulacijaInfo;
import entity.Kontakt;
import entity.Simulacija;

@ManagedBean
@ViewScoped
public class InfoFacade {

	
	@EJB SimulacijaBean simulacijaBean;
	
	public Integer getUkupnoSimulacija() {
		return simulacijaBean.getSimulacijeTotal();
	}
	
	public List<SimulacijaInfo> getSimulacijeInfo() {
		return simulacijaBean.getSimulacijeDani();
	}
	
	public List<Kontakt> getPorukeInfo() {
		return simulacijaBean.getMessages();
	}
	
	public List<Simulacija> getSimulacijeDetaljno()  {
		return simulacijaBean.getSimulacije();
	}
}
