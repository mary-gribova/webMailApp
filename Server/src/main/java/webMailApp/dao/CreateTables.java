package webMailApp.dao;


import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: maru
 * Date: 15.02.13
 * Time: 0:15
 * To change this template use File | Settings | File Templates.
 */

public class CreateTables {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("server");

    public static void main(String[] args) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction trx = em.getTransaction();


    }
}
