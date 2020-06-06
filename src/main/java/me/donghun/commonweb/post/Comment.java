package me.donghun.commonweb.post;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@NamedEntityGraph(name = "Comment.post",
        attributeNodes = @NamedAttributeNode("post")) // 연관관계 정의
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Comment {

    @Id @GeneratedValue
    private Long id;

    private String comment;

    // ~ToOne인 경우 fetch의 기본 값은 EAGER
    // ~ToMany인 경우 fetch의 기본 값은 LAZY -> 필요할 때 가져오기 -> Persistent 상태일 때만 가능
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    private int up;

    private int down;

    private boolean best;

    @CreatedDate
    private Date created;
    @CreatedBy
    @ManyToOne
    private Account createdBy;
    @LastModifiedDate
    private Date updated;
    @ManyToOne
    @LastModifiedBy
    private Account updatedBy;

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


    public int getUp() {
        return up;
    }

    public void setUp(int up) {
        this.up = up;
    }

    public int getDown() {
        return down;
    }

    public void setDown(int down) {
        this.down = down;
    }

    public boolean isBest() {
        return best;
    }

    public void setBest(boolean best) {
        this.best = best;
    }

    // audit 가능
    @PrePersist
    public void prePersist(){
        System.out.println("prepersist is called");
    }
}
