package segundUM.servicio;



import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import segundUM.modelo.Categoria;
import segundUM.repositorio.IRepositorioCategorias;

public class ServicioCategorias {
	private IRepositorioCategorias repositorioCategorias;
	
	public ServicioCategorias (IRepositorioCategorias sc) {
		this.repositorioCategorias=sc;
	}
	
	public void cargarJerarquia(String ruta) {
		try {
			JAXBContext context = JAXBContext.newInstance(Categoria.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			File fichero = new File(ruta);
			Categoria raiz = (Categoria) unmarshaller.unmarshal(fichero);
			if(repositorioCategorias.get(raiz.getId())==null) {
				vincularPadres(raiz);
				repositorioCategorias.add(raiz);
			}
			
		} catch (Exception e) {
			throw new RuntimeException("Error al cargar el XML de categorías: " + e.getMessage(), e);
		}
	}

	private void vincularPadres(Categoria raiz) {
		if(raiz.getSubcategorias() == null) {
			return;
		}
		
		for(Categoria hija: raiz.getSubcategorias()) {
			hija.setPadre(raiz);
			vincularPadres(hija);
		}
	}
	
	public void modificar(String id,String descripcion) {
		Categoria c = repositorioCategorias.get(id);
		if(c==null) {
			throw new RuntimeException("Categoría con ID " + id+ "no encontrada.\n");
		}
		
		c.setDescripcion(descripcion);
		
		repositorioCategorias.update(c);
	}
	
	public List<Categoria> getRaices() {
		return repositorioCategorias.getCategoriasRaiz();
	}
	
	public List<Categoria> getHijos(String id){
		Categoria c = repositorioCategorias.get(id);
		if(c==null) {
			throw new RuntimeException("Categoría con ID " + id+ "no encontrada.\n");
		}
		
		return repositorioCategorias.getDescendientes(c);
	}
}
