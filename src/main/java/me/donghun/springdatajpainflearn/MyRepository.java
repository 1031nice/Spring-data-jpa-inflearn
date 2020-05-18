package me.donghun.springdatajpainflearn;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;
import java.util.List;

// 내가 만든 Repository에서 공통으로 제공할 기능들
// ex CommentRepository에서는 이 인터페이스만 상속받으면 된다
@NoRepositoryBean
public interface MyRepository<T, Id extends Serializable> extends Repository<T, Id> {

    <E extends T> E save(E comment);

    List<T> findAll();

    long count();
}
