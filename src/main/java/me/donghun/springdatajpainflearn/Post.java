package me.donghun.springdatajpainflearn;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Post {

    @Id @GeneratedValue
    private Long id;

    private String title;

    // one to many의 fetch 기본값은 lazy: 나중에 가져온다
    @OneToMany(mappedBy = "post", cascade = {CascadeType.PERSIST, CascadeType.ALL}, fetch = FetchType.LAZY)
    private Set<Comment> comments = new HashSet<>();

    public void addComment(Comment comment){
        this.getComments().add(comment);
        comment.setPost(this);
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Set<Comment> getComments() {
        return comments;
    }
    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    // toString 주의할 것. 여기서 comments까지 찍으면 쿼리날라가서 comment까지 받아온다
    @Override
    public String toString() {
        return "Post{" +
                "title='" + title + '\'' +
                '}';
    }
}
