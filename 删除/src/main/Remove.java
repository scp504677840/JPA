package main;

import main.entities.AccountInfoEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.math.BigInteger;

public class Remove {

    public static void main(String[] args) {
        // 获取EntityManagerFactory
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("lab");
        // 获取EntityManager
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        // 获取事务
        EntityTransaction transaction = entityManager.getTransaction();
        // 开启事务
        transaction.begin();

        // 移除一个对象。注意：它只能删除一个持久化对象，不能移除游离对象和临时对象。
        AccountInfoEntity accountInfoEntity = entityManager.find(AccountInfoEntity.class, BigInteger.valueOf(2L));
        entityManager.remove(accountInfoEntity);

        // 提交事务
        transaction.commit();

        // 关闭资源
        entityManager.close();
        entityManagerFactory.close();
    }

}
/*
Hibernate:
    select
        accountinf0_.id as id1_0_0_,
        accountinf0_.balance as balance2_0_0_,
        accountinf0_.gmt_create as gmt_crea3_0_0_,
        accountinf0_.gmt_modified as gmt_modi4_0_0_,
        accountinf0_.name as name5_0_0_
    from
        account_info accountinf0_
    where
        accountinf0_.id=?
Hibernate:
    delete
    from
        account_info
    where
        id=?
 */
