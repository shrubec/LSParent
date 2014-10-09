package hr.shrubec.simulacija.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import listic.Broj;

public class SimulacijaResultFile {

	private File file = null;
	private BufferedWriter output=null;
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	
	public SimulacijaResultFile(String filename) {
		 file = new File("C:\\"+filename+".txt");
//		 file = new File("//home/simulacije//"+filename+".txt");
		 try {
			output = new BufferedWriter(new FileWriter(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	


	public void appendIzvuceno(Set<Broj> izvucenaKombinacija,Integer kolo,Date datum) throws IOException {
		
		output.write(System.getProperty("line.separator"));
		output.write(System.getProperty("line.separator"));
		output.write("-----------------------------------------------------------------------------------");
		output.write(System.getProperty("line.separator"));
		output.write(System.getProperty("line.separator"));
		
		output.write("Drawing " + kolo + ", " + sdf.format(datum));
//		output.write(System.getProperty("line.separator"));
//		output.write("----------------------------------------------------------");
		output.write(System.getProperty("line.separator"));
		output.write("Winning numbers: ");
		String s="";
		for (Broj broj:izvucenaKombinacija) {
			s=s+broj.getBroj()+", ";
		}
		output.write(s.substring(0,s.lastIndexOf(",")));
		output.write(System.getProperty("line.separator"));
//		output.write("----------------------------------------------------------");
		
	}
	
	public void appendOdigrano(Set<Broj> zaokruzeno) throws IOException {
		
		output.write(System.getProperty("line.separator"));
		output.write("My numbers: ");
		
		String s="";
		for (Broj broj:zaokruzeno) {
			s=s+broj.getBroj()+", ";
		}
		output.write(s);
		output.write("Match: ");
		s="";
		for (Broj broj:zaokruzeno) {
			if (broj.getPogodjen()) {
				s=s+broj.getBroj()+", ";
			}
		}
		
		if (s.contains(",")) 
			s=s.substring(0,s.lastIndexOf(","));
		output.write(s);
		
//		output.write(System.getProperty("line.separator"));
//		output.write("----------------------------------------------------------");
//		output.write(System.getProperty("line.separator"));
		output.flush();
	}
	
	public void closeFile() {
		try {
			output.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteFile() throws IOException {
		boolean deleted=false;
		if (file.exists()) {
			closeFile();
			try {
				output = null;
				System.gc();
				deleted=file.delete();
			} catch (Exception e) {
				System.out.println("GRESKA KOD BRISANJA");
				e.printStackTrace();
			}
			System.out.println("DELETED: " + deleted);
		}
		
	}



	public File getFile() {
		return file;
	}
	
	
}
