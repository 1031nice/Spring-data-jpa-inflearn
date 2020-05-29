package me.donghun.commonweb.post;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest // 단위 테스트가 아니라 integrated 테스트(application.properties에 있는 모든 속성 적용됨)
@AutoConfigureMockMvc
//@ActiveProfiles("test") // application-test.properties에 override할 것만 override해서 사용가능
public class PostControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    PostRepository postRepository;

    @Test // junit의 기본: 모든 test는 public void
    public void getPosts() throws Exception {
        createPosts();

        mockMvc.perform(MockMvcRequestBuilders.get("/posts/")
                    .param("page", "3") // page는 0부터 시작
                    .param("size", "10")
                    .param("sort", "created,desc")
                    .param("sort", "title"))
                .andDo(print()) // 성공했을 때도 정보를 찍어준다
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].title", Matchers.is("jpa")));
    }

    private void createPosts() {
        int postsCount = 100;
        while(postsCount > 0) {
            Post post = new Post();
            post.setTitle("jpa");
            postRepository.save(post);
            postsCount--;
        }
    }

    @Test
    public void getPost() throws Exception{
        Post post = new Post();
        post.setTitle("jpa");
        postRepository.save(post);

        mockMvc.perform(MockMvcRequestBuilders.get("/posts/" + post.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("jpa"));
    }

}