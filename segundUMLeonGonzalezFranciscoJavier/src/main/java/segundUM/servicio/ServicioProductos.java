package segundUM.servicio;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import segundUM.modelo.Categoria;
import segundUM.modelo.EstadoProducto;
import segundUM.modelo.LugarDeRecogida;
import segundUM.modelo.Producto;
import segundUM.modelo.Usuario;
import segundUM.repositorio.IRepositorioCategorias;
import segundUM.repositorio.IRepositorioProducto;
import segundUM.repositorio.IRepositorioUsuarios;

public class ServicioProductos {
	private IRepositorioProducto repositorioProductos;
	private IRepositorioCategorias repositorioCategorias;
	private IRepositorioUsuarios repositorioUsuarios;
	public ServicioProductos(IRepositorioProducto rp, IRepositorioCategorias sc, IRepositorioUsuarios ru) {
		this.repositorioProductos=rp;
		this.repositorioCategorias=sc;
		this.repositorioUsuarios = ru;
	}
	
	public String altaProducto(String titulo, String descripcion, double precio, EstadoProducto estado, String idCategoria, boolean envio, String idVendedor) {
		Usuario u = repositorioUsuarios.get(idVendedor);
		if (u==null) {
			throw new RuntimeException("No hay ningún usuario vinculado al id "+idVendedor+".\n");
		}
		Categoria c = repositorioCategorias.get(idCategoria);
		if (c==null) {
			throw new RuntimeException("No hay ninguna categoría vinculada al id "+idCategoria+".\n");
		}
		
		if (titulo == null || titulo.trim().isEmpty()) {
	        throw new IllegalArgumentException("El título del producto no puede estar vacío.");
	    }
	    
	    if (descripcion == null || descripcion.trim().isEmpty()) {
	        throw new IllegalArgumentException("La descripción del producto no puede estar vacía.");
	    }

	    if (estado == null) {
	        throw new IllegalArgumentException("El estado del producto no puede ser nulo.");
	    }

	    if (idCategoria == null || idCategoria.trim().isEmpty()) {
	        throw new IllegalArgumentException("El ID de la categoría no puede estar vacío.");
	    }
	    
	    if (idVendedor == null || idVendedor.trim().isEmpty()) {
	        throw new IllegalArgumentException("El ID del vendedor no puede estar vacío.");
	    }

	    if (precio < 0) {
	        throw new IllegalArgumentException("El precio no puede ser negativo.");
	    }
		
		Producto p = new Producto();
		p.setTitulo(titulo);
		p.setDescripcion(descripcion);
		p.setPrecio(precio);
		p.setEstado(estado);
		p.setCategoria(c);
		p.setVendedor(u);
		p.setEnvioDisponible(envio);
		
		p.setId(UUID.randomUUID().toString());
		p.setFechaPublicacion(LocalDateTime.now());
		p.setVisualizaciones(0);
		repositorioProductos.add(p);
		return p.getId();
	}
	
	public void asignarLugarDeRecogida(String id, double longitud, double latitud, String descripcion) {
		LugarDeRecogida lugar = new LugarDeRecogida();
		if(descripcion == null) {
			throw new IllegalArgumentException("Descripción vacía.\n");
		}
		lugar.setDescripcion(descripcion);
		lugar.setLatitud(latitud);
		lugar.setLongitud(longitud);
		Producto p = repositorioProductos.get(id);
		if(p == null) {
			throw new RuntimeException("Producto no encontrado con ID: " + id);
		}
		p.setRecogida(lugar);
		repositorioProductos.update(p);
	}
	
	public void modificarProducto(String id, Double precio, String descripcion) {
		Producto p = repositorioProductos.get(id);
		if(p == null) {
			throw new RuntimeException("Producto no encontrado con ID: " + id);
		}
		if(precio != null) {
	        if (precio >= 0) { 
	            p.setPrecio(precio);
	        } else {
	            throw new IllegalArgumentException("El precio no puede ser negativo.");
	            
	        }
	    }
		if(descripcion != null) {
			p.setDescripcion(descripcion);
		}
		repositorioProductos.update(p);
	}
	
	public void sumarVisualizacion(String id) {
		Producto p = repositorioProductos.get(id);
		if(p == null) {
			throw new RuntimeException("Producto no encontrado con ID: " + id);
		}
		int visitas = p.getVisualizaciones();
		p.setVisualizaciones(visitas+1);
		repositorioProductos.update(p);
	}
	public List<Producto> historialDelMes(int m, int a){
		return repositorioProductos.historialDelMes(m, a);
	}
	public List<Producto> filtrar(Categoria c, String descripcion, EstadoProducto estado, double precioMax){
		return repositorioProductos.filtrar(c, descripcion, estado, precioMax);
	}
}
