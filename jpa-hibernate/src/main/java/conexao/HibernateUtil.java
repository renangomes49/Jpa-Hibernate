package conexao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {

	public static EntityManagerFactory factory = null;
	
	
	// método para retornar o id de uma classe qualquer
	// com isso, utilizar para fazer uma consulta na classe DaoGenerico apartir desse ID
	public static Object getPrimarykey(Object entity) {
		
		return factory.getPersistenceUnitUtil().getIdentifier(entity);
	}
	
	static {
		init();
	}
	
	private static void init() {
		try {
			
			if(factory == null) {
				factory = Persistence.createEntityManagerFactory("jpa-hibernate");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
	
}
