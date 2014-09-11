package hr.shrubec.simulacija.util;

import hr.shrubec.simulacija.bean.SimulacijaBean;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SimulacijaSessionListener implements HttpSessionListener {

	@EJB SimulacijaBean simulacijaBean;
	
	
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		System.out.println("Current Session created : " + arg0.getSession().getId() + " at "+ new Date());		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		HttpSession session = arg0.getSession();
		Map simulacijaMap=(Map)session.getAttribute("simulacija");
		if (simulacijaMap != null) {
			Set<String> ids=simulacijaMap.keySet();
			for (String hash:ids) {
				simulacijaBean.zavrsiSimulaciju(hash, false);
			}
		}
		
		System.out.println("Current Session destroyed :" + session.getId()  + " at "+ new Date() ); 
		
	}

}
