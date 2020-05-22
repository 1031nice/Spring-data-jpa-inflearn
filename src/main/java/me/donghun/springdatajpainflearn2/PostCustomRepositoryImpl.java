package me.donghun.springdatajpainflearn2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
// naming convention "구현대상이름Impl" (default가 그렇다는 소리고 수정가능)
public class PostCustomRepositoryImpl implements PostCustomRepository {

    @Autowired
    EntityManager entityManager;

    @Override
    public List<Post> findMyPost() {
        System.out.println("custom findMyPost");
        return entityManager.createQuery("SELECT p FROM Post AS p", Post.class)
                .getResultList();
    }

    @Override
    public void delete(Object entity) {
        System.out.println("custom delete");
        entityManager.remove(entity);
    }
}
