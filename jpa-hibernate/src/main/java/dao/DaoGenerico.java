package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import conexao.HibernateUtil;


public class DaoGenerico<E> {

	private EntityManager entityManager = HibernateUtil.getEntityManager();
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void salvar(E entidade) {
		
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(entidade);
		transaction.commit();
	}
	
	public E updateMerge(E entidade) { // salva ou atualiza
		
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		E entidadeAtualizada = entityManager.merge(entidade);
		transaction.commit();
		
		return entidadeAtualizada;
	}
	
	public void deletar(E entidade) {
		
		Object id = HibernateUtil.getPrimarykey(entidade);
		
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.createNativeQuery("delete from " + entidade.getClass().getSimpleName().toLowerCase() + " where id =" + id ).executeUpdate();
		transaction.commit();
		
	}
	
	public List<E> listar(Class<E> entidade){
		
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		List<E> lista = entityManager.createQuery("from " + entidade.getName()).getResultList();
		
		transaction.commit();
		
		return lista;
	} 
	
	
	public E consultar(E entity) {
		
		Object id = HibernateUtil.getPrimarykey(entity);
		E e = (E) entityManager.find(entity.getClass(), id);
		
		return e;
	}
	
	// método consultar passando o id e a classe
	public E consultar2(Long id ,Class<E> entity) {
		
		E e = (E) entityManager.find(entity, id);
		
		return e;
	}
}





