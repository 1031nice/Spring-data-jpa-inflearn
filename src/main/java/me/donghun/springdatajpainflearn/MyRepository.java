package me.donghun.springdatajpainflearn;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

// 내가 만든 Repository에서 공통으로 제공할 기능들
// ex CommentRepository에서는 이 인터페이스만 상속받으면 된다
@NoRepositoryBean
public interface MyRepository<T, Id extends Serializable> extends Repository<T, Id> {

    // spring 5.0부터 지원, 런타임 널체크
    <E extends T> E save(@NonNull E comment);

    // 리스트는 Null이 안나옴(spring data jpa의 특성) 비어 있는 컬렉션이 나올 수는 있음
    List<T> findAll();

    long count();

    // 단일 값을 받아오는 경우 Optional을 쓰는 게 좋다. Null인 경우 더 잘 처리 가능
    <E extends T> Optional<E> findById(Id id);

//    @Nullable // null일 수도 있다(리턴 타입에 붙이는 것)
//    <E extends T> Optional<E> findById(Id id);

}
