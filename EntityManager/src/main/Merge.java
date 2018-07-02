package main;

import main.entities.AccountInfoEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.Instant;

/**
 * merge相当于hibernate里面的saveOrUpdate
 */
public class Merge {

    public static void main(String[] args) {
        // 获取EntityManagerFactory
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("lab");
        // 获取EntityManager
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        // 获取事务
        EntityTransaction transaction = entityManager.getTransaction();
        // 开启事务
        transaction.begin();

        // 数据在缓存中没有，数据库中也没有
        // fun1(entityManager);

        // 数据在缓存中没有，数据库中有
        // fun2(entityManager);

        // 数据在缓存中有，数据库中也有，查询出之后改变其属性值。
        // fun3(entityManager);

        // 数据在缓存中有，数据库中也有，创建一个数据对象，然后再查询出一个数据对象，创建出来的数据对象和查询出来的对象ID相同。
        fun4(entityManager);

        // 提交事务
        transaction.commit();

        // 关闭资源
        entityManager.close();
        entityManagerFactory.close();
    }

    /**
     * 数据在缓存中有，数据库中也有，
     * 创建一个数据对象，然后再查询出一个数据对象，创建出来的数据对象和查询出来的对象ID相同。
     *
     * @param entityManager 实体类管理器
     */
    private static void fun4(EntityManager entityManager) {
        // 准备数据
        AccountInfoEntity accountInfoEntity = new AccountInfoEntity();
        accountInfoEntity.setName("DD");
        accountInfoEntity.setGmtCreate(Timestamp.from(Instant.now()));
        accountInfoEntity.setGmtModified(Timestamp.from(Instant.now()));
        accountInfoEntity.setBalance(BigDecimal.valueOf(666L));
        accountInfoEntity.setId(BigInteger.valueOf(3L));

        AccountInfoEntity accountInfo = entityManager.find(AccountInfoEntity.class, BigInteger.valueOf(3L));
        AccountInfoEntity merge = entityManager.merge(accountInfoEntity);

        System.out.println("accountInfoEntity.getBalance: " + accountInfoEntity.getBalance());
        System.out.println("accountInfo.getBalance: " + accountInfo.getBalance());
        System.out.println("merge.getBalance: " + merge.getBalance());
        System.out.println("accountInfoEntity == merge: " + (accountInfoEntity == merge));
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
        accountInfoEntity.getBalance: 666
        accountInfo.getBalance: 666
        merge.getBalance: 666
        accountInfoEntity == merge: false
        Hibernate:
            update
                account_info
            set
                balance=?,
                gmt_create=?,
                gmt_modified=?,
                name=?
            where
                id=?
         */
    }

    /**
     * 数据在缓存中有，数据库中也有，查询出之后改变其属性值。
     *
     * @param entityManager 实体类管理器
     */
    private static void fun3(EntityManager entityManager) {
        AccountInfoEntity accountInfoEntity = entityManager.find(AccountInfoEntity.class, BigInteger.valueOf(3L));
        accountInfoEntity.setGmtModified(Timestamp.from(Instant.now()));
        accountInfoEntity.setBalance(BigDecimal.valueOf(999L));
        AccountInfoEntity merge = entityManager.merge(accountInfoEntity);

        System.out.println("accountInfoEntity.getId: " + accountInfoEntity.getId());
        System.out.println("merge.getId: " + merge.getId());
        System.out.println("accountInfoEntity == merge: " + (accountInfoEntity == merge));
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
        accountInfoEntity.getId: 3
        merge.getId: 3
        accountInfoEntity == merge: true
        Hibernate:
            update
                account_info
            set
                balance=?,
                gmt_create=?,
                gmt_modified=?,
                name=?
            where
                id=?
         */
    }

    /**
     * 数据在缓存中没有，数据库中有
     *
     * @param entityManager 实体类管理器
     */
    private static void fun2(EntityManager entityManager) {
        // 准备数据
        AccountInfoEntity accountInfoEntity = new AccountInfoEntity();
        accountInfoEntity.setName("AA");
        accountInfoEntity.setGmtCreate(Timestamp.from(Instant.now()));
        accountInfoEntity.setGmtModified(Timestamp.from(Instant.now()));
        accountInfoEntity.setBalance(BigDecimal.valueOf(111L));
        accountInfoEntity.setId(BigInteger.valueOf(3L));

        AccountInfoEntity merge = entityManager.merge(accountInfoEntity);

        System.out.println("accountInfoEntity.getId: " + accountInfoEntity.getId());
        System.out.println("merge.getId: " + merge.getId());
        System.out.println("accountInfoEntity == merge: " + (accountInfoEntity == merge));
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
        accountInfoEntity.getId: 3
        merge.getId: 3
        accountInfoEntity == merge: false
        Hibernate:
            update
                account_info
            set
                balance=?,
                gmt_create=?,
                gmt_modified=?,
                name=?
            where
                id=?
         */
    }

    /**
     * 数据在缓存中没有，数据库中也没有
     *
     * @param entityManager 实体类管理器
     */
    private static void fun1(EntityManager entityManager) {
        // 准备数据
        AccountInfoEntity accountInfoEntity = new AccountInfoEntity();
        accountInfoEntity.setName("TL");
        accountInfoEntity.setGmtCreate(Timestamp.from(Instant.now()));
        accountInfoEntity.setGmtModified(Timestamp.from(Instant.now()));
        accountInfoEntity.setBalance(BigDecimal.valueOf(10000L));
        AccountInfoEntity merge = entityManager.merge(accountInfoEntity);

        System.out.println("accountInfoEntity.getId: " + accountInfoEntity.getId());
        System.out.println("merge.getId: " + merge.getId());
        System.out.println("accountInfoEntity == merge: " + (accountInfoEntity == merge));
        /*
        Hibernate:
            select
                max(id)
            from
                account_info
        accountInfoEntity.getId: null
        merge.getId: 5
        accountInfoEntity == merge: false
        Hibernate:
            insert
            into
                account_info
                (balance, gmt_create, gmt_modified, name, id)
            values
                (?, ?, ?, ?, ?)
         */
    }

}
