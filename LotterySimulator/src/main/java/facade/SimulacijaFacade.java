package facade;

import hr.shrubec.simulacija.bean.SimulacijaBean;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import loto.Loto;
import loto.LotoThread;
import object.Kombinacija;
import object.OdigraniBrojevi;
import object.Simulacija;

import org.primefaces.event.FlowEvent;
import org.primefaces.model.chart.PieChartModel;

@ManagedBean
@ViewScoped
public class SimulacijaFacade {

	@EJB SimulacijaBean simulacijaBean;
	
	private Integer kombinacijaZaIspuniti=4;
	private Simulacija simulacija=new Simulacija();
	private boolean skip; 
	private boolean disabledBrojKombinacija=false;
	
	private List<SelectItem> brojeviKombinacijeK1=new ArrayList<SelectItem>();
	private List<SelectItem> brojeviKombinacijeK2=new ArrayList<SelectItem>();
	private List<SelectItem> brojeviKombinacijeK3=new ArrayList<SelectItem>();
	
	
	
	private List<SelectItem> kombinacija1=new ArrayList<SelectItem>();
	private List<String> selectedOptions;  
	
	private String trenutnaSimulacijaId=null;
	private String finished="false";
	private Integer pojedinacno=1;
	private List<List<Integer>> listaKombinacija;
	
	private Boolean mainVisible=true;
	private Boolean simulacijaVisible=false;
	private Integer simulationSpeed=0; //20
	private PieChartModel pieModel=new PieChartModel();  
	
	
	private Boolean simulationStarted=Boolean.FALSE;
	
	
	
	
	
	public List<String> getSelectedOptions() {  
	    return selectedOptions;  
	}  
	public void setSelectedOptions(List<String> selectedOptions) {  
		this.selectedOptions = selectedOptions;  
	}  
	    
	    
	    
	public SimulacijaFacade() {
		HttpSession session=(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		if (session == null) 
			session=(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	}

	
	
	
	public Simulacija getSimulacija() {
		return simulacija;
	}




	public void setSimulacija(Simulacija simulacija) {
		this.simulacija = simulacija;
	}




	public String onFlowProcess(FlowEvent event) {  
       System.out.println("Current wizard step:" + event.getOldStep() + ", " + event.getPhaseId().getOrdinal());  
       System.out.println("Next step:" + event.getNewStep());  
       
       if (event.getNewStep().equals("rezultati"))
    	   validiraj();
       
        if(skip) {  
            skip = false;   //reset in case user goes back  
            return "confirm";  
        }  
        else {  
            return event.getNewStep();  
        }  
        
       
        
    }  
	
	 public boolean isSkip() {  
	        return skip;  
	 }   
	  
	 public void setSkip(boolean skip) {  
	        this.skip = skip;  
	 }




	public boolean getDisableCustomTip() {
		
		if (simulacija.getVrsta() != null && simulacija.getVrsta().intValue() == 10)
			return false;
		else
			return true;
	}



	public boolean isDisabledBrojKombinacija() {
		
		if (simulacija.getTipIgre() != null && simulacija.getTipIgre().intValue() == 2)
			disabledBrojKombinacija= true;
		else
			disabledBrojKombinacija=false;
	
		return disabledBrojKombinacija;
	}




	public void setDisabledBrojKombinacija(boolean disabledBrojKombinacija) {
		this.disabledBrojKombinacija = disabledBrojKombinacija;
	}  
	    
	
	public void updateTipLota() {
		
	}
	
	
	public void updateBrojeva() {
		 if (simulacija.getVrsta() != null && simulacija.getVrsta().intValue() == 1) {
			 simulacija.setBrojeva(7);
			 simulacija.setOdBrojeva(39);
		 }
		 else if (simulacija.getVrsta() != null && simulacija.getVrsta().intValue() == 2) {
			 simulacija.setBrojeva(6);
			 simulacija.setOdBrojeva(45);
		 }
		 else if (simulacija.getVrsta() != null && simulacija.getVrsta().intValue() == 3) {
			 simulacija.setBrojeva(5);
			 simulacija.setOdBrojeva(90);
		 }
		 
		 if (simulacija.getBrojeva() != null)
			 simulacija.setBrojevaZaOdabrati(simulacija.getBrojeva()+1);
		 
		 /*
		 if (simulacija.getTipBrojeva() == Simulacija.SIMULACIJA_TIPBROJEVA_UNOS)
			 simulacija.setListicaPoIzvlacenju(1);*/
		 
	}
	
	public boolean getPrikaziBrojevaZaOdabrati() {
		
		if (simulacija.getTipIgre() != null && simulacija.getTipIgre().intValue() == 2)
			return true;
		else
			return false;
		
	}

	
	public List<SelectItem> getBrojeviKombinacijeK1() {
		brojeviKombinacijeK1=new ArrayList<SelectItem>();
		for (int i=1; i <= simulacija.getOdBrojeva(); i++) {
			brojeviKombinacijeK1.add(new SelectItem(new Integer(i),String.valueOf(i)));
			i=i+2;
		}
		
		return brojeviKombinacijeK1;
	}
	public void setBrojeviKombinacijeK1(List<SelectItem> brojeviKombinacijeK1) {
	
		this.brojeviKombinacijeK1 = brojeviKombinacijeK1;
	}
	public List<SelectItem> getBrojeviKombinacijeK2() {
		
		brojeviKombinacijeK2=new ArrayList<SelectItem>();
		for (int i=2; i <= simulacija.getOdBrojeva(); i++) {
			brojeviKombinacijeK2.add(new SelectItem(new Integer(i),String.valueOf(i)));
			i=i+2;
		}
		
		return brojeviKombinacijeK2;
	}
	public void setBrojeviKombinacijeK2(List<SelectItem> brojeviKombinacijeK2) {
		this.brojeviKombinacijeK2 = brojeviKombinacijeK2;
	}
	public List<SelectItem> getBrojeviKombinacijeK3() {
		
		brojeviKombinacijeK3=new ArrayList<SelectItem>();
		for (int i=3; i <= simulacija.getOdBrojeva(); i++) {
			brojeviKombinacijeK3.add(new SelectItem(new Integer(i),String.valueOf(i)));
			i=i+2;
		}
		
		return brojeviKombinacijeK3;
	}
	public void setBrojeviKombinacijeK3(List<SelectItem> brojeviKombinacijeK3) {
		this.brojeviKombinacijeK3 = brojeviKombinacijeK3;
	}
	

	public boolean generirajKombinaciju(int kombinacija) {
		if (kombinacija <=kombinacijaZaIspuniti)
			return true;
		else
			return false;
	}

	public boolean getDisableListicaPoIzvlacenju() {
		
		if (simulacija.getTipBrojeva().intValue() == 1) 
			return false;
		else 
			return true;
		
	}
	
	
	public boolean getRenderOdabirBrojeva() {
		
		if (simulacija.getTipBrojeva().intValue() != 1) 
			return true;
		else 
			return false;
		
	}
	
	public Integer getKombinacijaZaIspuniti() {
		return kombinacijaZaIspuniti;
	}
	public void setKombinacijaZaIspuniti(Integer kombinacijaZaIspuniti) {
		this.kombinacijaZaIspuniti = kombinacijaZaIspuniti;
	}


	private void validiraj() {
		
		if (simulacija.getTipBrojeva().intValue() == Simulacija.SIMULACIJA_TIPBROJEVA_UNOS) {
			boolean success=true;
			for (int i=0; i < kombinacijaZaIspuniti; i++) {
				if (!validirajKombinaciju(i))
					success=false;
			}	
			
			if (success) {
				
				System.out.println("NEW LOTO: " + simulacija.getBrojeva()+"/" + simulacija.getOdBrojeva());

				
				
				listaKombinacija=new ArrayList<List<Integer>>();	
				List<Integer> listaBrojeva=new ArrayList<Integer>();
				
				for (int i=0; i < kombinacijaZaIspuniti; i++) {
					
					try {
						OdigraniBrojevi brojevi=simulacija.getOdigraniBrojevi();
						Method method=OdigraniBrojevi.class.getMethod("getKombinacija"+(i+1), null);	
						Kombinacija kom= (Kombinacija)method.invoke(brojevi);
						List lista=new ArrayList(kom.getBrojeviKombinacije());
						if (lista.size() > 0 ) {

							listaBrojeva=new ArrayList<Integer>();
							for (int k=0; k < lista.size(); k++) {
								Integer broj=Integer.valueOf((String)lista.get(k));
								listaBrojeva.add(broj);
							}
							
							listaKombinacija.add(listaBrojeva);
							
						}	
					} catch (Exception e) {
						e.printStackTrace();
					} 

				}	
				
				HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
				Loto loto=new Loto(session,UUID.randomUUID().toString(),simulacija.getBrojeva(),simulacija.getOdBrojeva(),simulacija.getIzvlacenja());
				
				
				Map simulacijaMap=null;				
				if (session.getAttribute("simulacija") == null) {
					simulacijaMap=new HashMap<String,Loto>();	
				}
				else {
					simulacijaMap=(Map)session.getAttribute("simulacija");
				}
				
				simulacijaMap.put(loto.getSimulacijaId(), loto);
				
				session.setAttribute("simulacija", simulacijaMap);
				
				System.out.println("ATRIBBUT SIMULACIJA POSTAVLJEN");
				
				trenutnaSimulacijaId=loto.getSimulacijaId();
				
				
				System.out.println("TRENUTNA SIMULACIJA ID: " + trenutnaSimulacijaId);
				
				
				/*
				LotoThread thread=new LotoThread(loto,listaKombinacija);
				thread.start();*/
				
				//loto.kombinacije(1000, listaKombinacija);
				
				
			}
	
		}
		
	}
	
	
	public boolean validirajKombinaciju(Integer kombinacija) {
		
		try {
			OdigraniBrojevi brojevi=simulacija.getOdigraniBrojevi();
			Method method=OdigraniBrojevi.class.getMethod("getKombinacija"+(kombinacija+1), null);	
			Kombinacija kom= (Kombinacija)method.invoke(brojevi);
			List lista=new ArrayList(kom.getBrojeviKombinacije());
			if (lista.size() > 0 && lista.size() != simulacija.getBrojeva().intValue()) {
				
				String poruka="Kombinacija " + (kombinacija+1) + ": odabrano "+ lista.size() + " od potrebnih " + simulacija.getBrojeva() + " brojeva";
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, poruka, poruka));
				return false;
			}	
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		
		return true;
	}
	
	
	public void kombinacijaChanged(ValueChangeEvent evt) {
		
		validirajKombinaciju(1);
		
	}

	
	public void test() {
		System.out.println("Test Metoda!!!");
	}
	
	public void pokreniSimulaciju() {
		validiraj();
		
		
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		
		Loto loto=null;
		Map map=(Map)session.getAttribute("simulacija");
		if (map != null)
			loto=(Loto) map.get(trenutnaSimulacijaId);
		

		if (loto != null) {

			entity.Simulacija simulation=new entity.Simulacija();
			simulation.setHash(trenutnaSimulacijaId);
			simulation.setJackpot(false);
			simulation.setLotteryType(loto.getLotteryType());
			String s="";
			
			for (String day:simulacija.getIzvlacenja()) {
				s=s+" " + day.toString();
			}
			simulation.setSelectedDays(s);
			
			String selectedNumbers="";
			int i=1;
			for (List<Integer> list:listaKombinacija) {
				
				if (!list.isEmpty()) {
					String selectedNumbersList="Kombinacija " +i+": ";
					for (Integer number:list) {
						selectedNumbersList=selectedNumbersList+number.toString()+" ";
					}
					selectedNumbers=selectedNumbers+selectedNumbersList;
					
				}
				i++;
			}
			
			simulation.setSelectedNumbers(selectedNumbers);
			simulation.setStartTime(new Date());
			simulation.setSimulationDate(new Date());
			simulacijaBean.saveSimulacija(simulation);
			
			LotoThread thread=new LotoThread(loto,listaKombinacija);
			thread.start();
			simulationStarted=true;
		}
		 
		
	}
	
	public String getFinished() {
		
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		
		
		Map map=(Map)session.getAttribute("simulacija");
		if(map == null)
			return "false";
		
		Loto loto=(Loto) map.get(trenutnaSimulacijaId);
		if (loto != null) {
			System.out.println("FACADE simulacija id: " + trenutnaSimulacijaId + ", finished: " +loto.isFinished());
			
			return new Boolean(loto.isFinished()).toString();
		}
		
		return "false"; 
		
	}
	
	public String getTrenutnaSimulacijaId() {
		return trenutnaSimulacijaId;
	}
	public void setTrenutnaSimulacijaId(String trenutnaSimulacijaId) {
		this.trenutnaSimulacijaId = trenutnaSimulacijaId;
	}
	public void setFinished(String finished) { 
		this.finished = finished;
	}
	

	public PieChartModel getPieModel() {
		
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		
		Loto loto=null;
		Map map=(Map)session.getAttribute("simulacija");
		if (map != null)
			loto=(Loto) map.get(trenutnaSimulacijaId);
		
		System.out.println("Chart map: " + map);

		if(loto == null || loto.getTrenutnoKolo() == null) {

			pieModel.set("Promaseno", 0);
			pieModel.set("Pogoðen 1 broj", 0);
			pieModel.set("Pogoðena 2 broja", 0);
			pieModel.set("Pogoðena 3 broja", 0);
			pieModel.set("Pogoðena 4 broja", 0);
			pieModel.set("Pogoðena 5 broja", 0);
			pieModel.set("Pogoðena 6 broja", 0);
			pieModel.set("Pogoðena 7 broja", 0);

			return pieModel;
		}
		
		

	     pieModel.set("Promaseno",  loto.getTrenutnoKolo().getUkupnoPogodjeno0());  
	     pieModel.set("Pogoðen 1 broj",  loto.getTrenutnoKolo().getUkupnoPogodjeno1());  
	     pieModel.set("Pogoðena 2 broja",  loto.getTrenutnoKolo().getUkupnoPogodjeno2());  
	     pieModel.set("Pogoðena 3 broja",  loto.getTrenutnoKolo().getUkupnoPogodjeno3());
	     pieModel.set("Pogoðena 4 broja",  loto.getTrenutnoKolo().getUkupnoPogodjeno4());
	     pieModel.set("Pogoðena 5 broja",  loto.getTrenutnoKolo().getUkupnoPogodjeno5());
	     pieModel.set("Pogoðena 6 broja",  loto.getTrenutnoKolo().getUkupnoPogodjeno6());
	     pieModel.set("Pogoðena 7 broja",  loto.getTrenutnoKolo().getUkupnoPogodjeno7());
		
		return pieModel;
	} 
	public void setPieModel(PieChartModel pieModel) {
		this.pieModel = pieModel; 
	}
	
	public void odigrajPojedinacno() {
		
		if (pojedinacno == 1)
			validiraj();
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		
		Loto loto=null;
		Map map=(Map)session.getAttribute("simulacija");
		if (map != null)
			loto=(Loto) map.get(trenutnaSimulacijaId);
		

		if (loto != null) {
			loto.kombinacijePojedinacno(pojedinacno, 5000, listaKombinacija);
		}
			
		pojedinacno++;
		
		mainVisible=false;
		simulacijaVisible=true;
			
	}
	
	
	public void next() {
		validiraj();
	}
	
	public void noviBrojevi() {
		HttpServletResponse response=(HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		try {
			response.sendRedirect("simulacija.jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Boolean getMainVisible() {
		return mainVisible;
	}
	public void setMainVisible(Boolean mainVisible) {
		this.mainVisible = mainVisible;
	}
	public Boolean getSimulacijaVisible() {
		return simulacijaVisible;
	}
	public void setSimulacijaVisible(Boolean simulacijaVisible) {
		this.simulacijaVisible = simulacijaVisible;
	}
	public Integer getSimulationSpeed() {
		return simulationSpeed;
	}
	public void setSimulationSpeed(Integer simulationSpeed) {
		this.simulationSpeed = simulationSpeed;
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		
		Loto loto=null;
		Map map=(Map)session.getAttribute("simulacija");
		if (map != null)
			loto=(Loto) map.get(trenutnaSimulacijaId);
		
		if (loto != null) {
			loto.setSpeed(this.simulationSpeed);
		}
		
	}
	

	public void displaySimulationMessage() {
		
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		
		Loto loto=null;
		Map map=(Map)session.getAttribute("simulacija");
		if (map != null)
			loto=(Loto) map.get(trenutnaSimulacijaId);
		
		if (loto != null) {
			for (String message:loto.getMessagesList()) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", message));
				
			}
			loto.getMessagesList().clear();
		}
	}
	
	
	public Boolean getSimulationStarted() {
		return simulationStarted;
	}
	public void setSimulationStarted(Boolean simulationStarted) {
		this.simulationStarted = simulationStarted;
	}
	
	
}
