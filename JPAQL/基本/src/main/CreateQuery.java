package main;

import main.entities.AccountInfoEntity;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;
import java.util.function.Consumer;

public class CreateQuery {

    public static void main(String[] args) {
        // 获取EntityManagerFactory
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("lab");
        // 获取EntityManager
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        // 获取事务
        EntityTransaction transaction = entityManager.getTransaction();
        // 开启事务
        transaction.begin();

        // 查询所有结果集
        //String sql = "from AccountInfoEntity";
        //TypedQuery<AccountInfoEntity> query = entityManager.createQuery(sql, AccountInfoEntity.class);
        //List<AccountInfoEntity> resultList = query.getResultList();
        //resultList.forEach(System.out::println);

        //getSingleResult：查询单个结果集
        /*String sql = "select c from AccountInfoEntity c where c.id = ?1";
        TypedQuery<AccountInfoEntity> query = entityManager.createQuery(sql, AccountInfoEntity.class);
        query.setParameter(1, BigInteger.valueOf(3L));
        AccountInfoEntity accountInfoEntity = query.getSingleResult();
        System.out.println(accountInfoEntity);*/

        //获取部分属性
        //String sql = "select c.name,c.balance from AccountInfoEntity c where c.id = ?1";
        //Query query = entityManager.createQuery(sql);
        //query.setParameter(1, BigInteger.valueOf(3L));
        //Object result = query.getSingleResult();
        //Object里面实际上是一个Object[]数组，存放的是String类型的name和BigInteger类型的balance。
        //System.out.println(result instanceof Object[]);
        //Object[] r = (Object[]) result;
        //System.out.println(r[0] + ":" + r[1]);
        //true
        //LLKK:666.00

        //获取部分属性的另外一个写法【推荐】
        //String sql = "select new AccountInfoEntity (c.name,c.balance) from AccountInfoEntity c where c.id = ?1";
        //TypedQuery<AccountInfoEntity> query = entityManager.createQuery(sql, AccountInfoEntity.class);
        //query.setParameter(1, BigInteger.valueOf(3L));
        //AccountInfoEntity accountInfoEntity = query.getSingleResult();
        //System.out.println(accountInfoEntity);
        //AccountInfoEntity{id=null, gmtCreate=null, gmtModified=null, name='LLKK', balance=666.00}

        // 使用原始SQL
        String sql="select name from account_info where id = ?";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1,BigInteger.valueOf(3L));
        Object result = query.getSingleResult();
        System.out.println(result);
        //LLKK

        // 提交事务
        transaction.commit();

        // 关闭资源
        entityManager.close();
        entityManagerFactory.close();
    }

}
