package hr.shrubec.simulacija.bean;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import object.SimulacijaInfo;
import entity.Kontakt;
import entity.Simulacija;

@Stateless
@SuppressWarnings("unchecked")
public class SimulacijaBean {

	@PersistenceContext(unitName = "simulacijaPU")
	private EntityManager em;

	
	public void saveKontakt(Kontakt kontakt) {
//		em.persist(kontakt);
		
//		 File file = new File("C:\\kontakt.txt");
		 File file = new File("//home//simulator//kontakt.txt");
		 BufferedWriter output=null;
		 try {
			output = new BufferedWriter(new FileWriter(file,true));
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
		 SimpleDateFormat sdf=new SimpleDateFormat("dd.MM.yyyy HH:mm");
		 try {
			output.write(System.getProperty("line.separator")); 
			output.write(System.getProperty("line.separator")); 
			output.write("----------------------------------------"); 
			output.write(System.getProperty("line.separator")); 
			output.write("DATE: "+sdf.format(new Date()));
			output.write(System.getProperty("line.separator")); 
			output.write("NAME: "+kontakt.getName());
			output.write(System.getProperty("line.separator")); 
			output.write("EMAIL: "+kontakt.getEmail());
			output.write(System.getProperty("line.separator")); 
			output.write("MESSAGE: ");
			output.write(System.getProperty("line.separator")); 
			output.write(kontakt.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
		try {
			output.flush();
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
	}
	
	public void saveSimulacija(Simulacija simulacija) {
//		em.persist(simulacija);
		BufferedWriter output=null;
		try {
//			File file = new File("C:\\simulacije_info.txt");
			 File file = new File("//home/simulator//simulacije_info.txt");
			output = new BufferedWriter(new FileWriter(file,true));
			output.write("\r\n");
			output.write(simulacija.getStartTime() + ", "
					+ simulacija.getLotteryType() + ", "
					+ simulacija.getSelectedDays() + ", "
					+ simulacija.getSelectedNumbers());
			output.write("\r\n");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				output.flush();
				output.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<Simulacija> getSimulacije() {
		return (List<Simulacija>) em.createQuery("select o from Simulacija o order by o.startTime desc").setMaxResults(1000).getResultList();
	}
	
	public List<SimulacijaInfo> getSimulacijeDani() {
		List<SimulacijaInfo> dList=new ArrayList<SimulacijaInfo>();
		List<Object[]> list=em.createQuery("select o.simulationDate, count(o) from Simulacija o group by o.simulationDate order by o.simulationDate desc").setMaxResults(1000).getResultList();
		for (Object[] o:list) {
			SimulacijaInfo obj=new SimulacijaInfo();
			obj.setDate((Date) o[0]);
			obj.setTotal(((Long) o[1]).intValue());
			dList.add(obj);
		}
		return dList;
	}
	
	public List<Kontakt> getMessages() {
		return (List<Kontakt>) em.createQuery("select o from Kontakt o order by o.date desc").setMaxResults(1000).getResultList();
	}
	
	public Integer getSimulacijeTotal() {
		 return ((Long)em.createQuery("select count(o) from Simulacija o").getSingleResult()).intValue();
	}
	
	public void zavrsiSimulaciju(String hash,boolean jackpot) {
//		Simulacija simulacija=(Simulacija)em.createQuery("select o from Simulacija o where o.hash = :hash").setParameter("hash", hash).getSingleResult();
//		Calendar cal0=Calendar.getInstance();
//		cal0.setTime(simulacija.getStartTime());
//		Calendar cal=Calendar.getInstance();
//		simulacija.setEndTime(cal.getTime());
//		Long duration=new Long(cal.getTimeInMillis()-cal0.getTimeInMillis());
//		simulacija.setDuration(duration.intValue()/1000);
//		simulacija.setJackpot(jackpot);
//		em.merge(simulacija);
	}
}
