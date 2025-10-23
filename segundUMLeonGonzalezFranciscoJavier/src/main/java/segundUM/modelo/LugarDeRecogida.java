package segundUM.modelo;

import javax.persistence.Embeddable;

@Embeddable
public class LugarDeRecogida {
	private String descripcion;
	private double latitud;
	private double longitud;
	public LugarDeRecogida() {
		
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public double getLatitud() {
		return latitud;
	}
	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}
	public double getLongitud() {
		return longitud;
	}
	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}
}
