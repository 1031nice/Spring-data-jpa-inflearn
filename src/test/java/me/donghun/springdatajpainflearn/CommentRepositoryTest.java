package me.donghun.springdatajpainflearn;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentRepositoryTest {
    @Autowired
    CommentRepository commentRepository;

    @Test
    public void crud(){
        Comment comment = new Comment();
        comment.setComment("Hello Comment");
        commentRepository.save(comment);

        List<Comment> all = commentRepository.findAll();
        assertThat(all.size()).isEqualTo(1);

        Optional<Comment> byId = commentRepository.findById(100l);
        assertThat(byId).isEmpty();
//        Comment comment2 = byId.orElse(new Comment()); // 없으면 내가 만든 Comment 객체가 들어간다
        Comment comment1 = byId.orElseThrow(IllegalArgumentException::new); // 없으면 예외 던지기

    }
}