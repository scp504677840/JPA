package main.repository;

import main.entities.AccountInfoEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;

@Repository
public class AccountInfoRepository {

    /**
     * 通过注解@PersistenceContext来获取EntityManager
     */
    @PersistenceContext
    private EntityManager entityManager;

    public void getAccountInfo(){
        AccountInfoEntity accountInfoEntity = entityManager.find(AccountInfoEntity.class, BigInteger.valueOf(3L));
        System.out.println(accountInfoEntity);
    }

}
