package webMailApp.dao.communication;

import webMailApp.dao.dto.FolderDTO;
import webMailApp.dao.dto.LetterDTO;
import webMailApp.dao.entities.EmailEntity;
import webMailApp.dao.entities.FolderEntity;
import webMailApp.dao.entities.LetterEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: maru
 * Date: 15.02.13
 * Time: 16:16
 * To change this template use File | Settings | File Templates.
 */
public class LetterDAO {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("server");

    public String sendLetter(String letterFrom, String letterTo, String letterTheme,
                             Date letterDate, String letterBody)
    {
        LetterEntity newLetter = new LetterEntity();
        newLetter.setLetterBody(letterBody);
        newLetter.setLetterDate(letterDate);
        newLetter.setLetterTheme(letterTheme);

        EntityManager em = emf.createEntityManager();
        EntityTransaction trx = em.getTransaction();

        TypedQuery<EmailEntity> query = em.createNamedQuery("Address.findByName", EmailEntity.class);
        query.setParameter("addressName", letterFrom);
        List<EmailEntity> result = query.getResultList();

        EmailEntity addresFrom = result.get(0);

        query = em.createNamedQuery("Address.findByName", EmailEntity.class);
        query.setParameter("addressName", letterTo);
        result = query.getResultList();

        if (result != null && result.size() != 0) {
            EmailEntity addressTo = result.get(0);

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
                    return "Fail";
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
                    return "Fail";
                } finally {
                    if (trx.isActive())
                        trx.rollback();
                }
            }
        } else return "No such to";

        return "OK";
    }

    public List<FolderDTO> getRecievedLetters(String addressName) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<EmailEntity> query = em.createNamedQuery("Address.findByName", EmailEntity.class);
        query.setParameter("addressName", addressName);
        List<EmailEntity> result = query.getResultList();
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

    public boolean delLetters(List<LetterDTO> letters) {
        EntityManager em = emf.createEntityManager();
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
            EntityTransaction trx = em.getTransaction();
            trx.begin();
            for (LetterEntity l : lettersToDel) {
                em.createQuery("delete  from LetterEntity l where l = :letter")
                        .setParameter("letter", l)
                        .executeUpdate();

            }
            trx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;

    }
}
