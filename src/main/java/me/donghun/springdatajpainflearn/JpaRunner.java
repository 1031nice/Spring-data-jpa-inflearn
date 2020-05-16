package me.donghun.springdatajpainflearn;

import org.hibernate.Session;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Component
@Transactional
public class JpaRunner implements ApplicationRunner {

    @PersistenceContext
    EntityManager entityManager; // jpa의 핵심

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account account = new Account();
        account.setUsername("donghun");
        account.setPassword("1234");

        Study study = new Study();
        study.setName("Spring data JPA");

//        // 이런 코드는 한 묶음으로 만들어서 한 쪽에 두고 쓴다고한다
//        account.getStudies().add(study); // 얘는 optional but 객체지향적으로 보면 해주는게 맞고
//        study.setOwner(account); // 이게 데이터 정보를 저장하기 위해 반드시 해주어야 되는 일
        account.addStudy(study);

        Session session = entityManager.unwrap(Session.class);
        session.save(account); // hibernate 이용하기
        session.save(study);

//        entityManager.persist(account);
    }
}
