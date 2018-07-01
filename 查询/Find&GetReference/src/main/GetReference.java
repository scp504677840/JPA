package main;

import main.entities.AccountInfoEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.math.BigInteger;

public class GetReference {

    public static void main(String[] args) {
        // 获取EntityManagerFactory
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("lab");
        // 获取EntityManager
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        // 获取事务
        EntityTransaction transaction = entityManager.getTransaction();
        // 开启事务
        transaction.begin();

        // 查找对象；延迟检索。与find方法不同的是find是立即检索。
        AccountInfoEntity accountInfoEntity = entityManager.getReference(AccountInfoEntity.class, BigInteger.valueOf(1L));
        System.out.println(accountInfoEntity.getClass().getName());
        System.out.println("------");
        System.out.println(accountInfoEntity);

        // 提交事务
        transaction.commit();

        // 关闭资源
        entityManager.close();
        entityManagerFactory.close();
    }

}
/*
main.entities.AccountInfoEntity$HibernateProxy$Wuy8UOQT
------
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
AccountInfoEntity{id=1, gmtCreate=2018-06-27 22:48:28.0, gmtModified=2018-06-27 23:48:32.0, name='Tom', balance=17.72}
 */