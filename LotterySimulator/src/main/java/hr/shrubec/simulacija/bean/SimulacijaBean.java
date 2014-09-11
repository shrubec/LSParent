package hr.shrubec.simulacija.bean;

import java.util.ArrayList;
import java.util.Calendar;
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
		em.persist(kontakt);
	}
	
	public void saveSimulacija(Simulacija simulacija) {
		em.persist(simulacija);
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
		Simulacija simulacija=(Simulacija)em.createQuery("select o from Simulacija o where o.hash = :hash").setParameter("hash", hash).getSingleResult();
		Calendar cal0=Calendar.getInstance();
		cal0.setTime(simulacija.getStartTime());
		Calendar cal=Calendar.getInstance();
		simulacija.setEndTime(cal.getTime());
		Long duration=new Long(cal.getTimeInMillis()-cal0.getTimeInMillis());
		simulacija.setDuration(duration.intValue()/1000);
		simulacija.setJackpot(jackpot);
		em.merge(simulacija);
	}
}
