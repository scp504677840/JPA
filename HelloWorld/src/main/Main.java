package main;

import main.entities.AccountInfoEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;

public class Main {

    public static void main(String[] args) {
        // 1.获取EntityManagerFactory
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("lab");
        // 2.获取EntityManager
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        // 3.获取事务
        EntityTransaction transaction = entityManager.getTransaction();
        // 4.开启事务
        transaction.begin();

        // 5.准备数据
        AccountInfoEntity accountInfoEntity = new AccountInfoEntity();
        accountInfoEntity.setName("TT");
        accountInfoEntity.setGmtCreate(Timestamp.from(Instant.now()));
        accountInfoEntity.setGmtModified(Timestamp.from(Instant.now()));
        accountInfoEntity.setBalance(BigDecimal.valueOf(10000L));

        // 6.执行添加操作
        entityManager.persist(accountInfoEntity);

        // 7.提交事务
        transaction.commit();

        // 8.关闭资源
        entityManager.close();
        entityManagerFactory.close();
    }

}
