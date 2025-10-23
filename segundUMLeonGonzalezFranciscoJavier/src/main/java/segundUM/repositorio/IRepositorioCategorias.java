package segundUM.repositorio;

import java.util.List;

import segundUM.modelo.Categoria;

public interface IRepositorioCategorias {
	
	public void add(Categoria c);
	
	public Categoria get(String id);
	
	public void update(Categoria c);
	
	public List<Categoria> getCategoriasRaiz();
	
	public List<Categoria> getDescendientes(Categoria c);
}
