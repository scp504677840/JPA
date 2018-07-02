package main;

import main.entities.AccountInfoEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.math.BigInteger;

/**
 * refresh
 * 使数据库更新实体对象
 */
public class Refresh {

    public static void main(String[] args) {
        // 获取EntityManagerFactory
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("lab");
        // 获取EntityManager
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        // 获取事务
        EntityTransaction transaction = entityManager.getTransaction();
        // 开启事务
        transaction.begin();

        // 第一次查询，发送第一条SQL语句，并将查询结果加入缓存中。
        AccountInfoEntity accountInfoEntity = entityManager.find(AccountInfoEntity.class, BigInteger.valueOf(3L));
        // 第二次查询，若缓存中有，直接取，若没有则查库。实际上不会发送SQL语句，因为上面刚刚查过。
        accountInfoEntity = entityManager.find(AccountInfoEntity.class, BigInteger.valueOf(3L));
        //refresh，强制其实体对象与数据库中的数据同步。会再次发送SQL语句，查库。
        entityManager.refresh(accountInfoEntity);

        // 提交事务
        transaction.commit();

        // 关闭资源
        entityManager.close();
        entityManagerFactory.close();
    }

}
