package webMailApp.dao.communication;

import webMailApp.dao.entities.AddressEntity;
import webMailApp.dao.entities.FolderEntity;
import webMailApp.dao.entities.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: maru
 * Date: 15.02.13
 * Time: 16:16
 * To change this template use File | Settings | File Templates.
 */
public class UserDAO {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("server");

    public boolean addUser(String userFirstName, String userLastName, String userPassword,
                           Date userBirthDate, String userPhone, String userAddress) {

        UserEntity newUser = new UserEntity(userFirstName, userLastName, userPassword,
                                      userBirthDate, userPhone, userAddress);

        EntityManager em = emf.createEntityManager();
        EntityTransaction trx = em.getTransaction();

        try {
            trx.begin();
            em.persist(newUser);
            trx.commit();
        } catch (Exception e) {

        } finally {
            if (trx.isActive())
                trx.rollback();
        }


        return true;
    }
}
