package me.donghun.commonweb.post;

import javax.persistence.*;
import java.util.Date;

@Entity
//@NamedQuery(name = "Post.findByTitle", query = "SELECT p FROM Post AS p WHERE p.title = ?1")
// named query 단점, 도메인 엔티티 클래스가 지저분해진다 sql, jbql 코드가 들어오게 된다
public class Post {

    @Id @GeneratedValue
    private Long id;

    private String title;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
