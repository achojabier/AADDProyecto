package segundUM.repositorio;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FactoriaRepositorios {
	private EntityManagerFactory emf;
	
	public FactoriaRepositorios() {
		emf = Persistence.createEntityManagerFactory("segundUM");
	}
	
	public IRepositorioUsuarios getRepositorioUsuarios() {
		return new RepositorioUsuariosJPA(emf);
		
	}
	
	public IRepositorioCategorias getRepositorioCategorias() {
		// return new RepositorioCategoriasJPA(emf);
		return null;
	}
	
	public IRepositorioProducto getRepositorioProductos() {
		// return new RepositorioProductosJPA(emf);
		return null;
	}
	
	public void close() {
        if (this.emf != null && this.emf.isOpen()) {
            this.emf.close();
        }
    }
}
