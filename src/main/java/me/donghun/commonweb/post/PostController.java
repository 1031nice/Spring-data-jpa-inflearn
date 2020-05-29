package me.donghun.commonweb.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

//    @Autowired
    private final PostRepository posts;

    // 생성자가 하나만 있고 인자가 Bean인 경우 알아서 주입해준다
    public PostController(PostRepository posts) {
        this.posts = posts;
    }

    @GetMapping("/posts")
    public PagedModel<EntityModel<Post>> getPosts(Pageable pageable, PagedResourcesAssembler<Post> assembler){ // Pagable을 쓰는 게 좋다 sorting도 쓸 수 있으니까
        return assembler.toModel(posts.findAll(pageable));
    }

    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable("id") Post post){ // 이 경우 pathvariable 이름과 매개변수이름이 다르므로 어떤거에 매핑되는건지 명시해주어야 한다
        // DomainClassConverter에 ToIdConverter와 ToEntityConverter가 있고
        // 그래서 id가 전달되어도 converter 덕에 entity 객체로 받을 수 있다
        // ToEntityConverter같은 경우 내부적으로 repository를 사용하여
        // id를 가지고 findById를 한다. 따라서 아래 두줄 필요 없다
//        Optional<Post> byId = postRepository.findById(id);
//        Post post = byId.get();
        return post.getTitle();
    }

/*    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable("id") Long id){
        // 원래 다 문자열로 들어오는데 spring mvc에 있는 web data binder가 Long으로 변환해서 받아준다
        Optional<Post> byId = postRepository.findById(id);
        Post post = byId.get(); // get하면 null도 가능
        return post.getTitle();
    }*/

}
