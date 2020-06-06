package me.donghun.commonweb.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static me.donghun.commonweb.post.CommentSpecs.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
//@DataJpaTest slicing test이므로 accountAuditAware 빈을 못찾음
@SpringBootTest
public class CommentRepositoryTest2 {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    @Test
    public void qbe(){
        Comment prove = new Comment();
        prove.setBest(true);

        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny()
                .withIgnorePaths("up", "down");

        Example<Comment> example = Example.of(prove, exampleMatcher);

        commentRepository.findAll(example);
    }

    @Test
    public void specs(){
        Page<Comment> page = commentRepository.findAll(isBest().or(isGood()), PageRequest.of(0, 10));
    }

    @Test
    public void getComment(){
        Post post = new Post();
        post.setTitle("jpa");
        Post savedPost = postRepository.save(post);

        Comment comment = new Comment();
        comment.setPost(savedPost);
        comment.setUp(10);
        comment.setDown(1);
        comment.setComment("spring data jpa projection");
        commentRepository.save(comment);

        commentRepository.findByPost_Id(savedPost.getId(), CommentOnly.class).forEach(c -> {
            System.out.println("===========");
            System.out.println(c.getComment());
        });
    }

/*    @Test
    public void getComment(){
        commentRepository.getById(1l); // EAGER, EntityGraph 이용

        System.out.println("===========================");

        commentRepository.findById(1l); // LAZY, 기본 Repository method에 fetch를 LAZY로 설정

        // 이런식으로 경우에 따라 원하는 전략을 쓸 수 있다
        // 각각의 메소드마다 다른 패칭 전략으로 데이터를 읽어올 수 있도록 여러 메소드를 만들 수 있다
    }*/
}