package me.donghun.springdatajpainflearn;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    // 이렇게만 해도 spring data jpa가 만들어준다
    Page<Post> findByTitleContains(String title, Pageable pageable);
    long countByTitleContains(String title);
}
