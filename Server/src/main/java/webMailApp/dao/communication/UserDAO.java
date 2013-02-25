package webMailApp.dao.communication;


import org.mindrot.jbcrypt.BCrypt;
import webMailApp.dao.dto.UserDTO;
import webMailApp.dao.entities.*;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: maru
 * Date: 15.02.13
 * Time: 16:16
 * To change this template use File | Settings | File Templates.
 */

public class UserDAO {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("server");

    public boolean addUser(String userFirstName, String userLastName, Date userBirthDate,
                           String userPhone, String userPassword, String userAddress) {

        String hashed = BCrypt.hashpw(userPassword, BCrypt.gensalt());
        UserEntity newUser = new UserEntity(userFirstName, userLastName, userBirthDate,
                                        userPhone, hashed);

        EmailEntity newAddresss = new EmailEntity();
        newAddresss.setAddressDate(new Date());
        newAddresss.setAddressUser(newUser);
        newAddresss.setAddressName(userAddress);
        newUser.setUserAddress(newAddresss);

        EntityManager em = emf.createEntityManager();
        EntityTransaction trx = em.getTransaction();

        try {
            trx.begin();
            em.persist(newUser);
            em.persist(newAddresss);
            trx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (trx.isActive())
                trx.rollback();
        }

        return true;
    }

    public boolean loginUser(String userAddress, String userPass) {
       EntityManager em = emf.createEntityManager();

        TypedQuery<UserEntity> queryUser = em.createNamedQuery("User.findByAddressName",
                   UserEntity.class);
           queryUser.setParameter("addressName", userAddress);
           List<UserEntity> users = queryUser.getResultList();
           UserEntity user;

           if (users != null && users.size() != 0) {
               user = users.get(0);

                /*If password is correct*/
               if (BCrypt.checkpw(userPass, user.getUserPassword())) {
                   return true;
               } else return false;
           } else return false;
   }

    public UserDTO getUserByEmail(String email) {
        EntityManager em = emf.createEntityManager();
       TypedQuery<UserEntity> query = em.createNamedQuery("User.findByAddressName", UserEntity.class);
       query.setParameter("addressName", email);

       List<UserEntity> result = query.getResultList();

       if (result.size() != 0) {
           UserEntity userEntity = result.get(0);

           return new UserDTO(userEntity.getUserFirstName(), userEntity.getUserLastName(), userEntity.getUserPassword(),
                   userEntity.getUserBirthDate(), userEntity.getUserPhone(), userEntity.getUserAddress().getAddressName());
       } else {
           return null;
       }

    }


}
