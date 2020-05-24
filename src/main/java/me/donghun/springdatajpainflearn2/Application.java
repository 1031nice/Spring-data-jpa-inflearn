package me.donghun.springdatajpainflearn2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
// 이걸 통해서 접미어 바꿀 수 있음
@EnableJpaRepositories(repositoryImplementationPostfix = "Impl", repositoryBaseClass = SimpleMyRepository.class)
public class Application {

    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(Application.class);
        app.run();

    }

}
