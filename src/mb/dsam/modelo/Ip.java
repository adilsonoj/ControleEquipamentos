package mb.dsam.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Ip implements Serializable{
	  private String ip;
	  private String mascara;
	  private String binario;
	  private String binario1;
	  private String binario2;
	  private String binario3;
	  private String binario4;
	  private StringTokenizer st = null;
      
	  private int primeiroOcto = 0, primeiroOctoIp = 0;
	  private int segundoOcto = 0, segundoOctoIp = 0;
	  private int terceiroOcto = 0, terceiroOctoIp = 0;
	  private int quartoOcto = 0, quartoOctoIp = 0;
	  private int qtdeZeros = 0;
	  private int qtdeUms = 0;
      
	  private ArrayList<String> array;
      
	  private HashMap mask;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMascara() {
		return mascara;
	}

	public void setMascara(String mascara) {
		this.mascara = mascara;
	}

	public String getBinario() {
		return binario;
	}

	public void setBinario(String binario) {
		this.binario = binario;
	}

	public String getBinario1() {
		return binario1;
	}

	public void setBinario1(String binario1) {
		this.binario1 = binario1;
	}

	public String getBinario2() {
		return binario2;
	}

	public void setBinario2(String binario2) {
		this.binario2 = binario2;
	}

	public String getBinario3() {
		return binario3;
	}

	public void setBinario3(String binario3) {
		this.binario3 = binario3;
	}

	public String getBinario4() {
		return binario4;
	}

	public void setBinario4(String binario4) {
		this.binario4 = binario4;
	}

	public StringTokenizer getSt() {
		return st;
	}

	public void setSt(StringTokenizer st) {
		this.st = st;
	}

	public int getPrimeiroOcto() {
		return primeiroOcto;
	}

	public void setPrimeiroOcto(int primeiroOcto) {
		this.primeiroOcto = primeiroOcto;
	}

	public int getPrimeiroOctoIp() {
		return primeiroOctoIp;
	}

	public void setPrimeiroOctoIp(int primeiroOctoIp) {
		this.primeiroOctoIp = primeiroOctoIp;
	}

	public int getSegundoOcto() {
		return segundoOcto;
	}

	public void setSegundoOcto(int segundoOcto) {
		this.segundoOcto = segundoOcto;
	}

	public int getSegundoOctoIp() {
		return segundoOctoIp;
	}

	public void setSegundoOctoIp(int segundoOctoIp) {
		this.segundoOctoIp = segundoOctoIp;
	}

	public int getTerceiroOcto() {
		return terceiroOcto;
	}

	public void setTerceiroOcto(int terceiroOcto) {
		this.terceiroOcto = terceiroOcto;
	}

	public int getTerceiroOctoIp() {
		return terceiroOctoIp;
	}

	public void setTerceiroOctoIp(int terceiroOctoIp) {
		this.terceiroOctoIp = terceiroOctoIp;
	}

	public int getQuartoOcto() {
		return quartoOcto;
	}

	public void setQuartoOcto(int quartoOcto) {
		this.quartoOcto = quartoOcto;
	}

	public int getQuartoOctoIp() {
		return quartoOctoIp;
	}

	public void setQuartoOctoIp(int quartoOctoIp) {
		this.quartoOctoIp = quartoOctoIp;
	}

	public int getQtdeZeros() {
		return qtdeZeros;
	}

	public void setQtdeZeros(int qtdeZeros) {
		this.qtdeZeros = qtdeZeros;
	}

	public int getQtdeUms() {
		return qtdeUms;
	}

	public void setQtdeUms(int qtdeUms) {
		this.qtdeUms = qtdeUms;
	}

	public ArrayList<String> getArray() {
		return array;
	}

	public void setArray(ArrayList<String> array) {
		this.array = array;
	}

	public HashMap getMask() {
		return mask;
	}

	public void setMask(HashMap mask) {
		this.mask = mask;
	}
     
	  
}
