package modelo;

import java.util.Date;

public class Mensagem extends EntidadeNegocio{
	private String texto;
	private int emissorID;
	private int receptorID;
	private boolean status;
	private Date dataEnvio;
	
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public int getEmissorID() {
		return emissorID;
	}
	public void setEmissorID(int emissorID) {
		this.emissorID = emissorID;
	}
	public int getReceptorID() {
		return receptorID;
	}
	public void setReceptorID(int receptorID) {
		this.receptorID = receptorID;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Date getDataEnvio() {
		return dataEnvio;
	}
	public void setDataEnvio(Date dataEnvio) {
		this.dataEnvio = dataEnvio;
	}
	
}
