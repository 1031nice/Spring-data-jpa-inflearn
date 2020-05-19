package me.donghun.springdatajpainflearn;

import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

// 이렇게 하면 아무기능도 없다
// 기존의 것은 빼고 제공하고 싶은 기능이 있을 때 쓸 수 있을 듯
// 물론 bean으로 등록도 해준다
// 우리가 만든 코드이므로 테스트 코드를 작성해야한다
//@RepositoryDefinition(domainClass = Comment.class, idClass = Long.class)
//public interface CommentRepository {


@RepositoryDefinition(domainClass = Comment.class, idClass = Long.class)
public interface CommentRepository extends MyRepository{

        // 메소드를 정의하면, spring data jpa가 구현할 수 있는거라면 구현해준다
    Comment save(Comment comment);

    List<Comment> findAll();

}
