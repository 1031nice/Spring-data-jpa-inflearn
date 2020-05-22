package me.donghun.springdatajpainflearn2;

import java.util.List;

public interface PostCustomRepository<T> {

    List<Post> findMyPost();

    // JpaRepository의 메소드와 중복, 이 경우 내가 커스텀하게 구현한 구현체의 메소드를 더 우선함
    void delete(T entity);

}
