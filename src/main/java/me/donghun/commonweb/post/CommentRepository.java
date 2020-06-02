package me.donghun.commonweb.post;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

//    @EntityGraph(attributePaths = "post") // Comment 클래스 위에 별도의 설정 지우고 그냥 이렇게도 가능
    @EntityGraph(value = "Comment.post")
    Optional<Comment> getById(Long id);
}
