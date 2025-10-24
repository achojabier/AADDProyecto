package segundUM.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "categorias")
@XmlRootElement(name="categoria")
public class Categoria {
	@Id
	@XmlAttribute(name="id")
	private String id;
	@XmlElement(name="nombre")
	private String nombre;
	@XmlTransient
	private String descripcion;
	@XmlAttribute(name="ruta")
	private String ruta;
	@ManyToOne
	@XmlTransient
	private Categoria padre;
	@XmlElement(name="categoria")
	@OneToMany(mappedBy = "padre", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Categoria> subcategorias = new ArrayList<>();
	
	public Categoria() {
		
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getRuta() {
		return ruta;
	}
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	public Categoria getPadre() {
		return padre;
	}
	public void setPadre(Categoria padre) {
		this.padre = padre;
	}
	public List<Categoria> getSubcategorias() {
		return subcategorias;
	}
	public void setSubcategorias(List<Categoria> subcategorias) {
		this.subcategorias = subcategorias;
	}
}
