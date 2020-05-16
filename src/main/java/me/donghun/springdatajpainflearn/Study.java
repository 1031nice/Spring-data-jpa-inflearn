package me.donghun.springdatajpainflearn;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Study {

    @Id @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne // 양방향 관계에서는 foreign키를 가진쪽이 주인
    // 관계의 매핑을 주인에다 해주어야 관계의 정보가 저장된다
    private Account owner;


    public Account getOwner() {
        return owner;
    }

    public void setOwner(Account owner) {
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
