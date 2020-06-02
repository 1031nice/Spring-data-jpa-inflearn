package me.donghun.commonweb.post;

import javax.persistence.*;

@NamedEntityGraph(name = "Comment.post",
        attributeNodes = @NamedAttributeNode("post")) // 연관관계 정의
@Entity
public class Comment {

    @Id @GeneratedValue
    private Long id;

    private String comment;

    // ~ToOne인 경우 fetch의 기본 값은 EAGER
    // ~ToMany인 경우 fetch의 기본 값은 LAZY -> 필요할 때 가져오기 -> Persistent 상태일 때만 가능
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
