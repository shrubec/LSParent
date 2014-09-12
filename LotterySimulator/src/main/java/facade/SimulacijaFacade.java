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
			 simulacija.setBrojeva(5);
			 simulacija.setOdBrojeva(35);
		 }
		 else if (simulacija.getVrsta() != null && simulacija.getVrsta().intValue() == 2) {
			 simulacija.setBrojeva(5);
			 simulacija.setOdBrojeva(36);
		 }
		 else if (simulacija.getVrsta() != null && simulacija.getVrsta().intValue() == 3) {
			 simulacija.setBrojeva(5);
			 simulacija.setOdBrojeva(40);
		 }
		 else if (simulacija.getVrsta() != null && simulacija.getVrsta().intValue() == 4) {
			 simulacija.setBrojeva(5);
			 simulacija.setOdBrojeva(42);
		 }
		 else if (simulacija.getVrsta() != null && simulacija.getVrsta().intValue() == 5) {
			 simulacija.setBrojeva(5);
			 simulacija.setOdBrojeva(90);
		 }
		 else if (simulacija.getVrsta() != null && simulacija.getVrsta().intValue() == 6) {
			 simulacija.setBrojeva(6);
			 simulacija.setOdBrojeva(39);
		 }
		 else if (simulacija.getVrsta() != null && simulacija.getVrsta().intValue() == 7) {
			 simulacija.setBrojeva(6);
			 simulacija.setOdBrojeva(42);
		 }
		 else if (simulacija.getVrsta() != null && simulacija.getVrsta().intValue() == 8) {
			 simulacija.setBrojeva(6);
			 simulacija.setOdBrojeva(45);
		 }
		 else if (simulacija.getVrsta() != null && simulacija.getVrsta().intValue() == 9) {
			 simulacija.setBrojeva(6);
			 simulacija.setOdBrojeva(49);
		 }
		 else if (simulacija.getVrsta() != null && simulacija.getVrsta().intValue() == 10) {
			 simulacija.setBrojeva(7);
			 simulacija.setOdBrojeva(35);
		 }
		 else if (simulacija.getVrsta() != null && simulacija.getVrsta().intValue() == 11) {
			 simulacija.setBrojeva(7);
			 simulacija.setOdBrojeva(39);
		 }
		 else if (simulacija.getVrsta() != null && simulacija.getVrsta().intValue() == 12) {
			 simulacija.setBrojeva(7);
			 simulacija.setOdBrojeva(49);
		 }
		 else if (simulacija.getVrsta() != null && simulacija.getVrsta().intValue() == 13) {
			 simulacija.setBrojeva(12);
			 simulacija.setOdBrojeva(24);
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


	private boolean validiraj() {
		
		boolean success=true;
		if (simulacija.getTipBrojeva().intValue() == Simulacija.SIMULACIJA_TIPBROJEVA_UNOS) {
			for (int i=0; i < kombinacijaZaIspuniti; i++) {
				if (!validirajKombinaciju(i))
					success=false;
			}	
			
			if (success) {
				
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
				
				
				trenutnaSimulacijaId=loto.getSimulacijaId();
				
				System.out.println("TRENUTNA SIMULACIJA ID: " + trenutnaSimulacijaId);
				
				
				/*
				LotoThread thread=new LotoThread(loto,listaKombinacija);
				thread.start();*/
				
				//loto.kombinacije(1000, listaKombinacija);
				
				
			}
	
		}
		
		return success;
		
	}
	
	
	public boolean validirajKombinaciju(Integer kombinacija) {
		
		try {
			OdigraniBrojevi brojevi=simulacija.getOdigraniBrojevi();
			Method method=OdigraniBrojevi.class.getMethod("getKombinacija"+(kombinacija+1), null);	
			Kombinacija kom= (Kombinacija)method.invoke(brojevi);
			List lista=new ArrayList(kom.getBrojeviKombinacije());
			if (lista.size() > 0 && lista.size() != simulacija.getBrojeva().intValue()) {
				
				String poruka="Ticket " + (kombinacija+1) + ": please select " + simulacija.getBrojeva() + " numbers";
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
	}
	
	public void pokreniSimulaciju() {
		
		if (!validiraj()) {
			return;
		}
		
		
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
					String selectedNumbersList="Ticket " +i+": ";
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

			pieModel.set("All numbers missed", 0);
			pieModel.set("Match 1", 0);
			pieModel.set("Match 2", 0);
			pieModel.set("Match 3", 0);
			pieModel.set("Match 4", 0);
			pieModel.set("Match 5", 0);
			pieModel.set("Match 6", 0);
			pieModel.set("Match 7", 0);
			
			if (simulacija.getBrojeva().intValue() == 12) {
				pieModel.set("Match 8", 0);
				pieModel.set("Match 9", 0);
				pieModel.set("Match 10", 0);
				pieModel.set("Match 11", 0);
				pieModel.set("Match 12", 0);
			}

			return pieModel;
		}
		
		

	     pieModel.set("All numbers missed",  loto.getTrenutnoKolo().getUkupnoPogodjeno0());  
	     pieModel.set("Match 1 ",  loto.getTrenutnoKolo().getUkupnoPogodjeno1());  
	     pieModel.set("Match 2",  loto.getTrenutnoKolo().getUkupnoPogodjeno2());  
	     pieModel.set("Match 3",  loto.getTrenutnoKolo().getUkupnoPogodjeno3());
	     pieModel.set("Match 4",  loto.getTrenutnoKolo().getUkupnoPogodjeno4());
	     pieModel.set("Match 5",  loto.getTrenutnoKolo().getUkupnoPogodjeno5());
	     pieModel.set("Match 6",  loto.getTrenutnoKolo().getUkupnoPogodjeno6());
	     pieModel.set("Match 7",  loto.getTrenutnoKolo().getUkupnoPogodjeno7());
	     
	 	if (simulacija.getBrojeva().intValue() == 12) {
	 		 pieModel.set("Match 8",  loto.getTrenutnoKolo().getUkupnoPogodjeno8());
	 		 pieModel.set("Match 9",  loto.getTrenutnoKolo().getUkupnoPogodjeno9());
	 		 pieModel.set("Match 10",  loto.getTrenutnoKolo().getUkupnoPogodjeno10());
	 		 pieModel.set("Match 11",  loto.getTrenutnoKolo().getUkupnoPogodjeno11());
	 		 pieModel.set("Match 12",  loto.getTrenutnoKolo().getUkupnoPogodjeno12());
	 	}
		
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
