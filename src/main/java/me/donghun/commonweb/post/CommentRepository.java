package me.donghun.commonweb.post;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long>, JpaSpecificationExecutor<Comment>, QueryByExampleExecutor<Comment> {

//    @EntityGraph(attributePaths = "post") // Comment 클래스 위에 별도의 설정 지우고 그냥 이렇게도 가능
    @EntityGraph(value = "Comment.post")
    Optional<Comment> getById(Long id);

//    List<CommentSummary> findByPost_Id(Long id); // 이렇게하면 comment, up, down만 가져 오고
//    List<Comment> findByPost_id(Long id); // 이렇게하면 Comment의 모든 필드 다가져온다

    // 위와 같은 경우는 overload가 안된다. 제네릭을 이용해서 이렇게 해보자.
    <T> List<T> findByPost_Id(Long id, Class<T> type);
}
