package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import loto.IzvlacenjeDO;
import loto.KoloDO;
import loto.Loto;

/**
 * Servlet implementation class Updater
 */
@WebServlet("/Updater")
public class Updater extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static SimpleDateFormat sdf=new SimpleDateFormat("dd.MM.yyyy");
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Updater() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//generateNumbers(request, response);

	    response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    
	    String trenutnaSimulacijaId=request.getParameter("trenutnaSimulacija");
	    System.out.println("TrenutaSimulacijaId = " + trenutnaSimulacijaId);
		
		Map map=(Map) request.getSession().getAttribute("simulacija");
		
		System.out.println("MAP: " + map);
		
		KoloDO trenutnoKolo=null;
		if(map == null) {
			trenutnoKolo= new KoloDO();
		}
		else {
			Loto loto=(Loto) map.get(trenutnaSimulacijaId);
			if (loto != null)
				trenutnoKolo= loto.getTrenutnoKolo();
		}
			
		if (trenutnoKolo == null)
			trenutnoKolo=new KoloDO();
		
		
		System.out.println("TRENUTNO KOLO: " + trenutnoKolo.getKolo());
		
		/*out.println("Trenutno kolo: " + trenutnoKolo.getKolo());
		out.println("<br> ");
		out.println("Datum: " + sdf.format(trenutnoKolo.getDatum()));
		
		out.println("<br> ");
		
		out.println("Ukupno odigrano kombinacija: " + trenutnoKolo.getUkupnoOdigrano());	out.println("<br> ");
		
		out.println("Ukupno nula: " + trenutnoKolo.getUkupnoPogodjeno0());	out.println("<br> ");
		out.println("Ukupno jedinica: " + trenutnoKolo.getUkupnoPogodjeno1());	out.println("<br> ");
		out.println("Ukupno dvojki: " + trenutnoKolo.getUkupnoPogodjeno2());	out.println("<br> ");
		out.println("Ukupno trojki: " + trenutnoKolo.getUkupnoPogodjeno3());	out.println("<br> ");
		out.println("Ukupno cetvorki: " + trenutnoKolo.getUkupnoPogodjeno4());	out.println("<br> ");
		out.println("Ukupno petica: " + trenutnoKolo.getUkupnoPogodjeno5());	out.println("<br> ");
		out.println("Ukupno sestica: " + trenutnoKolo.getUkupnoPogodjeno6());	out.println("<br> ");
		out.println("Ukupno sedmica: " + trenutnoKolo.getUkupnoPogodjeno7());	out.println("<br> ");
		
		*/
		
		
		out.println("<head>");
		out.println("<LINK REL=StyleSheet HREF='tableStyle.css' TYPE='text/css'>");
		out.println("</head>");
		
		out.println("<table id='gradient-style'>");
		
		
		out.println("<tr>");
		out.println("<th scope='col' colspan='2' >Current progress</th>");
		out.println("<tr>");
		out.println("</tr>");
		
		
		out.println("<tr>");
		out.println("<td width='350'>");
		out.println("Trenutno kolo");
		out.println("</td>");
		out.println("<td align='right' width='250'>");
		out.println(trenutnoKolo.getKolo());
		out.println("</td>");
		out.println("</tr>");

		out.println("<tr>");
		out.println("<td width='350'>");
		out.println("Ukupno odigrano kombinacija");
		out.println("</td>");
		out.println("<td align='right' width='250'>");
		out.println(trenutnoKolo.getUkupnoOdigrano());
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td width='350'>");
		out.println("Datum");
		out.println("</td>");
		out.println("<td align='right' width='250'>");
		if (trenutnoKolo.getDatum() != null)
			out.println(sdf.format(trenutnoKolo.getDatum()));
		out.println("</td>");
		out.println("</tr>");
		
		
		out.println("<tr>");
		out.println("<td width='350'>");
		out.println("Izvuceni brojevi");
		out.println("</td>");
		out.println("<td align='right' width='250'>");
		
		out.println("<table style='table-layout: fixed;width:300px'>");
		out.println("<tr>");
		
		
		if (map != null) {
			Loto loto=(Loto) map.get(trenutnaSimulacijaId);
			List<Integer> izvuceno=loto.getTrenutnoIzvlacenje().getKola().get(0).getIzvuceno();
			for (int i=0; i < izvuceno.size(); i++) { 
				Integer broj=izvuceno.get(i);
				
				out.println("<td align='center'>");
				
				out.print(broj.intValue());  
				
				out.println("</td>");
				
			}
		}

		out.println("</tr>");
		out.println("</table>");
		
		
		out.println("</td>");
		out.println("</tr>");
		
		out.println("</table>");

		out.println("<table >");
		out.println("<tr>");
		
		out.println("<td width='150'>");
		
		out.println("<table id='gradient-style2' >");
		
		out.println("<tr>");
		out.println("<th scope='col' colspan='2' >Statictics</th>");
		out.println("<tr>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td width='200'>");
		out.println("Ukupno nula");
		out.println("</td>");
		out.println("<td align='right' width='50'>");
		out.println(trenutnoKolo.getUkupnoPogodjeno0());
		out.println("</td>");
		
		/*out.println("<td width='345' align='right' valign='top' rowspan=8>");
		
		generateNumbers(request, response);
		
		out.println("</td>");*/
		
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td width='200'>");
		out.println("Ukupno jedinica");
		out.println("</td>");
		out.println("<td align='right' width='50'>");
		out.println(trenutnoKolo.getUkupnoPogodjeno1());
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td width='200'>");
		out.println("Ukupno dvojki");
		out.println("</td>");
		out.println("<td align='right' width='50'>");
		out.println(trenutnoKolo.getUkupnoPogodjeno2());
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td width='200'>");
		out.println("Ukupno trojki");
		out.println("</td>");
		out.println("<td align='right' width='50'>");
		out.println(trenutnoKolo.getUkupnoPogodjeno3());
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td width='200'>");
		out.println("Ukupno cetvorki");
		out.println("</td>");
		out.println("<td align='right' width='50'>");
		out.println(trenutnoKolo.getUkupnoPogodjeno4());
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td width='200'>");
		out.println("Ukupno petica");
		out.println("</td>");
		out.println("<td align='right' width='50'>");
		out.println(trenutnoKolo.getUkupnoPogodjeno5());
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td width='200'>");
		out.println("Ukupno sestica");
		out.println("</td>");
		out.println("<td align='right' width='50'>");
		out.println(trenutnoKolo.getUkupnoPogodjeno6());
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td width='200'>");
		out.println("Ukupno sedmica");
		out.println("</td>");
		out.println("<td align='right' width='50'>");
		out.println(trenutnoKolo.getUkupnoPogodjeno7());
		out.println("</td>");
		out.println("</tr>");
		
		out.println("</table>");
		
		out.println("</td>");
		
		out.println("<td valign='top'>");
		
		generateNumbers(request, response);
		
		out.println("</td>");
		
		out.println("</table>");
		out.println("</tr>");

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	
	private void generateNumbers(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		System.out.println("U UPDATERU....");
		
		String trenutnaSimulacijaId=request.getParameter("trenutnaSimulacija");
		System.out.println("JSP TrenutaSimulacijaId = " + trenutnaSimulacijaId);

		Map map=(Map) request.getSession().getAttribute("simulacija");

		System.out.println("JSP MAP = " + map);

		Loto loto=null;
		IzvlacenjeDO trenutnoIzvlacenje=null;
		if (map != null) { 
			loto=(Loto) map.get(trenutnaSimulacijaId);
			trenutnoIzvlacenje= loto.getTrenutnoIzvlacenje();
		}
		
		
		
		if (trenutnoIzvlacenje != null) { 

			List<KoloDO> kola=trenutnoIzvlacenje.getKola();


			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<table id='gradient-style2' style='table-layout: fixed;'>");
			/*
			out.println("<tr>");
			
			out.println("<td colspan="+ loto.getTrenutnoIzvlacenje().getKola().get(0).getKombinacije().get(0).getOdigrano().size()+"> Izvuceni brojevi: </td>");
			
			out.println("</tr>");
			
			
			out.println("<tr>");
				
				

					List<Integer> izvuceno=loto.getTrenutnoIzvlacenje().getKola().get(0).getIzvuceno();
					for (Integer broj:izvuceno) { 
						
						out.println("<td>");
						
						out.print(broj.intValue());  out.println("&nbsp;&nbsp;&nbsp");
						
						out.println("</td>");
						
				}

				
					out.println("</tr>");
			*/
			
			

					
					out.println("<tr>");
					out.println("<th scope='col' colspan="+ loto.getTrenutnoIzvlacenje().getKola().get(0).getKombinacije().get(0).getOdigrano().size()+" >Odabrani brojevi</th>");
					out.println("<tr>");
					out.println("</tr>");
					

			
				java.util.Set<Integer> set=new java.util.HashSet<Integer>();
				set.addAll(loto.getTrenutnoIzvlacenje().getKola().get(0).getIzvuceno());
			
				for (KoloDO kolo:kola) {
				
					out.println("<tr>");
				
	
					List<Integer> odigrano=kolo.getKombinacije().get(0).getOdigrano();
					
				
					for (Integer broj:odigrano) { 
						
//						out.println("<td width='25' align='right' >");
						
						
						
						if (set.contains(broj)) {	
							out.println("<td align='right' >");
							out.println("<font color='red' >");
							out.println("<b>");
					
						}
					
						else {
							out.println("<td  align='right' >");
						}
						
						
						out.print(broj.intValue());  //out.println("&nbsp;&nbsp;&nbsp");
						
						

						if (set.contains(broj)) {	

							out.println("</font>");
							out.println("</b>");
						}

						
						
						out.println("</td>");
						
				}

				
					out.println("</tr>");
				
			 } 


				out.println("</table>");

		} 
		

		
	}
	

}
