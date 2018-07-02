package main;

import main.entities.AccountInfoEntity;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * flush
 * 使实体对象更新数据库
 */
public class Flush {

    public static void main(String[] args) {
        // 获取EntityManagerFactory
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("lab");
        // 获取EntityManager
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        // 获取事务
        EntityTransaction transaction = entityManager.getTransaction();
        // 开启事务
        transaction.begin();

        AccountInfoEntity accountInfoEntity = entityManager.find(AccountInfoEntity.class, BigInteger.valueOf(3L));
        System.out.println(accountInfoEntity);

        accountInfoEntity.setName("LLKK");

        //flush
        //可以在此处打一个断点，然后查看不同的执行结果
        entityManager.flush();

        //setFlushMode
        //entityManager.setFlushMode(FlushModeType.AUTO);//自动发送SQL语句，不必等到提交事务
        //entityManager.setFlushMode(FlushModeType.COMMIT);//提交事务时发生SQL语句

        //getFlushMode
        FlushModeType flushMode = entityManager.getFlushMode();
        System.out.println(flushMode.name());

        // 提交事务
        transaction.commit();

        // 关闭资源
        entityManager.close();
        entityManagerFactory.close();
    }

}
