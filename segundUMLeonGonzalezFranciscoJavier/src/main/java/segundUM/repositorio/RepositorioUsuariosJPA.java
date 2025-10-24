package segundUM.repositorio;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import segundUM.modelo.Usuario;

public class RepositorioUsuariosJPA implements IRepositorioUsuarios{
	
	private EntityManagerFactory emf;
	
	public RepositorioUsuariosJPA(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	@Override
	public void add(Usuario u) {
		EntityManager em = this.emf.createEntityManager();
		
		EntityTransaction et = em.getTransaction();
		
		try {
			et.begin();
			
			em.persist(u);
			
			et.commit();
		} catch (Exception e) {
			if(et.isActive()) {
				et.rollback();
			}
			
			e.printStackTrace();
		} finally {
			em.close();
		}
		}

	@Override
	public Usuario get(String id) {
		EntityManager em = this.emf.createEntityManager();
		try {
			Usuario u = em.find(Usuario.class, id);
			return u;
		} finally {
			em.close();
		}
	}

	@Override
	public void update(Usuario u) {
		EntityManager em = this.emf.createEntityManager();
		
		EntityTransaction et = em.getTransaction();
		
		try {
			et.begin();
			
			em.merge(u);
			
			et.commit();
		} catch (Exception e) {
			if(et.isActive()) {
				et.rollback();
			}
			
			e.printStackTrace();
		} finally {
			em.close();
		}
		
	}

}
