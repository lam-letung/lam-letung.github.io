package edu.poly.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;

public abstract class AbstractEntityDao<T> {
	private Class<T> entityClass;
	
//	Tạo ra constructor truyển vào lớp thể hiện của lớp class, xác định T
	public AbstractEntityDao(Class<T> cls) {
		this.entityClass = cls;
	}
	
	public void insert(T entity) {
		EntityManager em = JpaUtils.getEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			
			em.persist(entity);
			
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		}finally {
			em.close();
		}
	}
	
	public void update(T entity) {
		EntityManager em = JpaUtils.getEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			
			em.merge(entity);
			
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		}finally {
			em.close();
		}
	}
	
	public void delete(Object id) {
		EntityManager em = JpaUtils.getEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			
			T entity = em.find(entityClass, id);
			
			em.remove(entity);
			
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		}finally {
			em.close();
		}
	}
	
	public T findById(Object id) {
		EntityManager em = JpaUtils.getEntityManager();
		
		
			T entity = em.find(entityClass, id);
			
			return entity;
	}
	
//	phương thức tìm kiếm và trả về tất cả các entity
	public List<T> findAll() {
		EntityManager em = JpaUtils.getEntityManager();
		
//		sử dụng CriteriaQuery cho phép xây dựng truy vấn và sử dụng from để tạo ra truy vấn từ entity class
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			
//		tạo ra mệnh đề select từ entity class
			cq.select(cq.from(entityClass));
			
//		sau đó gọi createQuery getResultList để trả về danh sách tìm thấy được
			return em.createQuery(cq).getResultList();
		} finally {
			em.close();
		}
		
	}
	
//	xác định các giá trị phân trnag
	public List<T> findAll(boolean all, int firstResult , int maxResult) {
		EntityManager em = JpaUtils.getEntityManager();
		
//		sử dụng CriteriaQuery cho phép xây dựng truy vấn và sử dụng from để tạo ra truy vấn từ entity class
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
//		tạo ra mệnh đề select từ entity class
			cq.select(cq.from(entityClass));
			Query q = em.createQuery(cq);
			if(!all) {
				 q.setFirstResult(firstResult);
				 q.setMaxResults(maxResult);
			}
			
//		sau đó gọi createQuery getResultList để trả về danh sách tìm thấy được
			return q.getResultList();
		} finally {
			em.close();
		}
		
	}
	
//	đếm và trả về tổng số các entity
	public Long count() {
		EntityManager em = JpaUtils.getEntityManager();
		
//		sử dụng CriteriaQuery cho phép xây dựng truy vấn và sử dụng from để tạo ra truy vấn từ entity class
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			
			
			Root<T> rt = cq.from(entityClass);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return (Long) q.getSingleResult();
		} finally {
			em.close();
		}
		
		
	}
	
	
}
