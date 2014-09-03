package mb.dsam.util;

public class VerificaMacAdress {
	private String mac;
	
	public String getMac() {
		return mac;
	}

	public void setMac(String mac){
		this.mac = mac.replaceAll("\"", "");
		this.mac = this.mac.replaceAll("-", ":");
	}
	
}
