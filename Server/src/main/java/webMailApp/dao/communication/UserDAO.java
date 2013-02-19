package webMailApp.dao.communication;

import webMailApp.dao.dto.FolderDTO;
import webMailApp.dao.dto.LetterDTO;
import webMailApp.dao.dto.UserDTO;
import webMailApp.dao.entities.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: maru
 * Date: 15.02.13
 * Time: 16:16
 * To change this template use File | Settings | File Templates.
 */

public class UserDAO {
    private Logger log = Logger.getLogger(UserDAO.class.getName());

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

        FolderEntity folderEntity;

        TypedQuery<FolderEntity> folderQuery = em.createNamedQuery("Folder.findByAddressAndName", FolderEntity.class);
        folderQuery.setParameter("addressName", addressTo.getAddressName());
        folderQuery.setParameter("folderName", "Inbox");
        List<FolderEntity> folderResult = folderQuery.getResultList();

        if (folderResult.size() == 0) {
            folderEntity = new FolderEntity();
            folderEntity.setFolderName("Inbox");
            folderEntity.setFolderAddress(addressTo);
            folderEntity.setFolderLetters(new ArrayList<LetterEntity>());
            folderEntity.addLetter(newLetter);
            newLetter.setLetterFolder(folderEntity);

            try {
                trx.begin();
                em.persist(newLetter);
                em.persist(folderEntity);
                trx.commit();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            } finally {
                if (trx.isActive())
                    trx.rollback();
            }

        }  else {
            folderEntity = folderResult.get(0);
            folderEntity.addLetter(newLetter);
            newLetter.setLetterFolder(folderEntity);

            try {
                trx.begin();
                em.persist(newLetter);
                em.merge(folderEntity);
                trx.commit();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            } finally {
                if (trx.isActive())
                    trx.rollback();
            }
        }

        return true;

    }

   public String loginUser(String userAddress, String userPass) {
       TypedQuery<SessionEntity> query = em.createNamedQuery("Session.findByUserAddress",
                                                             SessionEntity.class);
       query.setParameter("userAddress", userAddress);
       List<SessionEntity> result = query.getResultList();


       String sessionNum;

       if (result != null && result.size() != 0 ) {
          if (result.get(0).getSessionUser().getUserPassword().equals(userPass)) {
              sessionNum = result.get(0).getSessionNum();
          } else {
              sessionNum = "";
          }
       } else {
           SessionEntity sessionEntity = new SessionEntity();

           TypedQuery<UserEntity> queryUser = em.createNamedQuery("User.findByAddressName",
                   UserEntity.class);
           queryUser.setParameter("addressName", userAddress);
           List<UserEntity> users = queryUser.getResultList();
           UserEntity user;

           if (users != null && users.size() != 0) {
               user = users.get(0);
                /*If password is correct*/
               if (user.getUserPassword().equals(userPass)) {
                   sessionEntity.setSessionUser(user);
                   sessionEntity.setSessionDate(new Date());
                   sessionEntity.setUserAddress(userAddress);

                   sessionNum = UUID.randomUUID().toString();
                   sessionEntity.setSessionNum(sessionNum);

                   sessionEntity.setSessionUser(user);
                   user.setUserSession(sessionEntity);

                   EntityTransaction trx = em.getTransaction();

                   try {
                       trx.begin();
                       em.persist(sessionEntity);
                       em.merge(user);
                       trx.commit();
                   } catch (Exception e) {
                       e.printStackTrace();
                   } finally {
                       if (trx.isActive())
                           trx.rollback();
                   }
               } else {
                   sessionNum = "";
               }
           } else sessionNum = "";



       }

       return sessionNum;
   }

    public UserDTO getUserBySession(String sessionNum) {
       TypedQuery<UserEntity> query = em.createNamedQuery("User.findUserBySessionID", UserEntity.class);
       query.setParameter("sessionNum", sessionNum);

       List<UserEntity> result = query.getResultList();

       if (result.size() != 0) {
           UserEntity userEntity = result.get(0);

           return new UserDTO(userEntity.getUserFirstName(), userEntity.getUserLastName(), userEntity.getUserPassword(),
                   userEntity.getUserBirthDate(), userEntity.getUserPhone(), userEntity.getUserAddress().getAddressName());
       } else {
           return null;
       }

    }

    public List<FolderDTO> getRecievedLetters(String addressName) {
        TypedQuery<AddressEntity> query = em.createNamedQuery("Address.findByName", AddressEntity.class);
        query.setParameter("addressName", addressName);
        List<AddressEntity> result = query.getResultList();
        List<FolderDTO> returnedFolders = new ArrayList<FolderDTO>();

        if (result != null && result.size() != 0) {
            List<FolderEntity> folders = result.get(0).getAddressFolder();

            if (folders != null && folders.size() != 0) {
                Iterator folderIterator = folders.iterator();

                while (folderIterator.hasNext()) {
                    FolderEntity curFolder = (FolderEntity) folderIterator.next();

                    List<LetterEntity> curLetters = curFolder.getFolderLetters();
                    List<LetterDTO> lettersDTO = new ArrayList<LetterDTO>();
                    Iterator lettersIterator = curLetters.iterator();

                    while (lettersIterator.hasNext()) {

                        LetterEntity letterEntity = (LetterEntity) lettersIterator.next();
                        LetterDTO letter = new LetterDTO(letterEntity.getLetterFrom().getAddressName(), letterEntity.getLetterTo().getAddressName(),
                                letterEntity.getLetterTheme(), letterEntity.getLetterDate(), letterEntity.getLetterBody());
                        lettersDTO.add(letter);
                    }

                    FolderDTO folderDTO = new FolderDTO(curFolder.getFolderName(), lettersDTO);
                    returnedFolders.add(folderDTO);
                }
            }

        } else {
          returnedFolders = null;
        }



        return returnedFolders;
    }

    public void delLetters(List<LetterDTO> letters) {
         TypedQuery<LetterEntity> query = em.createNamedQuery("Letter.findLetter", LetterEntity.class);
         ArrayList<LetterEntity> lettersToDel = new ArrayList<LetterEntity>();

         for (LetterDTO l : letters) {
             query.setParameter("letterDate", l.getLetterDate());
             query.setParameter("letterFrom", l.getLetterFrom());
             query.setParameter("letterTo", l.getLetterTo());
             query.setParameter("letterTheme", l.getLetterTheme());

             List<LetterEntity> result = query.getResultList();

             if (result != null && result.size() != 0) {
                 lettersToDel.add(result.get(0));
             }
         }

        try {
            for (LetterEntity l : lettersToDel) {
                EntityTransaction trx = em.getTransaction();
                trx.begin();
                em.remove(l);
                trx.commit();
                log.info("Commit letters ");


            }



        } catch (Exception e) {
            log.log(Level.SEVERE, "Exception!", e);
        } finally {
//            if (trx.isActive())
//                trx.rollback();
        }


    }
}
