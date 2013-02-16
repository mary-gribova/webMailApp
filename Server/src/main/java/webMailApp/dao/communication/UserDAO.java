package webMailApp.dao.communication;

import webMailApp.dao.entities.AddressEntity;
import webMailApp.dao.entities.FolderEntity;
import webMailApp.dao.entities.LetterEntity;
import webMailApp.dao.entities.UserEntity;

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

    private static EntityManager em = Persistence.createEntityManagerFactory("server").createEntityManager();

    public boolean addUser(String userFirstName, String userLastName, Date userBirthDate,
                           String userPhone, String userPassword, String userAddress) {

        UserEntity newUser = new UserEntity(userFirstName, userLastName, userBirthDate,
                                        userPhone, userPassword);

        AddressEntity newAddresss = new AddressEntity();
        newAddresss.setAddressDate(new Date());
        newAddresss.setAddressUser(newUser);
        newAddresss.setAddressName(userAddress);
        newUser.setUserAddress(newAddresss);

        EntityTransaction trx = em.getTransaction();

        try {
            trx.begin();
            em.persist(newUser);
            em.persist(newAddresss);
            trx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (trx.isActive())
                trx.rollback();
        }

        return true;
    }

    public boolean sendLetter(String letterFrom, String letterTo, String letterTheme,
                              Date letterDate, String letterBody)

    {
        LetterEntity newLetter = new LetterEntity();
        newLetter.setLetterBody(letterBody);
        newLetter.setLetterDate(letterDate);
        newLetter.setLetterTheme(letterTheme);

        EntityTransaction trx = em.getTransaction();

        TypedQuery<AddressEntity> query = em.createNamedQuery("Address.findByName", AddressEntity.class);
        query.setParameter("addressName", letterFrom);
        List<AddressEntity> result = query.getResultList();

        AddressEntity addresFrom = result.get(0);

        query = em.createNamedQuery("Address.findByName", AddressEntity.class);
        query.setParameter("addressName", letterTo);
        result = query.getResultList();

        AddressEntity addressTo = result.get(0);

        newLetter.setLetterTo(addressTo);
        newLetter.setLetterFrom(addresFrom);

        try {
            trx.begin();
            em.persist(newLetter);
            trx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (trx.isActive())
                trx.rollback();
        }
        return true;
    }



}
