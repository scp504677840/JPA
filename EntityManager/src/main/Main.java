package main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        // 获取EntityManagerFactory
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("lab");
        // 获取EntityManager
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        // 获取事务
        EntityTransaction transaction = entityManager.getTransaction();
        // 开启事务
        transaction.begin();

        //lock
        //lock
        //clear
        //detach
        //contains
        //getLockMode
        //setProperty
        //getProperties
        //createNamedStoredProcedureQuery
        //createStoredProcedureQuery
        //createStoredProcedureQuery
        //createStoredProcedureQuery
        //joinTransaction
        //isJoinedToTransaction
        //unwrap
        //getDelegate
        //isOpen
        //getCriteriaBuilder
        //getMetamodel
        //createEntityGraph
        //createEntityGraph
        //getEntityGraph
        //getEntityGraphs

        // 提交事务
        transaction.commit();

        // 关闭资源
        entityManager.close();
        entityManagerFactory.close();
    }

}
