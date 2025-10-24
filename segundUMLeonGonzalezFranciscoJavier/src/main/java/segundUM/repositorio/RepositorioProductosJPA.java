package segundUM.repositorio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import segundUM.modelo.Categoria;
import segundUM.modelo.EstadoProducto;
import segundUM.modelo.Producto;

public class RepositorioProductosJPA implements IRepositorioProducto {
private EntityManagerFactory emf;
	
	public RepositorioProductosJPA(EntityManagerFactory emf) {
		this.emf = emf;
	}
	@Override
	public void add(Producto p) {
		EntityManager em = this.emf.createEntityManager();
		
		EntityTransaction et = em.getTransaction();
		
		try {
			et.begin();
			
			em.persist(p);
			
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
	public Producto get(String id) {
		EntityManager em = this.emf.createEntityManager();
		try {
			Producto p = em.find(Producto.class, id);
			return p;
		} finally {
			em.close();
		}
	}

	@Override
	public void update(Producto p) {
		EntityManager em = this.emf.createEntityManager();
		
		EntityTransaction et = em.getTransaction();
		
		try {
			et.begin();
			
			em.merge(p);
			
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
	public List<Producto> historialDelMes(int m, int a) {
		EntityManager em = this.emf.createEntityManager();
		try {
			String jpql = "SELECT p from Producto p WHERE MONTH(p.fechaPublicacion) = :m AND YEAR(p.fechaPublicacion) = :a  ORDER BY p.visualizaciones DESC";
			TypedQuery<Producto> query = em.createQuery(jpql,Producto.class);
			query.setParameter("m", m);
			query.setParameter("a", a);
			return query.getResultList();
		} finally {
			em.close();
		}
	}

	@Override
	public List<Producto> filtrar(Categoria c, String descripcion, EstadoProducto estado, double precioMax) {
		EntityManager em = this.emf.createEntityManager();
		try {
			StringBuilder jpql = new StringBuilder("SELECT p from Producto p");
			boolean whereAdded = false;
			Map<String,Object> params = new HashMap<>();
			
			if (c !=null) {
				jpql.append("WHERE p.categoria.ruta LIKE :ruta");
				params.put("ruta", c.getRuta()+"%");
				whereAdded = true;
			}
			
			if (descripcion !=null && !descripcion.trim().isEmpty()) {
				jpql.append(whereAdded? "AND" : "WHERE");
				jpql.append("p.descripcion LIKE :d");
				params.put("d", "%"+descripcion+"%");
				whereAdded = true;
			}
			
			if (precioMax > 0) {
				jpql.append(whereAdded? "AND" : "WHERE");
				jpql.append("p.precio <= :precioMax");
				params.put("precioMax", precioMax);
				
				whereAdded = true;
			}
			
			if (estado != null) {
				jpql.append(whereAdded? "AND" : "WHERE");
				jpql.append("p.estado IN :estaosValidos");
				params.put("estadosValidos", getEstadosValidos(estado));
				whereAdded = true;
			}
			
			TypedQuery<Producto> query = em.createQuery(jpql.toString(),Producto.class);
			for(Map.Entry<String,Object> entry: params.entrySet()) {
				query.setParameter(entry.getKey(),entry.getValue());
			}
			return query.getResultList();
		} finally {
			em.close();
		}
	}
	private Object getEstadosValidos(EstadoProducto estado) {
		List<EstadoProducto> estados = new ArrayList<>();
        switch (estado) {
            case PARA_PIEZAS_O_REPARAR:
                estados.add(EstadoProducto.PARA_PIEZAS_O_REPARAR);
            case ACEPTABLE:
                estados.add(EstadoProducto.ACEPTABLE);
            case BUEN_ESTADO:
                estados.add(EstadoProducto.BUEN_ESTADO);
            case COMO_NUEVO:
                estados.add(EstadoProducto.COMO_NUEVO);
            case NUEVO:
                estados.add(EstadoProducto.NUEVO);
        }
        return estados;
	}

}
