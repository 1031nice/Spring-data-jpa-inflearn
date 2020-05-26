package me.donghun.springdatajpainflearn2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

// 신기하네 PostCustomRepository는 PostRepository가 아니라 PostCustomRepositoryImpl에서 구현했는데 그게 여기 적용이되는거잖아
//public interface PostRepository extends JpaRepository<Post, Long>, PostCustomRepository<Post> {
public interface PostRepository extends MyRepository<Post, Long>, QuerydslPredicateExecutor<Post> {
    //QueryDSL: type safe한 query를 만들 수 있게 도와주는 라이브러리
}
