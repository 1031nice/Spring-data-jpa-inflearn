package me.donghun.commonweb.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PostRepositoryTest3 {

    @Autowired
    private PostRepository postRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void updateTitle(){
        Post spring = savePost();

        String hibernate = "hibernate";
        int update = postRepository.updateTitle("hibernate", spring.getId());
        assertThat(update).isEqualTo(1);

        Optional<Post> byId = postRepository.findById(spring.getId()); // update query ok
        // 트랜잭션이 끝나지 않아서 캐시 유지됨 따라서 persistent 컨텍스트가 비워지지 않았기 때문에
        // spring 객체는 계쏙 "spring"
        // 이때 findById하면 DB안가고 자기가 캐싱하던거 그대로 준다 그래서 hibernate가 아니라 title이 spring인거다
        // update 쿼리가 발생했음에도.
        assertThat(byId.get().getTitle()).isEqualTo(hibernate);

        /*
        그래서 이 방법이 더 낫다
        spring.setTitle("hibernate");
        List<Post> all = postRepository.findAll(); // 여기서 이제 위의 변경사항을 저장해야된다고 판단하는거지
        // 그러니까 여기서 update 쿼리가 날라간다
        assertThat(all.get(0).getTitle()).isEqualTo("hibernate");
         */
    }

    @Test
    public void findByTitle(){
        savePost();
        List<Post> all = postRepository.findByTitle("Spring", Sort.by("title")); // property나 alias만 가능
        assertThat(all.size()).isEqualTo(1);
    }

    @Test
    public void findByTitleStartsWith(){
        savePost();

        List<Post> all = postRepository.findByTitleStartsWith("Spring");
        assertThat(all.size()).isEqualTo(1);

    }

    private Post savePost() {
        Post post = new Post();
        post.setTitle("Spring");
        return postRepository.save(post); // persist
    }

    @Test
    public void lazyTest(){
        savePost();
        postRepository.findByTitleStartsWith("Spring");
    }


    /*
    save는 새로운 객체라면 EntityManager의 persist(Transient -> Persistent)를,
    새로운 객체가 아니라면 merge(Detached -> Persistent)를 호출한다
    Transient: 새로 만들어진 객체
    hibernate와 db 둘 다 모른다(id가 없기 때문)
    Detached: 한 번이라도 db에 들어간 객체
     */

    @Test
    public void save(){
        Post post = new Post();
        post.setTitle("jpa");
        // persist(id없다 -> transient상태이다 -> save)
        Post savedPost = postRepository.save(post);

        // persist로 넘겨준 객체 자체도 영속화된다
        assertThat(entityManager.contains(post)).isTrue();
        assertThat(entityManager.contains(savedPost)).isTrue();
        // 둘이 다를 수도 있다
        assertThat(savedPost == post);

        Post postUpdate = new Post();
        postUpdate.setId(post.getId());
        postUpdate.setTitle("hibernate");
        // merge(id있다 -> detached상태이다 -> merge -> update query(insert 할 때도 있음) -> jpa 대신 hibernate)
        // merge에 넘긴 엔티티의 복사본을 만들고 그 복사본을 persistent 상태로 변경하고 복사본을 반환
        Post updatedPost = postRepository.save(postUpdate);

        assertThat(entityManager.contains(postUpdate)).isFalse();
        assertThat(entityManager.contains(updatedPost)).isTrue();
        assertThat(updatedPost == postUpdate).isFalse();

        /*
        Best practice: 어떤 경우든 save의의 return을 받아서 사용할 것(persistent 상태의 객체 사용할 것)
        변화를 감지하기 때문
         */
//        postUpdate.setTitle("smile"); // 그래도 update가 hibernate로 날아감
        updatedPost.setTitle("smile"); // update가 smile로 날아감 ㄷㄷㄷ 이게 managed 객체의 장점
        // 내가 명시하지 않아도 객체 변화 추적하다가 필요하다고 느낀 시점에 싱크.

        List<Post> all = postRepository.findAll(); // 바로 이 시점이겠지. 싱크 반영하고 가져오겠지.
        assertThat(all.size()).isEqualTo(1);
    }
}