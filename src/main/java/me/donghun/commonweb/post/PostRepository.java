package me.donghun.commonweb.post;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByTitleStartsWith(String title);

    @Query(value = "SELECT p FROM Post AS p WHERE p.title = :title") // 아래는 ?1, ?2 이렇게 채번으로 참조했는데
    List<Post> findByTitle(@Param("title") String keyword, Sort sort); // named parameter를 사용하면 이렇게 쓸 수 있음

    @Modifying(clearAutomatically = true) //캐시를 비워줌(그래야 test에서 발생한 문제 해결 가능)
//    @Modifying
    @Query("UPDATE Post p Set p.title = ?1 WHERE p.id = ?2")
    int updateTitle(String title, Long id);

//    @Query(value = "SELECT p FROM Post AS p WHERE p.title = ?1", nativeQuery = false) // named query보다 이게 더 깔끔하다 nativequery 쓴 경우 true로 주면 되고 default는 true
//    List<Post> findByTitle(String title, Sort sort);


}
