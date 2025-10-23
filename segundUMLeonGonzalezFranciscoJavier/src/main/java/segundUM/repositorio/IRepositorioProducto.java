package segundUM.repositorio;

import java.util.List;

import segundUM.modelo.Categoria;
import segundUM.modelo.EstadoProducto;
import segundUM.modelo.Producto;

public interface IRepositorioProducto {
	
	public void add(Producto p);
	
	public Producto get(String id);
	
	public void update(Producto p);
	
	public List<Producto> historialDelMes(int m, int a);
	
	public List<Producto> filtrar(Categoria c, String descripcion, EstadoProducto estado, double precio);
}
