package me.donghun.springdatajpainflearn;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Account {

    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    // @Column이 생략되어 있다
    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created = new Date();

    @Transient // Column으로 치지 않겠다
    private String no;

    @Embedded // value 타입 매핑
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "home_address"))
    })
    private Address homeAddress;

    @Embedded
    private Address officeAddress;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String name) {
        this.username = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
