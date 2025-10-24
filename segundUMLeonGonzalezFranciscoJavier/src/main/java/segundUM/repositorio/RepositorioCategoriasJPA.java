package segundUM.repositorio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import segundUM.modelo.Categoria;
import segundUM.modelo.Usuario;

public class RepositorioCategoriasJPA implements IRepositorioCategorias {

	private EntityManagerFactory emf;
	
	public RepositorioCategoriasJPA(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	
	@Override
	public void add(Categoria c) {
		EntityManager em = this.emf.createEntityManager();
		
		EntityTransaction et = em.getTransaction();
		
		try {
			et.begin();
			
			em.persist(c);
			
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
	public Categoria get(String id) {
		EntityManager em = this.emf.createEntityManager();
		try {
			Categoria c = em.find(Categoria.class, id);
			return c;
		} finally {
			em.close();
		}
	}

	@Override
	public void update(Categoria c) {
		EntityManager em = this.emf.createEntityManager();
		
		EntityTransaction et = em.getTransaction();
		
		try {
			et.begin();
			
			em.merge(c);
			
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
	public List<Categoria> getCategoriasRaiz() {
		EntityManager em = this.emf.createEntityManager();
		try {
			String jpql = "SELECT c from Categoria c WHERE c.padre IS NULL";
			TypedQuery<Categoria> query = em.createQuery(jpql,Categoria.class);
			return query.getResultList();
		} finally {
			em.close();
		}
	}

	@Override
	public List<Categoria> getDescendientes(Categoria c) {
		EntityManager em = this.emf.createEntityManager();
		try {
			String patronRuta = c.getRuta()+"%";
			String jpql = "SELECT c from Categoria c WHERE c.ruta LIKE :patron AND c.id <> :idPadre";
			TypedQuery<Categoria> query = em.createQuery(jpql,Categoria.class);
			
			query.setParameter("patron", patronRuta);
			query.setParameter("idPadre", c.getId());
			return query.getResultList();
		} finally {
			em.close();
		}
	}

}
