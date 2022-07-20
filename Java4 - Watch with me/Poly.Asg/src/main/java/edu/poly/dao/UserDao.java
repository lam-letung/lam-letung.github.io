package edu.poly.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import edu.poly.model.User;

public class UserDao extends AbstractEntityDao<User>{

	public UserDao() {
		super(User.class);
		
	}
	public void changePassword(String username, String oldPassword, String newPassword) throws Exception {
		EntityManager em = JpaUtils.getEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		String jpql = "select u from User u where u.username =:username and u.password =:password";
		
		try {
			trans.begin();
			TypedQuery<User> query = em.createQuery(jpql , User.class);
			query.setParameter("username", username);//thiết lập giá trị cho các tham số
			query.setParameter("password", oldPassword);
			
			User user = query.getSingleResult();//getSingleResult để trả về đối tượng, nếu jpql tím thấy thì trả về username
			//còn nếu trả về bằng rỗng thì ném ra ngoại lệ
			if(user == null) {
				throw new Exception("Current Password or Username are incorrect");
			}
			user.setPassword(newPassword);//nếu tìm thấy tiến thành thay password bằng newPassword
			
			em.merge(user);//thực hiện các thay đổi vs chương trình
			
			trans.commit();//hoàn tất các hành động với chương trình
		} catch (Exception e) {
			trans.rollback();
			
			throw e;
		}finally {
			em.close();
		}
	}
	
	
	public User findByUsernameAndEmail(String username, String email) {
		EntityManager em = JpaUtils.getEntityManager();
		
		String jpql = "select u from User u where u.username=:username and u.email =:email";
		
		try {
			TypedQuery<User> query = em.createQuery(jpql , User.class);
			query.setParameter("username", username);
			query.setParameter("email", email);
			
			return query.getSingleResult();
		} finally {
			em.close();
		}
		}
	}

