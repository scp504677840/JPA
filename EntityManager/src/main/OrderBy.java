package main;

import main.entities.AccountInfoEntity;
import org.hibernate.jpa.QueryHints;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;
import java.util.function.Consumer;

public class OrderBy {
    public static void main(String[] args) {
        // 获取EntityManagerFactory
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("lab");
        // 获取EntityManager
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        // 获取事务
        EntityTransaction transaction = entityManager.getTransaction();
        // 开启事务
        transaction.begin();

        String sql = "select c from AccountInfoEntity c order by c.gmtCreate asc ";
        TypedQuery<AccountInfoEntity> query = entityManager.createQuery(sql, AccountInfoEntity.class);
        List<AccountInfoEntity> resultList = query.getResultList();
        resultList.forEach(System.out::println);

        // 提交事务
        transaction.commit();

        // 关闭资源
        entityManager.close();
        entityManagerFactory.close();
    }
    //desc：
    //AccountInfoEntity{id=3, gmtCreate=2018-07-02 04:08:58.0, gmtModified=2018-07-02 04:08:58.0, name='LLKK', balance=666.00}
    //AccountInfoEntity{id=5, gmtCreate=2018-07-02 03:37:10.0, gmtModified=2018-07-02 03:37:10.0, name='TL', balance=10000.00}
    //AccountInfoEntity{id=4, gmtCreate=2018-07-02 03:34:31.0, gmtModified=2018-07-02 03:34:31.0, name='TL', balance=10000.00}
    //AccountInfoEntity{id=1, gmtCreate=2018-06-27 22:48:28.0, gmtModified=2018-06-27 23:48:32.0, name='Tom', balance=17.72}

    //asc：
    //AccountInfoEntity{id=1, gmtCreate=2018-06-27 22:48:28.0, gmtModified=2018-06-27 23:48:32.0, name='Tom', balance=17.72}
    //AccountInfoEntity{id=4, gmtCreate=2018-07-02 03:34:31.0, gmtModified=2018-07-02 03:34:31.0, name='TL', balance=10000.00}
    //AccountInfoEntity{id=5, gmtCreate=2018-07-02 03:37:10.0, gmtModified=2018-07-02 03:37:10.0, name='TL', balance=10000.00}
    //AccountInfoEntity{id=3, gmtCreate=2018-07-02 04:08:58.0, gmtModified=2018-07-02 04:08:58.0, name='LLKK', balance=666.00}
}
