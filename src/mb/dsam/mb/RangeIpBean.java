package mb.dsam.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import mb.dsam.modelo.Ip;
@ViewScoped
@Named
public class RangeIpBean implements Serializable{
	//@Inject
	private Ip ip = new Ip();
	//private HashMap mask;
	HashMap mask = new HashMap();
	
	ArrayList<String> array = new ArrayList<String>();
	
	

	private void RangeIp(){
		mask.put("/20", "255.255.240.0");
		this.ip.setMascara((String) mask.get("/20"));
		this.ip.setIp("10.0.144.0");
		
		this.ip.setSt(new StringTokenizer(ip.getMascara(), "."));
		
		 while (this.ip.getSt().hasMoreTokens()) {
	         this.ip.setPrimeiroOcto(Integer.parseInt(this.ip.getSt().nextToken())); 
	         this.ip.setSegundoOcto(Integer.parseInt(this.ip.getSt().nextToken()));
	         this.ip.setTerceiroOcto(Integer.parseInt(this.ip.getSt().nextToken()));
	         this.ip.setQuartoOcto(Integer.parseInt(this.ip.getSt().nextToken()));
	        
	      }
		 
		 
		 this.ip.setSt(new StringTokenizer(ip.getIp(), "."));
	      while (this.ip.getSt().hasMoreTokens()) {
	    	  this.ip.setPrimeiroOctoIp(Integer.parseInt(this.ip.getSt().nextToken())); 
		         this.ip.setSegundoOctoIp(Integer.parseInt(this.ip.getSt().nextToken()));
		         this.ip.setTerceiroOctoIp(Integer.parseInt(this.ip.getSt().nextToken()));
		         this.ip.setQuartoOctoIp(Integer.parseInt(this.ip.getSt().nextToken()));
	         
	      }
	      
	      this.ip.setBinario1(Integer.toBinaryString(this.ip.getPrimeiroOcto()));
	      this.ip.setBinario2(Integer.toBinaryString(this.ip.getSegundoOcto()));
	      this.ip.setBinario3(Integer.toBinaryString(this.ip.getTerceiroOcto()));
	      this.ip.setBinario4(Integer.toBinaryString(this.ip.getQuartoOcto()));
	      
		
	}
	
	private void AddArray(){
		for (int i = 0; i <= 5; i++) {
	    	  
	         for(int x = 0; x < 255; x++){
	        	 String range = this.ip.getPrimeiroOctoIp() + "." + this.ip.getSegundoOctoIp() + "." + (this.ip.getTerceiroOctoIp() + i) + "." + x;
	        	 this.array.add(range); 
	        	 
	         }
	      }
	}
	
	public ArrayList<String> getImprimir(){
		this.RangeIp();
		this.AddArray();
		
		for (int i = 0; i < this.array.size(); i++) { 
    		
  		  if (this.array.get(i).equals("144.0")) {  
  			  this.array.remove(i);  
  		    } 
 
  		  this.array.get(i); 
  		
  	  }
		System.out.println(this.array);
		return  this.array;
		
	}

	public Ip getIp() {
		return ip;
	}

	public void setIp(Ip ip) {
		this.ip = ip;
	}
	
	
	
}
