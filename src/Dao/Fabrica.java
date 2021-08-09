package Dao;

import javax.persistence.*;

/***
 * Minha fabrica de conexoes (hib/jpa)
 * @author Rodrigo
 *
 */
public class Fabrica {
	static EntityManagerFactory emf = 
			 Persistence.createEntityManagerFactory("Nome_Da_Unit_De_Persistencia");

	public static EntityManager getEntityManager() { // getConnection() {
		System.out.println("Conectou");
		return emf.createEntityManager();
	}

}
