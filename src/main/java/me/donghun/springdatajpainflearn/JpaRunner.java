package me.donghun.springdatajpainflearn;

import org.hibernate.Session;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class JpaRunner implements ApplicationRunner {

    @PersistenceContext
    EntityManager entityManager; // jpa의 핵심

    @Override
    public void run(ApplicationArguments args) throws Exception {

        // 아래의 쿼리는 JPQL로 DB에 독립적인 쿼리, 데이터 테이블이 아닌 엔티티 객체 모델을 기준으로 작성
        TypedQuery<Post> query = entityManager.createQuery("SELECT p FROM Post AS p", Post.class);
        List<Post> posts = query.getResultList();
        posts.forEach(System.out::println);

/*
        Fetch
        Session session = entityManager.unwrap(Session.class);
        Post post = session.get(Post.class, 1l);
        System.out.println("=============");
        System.out.println(post.getTitle());

        post.getComments().forEach(c -> {
            System.out.println("---------------");
            System.out.println(c.getComment());
        });*/

        /*Comment comment = session.get(Comment.class, 2l); // 여기서 Post가지 가져옴
        System.out.println("=============");
        System.out.println(comment.getComment());
        System.out.println(comment.getPost().getTitle()); // Many to one은 위에서 바로 가져오므로 쿼리 발생 X
*/

        /*        Post post = new Post();
        post.setTitle("Spring data jpa");

        Comment comment = new Comment();
        comment.setComment("Hello world");
        post.addComment(comment);

        Comment comment1 = new Comment();
        comment1.setComment("World Hello");
        post.addComment(comment1);

        Session session = entityManager.unwrap(Session.class);
        session.save(post); */// cascade type으로 persist를 주었기 때문에 post의 변경사항이 comment에도 전파


        // persistent에서 removed가 되고 cascade type으로 removed를 주었기 때문에
        // transaction이 끝나고 이게 commit이 될 때 cascade가 일어나서 comment까지 삭제
/*        Post post2 = session.get(Post.class, 1l);
        session.delete(post2); */

        /*        Account account = new Account();
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

        // save 했다고 바로 DB에 올라가진 않는다
        // 일단 1차 캐시에 저장됨. 따라서 아래의 요청은 쿼리 발생하지 않음(DB에 가지 않음)
        Account donghun = session.load(Account.class, account.getId());
        System.out.println(donghun.getUsername());
        donghun.setUsername("Apple"); // 쿼리 발생 X
        donghun.setUsername("Banana"); // 쿼리 발생 X
//        donghun.setUsername("Chair"); // update 하라고 한 적 없는데 update 쿼리 발생
        donghun.setUsername("donghun"); // 쿼리 발생 X dirty checking으로 변경사항 추적하고 있는데
        // 처음 이름과 똑같으므로 update 안함
        System.out.println(donghun.getUsername());

        // save는 transaction이 끝나서 commit을 해야할 때 이루어짐
//        entityManager.persist(account);*/
    }
}
