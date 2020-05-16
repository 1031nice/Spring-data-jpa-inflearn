package me.donghun.springdatajpainflearn;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Account {

    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    // @Column이 생략되어 있다
    private String password;

    // 그냥 OntToMany ManyToOne으로 연결하면 양방향이 아니라 단방향이 두 개
    @OneToMany(mappedBy = "owner")
    private Set<Study> studies = new HashSet<>();

    @Temporal(TemporalType.TIMESTAMP)
    private Date created = new Date();

    @Transient // Column으로 치지 않겠다
    private String no;

    @Embedded // value 타입 매핑
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "home_street"))
    })
    private Address homeAddress;

//    @Embedded
//    private Address officeAddress;

    public Set<Study> getStudies() {
        return studies;
    }

    public void setStudies(Set<Study> studies) {
        this.studies = studies;
    }

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

    // 관계를 관리하는 메소드를 convenient 메소드라고 한다
    public void addStudy(Study study) {
        this.getStudies().add(study); // 얘는 optional but 객체지향적으로 보면 해주는게 맞고
        study.setOwner(this); // 이게 데이터 정보를 저장하기 위해 반드시 해주어야 되는 일
    }

    // remove도 마찬가지
    public void removeStudy(Study study) {
        this.getStudies().remove(study); // 얘는 optional but 객체지향적으로 보면 해주는게 맞고
        study.setOwner(null); // 이게 데이터 정보를 저장하기 위해 반드시 해주어야 되는 일
    }

}
