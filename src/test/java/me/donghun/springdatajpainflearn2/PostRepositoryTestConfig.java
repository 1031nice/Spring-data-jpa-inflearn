package me.donghun.springdatajpainflearn2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostRepositoryTestConfig {

    // listener 등록
    @Bean
    public PostListener postListener(){
        return new PostListener();
    }

}
