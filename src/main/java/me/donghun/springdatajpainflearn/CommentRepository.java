package me.donghun.springdatajpainflearn;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.List;
import java.util.concurrent.Future;

// 이렇게 하면 아무기능도 없다
// 기존의 것은 빼고 제공하고 싶은 기능이 있을 때 쓸 수 있을 듯
// 물론 bean으로 등록도 해준다
// 우리가 만든 코드이므로 테스트 코드를 작성해야한다
//@RepositoryDefinition(domainClass = Comment.class, idClass = Long.class)
//public interface CommentRepository {


@RepositoryDefinition(domainClass = Comment.class, idClass = Long.class)
//@EnableAsync // 이 애노테이션을 붙여야 async하게 동작
// 얘를 추가해야 @Async라고 붙어있는 public 메소드들을 백그라운드 스레드풀에 넣어서 실행해준다
// 근데 비추.
public interface CommentRepository extends MyRepository<Comment, Long>{

    /*잘 만들어졌는지 확인하려면 테스트 코드를 작성해야 한다.
    * 만약 문제가 있으면 빈 생성할 때 문제가 생기므로
    * 프로그램을 실행하는 것만으로 제대로 만들어졌는지 테스트 가능*/

        // 메소드를 정의하면, spring data jpa가 구현할 수 있는거라면 구현해준다
    Comment save(Comment comment);

    List<Comment> findAll();

    // 비동기 쿼리 메소드
    @Async
//    Future<List<Comment>> findByCommentContainsIgnoreCase(String keyword, Pageable pagable);
    ListenableFuture<List<Comment>> findByCommentContainsIgnoreCase(String keyword, Pageable pagable);

    // 정적
//    List<Comment> findByCommentContainsIgnoreCaseOrderByLikeCountAsc(String title);
    // 동적
//    Page<Comment> findByCommentContainsIgnoreCase(String title, Pageable pageable);

    //아래 같은 것도 가능
//    List<Comment> findByTitleContains(String keyword);
    // Page로 받으려면 Pagable을 인자로 전달해야 한다
//    Page<Comment> findByLikeGreaterThanAndPostOrderByCreatedDesc(int likeCount, Post post, Pageable pageable);
    // 이렇게 하면 정렬도 된다리
//    List<Comment> findByLikeGreaterThanAndPost(int likeCount, Post post, Sort sort);

}
