package mb.dsam.mb;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.chart.PieChartModel;

@ManagedBean
@RequestScoped
public class GraficoBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PieChartModel pieModel;  
	
	public GraficoBean() {  
		 pieModel = new PieChartModel();  
		  
	        pieModel.set("Windows 7", 540);  
	        pieModel.set("Windows XP", 300);
	        pieModel.set("Windows 8", 500);  
	        pieModel.set("Windows 2k", 325);
    } 

	public PieChartModel getPieModel() {  
		System.out.println(pieModel);
		return pieModel;  
		
    }  
	
	
	
}
