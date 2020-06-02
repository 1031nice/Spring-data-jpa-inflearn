package me.donghun.commonweb.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentRepositoryTest2 {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    @Test
    public void getComment(){
        commentRepository.getById(1l); // EAGER, EntityGraph 이용

        System.out.println("===========================");

        commentRepository.findById(1l); // LAZY, 기본 Repository method에 fetch를 LAZY로 설정

        // 이런식으로 경우에 따라 원하는 전략을 쓸 수 있다
        // 각각의 메소드마다 다른 패칭 전략으로 데이터를 읽어올 수 있도록 여러 메소드를 만들 수 있다
    }
}