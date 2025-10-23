package segundUM.modelo;

import java.time.LocalDateTime;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "productos")
public class Producto {
	@Id
	private String id;
	private String titulo;
	private String descripcion;
	private double precio;
	@Enumerated(EnumType.STRING)
	private EstadoProducto estado;
	private LocalDateTime fechaPublicacion;
	@ManyToOne
	private Categoria categoria;
	private int visualizaciones;
	private boolean envioDisponible;
	@Embedded
	private LugarDeRecogida recogida;
	@ManyToOne
	private Usuario vendedor;
	public Producto() {
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public EstadoProducto getEstado() {
		return estado;
	}
	public void setEstado(EstadoProducto estado) {
		this.estado = estado;
	}
	public LocalDateTime getFechaPublicacion() {
		return fechaPublicacion;
	}
	public void setFechaPublicacion(LocalDateTime fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public int getVisualizaciones() {
		return visualizaciones;
	}
	public void setVisualizaciones(int visualizaciones) {
		this.visualizaciones = visualizaciones;
	}
	public boolean isEnvioDisponible() {
		return envioDisponible;
	}
	public void setEnvioDisponible(boolean envioDisponible) {
		this.envioDisponible = envioDisponible;
	}
	public LugarDeRecogida getRecogida() {
		return recogida;
	}
	public void setRecogida(LugarDeRecogida recogida) {
		this.recogida = recogida;
	}
	public Usuario getVendedor() {
		return vendedor;
	}
	public void setVendedor(Usuario vendedor) {
		this.vendedor = vendedor;
	}
	
}
