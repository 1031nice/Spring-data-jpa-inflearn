package me.donghun.springdatajpainflearn2;

import com.querydsl.core.types.Predicate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest // 얘 속에 roll back이 있으므로
@Import(PostRepositoryTestConfig.class) // slicing test이기 때문에 import 해주어야 한다
public class PostRepositoryTest2 {

    @Autowired
    PostRepository postRepository;

//    @Autowired
//    ApplicationContext applicationContext;

//    @Test
//    public void event(){
//        Post post = new Post();
//        post.setTitle("event");
//        PostPublishedEvent event = new PostPublishedEvent(post);
//        applicationContext.publishEvent(event);
//    }

    @Test
    public void crud(){
        Post post = new Post();
        post.setTitle("hibernate");
        postRepository.save(post.publish());

        Predicate predicate = QPost.post.title.containsIgnoreCase("Hi");
        Optional<Post> one = postRepository.findOne(predicate);
        assertThat(one).isNotEmpty();

    }

/*    @Test
    public void crud(){
        Post post = new Post();
        post.setTitle("hibernate");

        assertThat(postRepository.contains(post)).isFalse();

        postRepository.save(post.publish());

        assertThat(postRepository.contains(post)).isTrue();

        postRepository.delete(post);
        postRepository.flush(); // 얘(싱크)를 해줘야 delete까지 날아감
    }*/

//    @Test
//    public void crud(){
//        postRepository.findMyPost();
//        Post post = new Post();
//        post.setTitle("hibernate");
//        postRepository.save(post);
//        postRepository.findMyPost(); // 얘 추가하면 insert 날아감 그래도 delete은 안날아감
//        postRepository.delete(post);
//        // roll back이기 때문에 insert 날아가지도 않는다
//        // 삭제할 데이터이므로 save 명령을 주어도 insert를 수행하지 않음
//        postRepository.flush(); // 얘(싱크)를 해줘야 delete까지 날아감
//    }

}