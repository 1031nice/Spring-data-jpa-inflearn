package me.donghun.springdatajpainflearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws SQLException {

        SpringApplication application = new SpringApplication(Application.class);
        application.run();

//        String url = "jdbc:postgresql://192.168.99.100:5432/springdata";
//        String username = "donghun";
//        String password = "pass";

//        try(Connection connection = DriverManager.getConnection(url, username, password)){
//            String sql = "INSERT INTO ACCOUNT VALUES (1, 'donghun', 'pass');";
//        try(PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.execute();
//        }
//        }
    }

}
