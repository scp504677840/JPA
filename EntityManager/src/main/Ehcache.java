package main;

import main.entities.AccountInfoEntity;
import org.hibernate.jpa.QueryHints;

import javax.persistence.*;
import java.math.BigInteger;

public class Ehcache {
    public static void main(String[] args) {
        // 获取EntityManagerFactory
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("lab");
        // 获取EntityManager
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        // 获取事务
        EntityTransaction transaction = entityManager.getTransaction();
        // 开启事务
        transaction.begin();

        String sql = "select c from AccountInfoEntity c where c.id = ?1";
        TypedQuery<AccountInfoEntity> query = entityManager.createQuery(sql, AccountInfoEntity.class);
        //启用二级缓存
        query.setHint(QueryHints.HINT_CACHEABLE, true);
        query.setParameter(1, BigInteger.valueOf(3L));
        AccountInfoEntity accountInfoEntity = query.getSingleResult();
        System.out.println(accountInfoEntity);

        query = entityManager.createQuery(sql, AccountInfoEntity.class);
        //启用二级缓存
        query.setHint(QueryHints.HINT_CACHEABLE, true);
        query.setParameter(1, BigInteger.valueOf(3L));
        accountInfoEntity = query.getSingleResult();
        System.out.println(accountInfoEntity);

        // 提交事务
        transaction.commit();

        // 关闭资源
        entityManager.close();
        entityManagerFactory.close();
    }
}
